package org.example.session3.service.user;

import org.example.session3.dto.user.request.PasswordChangeRequestDto;
import org.example.session3.dto.user.request.UserLoginRequestDTO;
import org.example.session3.dto.user.request.UserSignupRequestDTO;
import org.example.session3.dto.user.response.UserLoginResponseDTO;
import org.example.session3.entitiy.user.User;
import org.example.session3.repository.user.UserRepository;
import org.example.session3.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 정의한 적이 없는 애들은 Security에서 제공하는 인터페이스

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔑 회원가입 (비밀번호 암호화 후 저장)
    public void signup(UserSignupRequestDTO requestDto) {
        User user = new User();
        user.setUserId(requestDto.getUserId());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword
                ()));
        user.setName(requestDto.getName());
        user.setProfileImage(requestDto.getProfileImage());
        userRepository.save(user);
    }
    // 🔐 로그인 (ID/PW 검증 후 JWT 발급)
    public UserLoginResponseDTO login(UserLoginRequestDTO requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + requestDto.getUserId()));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = jwtTokenProvider.createToken(user.getUserId());
        return new UserLoginResponseDTO(user.getUserId(), token);
    }
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    // 사용자 조회 메서드 추가
    // userId로 User 객체를 조회하는 함수 --> User 타입을 반환하는 게 당연하다.
    // login은 사용자 인증 후에 토큰 생성까지 해서 DTO로 묶어 반환하는 것.
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 사용자가 없습니다. : " + userId));
    }

    // 비밀번호 변경
    public void changePassword(String userId, PasswordChangeRequestDto requestDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 기존 비밀번호 확인
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호와 일치하지 않습니다.");
        }

        // 변경할 비밀번호가 일치하는지 확인
        if (!requestDto.getNewPassword().equals(requestDto.getNewPasswordCheck())) {
            throw new IllegalArgumentException("새 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 변경
        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
    }
}

