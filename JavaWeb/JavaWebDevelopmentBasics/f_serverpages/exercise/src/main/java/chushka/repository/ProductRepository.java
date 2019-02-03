package chushka.repository;

import chushka.domain.entities.Product;

public interface ProductRepository extends GenericRepository<Product, String> {

    Product findByName(String name);
}
