package com.desktopapp.repositories;

import com.desktopapp.model.Product;

public interface ProductRepository {
    void update(Product product);
    void delete(Product product);
}
