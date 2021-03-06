# Parkie
Parking is a parking sytstem with the following features:
1. Keeps track of incoming vehicles by monitoring time in, parking time, and time out.
2. Get the current charge of their vehicle.
3. Get the list of currently parked vehicles with time (in hours) since parked.
4. Check if there are slots available. 

# Deployment
1. Open Terminal in the repository's folder.
2. Type into the Terminal ```docker-compose up``` and press enter

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

### Error Response
**URL: ** ```http://localhost:8080/vehicle/in?vehicleNumber=9548```
**Method: ** ```POST```
**Code:** ```400 BAD REQUEST```
**Content:**
```json
{
    "error": "Vehicle has entered already!"
}
```

**URL: ** ```http://localhost:8080/vehicle/in```
**Method: ** ```POST```
**Code:** ```400 BAD REQUEST```
**Content:**
```json
{
    "timestamp": "2021-03-06T04:45:05.289+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "",
    "path": "/vehicle/in"
}
```



## Vehicle Park

------------


**URL: ** ```/vehicle/park```
**Method: ** ```POST```
**Query Params:**
- vehicleNumber: [Vehicle Plate Number]

### Success Response
**URL: ** ```http://localhost:8080/vehicle/park?vehicleNumber=9548```
**Method: ** ```POST```
**Code:** ```200 OK```
**Content:**
```json
{
    "vehicleNumber": 9548,
    "timeIn": "2021-03-06T04:35:18.379+00:00",
    "timeParked": "2021-03-06T04:45:58.512+00:00",
    "timeOut": null
}
```


### Error Response
**URL: ** ```http://localhost:8080/vehicle/park?vehicleNumber=9548```
**Method: ** ```POST```
**Code:** ```400 BAD REQUEST```
**Content:**
```json
{
    "error": "Vehicle already parked!"
}
```

**URL: ** ```http://localhost:8080/vehicle/park?vehicleNumber=1234```
**Method: ** ```POST```
**Code:** ```400 BAD REQUEST```
**Content:**
```json
{
    "error": "Vehicle not found!"
}
```

**URL: ** ```http://localhost:8080/vehicle/park```
**Method: ** ```POST```
**Code:** ```400 BAD REQUEST```
**Content:**
```json
{
    "timestamp": "2021-03-06T04:50:50.649+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "",
    "path": "/vehicle/park"
}
```
