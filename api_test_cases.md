### Test Case(s) for GET /parkingboys
1.  Given: Parking boys record stored in database

    When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys in data base
    ```JSON
    [{"employeeID": "string"}]
    ```

### Test Case(s) for POST /parkingboys
1. Given: a ParkingBoy: {"employeeID": string}

    When: POST to /parkingboys
    
    Then: it should 
    
    * save the given parking boy
    * return status code 201 (Created) with location in response header as "/parkingboys/{employeeID}"

2. Given a ParkingBoy: {"employeeID": string} which "employeeID" is repeated with other record in server

    When POST to /parkingboys
    
    Then it should 
    
    * not save the given parking boy
    * return status code 400 (Bad Request)
    
