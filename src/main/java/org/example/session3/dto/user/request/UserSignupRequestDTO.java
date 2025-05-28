package org.example.session3.dto.user.request;

import lombok.Data;

@Data
public class UserSignupRequestDTO {
    private String userId;
    private String password;
    private String name;
    private String profileImage;
}
