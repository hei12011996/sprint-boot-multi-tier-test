### Test Case(s) for GET /parkingboys
1.  Given: Parking boys record stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys in data base
    ```JSON
    [{"employeeID": "string"}]
    ```

### Test Case(s) for POST /parkingboys
1. Given: a ParkingBoy: {"employeeID": "string"}

    When: POST to /parkingboys
    
    Then: it should 
    
    * save the given parking boy
    * return status code 201 (Created)

2. Given a ParkingBoy: {"employeeID": "string"} which "employeeID" is repeated with other record in server

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)
    
### Test Case(s) for GET /parkinglots
1.  Given: Parking lots record stored in database

    When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing all parking lotss in database
    ```JSON
    {"parkingLotID": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}
    ```

### Test Case(s) for POST /parkinglots
1. Given: a ParkingLoy: {"parkingLotID": "string", "capacity": "integer(1-100)"}

    When: POST to /parkinglots
    
    Then: it should 
    
    * save the given parking lot
    * return status code 201 (Created)

2. Given a ParkingLot: {"parkingLotID": "string", "capacity": "integer(1-100)"} which "parkingLotID" is repeated with other record in server

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

3. Given a ParkingLot: {"parkingLotID": "string", "capacity": "integer(1-100)"} which "capacity" smaller than 1

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

4. Given a ParkingLot: {"parkingLotID": "string", "capacity": "integer(1-100)"} which "capacity" larger than 100

    When POST to /parkinglots
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)
