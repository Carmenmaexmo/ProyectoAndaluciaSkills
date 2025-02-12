package com.example.andaluciaskills.service.base;

import com.example.andaluciaskills.dto.ItemDTO;
import java.util.List;
import java.util.Optional;

public interface ItemServiceBase {
    List<ItemDTO> obtenerTodos();
    Optional<ItemDTO> obtenerPorId(Integer id);
    ItemDTO agregarItem(ItemDTO itemDTO);
    void eliminarItem(Integer id);
}