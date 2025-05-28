package org.example.session3.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;

//로그인 요청 DTO
@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    private String userId;
    private String password;
}