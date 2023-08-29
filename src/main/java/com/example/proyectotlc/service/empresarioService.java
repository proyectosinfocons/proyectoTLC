package com.example.proyectotlc.service;

import com.example.proyectotlc.model.empresaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface empresarioService {


    empresaInfo saverequest(empresaInfo tipodecambiomonto);


}
