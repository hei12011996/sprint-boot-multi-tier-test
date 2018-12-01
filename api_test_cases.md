### Test Case(s) for GET /parkingboys
1.  When GET to /parkingboys
    
    Then it should return status code 200 (OK) with a list containing all parking boys
    ```JSON
    [{"employeeID": "string"}]
    ```

### Test Case(s) for POST /parkingboys
1. Given a ParkingBoy: {"employeeID": string}

    When POST to /parkingboys
    
    Then it should return status code 201 (Created)
    
    with location in response header as "/parkingboys/{employeeID}"

2. Given a ParkingBoy: {"employeeID": string} which "employeeID" is repeated with the record in server

    When POST to /parkingboys
    
    Then it should return status code 400 (Bad Request)
    
