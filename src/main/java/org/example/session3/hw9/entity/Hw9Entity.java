package org.example.session3.hw9.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.session3.apiPayload.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

@Entity
@Table(name = "Hw9_Signup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hw9Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이디 (Primary Key)

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username; // 아이디

    @Column(name = "password", length = 255, nullable = false)
    private String password; // 비밀번호

    @Column(name = "name", length = 20, nullable = false)
    private String name; // 이름

}
