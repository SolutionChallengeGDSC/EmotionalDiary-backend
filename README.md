# Emotional Diary Backend
**Emotional Diary**의 **Backend**입니다.  
Spring Boot / MySql(Docker) / Data JPA를 사용합니다.

### Response
모든 리스폰스 객체의 형식을 통일화하였습니다. CommonResponse를 상속받은 3개의 Response를 쓰면됩니다.
해당 Response는 빌더 패턴으로 만들어졌습니다.
- ListResponse
- SingleResponse
- ErrorResponse

아래와 같이 사용하시면 됩니다.
```java
    @GetMapping(value = "/test")
    public final CommonResponse test() {
        return SingleResponse.<String>builder()
                .success(true)
                .status(200)
                .message("hi")
                .result("what the hell")
                .build();
    }
```

앞의 예시(SingleResponse)의 결과는 다음과 같습니다.

```json
{
  "success": true,
  "status": 200,
  "message": "hi",
  "result": "what the hell"
}
```
ListResponse를 사용하는 경우는 result의 List가 담기게 됩니다.
```json
{
  "success": true,
  "status": 200,
  "message": "hi",
  "result": [...]
}
```

### Error Handling
일단 기본적으로 **GlobalExceptionController**에서 처리 합니다. 자세한 처리 방식은 해당 컨트롤러를 참고하시면 됩니다.(혹은 저에게 물어봐주세요)   
전역으로 처리가 불필요한 CustomException 들은 해당 에러가 발생하는 객체내에서 처리하셔도 됩니다.

### 단위 테스트
단위 테스트는 일단 **서비스 레이어**에만 적용하도록 합니다. 테스트 코드 작성법은 **test** 폴더 내에서 확인하시길 바랍니다.    
테스트 작성시 **given / when / then** 패턴으로 진행하겠습니다.
- **given: 데이터가 준비**
- **when: 서비스 실행**
- **then: 서비스 검증**

### 주의 사항
1. **env**파일과 **application.yml** 파일은 **gitignore**처리하였기 때문에 직접 작성 혹은 복붙 해야합니다.   
2. **env**파일과 **application.yml** 파일은 **노션**에 공유했으니 확인부탁드리겠습니다.
3. **env** 파일 위치는 **docker-compose.yml**과 같은 위치이어야 합니다.
4. **application.yml** 파일 위치는 **src/main/resources**입니다.
