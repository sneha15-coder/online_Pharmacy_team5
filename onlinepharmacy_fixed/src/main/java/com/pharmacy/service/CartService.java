package com.pharmacy.service;

import com.pharmacy.model.Cart;
import com.pharmacy.model.Drug;
import com.pharmacy.model.User;
import com.pharmacy.repository.CartRepository;
import com.pharmacy.repository.DrugRepository;
import com.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DrugRepository drugRepo;

    public Cart addToCart(String username, int drugId, int quantity) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        Optional<Drug> optionalDrug = drugRepo.findById(drugId);

        if (optionalUser.isEmpty() || optionalDrug.isEmpty()) {
            return null; // Username or drug ID invalid
        }

        User user = optionalUser.get();
        Drug drug = optionalDrug.get();

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setDrug(drug);
        cart.setQuantity(quantity);

        return cartRepo.save(cart);
    }

    public List<Cart> getCartByUser(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return List.of();
        }
        return cartRepo.findByUser(optionalUser.get());
    }

    public void clearCart(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            List<Cart> items = cartRepo.findByUser(optionalUser.get());
            cartRepo.deleteAll(items);
        }
    }
}
