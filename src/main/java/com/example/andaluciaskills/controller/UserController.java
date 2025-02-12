package com.example.andaluciaskills.controller;

import com.example.andaluciaskills.dto.UserDTO;
import com.example.andaluciaskills.dto.UserRegisterDTO;
import com.example.andaluciaskills.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> listar() {
        return userService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> obtener(@PathVariable Integer id) {
        return userService.obtenerPorId(id);
    }

   @PostMapping
    public UserDTO agregar(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.agregarUser(userRegisterDTO);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        userService.eliminarUser(id);
    }
}