package com.example.andaluciaskills.service.base;

import com.example.andaluciaskills.dto.UserDTO;
import com.example.andaluciaskills.dto.UserRegisterDTO;

import java.util.List;
import java.util.Optional;

public interface UserServiceBase {
    List<UserDTO> obtenerTodos();
    Optional<UserDTO> obtenerPorId(Integer id);
    UserDTO agregarUser(UserRegisterDTO userregisterDTO);
    void eliminarUser(Integer id);
}
