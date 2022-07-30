package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

// 통신할때 필요한 데이터를 담아두는 오브젝트  (Data Transfer Object)
// 도메인을 사용하게 되면 필요하지 않은 데이터까지 노출이 될 수 있는 우려가 있다.
// dto 를 사용하면 domain 을 캡슐화 할 수 있다.
@Data   // Getter, Setter
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
