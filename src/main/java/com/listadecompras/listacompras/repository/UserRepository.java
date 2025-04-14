package com.listadecompras.listacompras.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.listadecompras.listacompras.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,String> {
    Optional<UserDetails> findByEmail(String email);
}
