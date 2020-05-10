package com.gmail.yauhen2012.service;

import java.util.List;

import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;

public interface ItemService {

    int addItem(AddItemDTO addItemDTO);

    void deleteItemsByStatus(String status);

    void updateStatusById(Integer id);

    List<ItemDTO> findAll();

    List<ItemDTO> findAllCompletedItems();

    ItemDTO findItemById(Integer id);

}
