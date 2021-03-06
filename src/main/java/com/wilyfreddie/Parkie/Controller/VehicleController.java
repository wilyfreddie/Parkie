package com.wilyfreddie.Parkie.Controller;

import com.wilyfreddie.Parkie.Model.ParkingConfig;
import com.wilyfreddie.Parkie.Model.Vehicle;
import com.wilyfreddie.Parkie.Model.VehicleHistory;
import com.wilyfreddie.Parkie.Repositories.VehicleHistoryRepository;
import com.wilyfreddie.Parkie.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleHistoryRepository vehicleHistory;


    @PostMapping("/vehicle/in")
    public ResponseEntity vehicleIn(@RequestParam(name = "vehicleNumber") String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicle != null) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Vehicle has entered already!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if ((ParkingConfig.getSlotsAvailable() - (int) vehicleRepository.count()) == 0) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Parking is full!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        Date currentDate = new Date();
        vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setTimeIn(currentDate);
        return ResponseEntity.ok(vehicleRepository.save(vehicle));
    }

    @PostMapping("/vehicle/park")
    public ResponseEntity vehiclePark(@RequestParam(name = "vehicleNumber") String vehicleNumber) {
        Date currentDate = new Date();
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicle == null) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Vehicle not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (vehicle.getTimeParked() != null) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Vehicle already parked!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        vehicle.setTimeParked(currentDate);
        return ResponseEntity.ok(vehicleRepository.save(vehicle));
    }

    @PostMapping("/vehicle/out")
    public ResponseEntity vehicleOut(@RequestParam(name = "vehicleNumber") String vehicleNumber) {
        Date currentDate = new Date();
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);

        if (vehicle == null) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Vehicle not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        VehicleHistory _vehicleHistory = new VehicleHistory();
        _vehicleHistory.setVehicleNumber(vehicle.getVehicleNumber());
        _vehicleHistory.setTimeIn(vehicle.getTimeIn());
        _vehicleHistory.setTimeParked(vehicle.getTimeParked());
        _vehicleHistory.setTimeOut(currentDate);
        if(vehicle.getTimeParked() == null)
            _vehicleHistory.setCharge(ParkingConfig.getRate());
        else
            _vehicleHistory.setCharge(vehicle.currentCharge());
        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok(vehicleHistory.save(_vehicleHistory));
    }

    @GetMapping("/vehicle/current-charge")
    public ResponseEntity vehicleCurrentCharge(@RequestParam(name = "vehicleNumber") String vehicleNumber) {
        Date currentDate = new Date();
        Map<String, String> detail = new HashMap<>(2);
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicle == null) {
            Map<String, String> error = new HashMap<>(2);
            error.put("error", "Vehicle not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        detail.put("currentCharge", Float.toString(vehicle.currentCharge()));
        return ResponseEntity.ok(detail);
    }


    @GetMapping("/vehicle/list-parked")
    @ResponseBody
    public List vehicleCurrentCharge() {
        class VehicleList {
            public String getVehicleNumber() {
                return vehicleNumber;
            }

            public void setVehicleNumber(String vehicleNumber) {
                this.vehicleNumber = vehicleNumber;
            }

            public float getTimeSinceParked() {
                return timeSinceParked;
            }

            public void setTimeSinceParked(float timeSinceParked) {
                this.timeSinceParked = timeSinceParked;
            }

            public Date getTimeParked() {
                return timeParked;
            }

            public void setTimeParked(Date timeParked) {
                this.timeParked = timeParked;
            }

            private String vehicleNumber;
            private float timeSinceParked;
            private Date timeParked;
        }

        List<VehicleList> vehicleList = new ArrayList<VehicleList>();
        List<Vehicle> _vehicleList = vehicleRepository.findAll();

        for (Vehicle v : _vehicleList) {
            VehicleList vehicle = new VehicleList();
            if (v.getTimeParked() == null)
                continue;
            vehicle.setVehicleNumber(v.getVehicleNumber());
            vehicle.setTimeSinceParked(v.timeSinceParked());
            vehicle.setTimeParked(v.getTimeParked());
            vehicleList.add(vehicle);
        }

        return vehicleList;
    }

    @GetMapping("/vehicle/list-in")
    public ResponseEntity vehicleListIn() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    @GetMapping("/vehicle/slots-available")
    public Map<String, String> slotsAvailable() {
        Map<String, String> detail = new HashMap<>(2);
        detail.put("slotsAvailable", Integer.toString(ParkingConfig.getSlotsAvailable() - (int) vehicleRepository.count()));
        return detail;
    }

    @GetMapping("/vehicle/history")
    public ResponseEntity vehicleHistoryList() {
        return ResponseEntity.ok(vehicleHistory.findAll());
    }
}
