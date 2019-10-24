package com.example.unittestdemo.controller;

import com.example.unittestdemo.request.NasabahRequest;
import com.example.unittestdemo.response.CommonResponse;
import com.example.unittestdemo.response.GetListNasabahResponse;
import com.example.unittestdemo.response.GetNasabahResponse;
import com.example.unittestdemo.service.impl.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/nasabah")
@RestController
public class NasabahRestController {

    private NasabahService nasabahService;

    public NasabahRestController(NasabahService nasabahService){
        this.nasabahService = nasabahService;
    }

    @GetMapping("/all")
    public GetListNasabahResponse findAll(){
        return nasabahService.findAll();
    }

    @GetMapping("/get/{nasabahID}")
    public GetNasabahResponse findById(@PathVariable("nasabahID") String nasabahID){
        return nasabahService.findById(nasabahID);
    }

    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody @Valid NasabahRequest request){
        if(request == null){
            throw new IllegalArgumentException("Request is required");
        }
        return nasabahService.insertNasabah(request);
    }

    @PutMapping("/update")
    public CommonResponse update(@RequestBody @Valid NasabahRequest request){
        if(request == null){
            throw new IllegalArgumentException("Request is required");
        }
        return nasabahService.updateNasabah(request);
    }

    @DeleteMapping("/delete/{nasabahID}")
    public CommonResponse delete(@PathVariable("nasabahID") String nasabahID){
        return nasabahService.deleteNasabah(nasabahID);
    }

}
