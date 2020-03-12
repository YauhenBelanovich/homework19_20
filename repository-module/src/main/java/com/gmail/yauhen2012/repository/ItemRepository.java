package com.gmail.yauhen2012.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gmail.yauhen2012.repository.model.Item;

public interface ItemRepository extends GenericRepository<Item>{

    List<Item> findAllItemsWithCompletedStatus(Connection connection) throws SQLException;

    Item findItemById(Integer id, Connection connection) throws SQLException;

}
