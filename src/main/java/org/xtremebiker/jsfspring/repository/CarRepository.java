package org.xtremebiker.jsfspring.repository;

import org.checkerframework.checker.units.qual.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>
{
    void deleteById(long id);

    Car findById(long id);

    List<Car> findByYearAndBrandAndColor(int year, String brand, String color);

    List<Car> findByBrandAndColor(String brand, String color);

    List<Car> findByYearAndBrand(int year, String brand);

    List<Car> findByYearAndColor(int year, String color);

    List<Car> findByYear(int year);

    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);
}
