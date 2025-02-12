package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.UserDTO;
import com.example.andaluciaskills.dto.UserRegisterDTO;
import com.example.andaluciaskills.model.Especialidad;
import com.example.andaluciaskills.model.User;
import com.example.andaluciaskills.repository.UserRepository;
import com.example.andaluciaskills.repository.EspecialidadRepository;
import com.example.andaluciaskills.service.base.UserServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> obtenerTodos() {
        return userRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> obtenerPorId(Integer id) {
        return userRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    public UserDTO agregarUser(UserRegisterDTO userRegisterDTO) {
        // Verificar si la especialidad existe
        Especialidad especialidad = especialidadRepository.findById(userRegisterDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        // Crear el usuario
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword())); // Encripta la contraseña
        user.setRole(userRegisterDTO.getRole());
        user.setEspecialidad(especialidad); // Asignar la especialidad al usuario
        
        // Guardar el usuario
        user = userRepository.save(user);

        return convertirADTO(user);
    }

    @Override
    public void eliminarUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Convertir User a UserDTO
    private UserDTO convertirADTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
