package com.example.proyectotlc.controller;


import com.example.proyectotlc.model.empresaInfo;
import com.example.proyectotlc.repository.empresaRepository;
import com.example.proyectotlc.service.empresarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/principal")
public class empresaController {





    private empresarioService empservice;



    private empresaRepository emprepository;


    @PostMapping(value = "/crear")
    public ResponseEntity<empresaInfo> montototaldetipocambio(@RequestBody empresaInfo requestempresaDTO) {

      empresaInfo rt=  empservice.saverequest(requestempresaDTO);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(rt);
    }


    @GetMapping(value = "/listadotodos")
    public ResponseEntity<List<empresaInfo>> listartodostipos() {
        List<empresaInfo> listTipoCambio = emprepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(listTipoCambio.subList(listTipoCambio.size()-3, listTipoCambio.size()));
    }

}
