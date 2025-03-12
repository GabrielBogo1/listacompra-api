package com.listadecompras.listacompras.dto;

import com.listadecompras.listacompras.enums.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
