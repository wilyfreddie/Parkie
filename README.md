
# Parkie
Parkie is a parking sytstem with the following features:
1. Keeps track of incoming vehicles by monitoring time in, parking time, and time out.
2. Get the current charge of their vehicle.
3. Get the list of currently parked vehicles with time (in hours) since parked.
4. Check if there are slots available. 

# Building
1. Open Terminal in the repository's folder.
2. Type into the Terminal ```mvn clean install -DskipTests=true``` and press enter.

# Deployment
1. Open Terminal in the repository's folder.
2. Type into the Terminal ```docker-compose up``` and press enter.

# Configuration
The parking system can be configured by editing the ```ParkingConfig.java``` file. The following parameters can be changed to satisfy the parking requirements.

 - **slotsAvailable** - The amount of available parking spaces in the parking lot.
 - **rate** - Parking charge rate in currency/hour.

# REST API Endpoints
Base Url: http://localhost:8080

## Vehicle In
**Description:** Action taken whenever a vehicle enters the parking lot.

**URL:** ```/vehicle/in```

**Method:** ```POST```

**Query Params:**
- vehicleNumber: [Vehicle Plate Number]

### Success Response
**URL:**  ```http://localhost:8080/vehicle/in?vehicleNumber=9548```

**Method:** ```POST```


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
**URL:**```http://localhost:8080/vehicle/in?vehicleNumber=9548```

**Method:** ```POST```

**Code:** ```400 BAD REQUEST```

**Content:**
```json
{
    "error": "Vehicle has entered already!"
}
```

**URL:** ```http://localhost:8080/vehicle/in```

**Method:** ```POST```

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
**Description:** Action taken when a vehicle inside the parking lot has parked.

**URL:** ```/vehicle/park```

**Method:** ```POST```

**Query Params:**
- vehicleNumber: [Vehicle Plate Number]

### Success Response
**URL:** ```http://localhost:8080/vehicle/park?vehicleNumber=9548```

**Method:** ```POST```

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
**URL:** ```http://localhost:8080/vehicle/park?vehicleNumber=9548```

**Method:** ```POST```

**Code:** ```400 BAD REQUEST```

**Content:**
```json
{
    "error": "Vehicle already parked!"
}
```

**URL:** ```http://localhost:8080/vehicle/park?vehicleNumber=1234```

**Method:** ```POST```

**Code:** ```400 BAD REQUEST```

**Content:**
```json
{
    "error": "Vehicle not found!"
}
```

**URL:** ```http://localhost:8080/vehicle/park```

**Method:** ```POST```

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

## Vehicle Out
**Description:** Action taken when a vehicle leaves the parking lot. Data from the vehicles table is deleted and transferred into the vehicle history table.

**URL:** ```/vehicle/out```

**Method:** ```POST```

**Query Params:**
- vehicleNumber: [Vehicle Plate Number]

### Success Response
**URL:** ```http://localhost:8080/vehicle/out?vehicleNumber=9548```

**Method:** ```POST```

**Code:** ```200 OK```

**Content:**
```json
{
	"id":  1,
	"charge":  0.0,
	"vehicleNumber":  9548,
	"timeIn":  "2021-03-06T09:44:48.276+00:00",
	"timeParked":  "2021-03-06T09:44:52.886+00:00",
	"timeOut":  "2021-03-06T09:45:05.707+00:00"
}
```


### Error Response
**URL:** ```http://localhost:8080/vehicle/out?vehicleNumber=9548```

**Method:** ```POST```

**Code:** ```400 BAD REQUEST```

**Content:**
```json
{
	"error":  "Vehicle not found!"
}
```


## Current Charge
**Description:** Returns current parking charge of the vehicle.

**URL:** ```/vehicle/current-charge```

**Method:** ```GET```

**Query Params:**
- vehicleNumber: [Vehicle Plate Number]


### Success Response
**URL:** ```http://localhost:8080/vehicle/current-charge?vehicleNumber=9548```

**Code:** ```200 OK```

**Content:**
```json
{
	"currentCharge":  "0.0"
}
```

### Error Response
**URL:** ```http://localhost:8080/vehicle/current-charge?vehicleNumber=9548```

**Method:** ```POST```

**Code:** ```400 BAD REQUEST```

**Content:**
```json
{
	"error":  "Vehicle not found!"
}
```


## Slots Available
**Description:** Returns the number of available parking slots.

**URL:** ```/vehicle/slots-available```

**Method:** ```GET```


### Success Response
**URL:** ```http://localhost:8080/vehicle/slots-available```
**Code:** ```200 OK```
**Content:**
```json
{
	"slotsAvailable":  "46"
}
```

## Parked List
**Description:** Returns list of parked vehicles.

**URL:** ```/vehicle/list-parked```

**Method:** ```GET```


### Success Response
**URL:** ```http://localhost:8080/vehicle/list-parked```
**Code:** ```200 OK```
**Content:**
```json
[
	{
		"vehicleNumber":  "AAA1234",
		"timeSinceParked":  0.0,
		"timeParked":  "2021-03-06T10:00:51.116+00:00"
	},
	{
		"vehicleNumber":  "BBB5678",
		"timeSinceParked":  0.0,
		"timeParked":  "2021-03-06T10:00:58.420+00:00"
	},
	{
		"vehicleNumber":  "CCC9012",
		"timeSinceParked":  0.0,
		"timeParked":  "2021-03-06T10:01:04.336+00:00"
	}
]
```


## Vehicles Inside Parking List
**Description:** Returns list of vehicles inside the parking regardless whether it is parked or not.

**URL:** ```/vehicle/list-in```

**Method:** ```GET```


### Success Response
**URL:** ```http://localhost:8080/vehicle/list-in```
**Code:** ```200 OK```
**Content:**
```json
[
	{
		"vehicleNumber":  "AAA1234",
		"timeIn":  "2021-03-06T10:00:03.590+00:00",
		"timeParked":  "2021-03-06T10:00:51.116+00:00",
		"timeOut":  null
	},
	{
		"vehicleNumber":  "BBB5678",
		"timeIn":  "2021-03-06T10:00:17.607+00:00",
		"timeParked":  "2021-03-06T10:00:58.420+00:00",
		"timeOut":  null
	},
	{
		"vehicleNumber":  "CCC9012",
		"timeIn":  "2021-03-06T10:00:29.934+00:00",
		"timeParked":  "2021-03-06T10:01:04.336+00:00",
		"timeOut":  null
	},
	{
		"vehicleNumber":  "DDD9548",
		"timeIn":  "2021-03-06T10:04:27.264+00:00",
		"timeParked":  null,
		"timeOut":  null
	}
]
```


## Parking History
**Description:** Returns list of past parking transactions. Particulars such as time in, parking time, time out, and parking charge are returned.

**URL:** ```/vehicle/history```

**Method:** ```GET```


### Success Response
**URL:** ```http://localhost:8080/vehicle/history```
**Code:** ```200 OK```
**Content:**
```json
[
	{
		"id":  1,
		"charge":  50.0,
		"vehicleNumber":  "AAA1234",
		"timeIn":  "2021-03-06T10:00:03.590+00:00",
		"timeParked":  "2021-03-06T10:00:51.116+00:00",
		"timeOut":  "2021-03-06T10:09:24.630+00:00"
	},
	{
		"id":  2,
		"charge":  50.0,
		"vehicleNumber":  "BBB5678",
		"timeIn":  "2021-03-06T10:00:17.607+00:00",
		"timeParked":  "2021-03-06T10:00:58.420+00:00",
		"timeOut":  "2021-03-06T10:09:33.808+00:00"
	},
	{
		"id":  3,
		"charge":  50.0,
		"vehicleNumber":  "CCC9012",
		"timeIn":  "2021-03-06T10:00:29.934+00:00",
		"timeParked":  "2021-03-06T10:01:04.336+00:00",
		"timeOut":  "2021-03-06T10:09:42.402+00:00"
	}
]
```
