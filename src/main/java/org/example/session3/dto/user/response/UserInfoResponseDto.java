package org.example.session3.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDto {
    private String userId;
    private String name;
    private String profileImage;
}
