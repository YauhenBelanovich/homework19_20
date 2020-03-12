package com.gmail.yauhen2012.webmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;

    public ItemController(ItemService itemService) {this.itemService = itemService;}

    @GetMapping("/items")
    public String getItemsList(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAll();
        model.addAttribute("itemList", itemDTOList);

        List<ItemDTO> completedItems = itemService.findAllCompletedItems();
        model.addAttribute("completedItems", completedItems);
        logger.debug("Get ItemList method");
        return "items";
    }

}
