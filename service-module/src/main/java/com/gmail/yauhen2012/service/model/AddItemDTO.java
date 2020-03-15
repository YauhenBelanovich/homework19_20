package com.gmail.yauhen2012.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddItemDTO {

    @NotNull(message = "is required")
    @Size(min = 3, max = 40, message = "three characters or more")
    private String name;

    @NotNull(message = "is required")
    private ItemStatusEnum status;

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

    @Override
    public String toString() {
        return "AddItemDTO{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

}
