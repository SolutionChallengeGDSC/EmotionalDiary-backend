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

### 주의 사항
1. **env**파일과 **application.yml** 파일은 **gitignore**처리하였기 때문에 직접 작성 혹은 복붙 해야합니다.   
2. **env**파일과 **application.yml** 파일은 **노션**에 공유했으니 확인부탁드리겠습니다.
3. **env** 파일 위치는 **docker-compose.yml**과 같은 위치이어야 합니다.
4. **application.yml** 파일 위치는 **"src/main/resources"**입니다.
