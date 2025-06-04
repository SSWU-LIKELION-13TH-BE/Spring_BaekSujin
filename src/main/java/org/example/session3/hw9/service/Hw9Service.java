package org.example.session3.hw9.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.session3.apiPayload.dto.ApiResponse;
import org.example.session3.apiPayload.code.ErrorStatus;
import org.example.session3.apiPayload.code.SuccessStatus;
import org.example.session3.apiPayload.exception.GeneralException;
import org.example.session3.hw9.dto.Hw9RequestDto;
import org.example.session3.hw9.entity.Hw9Entity;
import org.example.session3.hw9.repository.Hw9Repository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Hw9Service {
    private final Hw9Repository hw9Repository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<String> signup(Hw9RequestDto requestDto) {
        // 아이디 중복 확인
        if (hw9Repository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        // 유저 객체 생성 후 저장
        Hw9Entity hw9user = new Hw9Entity();
        hw9user.setUsername(requestDto.getUsername());
        hw9user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        hw9user.setName(requestDto.getName());
        hw9Repository.save(hw9user);

        return ApiResponse.of(SuccessStatus._OK, "회원가입 성공");
    }
}
