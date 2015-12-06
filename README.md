# spring-oauth-tokenenhancer-test

Spring Security Oauth2 test with token enhancer for issue [#652](https://github.com/spring-projects/spring-security-oauth/issues/652)

This project is configured using Spring Boot and can be run with:
```
   mvn spring-boot:run 
```


## Test case
```
   $ curl -u client:secret -X POST http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=myuser&password=mypassword&grant_type=password"

   $ curl -u client:secret -X POST http://localhost:8080/oauth/check_token -H "Accept: application/json" -d "token={access_token}"
```