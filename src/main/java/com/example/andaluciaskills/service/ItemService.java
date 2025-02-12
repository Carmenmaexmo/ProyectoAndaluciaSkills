package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.ItemDTO;
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

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> obtenerTodos() {
        return itemRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemDTO> obtenerPorId(Integer id) {
        return itemRepository.findById(id)
                .map(this::convertirADTO);
    }

    @Override
    public ItemDTO agregarItem(ItemDTO itemDTO) {
        Item item = convertirAEntidad(itemDTO);
        Item itemGuardado = itemRepository.save(item);
        return convertirADTO(itemGuardado);
    }

    @Override
    public void eliminarItem(Integer id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO convertirADTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setIdItem(item.getIdItem());
        itemDTO.setDescripcion(item.getDescripcion());
        itemDTO.setPeso(item.getPeso());
        itemDTO.setGradosConsecucion(item.getGradosConsecucion());
        return itemDTO;
    }

    private Item convertirAEntidad(ItemDTO itemDTO) {
        Item item = new Item();
        item.setIdItem(itemDTO.getIdItem());
        item.setDescripcion(itemDTO.getDescripcion());
        item.setPeso(itemDTO.getPeso());
        item.setGradosConsecucion(itemDTO.getGradosConsecucion());
        return item;
    }
}