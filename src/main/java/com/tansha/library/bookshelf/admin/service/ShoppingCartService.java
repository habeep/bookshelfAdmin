package com.tansha.library.bookshelf.admin.service;

import com.tansha.library.bookshelf.admin.exceptions.NotEnoughProductsInStockException;
import com.tansha.library.bookshelf.admin.model.Book;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Book book);

    void removeProduct(Book book);

    Map<Book, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
