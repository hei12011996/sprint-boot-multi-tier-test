### Test Case(s) for GET /parkingboys
1.  Given: One parking boy record stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing that parking boy in data base
    ```JSON
    [{"employeeID": "string"}]
    ```
2.  Given: Multiple parking boy records stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys in data base
    ```JSON
    [{"employeeID": "string"}]
    ```
3.  Given: No parking boy record stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with an empty list
    ```JSON
    []
    ```

### Test case(s) for GET /parkingboys/{pbId}
1.  Given: A parking boy id "pbId": "number" which can be referenced to an id of an existing record of parking boy in the server 

    When: GET to /parkingboys/{pbId}
    
    Then: It should return status code 200 (OK) with the corresponding parking boy
    ```JSON
    {"employeeID": "string"}
    ```

2.  Given: A parking boy id "pbId": "number" which does not exist as an id of a parking boy in the server

    When: GET to /parkingboys/{pbId}
    
    Then: It should return status code 404 (Not Found)

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
    * return status code 409 (Conflict)

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

5. Given a ParkingBoy: {"employeeId": "string"} which employeeId is a string which length longer than 64

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)

6. Given a ParkingBoy: {"employeeId": "string"} which employeeId is a string which length exactly equals to 64

    When POST to /parkingboys
    
    Then: it should 
    
    * save the given parking boy
    * return status code 201 (Created)

### Test Case(s) for GET /parkinglots
1.  Given: One parking lot record stored in database

    When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing that parking lot in database
    ```JSON
    [{"parkingLotId": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}]
    ```
2.  Given: Multiple parking lots record stored in database

    When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with a list containing all parking lots in database
    ```JSON
    [{"parkingLotId": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}]
    ```
3.  Given: No parking lots record stored in database

    When: GET to /parkinglots
    
    Then: It should return status code 200 (OK) with an empty list
    ```JSON
    []
    ```

### Test case(s) for GET /parkingboys/{plId}
1.  Given: A parking lot id "plId": "number" which can be referenced to an id of an existing record of parking lot in the server 

    When: GET to /parkinglots/{plId}
    
    Then: It should return status code 200 (OK) with the corresponding parking lot
    ```JSON
    {"parkingLotId": "string", "availablePositionCount": "integer", "capacity": "integer(1-100)"}
    ```

2.  Given: A parking lot id "plId": "number" which does not exist as an id of a parking lot in the server

    When: GET to /parkingboys/{plId}
    
    Then: It should return status code 404 (Not Found)

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
    * return status code 409 (Conflict)

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

8. Given a ParkingLot: {"parkingLotId": "string", "capacity": "integer(1-100)"} which parkingLotId is a string which length longer than 64

    When POST to /parkinglot
    
    Then it should 
    
    * not save the given parking lot
    * return status code 400 (Bad Request)

9. Given a ParkingLot: {"parkingLotId": "string", "capacity": "integer(1-100)"} which parkingLotId is a string which length exactly equals to 64

    When POST to /parkinglot
    
    Then it should 
    
    * save the given parking lot
    * return status code 201 (Created)


### Test Case(s) for GET /parkingboys/{pbId}/parkinglots
1.  Given: A ParkingBoy id "pbId": "number" which "pbId" can be referenced to an id of an existing record of parking boy in the server and that parking boy is managing one parking lot

    When: GET to /parkingboys/{pbId}/parkinglots
    
    Then: It should return status code 200 (OK) with the "employeeId" of that parking boy and a list containing the "parkingLotId"s of all parking lots under that parking boy management
    ```JSON
    {"employeeId": "string", "associatedParkingLots": [{"parkingLotId": "string"}]}
    ```

2.  Given: A ParkingBoy id "pbId": "number" which "pbId" can be referenced to an id of an existing record of parking boy in the server and the parking boy is managing multiple parking lots

    When: GET to /parkingboys/{pbId}/parkinglots
    
    Then: It should return status code 200 (OK) with the "employeeId" of that parking boy and a list containing the "parkingLotId"s of all parking lots under that parking boy management
    ```JSON
    {"employeeId": "string", "associatedParkingLots": [{"parkingLotId": "string"}]}
    ```

3.  Given: A ParkingBoy id "pbId": "number" which "pbId" can be referenced to an id of an existing record of parking boy in the server and the parking boy is managing zero parking lots

    When: GET to /parkingboys/{pbId}/parkinglots
    
    Then: It should return status code 200 (OK) with the "employeeId" of that parking boy and a empty list containing the "parkingLotId"s
    ```JSON
    {"employeeId": "string", "associatedParkingLots": []}
    ```

4.  Given: A ParkingBoy id "pbId": "number" which "pbId" does not exist as an id of a parking boy in the server

    When: GET to /parkingboys/{pbId}/parkinglots
    
    Then: It should return status code 404 (Not found)

### Test Case(s) for POST /parkingboys/{pbId}/parkinglots/{plId}
1.  Given: A ParkingBoy id "pbId": "number" and a ParkingLot id "plId": "number"

    When: POST to /parkingboys/{pbId}/parkinglots/{plId}
    
    Then: It should return status code 201 (Created)

2.  Given: A ParkingBoy id "pbId": "number" and a ParkingLot id "plId": "number" where "pbId" does not exist as an id of a parking boy in the server

    When: POST to /parkingboys/{pbId}/parkinglots/{plId}
    
    Then: It should return status code 400 (Bad Request)

3.  Given: A ParkingBoy id "pbId": "number" and a ParkingLot id "plId": "number" where "plId" does not exist as an id of a parking lot in the server

    When: POST to /parkingboys/{pbId}/parkinglots/{plId}
    
    Then: It should return status code 400 (Bad Request)
