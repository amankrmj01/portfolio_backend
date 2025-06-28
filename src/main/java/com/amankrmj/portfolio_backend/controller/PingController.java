package com.amankrmj.portfolio_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ping")
public class PingController {
    @GetMapping()
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Server is awake and ready to serve! 🚀");
        return response;
    }
}
