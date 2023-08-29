package com.example.proyectotlc.impl;

import com.example.proyectotlc.model.empresaInfo;
import com.example.proyectotlc.repository.empresaRepository;
import com.example.proyectotlc.service.empresarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class empresarioServiceimpl implements empresarioService {

    private final empresaRepository repositoryem;

    @Override
    public empresaInfo saverequest(empresaInfo emp) {
        return repositoryem.save(emp);
    }
}
