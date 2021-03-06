package com.wilyfreddie.Parkie.Repositories;

import com.wilyfreddie.Parkie.Model.Vehicle;
import com.wilyfreddie.Parkie.Model.VehicleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleHistoryRepository extends JpaRepository<VehicleHistory,Integer> {

}
