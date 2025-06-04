package org.example.session3.hw9.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hw9RequestDto {
    private Long userId;

    @NotBlank(message="아이디는 필수입니다.")
    @Size(min=2, message="아이디는 최소 2자 이상이어야 합니다.")
    private String username;

    @NotBlank(message="비밀번호는 필수입니다.")
    @Size(min=8, message="비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message="이름은 필수입니다.")
    private String name;
}

