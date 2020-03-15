package com.gmail.yauhen2012.webmodule.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemAPIController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;

    public ItemAPIController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object saveItem(@RequestBody @Valid AddItemDTO addItemDTO, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<String> errors = new ArrayList<>();
            for (ObjectError error : allErrors) {
                errors.add("ErrorType: " + error.getCode() + " message: " + error.getDefaultMessage());
            }
            return errors.toString();
        }
        logger.debug("POST API save Item method");
        int id = itemService.addItem(addItemDTO);
        return itemService.findItemById(id);
    }

    @GetMapping
    public List<ItemDTO> getItems() {
        logger.debug("Get API getItems method");
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable Integer id) {
        logger.debug("Get API getItem method");
        return itemService.findItemById(id);
    }

    @PutMapping("/{id}")
    public List<ItemDTO> updateItem(@PathVariable Integer id) {
        itemService.updateStatusById(id);
        logger.debug("PUT API updateItem method");
        return itemService.findAll();
    }

    @DeleteMapping("/{status}")
    public List<ItemDTO> deleteItem(@PathVariable String status) {
        itemService.deleteItemsByStatus(status);
        logger.debug("DELETE API deleteItem method");
        return itemService.findAll();
    }

}
