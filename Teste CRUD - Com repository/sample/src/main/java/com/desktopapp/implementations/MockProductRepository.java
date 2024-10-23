package com.desktopapp.implementations;

import java.util.ArrayList;
import java.util.List;

import com.desktopapp.model.Product;
import com.desktopapp.repositories.ProductRepository;

public class MockProductRepository implements ProductRepository {

    List<Product> data = new ArrayList<>();

    public MockProductRepository()
    {
        Product p = new Product();
        p.setName("pantufa da dora aventureira");
        p.setDescricao("Diga pantufa");
        p.setValorUnitario("100");
        p.setId(1l);
        data.add(p);
    }


    @Override
    public void update(Product product) {
        var prod = data.stream()
            .filter(p -> p.getId() == product.getId())
            .findFirst();
        
        if (!prod.isPresent())
            return;
        
        var findedProd = prod.get();
        findedProd.setName(product.getName());
    }

    @Override
    public void delete(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
