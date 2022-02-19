package com.example.olimtube.controller;

import com.example.olimtube.model.User;
import com.example.olimtube.requestDto.LoginDto;
import com.example.olimtube.requestDto.SignupRequestDto;
import com.example.olimtube.responseDto.CheckIdResponseDto;
import com.example.olimtube.responseDto.LoginResponseDto;
import com.example.olimtube.responseDto.UserInfoResponseDto;
import com.example.olimtube.security.UserDetailsImpl;
import com.example.olimtube.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Api(tags = {"User"})
public class UserController {

    private final UserService userService;

    // 회원 가입 요청 처리
    @ApiOperation(value = "회원가입", notes = "회원가입요청")
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody SignupRequestDto requestDto) {
        User user = userService.registerUser(requestDto);
        return ResponseEntity.ok(user);
    }

    // ID 중복 체크.
    @ApiOperation(value = "ID 중복 체크", notes = "ID 중복 체크")
    @PostMapping("/checkId")
    public ResponseEntity<CheckIdResponseDto> checkId(@RequestBody SignupRequestDto requestDto){
        CheckIdResponseDto checkIdResponseDto = userService.checkId(requestDto);
        return ResponseEntity.ok(checkIdResponseDto);
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    // 유저정보 전달.
    @ApiOperation(value = "유저정보 전달.", notes = "유저정보 전달.")
    @PostMapping("/user")
    public ResponseEntity<UserInfoResponseDto> userInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserInfoResponseDto userInfoResponseDto = userService.userInfo(userDetails);
        return ResponseEntity.ok(userInfoResponseDto);
    }
}