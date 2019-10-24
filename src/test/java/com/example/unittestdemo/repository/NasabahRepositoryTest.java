package com.example.unittestdemo.repository;

import com.example.unittestdemo.domain.Nasabah;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class NasabahRepositoryTest {

    @Autowired
    NasabahRepository nasabahRepository;

    @Test
    void setUpNasabahTest(){
        Nasabah nasabah = new Nasabah( "test", "Nasabah Test", 99);
        nasabahRepository.save(nasabah);

        Nasabah nasabah2 = new Nasabah( "test2", "Coba Nasabah", 39);
        nasabahRepository.save(nasabah2);

        Optional<Nasabah> optionalNasabah1 = nasabahRepository.findOneByIdNasabah(nasabah.getIdNasabah());
        assertEquals(true, optionalNasabah1.isPresent());

        Optional<Nasabah> optionalNasabah2 = nasabahRepository.findOneByIdNasabah("test99");
        assertEquals(false, optionalNasabah2.isPresent());

        List<Nasabah> listNasabah = nasabahRepository.findAllByNamaContainingIgnoreCase("nasabah");
        assertEquals(2, listNasabah.size());

        List<Nasabah> listNasabah2 = nasabahRepository.findAllByNamaContainingIgnoreCase("test");
        assertEquals(1, listNasabah2.size());

        List<Nasabah> listNasabah3 = nasabahRepository.findAllByNamaContainingIgnoreCase("feaw");
        assertEquals(0, listNasabah3.size());

        //update
        nasabah.setUmur(80);
        nasabahRepository.save(nasabah);

        Optional<Nasabah> optionalNasabah = nasabahRepository.findOneByIdNasabah(nasabah.getIdNasabah());
        assertEquals(80, optionalNasabah.get().getUmur());


        nasabahRepository.delete(nasabah2);
        Optional<Nasabah> optionalNasabah4 = nasabahRepository.findOneByIdNasabah(nasabah2.getIdNasabah());
        assertEquals(false, optionalNasabah4.isPresent());

    }

}
