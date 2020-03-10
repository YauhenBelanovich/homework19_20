package com.gmail.yauhen2012.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.constant.RepositoryConstant;
import com.gmail.yauhen2012.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Item> implements ItemRepository {

    @Override
    public Item add(Item item, Connection connection) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO item (name, status) values (?,?)",
                Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating item failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating item failed, no ID obtained.");
                }
            }
            return item;

        }
    }

    @Override
    public List<Item> findAll(Connection connection) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, status FROM item"
        )) {
            List<Item> itemList = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = getItem(rs);
                    itemList.add(item);
                }
                return itemList;
            }
        }
    }

    @Override
    public int delete(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM item WHERE status=?"
        )
        ) {
            statement.setString(1, RepositoryConstant.COMPLETED);
            return statement.executeUpdate();
        }
    }

    @Override
    public int update(Integer id, String newStatus, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE item SET status=? WHERE id=?"
        )) {
            statement.setString(1, newStatus);
            statement.setInt(2, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public Item findItemById(Integer id, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name, status FROM item where id=?;"
                )
        ) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getItem(rs);
                }
                return null;
            }
        }
    }

    @Override
    public List<Item> findAllItemsWithCompletedStatus(Connection connection) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, status FROM item WHERE status=?"
        )) {
            statement.setString(1, RepositoryConstant.COMPLETED);
            List<Item> itemList = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = getItem(rs);
                    itemList.add(item);
                }
                return itemList;
            }
        }
    }

    private Item getItem(ResultSet rs) throws SQLException {

        Item item = new Item();
        Integer id = rs.getInt("id");
        item.setId(id);
        String name = rs.getString("name");
        item.setName(name);
        String status = rs.getString("status");
        item.setStatus(status);
        return item;
    }

}
