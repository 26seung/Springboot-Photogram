package com.cos.photogramstart.config.oauth;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
// 그냥 사용시 SecurityConfig 에 bean 으로 등록한 BCryptPasswordEncoder 보다 OAuth2DetailsService 가 먼저 실행되어 문제가 발생
    // 로직 안에 new 를 사용해서 사용은 가능 IOC 는 오류 발생
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("OAuth2 서비스 시작");

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String , Object> userInfo = oAuth2User.getAttributes();

        String username = "facebook_" + (String)userInfo.get("id");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String email = (String)userInfo.get("email");       //   Object 타입이기 때문에 String 으로 다운 캐스팅
        String name = (String)userInfo.get("name");

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .role("ROLE_USER")
                .build();

        return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());
        }else {
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
        }

    }
}
