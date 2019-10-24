package com.example.unittestdemo.repository;

import com.example.unittestdemo.domain.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, Long> {

    Optional<Nasabah> findOneByIdNasabah(String idNasabah);
    List<Nasabah> findAllByNamaContainingIgnoreCase(String nama);


}
