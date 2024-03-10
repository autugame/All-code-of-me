package com.qfedu.shoppingcart.controller;

import com.qfedu.shoppingcart.service.ShoppingCartDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartDelController {
    @Autowired
    private ShoppingCartDelService shoppingCartDelService;
    @DeleteMapping("/shopping/del")
    public int delete(String cids) {
        return shoppingCartDelService.delShoppingCart(cids);
    }
}
