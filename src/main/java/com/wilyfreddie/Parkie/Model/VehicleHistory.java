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
public class VehicleHistory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private float charge;

    @Column(name="vehicleNumber")
    @NotNull
    private int vehicleNumber;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeIn;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeParked;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOut;
}
