package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
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
public class ParkingLotResourceTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void clear_repository(){
        parkingLotRepository.deleteAll();
        parkingLotRepository.flush();
    }

    @Test
    public void should_get_parking_lot() throws Exception {
        // Given
        parkingLotRepository.save(new ParkingLot("TEST CASE 1", 100));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingLotResponse[] parkingLots = getContentAsObject(result, ParkingLotResponse[].class);

        assertEquals(1, parkingLots.length);
        assertEquals("TEST CASE 1", parkingLots[0].getParkingLotId());
        assertEquals(new Integer(100), parkingLots[0].getCapacity());
        assertEquals(new Integer(100), parkingLots[0].getAvailablePositionCount());
    }

    @Test
    public void should_get_parking_lots() throws Exception {
        // Given
        parkingLotRepository.save(new ParkingLot("TEST CASE 2 Parking Lot 1", 100));
        parkingLotRepository.save(new ParkingLot("TEST CASE 2 Parking Lot 2", 100));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingLotResponse[] parkingLots = getContentAsObject(result, ParkingLotResponse[].class);

        assertEquals(2, parkingLots.length);
        assertEquals("TEST CASE 2 Parking Lot 1", parkingLots[0].getParkingLotId());
        assertEquals(new Integer(100), parkingLots[0].getCapacity());
        assertEquals(new Integer(100), parkingLots[0].getAvailablePositionCount());
        assertEquals("TEST CASE 2 Parking Lot 2", parkingLots[1].getParkingLotId());
        assertEquals(new Integer(100), parkingLots[1].getCapacity());
        assertEquals(new Integer(100), parkingLots[1].getAvailablePositionCount());
    }

    @Test
    public void should_get_no_parking_lots_if_database_is_empty() throws Exception {
        // Given

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingLotResponse[] parkingLots = getContentAsObject(result, ParkingLotResponse[].class);

        assertEquals(0, parkingLots.length);
    }

    @Test
    public void should_save_parking_lot_given_a_parking_lot_with_unqiue_parking_lot_ID() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot("TEST CASE 4", 100);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        final String location = result.getResponse().getHeader("location");
        final Long id = parseIdFromLocation(location, "/parkinglots/");

        assertEquals(201, result.getResponse().getStatus());
        assertTrue(location.contains("/parkinglots/"));

        final ParkingLot parkingLotRecord = parkingLotRepository.findById(id).get();

        assertEquals("TEST CASE 4", parkingLotRecord.getParkingLotId());
        assertEquals(new Integer(100), parkingLotRecord.getCapacity());
        assertEquals(new Integer(100), parkingLotRecord.getAvailablePositionCount());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_duplicated_employeeID_in_server() throws Exception {
        // Given
        parkingLotRepository.save(new ParkingLot("TEST CASE 5", 100));
        final ParkingLot parkingLot = new ParkingLot("TEST CASE 5", 100);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(1, parkingLots.size());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_capacity_smaller_than_1() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot("TEST CASE 6", 0);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(0, parkingLots.size());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_capacity_larger_than_100() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot("TEST CASE 7", 500);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(0, parkingLots.size());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_capacity_is_null() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot("TEST CASE 8", null);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(0, parkingLots.size());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_parking_lot_id_is_null() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot(null, 100);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(0, parkingLots.size());
    }

    @Test
    public void should_not_save_parking_lot_given_a_parking_lot_with_parking_lot_id_is_a_empty_string() throws Exception {
        // Given
        final ParkingLot parkingLot = new ParkingLot("", 100);
        final String parkingLotJSONString = toJSON(parkingLot);

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingLotJSONString))
                .andReturn();

        // Then
        assertEquals(400, result.getResponse().getStatus());

        final List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertEquals(0, parkingLots.size());
    }
}
