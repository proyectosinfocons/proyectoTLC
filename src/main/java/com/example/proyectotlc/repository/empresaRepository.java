package com.example.proyectotlc.repository;

import com.example.proyectotlc.model.empresaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface empresaRepository extends JpaRepository<empresaInfo,Integer> {

    List<empresaInfo> findTop3By();

}
