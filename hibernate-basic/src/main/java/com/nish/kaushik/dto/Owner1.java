package com.nish.kaushik.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ownerId;
    private String name;
    private String phone;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "VEHICLE_LIST")
    private List<Vehicle> vehicleList =  new ArrayList<>();


    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public String toString() {
        return "Owner1{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicleList=" + vehicleList +
                '}';
    }
}
