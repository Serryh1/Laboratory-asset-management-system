package com.acmlab.file.controller;

import com.acmlab.file.entity.Device;
import com.acmlab.file.mapper.DeviceMapper;
import com.acmlab.file.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceMapper deviceMapper;

    @PostMapping("/add")
    public Result<String> upload(@RequestBody Device device){
        if(device.getClassify().isEmpty() || device.getName().isEmpty()|| device.getQuantity() == null){
            return Result.error();
        }
        else {
            deviceMapper.add(device);
            return Result.ok();
        }
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody Device device){
        if(device.getClassify().isEmpty() || device.getName().isEmpty()|| device.getQuantity() == null||device.getSid() == null){
            return Result.error();
        }
        else {
            deviceMapper.update(device);
            return Result.ok();
        }
    }
    @GetMapping("/delete")
    public Result<String> update(@RequestParam Integer sid){
        if(sid == null){
            return Result.error();
        }
        else {
            deviceMapper.delete(sid);
            return Result.ok();
        }
    }
    @PostMapping("/select")
    public Result<List<Device>> select(@RequestBody Device device) {
        List<Device> list = deviceMapper.select(device);
        System.out.println(device.getClassify()+"+"+device.getQuantity()+"+"+device.getSid()+"+"+device.getName());
        return Result.ok(list);
    }

}

