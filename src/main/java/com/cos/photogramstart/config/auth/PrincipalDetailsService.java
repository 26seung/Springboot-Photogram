package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service        // IOC
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername_(username): " + username);

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            return null;
        }else {
            return new PrincipalDetails(userEntity);    // SecurityContextHolder => Authentication 객체 내부에 담김.
        }
    }
}
