package com.thirdwayv.controller;

import com.thirdwayv.common.Util;
import com.thirdwayv.service.DeviceTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1.0")
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
public class DeviceTempController {


    @Autowired
    Util util;

    @Autowired
    DeviceTempService deviceTempService;


    @GetMapping(value = "/devicetemp/{value}")
    public ResponseEntity<?> saveDeviceTempHex(@PathVariable String value) throws Exception {
        try {
            this.deviceTempService.parseAndSaveValue(value);
            return new ResponseEntity<>( "", HttpStatus.OK);
        } catch (Exception e) {
            return util.returnError(e);
        }
    }

    @GetMapping(value = "/devicetemp/latest")
    public ResponseEntity<?> getDeviceTempLatest() throws Exception {
        try {
            return new ResponseEntity<>(this.deviceTempService.getAllDevicesWithLatestTemp(), HttpStatus.OK);
        } catch (Exception e) {
            return util.returnError(e);
        }
    }


    @GetMapping(value = "/devicetemp/device/{deviceId}")
    public ResponseEntity<?> getAllTempByDeviceId(@PathVariable String deviceId) throws Exception {
        try {
            return new ResponseEntity<>(this.deviceTempService.getAllTempByDeviceId(deviceId), HttpStatus.OK);
        } catch (Exception e) {
            return util.returnError(e);
        }
    }


    @GetMapping(value = "/devicetemp")
    public ResponseEntity<?> getAllData() throws Exception {
        try {
            return new ResponseEntity<>(this.deviceTempService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return util.returnError(e);
        }
    }
}
