### Story 1
1. Given: A ParkingBoy
    ```JSON
    {"employeeID": "string"}
    ```
    When: POST to /parkingboys
    
    Then: It should return status code 201 (Created)
    
2.  When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys
    ```JSON
    [{"employeeID": "string"}]
    ```

----
### Story 2
1. Given: A ParkingLot
	```JSON
	{"parkingLotID": "string", "capacity": "integer(1-100)"}
	```
    When: POST to /parkinglots
    
    Then: It should return status code 201 (Created)
    
2.  When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing all parking lots
    ```JSON
    [{"parkingLotID": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}]
    ```
