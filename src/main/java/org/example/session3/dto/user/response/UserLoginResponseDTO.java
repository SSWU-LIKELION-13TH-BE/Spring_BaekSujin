package org.example.session3.dto.user.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {
    private String userId;
    private String token;
}
