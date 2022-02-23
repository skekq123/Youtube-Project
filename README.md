# Youtube-Project
# 유투브 클론코딩

# 팀원 정보
![팀원](https://user-images.githubusercontent.com/93329407/155321170-b59d0f13-e9f5-4cd2-a103-c8e3f6a78a5d.png)

# 1. 프로젝트 개요(Project).🎵

### 프로젝트 소개(Introduction).
    
유튜브 클론코딩

# 2. 개발환경( Development).❤️

### 와이어 프레임(Wireframe)

![팀원3](https://user-images.githubusercontent.com/93329407/155322150-a1cdfadc-e08c-4fb4-b24c-b484a4563fcc.png)
![팀원1](https://user-images.githubusercontent.com/93329407/155321881-be870521-06a3-447d-8771-391c62f0f864.png)
![팀원2](https://user-images.githubusercontent.com/93329407/155321899-6df41873-195c-4346-9e16-4e40db130fde.png)

## API 설계(API Table)

![111111](https://user-images.githubusercontent.com/93329407/155322371-ca154bd6-305b-4111-bf83-c6cc59e1a239.png)

## 테이블 도표(Entity Diagram)

![도표](https://user-images.githubusercontent.com/45589210/155321852-1238f21d-d341-4d81-920a-f4d9d35f22a3.png)

# 3. 기술 스택(Tech Stack).🙅🏻‍♂️

## 핵심기능(Function).

- 로그인, 회원가입
    - JWT를 이용하여 로그인과 회원가입을 구현.
    - 아이디는 3글자 이상 10글자 숫자,영문자 소/대문자로 구성.
    - 비밀번호는 4글자 이상으로 구성해야 합니다.
    - 유저프로필 이미지 등록.
    - 아이디를 이미 사용 중이면 회원가입이 불가능합니다.
- 메인 페이지
    - 전체 게시글을 조회 합니다.
    - 마이페이지로 이동할 수 있습니다.
    - 로그아웃을 구현했습니다.
    - 게시글을 눌러 상세페이지로 이동할 수 있습니다.
    - 등록한 이미지목록이 보이도록 구현했습니다.
    - 페이지 우측 하단에 버튼을 눌러 게시글 작성 페이지로 이동합니다.
- 마이페이지.
    - 본인이 작성한 글만 조회 합니다.
    - 로그아웃을 구현했습니다.
    - 게시글을 눌러 상세페이지로 이동할 수 있습니다.
    - 페이지 우측 하단에 버튼을 눌러 게시글 작성 페이지로 이동합니다.
    - 등록한 이미지목록이 보이도록 구현했습니다.
- 상세페이지(CRUD)
    - 로그아웃을 구현했습니다.
    - 마이페이지로 이동할 수 있습니다.
    - 페이지 우측 하단에 버튼을 눌러 게시글 작성 페이지로 이동합니다.
    - JWT를 이용하여 삭제 기능을 구현하였습니다. 삭제 후 메인페이지로 이동합니다.
    - 본인 글 수정버튼을 눌러 수정페이지로 이동합니다.
    - 댓글 작성 기능을 구현하였습니다.
    - 댓글 삭제 기능을 구현하였습니다.
- 수정페이지
    - 로그아웃을 구현했습니다.
    - 게시글을 눌러 상세페이지로 이동할 수 있습니다.
    - 페이지 우측 하단에 버튼을 눌러 게시글 작성 페이지로 이동합니다.
    - JWT를 이용하여 본인 글 수정버튼을 눌러 수정페이지로 이동합니다.
- 글작성 페이지
    - 로그아웃을 구현했습니다.
    - 마이페이지로 이동할 수 있습니다.
    - 이모티콘기능을 구현했습니다.
    - 태그기능을 구현했습니다.
    - 게시글을 공개 / 비공배(마이페이지)로 체크하는 기능을 구현했습니다.
    - 작성기능을 구현했습니다.  작성후 메인페이지로 이동합니다.

## 개발도구(Tools).

![기술](https://user-images.githubusercontent.com/97504973/154630912-63128979-7275-444f-af34-583de69a407c.png)

# 4. 데모영상 및 개발노션.♣️

Demo link : https://youtu.be/ey0leGmB97Y

Notion link : www.notion.so/Dayily-Diary-e3d8f48497bf4eb28d533bdbbdd2704f

# 5. 트러블 슛팅(Trouble Shooting).🚶🏻‍♂️

1. CORS 정책으로 인한 접속문제.
- (1) 에러 내용
    
    ![https://user-images.githubusercontent.com/87135478/145666395-7f840620-48a8-43a9-b371-ca1b0a26fee7.png](https://user-images.githubusercontent.com/87135478/145666395-7f840620-48a8-43a9-b371-ca1b0a26fee7.png)
    
    (2) 해결
    
    [//WebSecureConfig.java](https://websecureconfig.java/) 파일 내, CORS 관련 설정 추가
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http
    .cors()
    .and()
    .csrf()
    .disable();
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    
    ```
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.addExposedHeader("Authorization");
    configuration.setAllowCredentials(true); // 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있도록 함
    configuration.addAllowedOriginPattern("*");
    
    source.registerCorsConfiguration("/**", configuration);
    return source;
    }




3. Entity에 ImageUrl List 추가하는 문제**
    - 바로 삽입할 수 가 없어서 ImageUrl table을 따로 만들고 Diary와 연관관계를 맺어 controller와 service에서 다시 request값을 리스트화 시켜 저장하는 방법을 사용하였다.

4. 용량이 큰 이미지 파일은 업로드 되지 않은 문제.**

# 6. 개인회고록(자유롭게 작성)💬.
이규진 : https://rbwls44.tistory.com/68?category=1018562
