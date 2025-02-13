package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.UserDTO;
import com.example.andaluciaskills.dto.UserRegisterDTO;
import com.example.andaluciaskills.mapper.UserMapper;
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

@Service
public class UserService implements UserServiceBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> obtenerTodos() {
        return userMapper.toDTOs(userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> obtenerPorId(Integer id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    @Override
    public UserDTO agregarUser(UserRegisterDTO userRegisterDTO) {
        // Verificar si la especialidad existe
        Especialidad especialidad = especialidadRepository.findById(userRegisterDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        // Convertir DTO a entidad
        User user = userMapper.toEntity(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encripta la contraseña
        user.setEspecialidad(especialidad); // Asignar la especialidad al usuario
        
        // Guardar el usuario
        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public void eliminarUser(Integer id) {
        userRepository.deleteById(id);
    }
}
