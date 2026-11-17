package com.example.demo.controller;

import com.example.demo.dto.EchoRequest;
import com.example.demo.dto.EchoResponse;
import com.example.demo.service.GreetingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 三个示例接口：
 * 1. GET  /api/hello  打招呼
 * 2. GET  /api/time   服务器当前时间
 * 3. POST /api/echo   回显请求内容
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final GreetingService greetingService;

    public ApiController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /** 接口一：打招呼 */
    @GetMapping("/hello")
    public Map<String, Object> hello(
            @RequestParam(value = "name", defaultValue = "World") String name) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", greetingService.greet(name));
        body.put("timestamp", LocalDateTime.now().format(FORMATTER));
        return body;
    }

    /** 接口二：服务器当前时间 */
    @GetMapping("/time")
    public Map<String, Object> time(
            @RequestParam(value = "zone", defaultValue = "Asia/Shanghai") String zone) {

        LocalDateTime now = LocalDateTime.now(ZoneId.of(zone));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("zone", zone);
        body.put("datetime", now.format(FORMATTER));
        body.put("epochMillis", System.currentTimeMillis());
        return body;
    }

    /** 接口三：回显请求体 */
    @PostMapping("/echo")
    public ResponseEntity<EchoResponse> echo(@Valid @RequestBody EchoRequest request) {
        EchoResponse response = new EchoResponse(
                request.message(),
                request.message().length(),
                LocalDateTime.now().format(FORMATTER));
        return ResponseEntity.ok(response);
    }
}
