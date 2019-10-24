package com.example.unittestdemo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NasabahRequest {

    @NotEmpty(message = "nasabah id is required")
    private String nasabahID;
    @NotEmpty(message = "nama is required")
    private String nama;
    @NotNull(message = "umur is required")
    private Integer umur;

}
