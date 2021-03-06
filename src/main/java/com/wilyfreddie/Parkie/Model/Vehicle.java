package com.wilyfreddie.Parkie.Model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Id
    @Column(name="vehicleNumber")
    @NotNull
    private int vehicleNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timeIn")
    private Date timeIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timeParked")
    private Date timeParked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timeOut")
    private Date timeOut;

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public Date getTimeParked() {
        return timeParked;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public float currentCharge(){
        Date currentDate = new Date();
        return (float)(this.timeSinceParked() * ParkingConfig.getRate());
    }

    public float timeSinceParked(){
//        Returns hours
        Date currentDate = new Date();
        return (currentDate.getTime()-this.timeParked.getTime())/(60 * 60 * 1000);
    }
}
