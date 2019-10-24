package com.example.unittestdemo.service.impl;

import com.example.unittestdemo.constant.CommonConstant;
import com.example.unittestdemo.domain.Nasabah;
import com.example.unittestdemo.repository.NasabahRepository;
import com.example.unittestdemo.request.NasabahRequest;
import com.example.unittestdemo.response.CommonResponse;
import com.example.unittestdemo.response.GetListNasabahResponse;
import com.example.unittestdemo.response.GetNasabahResponse;
import com.example.unittestdemo.service.INasabahService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NasabahService implements INasabahService {

    private NasabahRepository nasabahRepository;

    public NasabahService(NasabahRepository nasabahRepository){
        this.nasabahRepository = nasabahRepository;
    }

    @Override
    public GetListNasabahResponse findAll() {
        GetListNasabahResponse nasabahResponse = new GetListNasabahResponse();
        nasabahResponse.setMessage(CommonConstant.OK);
        nasabahResponse.setCode("200");
        nasabahResponse.setData(nasabahRepository.findAll());
        return nasabahResponse;
    }

    @Override
    public GetNasabahResponse findById(String nasabahID) {
        GetNasabahResponse nasabahResponse = new GetNasabahResponse();
        nasabahResponse.setCode("200");
        Optional<Nasabah> optionalNasabah = nasabahRepository.findOneByIdNasabah(nasabahID);
        if(optionalNasabah.isPresent()){
            nasabahResponse.setMessage("ok");
            nasabahResponse.setNasabah(optionalNasabah.get());
        }else {
            nasabahResponse.setMessage("Nasabah not found");
            nasabahResponse.setNasabah(null);
        }
        return nasabahResponse;
    }

    @Override
    public CommonResponse insertNasabah(NasabahRequest request) {
        CommonResponse response = new CommonResponse();
        Optional<Nasabah> optionalNasabah = nasabahRepository.findOneByIdNasabah(request.getNasabahID());
        if(optionalNasabah.isPresent()){
            response.setMessage(CommonConstant.NASABAH_EXIST);
            response.setCode(CommonConstant.INTERNAL_SERVER_ERROR_CODE);
        }else {
            Nasabah nasabah = new Nasabah(request.getNasabahID(), request.getNama(), request.getUmur());
            nasabahRepository.save(nasabah);
            response.setMessage(CommonConstant.OK);
            response.setCode(CommonConstant.OK_CODE);
        }
        return response;
    }

    @Override
    public CommonResponse deleteNasabah(String nasabahID) {
        CommonResponse response = new CommonResponse();
        Optional<Nasabah> optionalNasabah = nasabahRepository.findOneByIdNasabah(nasabahID);
        if(optionalNasabah.isPresent()){
            Nasabah nasabah = optionalNasabah.get();
            nasabahRepository.delete(nasabah);
            response.setMessage(CommonConstant.OK);
            response.setCode(CommonConstant.OK_CODE);
        }else {
            response.setMessage(CommonConstant.NASABAH_NOT_EXIST);
            response.setCode(CommonConstant.NOT_FOUND_CODE);
        }
        return response;
    }

    @Override
    public CommonResponse updateNasabah(NasabahRequest request) {
        CommonResponse response = new CommonResponse();
        Optional<Nasabah> optionalNasabah = nasabahRepository.findOneByIdNasabah(request.getNasabahID());
        if(optionalNasabah.isPresent()){
            Nasabah nasabah = optionalNasabah.get();
            nasabah.setUmur(request.getUmur());
            nasabah.setNama(request.getNama());
            nasabahRepository.save(nasabah);
            response.setMessage(CommonConstant.OK);
            response.setCode(CommonConstant.OK_CODE);
        }else {
            response.setMessage(CommonConstant.NASABAH_NOT_EXIST);
            response.setCode(CommonConstant.NOT_FOUND_CODE);
        }
        return response;
    }
}
