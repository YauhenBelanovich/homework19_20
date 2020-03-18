package com.gmail.yauhen2012.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.model.Item;
import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import com.gmail.yauhen2012.repository.model.ItemStatusEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public int addItem(AddItemDTO addItemDTO) {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Item item = convertItemDTOToDatabaseItem(addItemDTO);
                Item addedItem = itemRepository.add(item, connection);
                connection.commit();
                return addedItem.getId();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public void deleteItemsByStatus(String status) {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                itemRepository.deleteItemsByStatus(connection, status);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateStatusById(Integer id) {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Item item = itemRepository.findItemById(id, connection);
                if (item != null) {
                    String newStatus;
                    if ((item.getStatus().equals(ItemStatusEnum.READY))) {
                        newStatus = ItemStatusEnum.COMPLETED.name();
                    } else {
                        newStatus = ItemStatusEnum.READY.name();
                    }
                    itemRepository.update(id, newStatus, connection);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<ItemDTO> findAll() {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Item> itemList = itemRepository.findAll(connection);
                List<ItemDTO> itemDTOList = itemList.stream()
                        .map(this::convertDatabaseObjectToDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return itemDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ItemDTO> findAllCompletedItems() {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Item> itemList = itemRepository.findAllItemsWithCompletedStatus(connection);
                List<ItemDTO> itemDTOList = itemList.stream()
                        .map(this::convertDatabaseObjectToDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return itemDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public ItemDTO findItemById(Integer id) {
        try (Connection connection = itemRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Item item = itemRepository.findItemById(id, connection);
                ItemDTO itemDTO = convertDatabaseObjectToDTO(item);
                connection.commit();
                return itemDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private ItemDTO convertDatabaseObjectToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setStatus(item.getStatus());
        return itemDTO;
    }

    private Item convertItemDTOToDatabaseItem(AddItemDTO addItemDTO) {
        Item item = new Item();
        item.setName(addItemDTO.getName());
        item.setStatus(addItemDTO.getStatus());
        return item;
    }

}
