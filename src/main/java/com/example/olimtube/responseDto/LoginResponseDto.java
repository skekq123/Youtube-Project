package com.example.olimtube.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
    //로그인 되었을때 리턴되는객체들 토큰발행
    private String token;
    private String username;
    private String profile;
    private boolean admin = false;
    private String adminToken = "";
}
