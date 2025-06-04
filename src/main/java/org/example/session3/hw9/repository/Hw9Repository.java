package org.example.session3.hw9.repository;

import org.example.session3.hw9.entity.Hw9Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Hw9Repository extends JpaRepository<Hw9Entity, Long> {
    // 특정 사용자 ID로 사용자를 조회하는 메서드
    Optional<Hw9Entity> findByUsername(String username);
}

