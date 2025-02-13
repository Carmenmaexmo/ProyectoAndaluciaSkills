package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.ItemDTO;
import com.example.andaluciaskills.mapper.ItemMapper;
import com.example.andaluciaskills.model.Item;
import com.example.andaluciaskills.repository.ItemRepository;
import com.example.andaluciaskills.service.base.ItemServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService implements ItemServiceBase {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDTO> obtenerTodos() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemDTO> obtenerPorId(Integer id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDTO);
    }

    @Override
    public ItemDTO agregarItem(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        Item itemGuardado = itemRepository.save(item);
        return itemMapper.toDTO(itemGuardado);
    }

    @Override
    public void eliminarItem(Integer id) {
        itemRepository.deleteById(id);
    }
}