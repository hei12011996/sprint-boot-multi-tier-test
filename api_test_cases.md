### Test Case(s) for GET /parkingboys
1.  Given: Parking boys record stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys in data base
    ```JSON
    [{"employeeID": "string"}]
    ```

### Test Case(s) for POST /parkingboys
1. Given: a ParkingBoy: {"employeeId": "string"}

    When: POST to /parkingboys
    
    Then: it should 
    
    * save the given parking boy
    * return status code 201 (Created)

2. Given a ParkingBoy: {"employeeID": "string"} which "employeeID" is repeated with other record in server

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)

3. Given a ParkingBoy: {"employeeId": null} which employeeId is null

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)

4. Given a ParkingBoy: {"employeeId": ""} which employeeId is a empty string

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)
    
### Test Case(s) for GET /parkinglots
1.  Given: Parking lots record stored in database

    When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing all parking lots in database
    ```JSON
    {"parkingLotId": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}
    ```

### Test Case(s) for POST /parkinglots
1. Given: a ParkingLoy: {"parkingLotId": "string", "capacity": "integer(1-100)"}

    When: POST to /parkinglots
    
    Then: it should 
    
    * save the given parking lot
    * return status code 201 (Created)

2. Given a ParkingLot: {"parkingLotId": "string", "capacity": "integer(1-100)"} which "parkingLotId" is repeated with other record in server

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

3. Given a ParkingLot: {"parkingLotId": "string", "capacity": "integer(1-100)"} which "capacity" smaller than 1

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

4. Given a ParkingLot: {"parkingLotId": "string", "capacity": "integer(1-100)"} which "capacity" larger than 100

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

5. Given a ParkingLot: {"parkingLotId": "string", "capacity": null} which "capacity" is null

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

6. Given a ParkingLot: {"parkingLotId": null, "capacity": "integer(1-100)"} which parkingLotId is null

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

7. Given a ParkingLot: {"parkingLotId": "", "capacity": "integer(1-100)"} which parkingLotId is a empty string

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)