package com.spring_security.project.security.service;

import com.spring_security.project.domain.Account;
import com.spring_security.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * 스프링 시큐리티 인증 과정에서, 사용자가 로그인을 시도할 때 제공한 사용자 이름은 CustomUserDetailsService에 전달하고,
     * 이 서비스는 해당 사용자에 대한 정보를 데이터베이스나 다른 저장소에서 조회한다.
     * 그리고 이 정보를 UserDetails 객체로 반환한다. (사용자 이름, 비밀번호, 권한, 계정 상태 등의 정보 포함)
     */

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username);

        if(account == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
