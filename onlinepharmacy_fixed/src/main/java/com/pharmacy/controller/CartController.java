package com.pharmacy.controller;

import com.pharmacy.model.Cart;
import com.pharmacy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add to cart
    @PostMapping("/add")
    public Cart addToCart(@RequestParam String username,
                          @RequestParam int drugId,
                          @RequestParam int quantity) {
        return cartService.addToCart(username, drugId, quantity);
    }

    // View cart
    @GetMapping("/view")
    public List<Cart> viewCart(@RequestParam String username) {
        return cartService.getCartByUser(username);
    }

    // Clear cart
    @DeleteMapping("/clear")
    public String clearCart(@RequestParam String username) {
        cartService.clearCart(username);
        return "Cart cleared for user: " + username;
    }
}
