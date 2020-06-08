package com.thirdwayv.service;

import com.google.gson.Gson;
import com.thirdwayv.model.DeviceTemp;
import com.thirdwayv.repository.DeviceTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceTempService {

    private final SimpMessagingTemplate template;

    @Autowired
    DeviceTempService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    DeviceTempRepository deviceTempRepo;


    public void parseAndSaveValue(String value) {
        String input = value.substring(2);
        String hexDeviceId = input.substring(0, 8);
        input = input.replaceFirst(hexDeviceId, "");
        String hexTemp = input.substring(0, 2);
        input = input.replaceFirst(hexTemp, "");
        this.saveDecimalValues(hexDeviceId, hexTemp);
        if (input.equals("")) {
            this.sendDataOnSocket();
            return;
        } else {
            this.parseAndSaveValue("0x" + input);
        }

    }

    private void sendDataOnSocket() {
        List<DeviceTemp> updatedData = this.getAllDevicesWithLatestTemp();
        Gson json = new Gson();
        this.template.convertAndSend("/message", json.toJson(updatedData));
    }

    public void saveDecimalValues(String hexDevice, String hexTemp) {
        DeviceTemp deviceTemp = new DeviceTemp();
        deviceTemp.setDeviceId(String.valueOf(Long.parseLong(hexDevice, 16)));
        deviceTemp.setTemp(String.valueOf(Long.parseLong(hexTemp, 16)));
        deviceTemp.setCreationDate(new Date());
        this.deviceTempRepo.save(deviceTemp);
    }

    public List<DeviceTemp> getAllDevicesWithLatestTemp() {
        List<DeviceTemp> deviceWithLatestTemp = new ArrayList<>();
        this.deviceTempRepo.findAllOrderByDateDesc().stream().forEach(deviceTemp -> {
            if (!deviceWithLatestTemp.contains(deviceTemp)) {
                deviceWithLatestTemp.add(deviceTemp);
            }
        });
        return deviceWithLatestTemp;
    }

    public List<DeviceTemp> getAllTempByDeviceId(String deviceId) {
        return this.deviceTempRepo.findAllByDeviceId(deviceId);
    }

    public List<DeviceTemp> getAll() {
        return this.deviceTempRepo.findAll();
    }
}
