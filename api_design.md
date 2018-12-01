### Story 1
1. Given: A ParkingBoy: {"employeeID": string}

    When: POST to /parkingboys
    
    Then: It should return status code 201 (Created) with location in response header as "/parkingboys/{employeeID}"
    
2.  When: GET to /parkingboys
    
    Then: It should return status code 200 (OK) with a list containing all parking boys
    ```JSON
    [{"employeeID": "string"}]
    ```

----