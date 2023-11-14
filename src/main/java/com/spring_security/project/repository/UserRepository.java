package com.spring_security.project.repository;

import com.spring_security.project.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account,Long> {
}
