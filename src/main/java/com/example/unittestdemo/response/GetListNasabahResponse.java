package com.example.unittestdemo.response;

import com.example.unittestdemo.domain.Nasabah;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListNasabahResponse extends CommonResponse {

    private List<Nasabah> data;


}
