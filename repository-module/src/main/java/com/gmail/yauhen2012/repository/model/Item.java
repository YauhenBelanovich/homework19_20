package com.gmail.yauhen2012.repository.model;

public class Item {

    private Integer id;
    private String name;
    private ItemStatusEnum status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ItemStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

}
