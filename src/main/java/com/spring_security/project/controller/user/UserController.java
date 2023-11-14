package com.spring_security.project.controller.user;

import com.spring_security.project.domain.Account;
import com.spring_security.project.domain.AccountDto;
import com.spring_security.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/mypage")
    public String myPage() throws Exception{
        return "user/mypage";
    }

    @GetMapping("/users")
    public String showRegisterUserForm(){
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto){
        Account account = accountDto.toEntity();
        account.encodePwd(passwordEncoder.encode(account.getPassword()));
        userService.createUser(account);

        return "redirect:/";
    }
}
