package com.gmail.yauhen2012.service;

import java.util.List;

import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;

public interface ItemService {

    void addItem(AddItemDTO addItemDTO);

    void deleteCompletedItems();

    void updateStatusById(Integer id);

    List<ItemDTO> findAll();

    List<ItemDTO> findAllCompletedItems();

}
