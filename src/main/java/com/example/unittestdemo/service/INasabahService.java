package com.example.unittestdemo.service;

import com.example.unittestdemo.request.NasabahRequest;
import com.example.unittestdemo.response.CommonResponse;
import com.example.unittestdemo.response.GetListNasabahResponse;
import com.example.unittestdemo.response.GetNasabahResponse;


public interface INasabahService {

    GetListNasabahResponse findAll();
    GetNasabahResponse findById(String nasabahID);
    CommonResponse insertNasabah(NasabahRequest request);
    CommonResponse deleteNasabah(String nasabahID);
    CommonResponse updateNasabah(NasabahRequest request);

}
