package com.thirdwayv.repository;

import com.thirdwayv.model.DeviceTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTempRepository extends JpaRepository<DeviceTemp, Integer> {

    @Query(value = " select * from device_temp order by creation_date desc ", nativeQuery = true)
    public List<DeviceTemp> findAllOrderByDateDesc();

    public List<DeviceTemp> findAllByDeviceId(String deviceId);
}
