package org.example.session3.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.session3.apiPayload.code.SuccessStatus;
import org.example.session3.apiPayload.dto.ApiResponse;
import org.example.session3.apiPayload.exception.GeneralException;
import org.example.session3.dto.user.request.PasswordChangeRequestDto;
import org.example.session3.dto.user.request.UserLoginRequestDTO;
import org.example.session3.dto.user.request.UserSignupRequestDTO;
import org.example.session3.dto.user.response.UserInfoResponseDto;
import org.example.session3.dto.user.response.UserLoginResponseDTO;
import org.example.session3.entitiy.user.User;
import org.example.session3.security.JwtTokenProvider;
import org.example.session3.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDTO requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO requestDto) {
        UserLoginResponseDTO response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    // 사용자 조회
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponseDto> getInfo(@RequestHeader("Authorization") String userToken) {
        String token = userToken.replace("Bearer ", ""); // Authorization 중 Bearer을 제외하고 순수 token만 담겠다.
                                        // Bearer을 "" 빈 문자열로 바꿔서 제거한다는 뜻!
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = jwtTokenProvider.getUserId(token);
        User user = userService.findByUserId(userId);

        return ResponseEntity.ok(new UserInfoResponseDto(
                user.getUserId(),
                user.getName(),
                user.getProfileImage()
        ));
    }

    @PostMapping("/password")
    public ApiResponse<?> changePassword(
            @RequestBody @Valid PasswordChangeRequestDto requestDto,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");

        // 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(token)) {
            return ApiResponse.onFailure("401", "유효하지 않은 토큰입니다.", "") ;
        }

        String userId = jwtTokenProvider.getUserId(token);

        userService.changePassword(userId, requestDto);
            return ApiResponse.of(SuccessStatus._OK, "비밀번호 변경!");
//        } catch (IllegalArgumentException e) {
//            return ApiResponse.onFailure("400", e.getMessage(), "");
//        } catch (GeneralException e) {
//            return ApiResponse.onFailure(
//                            e.getErrorStatus().getCode(),
//                            e.getErrorStatus().getMessage(),
//                            "");
//        }
    }
    }



