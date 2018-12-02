### Story 1
1. Given: A ParkingBoy
    ```JSON
    {"employeeId": "string"}
    ```
    When: POST to /parkingboys
    
    Then: It should return status code 201 (Created)
    
2.  When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys
    ```JSON
    [{"employeeId": "string"}]
    ```

----
### Story 2
1. Given: A ParkingLot
	```JSON
	{"parkingLotId": "string", "capacity": "integer(1-100)"}
	```
    When: POST to /parkinglots
    
    Then: It should return status code 201 (Created)
    
2.  When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing all parking lots
    ```JSON
    [{"parkingLotId": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}]
    ```

----
### Story 3
1. Given: A ParkingBoy id "pbId": "number" and a ParkingLot id "plId": "number"

    When: POST to /parkingboys/{pbId}/parkinglots/{plId}
    
    Then: It should return status code 201 (Created)
    
2. Given: A ParkingBoy id "pbId": "number"

    When: GET to /parkingboys/{pbId}/parkinglots
    
    Then: It should return status code 200 (OK) with the "employeeId" of that parking boy and a list containing the "parkingLotId"s of all parking lots under that parking boy management
    ```JSON
    {"employeeID": "string", "associatedParkingLots": [{"parkingLotId": "string"}]}
    ```
