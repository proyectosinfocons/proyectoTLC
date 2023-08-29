package com.example.proyectotlc.dto;

import lombok.*;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    private String username;
}