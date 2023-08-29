package com.example.proyectotlc;

import com.example.proyectotlc.configuration.variablesRegistro;
import com.example.proyectotlc.model.empresaInfo;
import com.example.proyectotlc.repository.empresaRepository;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ProyectoTlcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoTlcApplication.class, args);
    }



    @Autowired
    variablesRegistro registrosmock;

    @Autowired
    empresaRepository emrepository;

    @SuppressWarnings("unused")
    private List<empresaInfo> empresainformacion() throws JsonProcessingException {
        ObjectMapper ejmmapper = new ObjectMapper();
        String json = rutadeconversion(registrosmock.getEmpresa());
        return ejmmapper.readValue(json, new TypeReference<List<empresaInfo>>() {
        });
    }



    private String rutadeconversion(String resource) {
        URL isyeyu = ProyectoTlcApplication.class.getClassLoader().getResource(resource);
        try {
            String ruta = Resources.toString(isyeyu, Charsets.UTF_8);
            return ruta;
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    @Override
    public void run(String... args) throws Exception {
        emrepository.saveAll(empresainformacion());
    }
}
