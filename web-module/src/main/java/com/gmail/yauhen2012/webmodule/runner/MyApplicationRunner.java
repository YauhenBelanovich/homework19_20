package com.gmail.yauhen2012.webmodule.runner;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.gmail.yauhen2012.service.impl.MyUserDetailsService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.ItemDTO;
import com.gmail.yauhen2012.service.model.ItemStatusEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;
    private final MyUserDetailsService myUserDetailsService;

    public MyApplicationRunner(ItemService itemService, MyUserDetailsService myUserDetailsService) {
        this.itemService = itemService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        AddItemDTO addItemDTO1 = new AddItemDTO();
        addItemDTO1.setName("item1");
        addItemDTO1.setStatus(ItemStatusEnum.COMPLETED);
        itemService.addItem(addItemDTO1);

        AddItemDTO addItemDTO2 = new AddItemDTO();
        addItemDTO2.setName("item2");
        addItemDTO2.setStatus(ItemStatusEnum.COMPLETED);
        itemService.addItem(addItemDTO2);

        AddItemDTO addItemDTO3 = new AddItemDTO();
        addItemDTO3.setName("item3");
        addItemDTO3.setStatus(ItemStatusEnum.READY);
        itemService.addItem(addItemDTO3);

//        itemService.deleteCompletedItems();
//        itemService.updateStatusById(2);

//        List<ItemDTO> itemList =  itemService.findAll();
//        itemList.forEach(System.out::println);

        List<ItemDTO> completedItems = itemService.findAllCompletedItems();
        completedItems.forEach(System.out::println);

    }

}
