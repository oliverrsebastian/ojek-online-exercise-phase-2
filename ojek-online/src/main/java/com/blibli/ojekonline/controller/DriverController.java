package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/api/driver/all")
    public BaseResponse<List<Driver>> getAllDriver() {
        BaseResponse response = new BaseResponse();
        response.setValue(driverService.getDriverList());
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/driver/{id}")
    public BaseResponse<Driver> getDriver(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            response.setValue(driverService.getDriverById(id));
            response.setStatus(HttpStatus.OK.toString());
        } catch (RuntimeException e) {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @PostMapping("/api/driver")
    public BaseResponse<String> insertDriver(@RequestBody Driver driver) {
        Driver returned = driverService.saveDriver(driver);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setValue("Save success");
            response.setStatus(HttpStatus.OK.toString());
        } else {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @PutMapping("/api/driver")
    public BaseResponse<String> updateDriver(@RequestBody Driver driver) {
        Driver returned = driverService.saveDriver(driver);
        BaseResponse response = new BaseResponse();
        if (response != null) {
            response.setValue("Update success!");
            response.setStatus(HttpStatus.OK.toString());
        } else {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @DeleteMapping("/api/driver/{id}")
    public BaseResponse<String> deleteDriver(@PathVariable int id) {
        String returnMessage = driverService.deleteDriverById(id);
        BaseResponse response = new BaseResponse();
        response.setValue(returnMessage);
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }
}
