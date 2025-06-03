package org.example.session3.dto.user.request;

import lombok.*;

//로그인 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {
    private String userId;
    private String password;
}