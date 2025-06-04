package org.example.session3.hw9.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.session3.apiPayload.dto.ApiResponse;
import org.example.session3.hw9.dto.Hw9RequestDto;
import org.example.session3.hw9.entity.Hw9Entity;
import org.example.session3.hw9.service.Hw9Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class Hw9Controller {
    private final Hw9Service hw9service;

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody @Valid Hw9RequestDto requestDto) {
        ApiResponse<String> response = hw9service.signup(requestDto);
        return ResponseEntity.ok(response);
    }
}
