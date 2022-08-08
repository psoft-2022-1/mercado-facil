package com.ufcg.psoft.mercadofacil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class VersionApiController {

    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}
