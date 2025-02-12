package com.listadecompras.listacompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.listadecompras.listacompras.entity.user;

public interface userRepository extends JpaRepository <user,Long> {}
