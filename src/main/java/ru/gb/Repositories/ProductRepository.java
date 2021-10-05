package ru.gb.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gb.Domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT *  FROM homework7.products WHERE (price) > (SELECT MIN (price) FROM homework7.products)", nativeQuery = true)
    Iterable<Product> findMoreMinPrice();

    @Query(value = "SELECT *  FROM homework7.products WHERE (price) < (SELECT MAX (price) FROM homework7.products)", nativeQuery = true)
    Iterable<Product> findLessMaxPrice();

    @Query(value = "SELECT *  FROM homework7.products AS p WHERE p.price > :min AND p.price < :max", nativeQuery = true)
    Iterable<Product> findBetweenMinMax(@Param("min") int min, @Param("max") int max);
}
