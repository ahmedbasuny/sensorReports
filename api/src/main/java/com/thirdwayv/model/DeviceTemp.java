package com.thirdwayv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@Entity
@ApiModel(description = "device temp details")
public class DeviceTemp {

    @Id
    @GeneratedValue
    private Integer id;

    private String deviceId;

    private String temp;

    private Date creationDate;

    public DeviceTemp() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeviceTemp) {
            DeviceTemp deviceTemp = (DeviceTemp) o;
            return this.getDeviceId().equals(deviceTemp.deviceId);
        } else
            return false;
    }

}

