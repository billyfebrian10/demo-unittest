package com.example.unittestdemo.service;

import com.example.unittestdemo.constant.CommonConstant;
import com.example.unittestdemo.domain.Nasabah;
import com.example.unittestdemo.repository.NasabahRepository;
import com.example.unittestdemo.request.NasabahRequest;
import com.example.unittestdemo.response.CommonResponse;
import com.example.unittestdemo.response.GetListNasabahResponse;
import com.example.unittestdemo.response.GetNasabahResponse;
import com.example.unittestdemo.service.impl.NasabahService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NasabahServiceTest {

    @Mock
    private NasabahRepository nasabahRepository;

    @InjectMocks
    private NasabahService nasabahService;

    @Test
    void testFindAll_shouldReturnArrayList(){
        List<Nasabah> mockList = Arrays.asList(new Nasabah(), new Nasabah());
        GetListNasabahResponse expected = new GetListNasabahResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);
        expected.setData(mockList);

        when(nasabahRepository.findAll()).thenReturn(mockList);

        GetListNasabahResponse actual = nasabahService.findAll();

        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getData(), actual.getData());

    }

    @Test
    void givenNasabahIdNotExistInDatabase_shouldReturnNull(){
        GetNasabahResponse expected = new GetNasabahResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);
        expected.setNasabah(null);

        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.empty());

        GetNasabahResponse actual = nasabahService.findById(anyString());

        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getNasabah(), actual.getNasabah());

    }

    @Test
    void givenNasabahIdExistInDatabase_shouldReturnNasabah(){

        Nasabah mockNasabah = new Nasabah();

        GetNasabahResponse expected = new GetNasabahResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);
        expected.setNasabah(mockNasabah);

        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.of(mockNasabah));

        GetNasabahResponse actual = nasabahService.findById(anyString());

        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getNasabah(), actual.getNasabah());

    }

    @Test
    void testInsertNasabah_givenNasabahNotExist_shouldReturnSuccess(){

        NasabahRequest nasabahToInsert = new NasabahRequest("test", "Nasabah test", 99);
        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.empty());
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);

        CommonResponse actual = nasabahService.insertNasabah(nasabahToInsert);
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

    @Test
    void testInsertNasabah_givenNasabahExist_shouldReturnFail(){

        NasabahRequest nasabahToInsert = new NasabahRequest("test", "Nasabah test", 99);
        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.of(new Nasabah()));
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.INTERNAL_SERVER_ERROR_CODE);
        expected.setMessage(CommonConstant.NASABAH_EXIST);

        CommonResponse actual = nasabahService.insertNasabah(nasabahToInsert);
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

    @Test
    void testUpdateNasabah_givenNasabahExist_shouldReturnSuccess(){

        NasabahRequest nasabahToUpdate = new NasabahRequest("test", "Nasabah test update", 99);
        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.of(new Nasabah()));
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);

        CommonResponse actual = nasabahService.updateNasabah(nasabahToUpdate);
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

    @Test
    void testUpdateNasabah_givenNasabahNotExist_shouldReturnFail(){

        NasabahRequest nasabahToInsert = new NasabahRequest("test", "Nasabah test", 99);
        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.empty());
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.NOT_FOUND_CODE);
        expected.setMessage(CommonConstant.NASABAH_NOT_EXIST);

        CommonResponse actual = nasabahService.updateNasabah(nasabahToInsert);
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

    @Test
    void testDeleteNasabah_givenNasabahNotExist_shouldReturnFail(){
        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.empty());
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.NOT_FOUND_CODE);
        expected.setMessage(CommonConstant.NASABAH_NOT_EXIST);

        CommonResponse actual = nasabahService.deleteNasabah("test");
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

    @Test
    void testDeleteNasabah_givenNasabahExist_shouldReturnSuccess(){

        when(nasabahRepository.findOneByIdNasabah(anyString())).thenReturn(Optional.of(new Nasabah()));
        CommonResponse expected = new CommonResponse();
        expected.setCode(CommonConstant.OK_CODE);
        expected.setMessage(CommonConstant.OK);

        CommonResponse actual = nasabahService.deleteNasabah("test");
        assertNotNull(actual);
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getMessage(),actual.getMessage());

    }

}
