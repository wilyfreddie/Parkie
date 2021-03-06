package com.wilyfreddie.Parkie.Repositories;

import com.wilyfreddie.Parkie.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleNumber = :vehicleNumber")
    Vehicle findByVehicleNumber(@Param("vehicleNumber") int vehicleNumber);
}
