package com.gmail.yauhen2012.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddItemDTO {

    @NotNull(message = "is required")
    @Size(min = 3, max = 40, message = "three characters or more")
    private String name;

    @NotNull
    @Size(min = 5, max = 9, message = "READY or COMPLETED")
    private ItemEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemEnum getStatus() {
        return status;
    }

    public void setStatus(ItemEnum status) {
        this.status = status;
    }

}
