package com.tansha.library.bookshelf.admin.exceptions;

import com.tansha.library.bookshelf.admin.model.Book;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Book book) {
        super(String.format("Not enough %s products in stock. Only %d left", book.getBookId(), book.getIsbncode()));
    }

}
