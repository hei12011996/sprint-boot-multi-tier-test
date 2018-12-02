package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyParkingLotsAssociationResponse;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.oocl.web.sampleWebApp.WebTestUtil.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingBoyResourceTest {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void clear_repository(){
        parkingBoyRepository.deleteAll();
        parkingBoyRepository.flush();
        parkingLotRepository.deleteAll();
        parkingLotRepository.flush();
    }

    @Test
    public void should_get_parking_boys() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("TEST CASE 1"));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingBoyResponse[] parkingBoys = getContentAsObject(result, ParkingBoyResponse[].class);

        assertEquals(1, parkingBoys.length);
        assertEquals("TEST CASE 1", parkingBoys[0].getEmployeeId());
    }

    @Test
    public void should_save_parking_boy_given_a_parking_boy_with_unqiue_employeeID() throws Exception {
        // Given
        final ParkingBoy parkingBoy = new ParkingBoy("TEST CASE 2");
        final String parkingBoyJSONString = toJSON(parkingBoy);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingBoyJSONString))
                .andReturn();

        // Then
        final String location = result.getResponse().getHeader("location");
        final Long id = parseIdFromLocation(location, "/parkingboys/");

        assertEquals(201, result.getResponse().getStatus());
        assertTrue(location.contains("/parkingboys/"));

        final ParkingBoy parkingBoyRecord = parkingBoyRepository.findById(id).get();

        assertEquals("TEST CASE 2", parkingBoyRecord.getEmployeeId());
    }

    @Test
    public void should_not_save_parking_boy_given_a_parking_boy_with_duplicated_employeeID_in_server() throws Exception {
        // Given
        parkingBoyRepository.save(new ParkingBoy("TEST CASE 3"));
        final ParkingBoy parkingBoy = new ParkingBoy("TEST CASE 3");
        final String parkingBoyJSONString = toJSON(parkingBoy);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingBoyJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();

        assertEquals(1, parkingBoys.size());
    }

    @Test
    public void should_not_save_parking_boy_given_a_parking_boy_with_null_employee_id() throws Exception {
        // Given
        final ParkingBoy parkingBoy = new ParkingBoy(null);
        final String parkingBoyJSONString = toJSON(parkingBoy);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingBoyJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();

        assertEquals(0, parkingBoys.size());
    }

    @Test
    public void should_not_save_parking_boy_given_a_parking_boy_with_empty_employee_id() throws Exception {
        // Given
        final ParkingBoy parkingBoy = new ParkingBoy("");
        final String parkingBoyJSONString = toJSON(parkingBoy);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingBoyJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();

        assertEquals(0, parkingBoys.size());
    }

    @Test
    public void should_get_parking_boy_parking_lots_association_with_its_managing_parking_lot_id() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("TEST CASE 6 Parking Boy"));
        final Long pbId = boy.getId();
        final ParkingLot lot = new ParkingLot("TEST CASE 6 Parking Lot", 100);
        lot.setParkingBoyId(pbId);
        parkingLotRepository.saveAndFlush(lot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys/" + pbId + "/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingBoyParkingLotsAssociationResponse association = getContentAsObject(result, ParkingBoyParkingLotsAssociationResponse.class);

        assertEquals("TEST CASE 6 Parking Boy", association.getEmployeeId());
        assertEquals("TEST CASE 6 Parking Lot", association.getAssociatedParkingLots().get(0));
    }

    @Test
    public void should_get_parking_boy_parking_lots_association_with_multiple_parking_lot_id() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("TEST CASE 7 Parking Boy"));
        final Long pbId = boy.getId();
        final ParkingLot lot_1 = new ParkingLot("TEST CASE 7 Parking Lot 1", 100);
        lot_1.setParkingBoyId(pbId);
        final ParkingLot lot_2 = new ParkingLot("TEST CASE 7 Parking Lot 2", 100);
        lot_2.setParkingBoyId(pbId);
        parkingLotRepository.save(lot_1);
        parkingLotRepository.saveAndFlush(lot_2);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys/" + pbId + "/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingBoyParkingLotsAssociationResponse association = getContentAsObject(result, ParkingBoyParkingLotsAssociationResponse.class);

        assertEquals("TEST CASE 7 Parking Boy", association.getEmployeeId());
        assertEquals("TEST CASE 7 Parking Lot 1", association.getAssociatedParkingLots().get(0));
        assertEquals("TEST CASE 7 Parking Lot 2", association.getAssociatedParkingLots().get(1));
    }

    @Test
    public void should_get_parking_boy_parking_lots_association_with_zero_parking_lot_id() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.saveAndFlush(new ParkingBoy("TEST CASE 8 Parking Boy"));
        final Long pbId = boy.getId();

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys/" + pbId + "/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingBoyParkingLotsAssociationResponse association = getContentAsObject(result, ParkingBoyParkingLotsAssociationResponse.class);

        assertEquals("TEST CASE 8 Parking Boy", association.getEmployeeId());
        assertTrue(association.getAssociatedParkingLots().isEmpty());
    }
    @Test
    public void should_not_get_parking_boy_parking_lots_association_when_parking_boy_not_exist() throws Exception {
        // Given
        final Long pbId = 0L;

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys/" + pbId + "/parkinglots"))
                .andReturn();

        // Then
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    public void should_save_parking_boy_parking_lots_association() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("TEST CASE 10 Parking Boy"));
        final Long pbId = boy.getId();
        final ParkingLot lot = parkingLotRepository.save(new ParkingLot("TEST CASE 10 Parking Lot", 100));
        final Long plId = lot.getId();

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys/" + pbId + "/parkinglots/" + plId))
                .andReturn();

        // Then
        final String location = result.getResponse().getHeader("location");

        assertEquals(201, result.getResponse().getStatus());
        assertTrue(location.contains("/parkingboys/"));

        final ParkingLot updatedParkingLot = parkingLotRepository.findByParkingLotId("TEST CASE 10 Parking Lot");

        assertEquals(pbId, updatedParkingLot.getParkingBoyId());
    }

    @Test
    public void should_not_save_parking_boy_parking_lots_association_given_none_existing_parking_boy_id() throws Exception {
        // Given
        final Long pbId = 0L;
        final ParkingLot lot = parkingLotRepository.save(new ParkingLot("TEST CASE 11 Parking Lot", 100));
        final Long plId = lot.getId();

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys/" + pbId + "/parkinglots/" + plId))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void should_not_save_parking_boy_parking_lots_association_given_none_existing_parking_lot_id() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("TEST CASE 12 Parking Boy"));
        final Long pbId = boy.getId();
        final Long plId = 0L;

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys/" + pbId + "/parkinglots/" + plId))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());
    }
}
