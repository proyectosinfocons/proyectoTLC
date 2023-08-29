package com.example.proyectotlc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Empresa")
public class empresaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    private String ruc;



    private String razonSocial;


    private String direccion;


    private String estado;

}
