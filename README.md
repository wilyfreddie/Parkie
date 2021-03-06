# Parkie
Parking is a parking sytstem with the following features:
1. Keeps track of incoming vehicles by monitoring time in, parking time, and time out.
2. Get the current charge of their vehicle.
3. Get the list of currently parked vehicles with time (in hours) since parked.
4. Check if there are slots available. 

# Deployment
1. Open Terminal in the repository's folder.
2. Type into the terminal ```docker-compose up ``` and press enter

# REST API Endpoints
Base Url: http://localhost:8080

## Vehicle In

------------


**URL: ** ```/vehicle/in```
**Method: ** ```POST```
**Query Params:**
- vehicleNumber: [Vehicle Plate Number]

### Success Response
**URL: ** ```http://localhost:8080/vehicle/in?vehicleNumber=9548```
**Method: ** ```POST```
**Code:** ```200 OK```
**Content:**
```json
{
    "vehicleNumber": 9548,
    "timeIn": "2021-03-06T04:35:18.379+00:00",
    "timeParked": null,
    "timeOut": null
}
```
