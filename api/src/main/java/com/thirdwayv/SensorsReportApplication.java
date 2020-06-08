package com.thirdwayv;

import com.thirdwayv.model.DeviceTemp;
import com.thirdwayv.repository.DeviceTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SensorsReportApplication {

    @Autowired
    DeviceTempRepository deviceTempRepository;

    public static void main(String[] args) {
        SpringApplication.run(SensorsReportApplication.class, args);
    }
}
