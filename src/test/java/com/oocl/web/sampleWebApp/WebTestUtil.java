package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

public class WebTestUtil {
    public static <T> T toObject(String jsonContent, Class<T> clazz) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, clazz);
    }

    public static <T> T getContentAsObject(MvcResult result, Class<T> clazz) throws Exception {
        return toObject(result.getResponse().getContentAsString(), clazz);
    }

    public static Long parseIdFromLocation(String location, String prefix){
        return Long.valueOf(location.split(prefix)[1]);
    }

    public static String toJSON(ParkingBoy parkingBoy){
        return "{\"employeeId\": " + "\"" + parkingBoy.getEmployeeId() + "\"" + "}";
    }

    public static String toJSON(ParkingLot parkingLot){
        return "{" +
                "\"parkingLotId\": " + "\"" + parkingLot.getParkingLotId() + "\"," +
                "\"capacity\": " + parkingLot.getCapacity()+
                "}";
    }
}
