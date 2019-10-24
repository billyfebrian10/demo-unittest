package com.example.unittestdemo.response;

import com.example.unittestdemo.domain.Nasabah;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNasabahResponse extends CommonResponse{

    private Nasabah nasabah;

}
