package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.junit.After;
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
    private MockMvc mvc;

    @Before
    public void clear_repository(){
        parkingBoyRepository.deleteAll();
        parkingBoyRepository.flush();
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
}
