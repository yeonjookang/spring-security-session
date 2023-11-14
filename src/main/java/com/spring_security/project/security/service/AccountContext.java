package com.spring_security.project.security.service;

import com.spring_security.project.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    /**
     *  인증된 사용자의 상세정보를 담고 있는 스프링 시큐리티의 User 클래스를 상속받는다.
     *  (User 클래스는 UserDetails 인터페이스의 구현체)
     *  User 클래스의 기본 정보 외에도 추가적인 정보를 포함할 수 있다.
     *  즉, AccountContext를 사용하여 UserDetails 인터페이스의 구현을 확장하고 유연하게 만들 수 이싿.
     */

    private final Account account;

    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account=account;
    }

    public Account getAccount() {
        return account;
    }
}
