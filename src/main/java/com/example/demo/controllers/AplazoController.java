package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.InputModel;
import com.example.demo.models.LoggerModel;
import com.example.demo.models.ResponseVO;
import com.example.demo.services.CalculoService;


@RestController
@RequestMapping("/aplazo")
public class AplazoController {
    @Autowired
    CalculoService calculoService;

    @PostMapping("/calculo")
    public ResponseVO calcularPlazos(@RequestBody InputModel input){
        return this.calculoService.calcularplazo(input);
    }
    
    @GetMapping("/listado")
    public List<LoggerModel> obtenerLogger(){
        return calculoService.obtenerLogger();
    }
}