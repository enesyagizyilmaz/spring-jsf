package org.xtremebiker.jsfspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.model.Car;
import org.xtremebiker.jsfspring.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService
{
    CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars()
    {
        return carRepository.findAll();
    }

    public List<Car> findCarsByYearAndBrandAndColor(int year, String brand, String color)
    {
        return carRepository.findByYearAndBrandAndColor(year, brand, color);
    }

    public List<Car> findCarsByBrandAndColor(String brand, String color)
    {
        return carRepository.findByBrandAndColor(brand, color);
    }

    public List<Car> findCarsByYearAndBrand(int year, String brand)
    {
        return carRepository.findByYearAndBrand(year, brand);
    }

    public List<Car> findCarsByYearAndColor(int year, String color)
    {
        return carRepository.findByYearAndColor(year, color);
    }

    public List<Car> findCarsByYear(int year)
    {
        return carRepository.findByYear(year);
    }

    public List<Car> findCarsByBrand(String brand)
    {
        return carRepository.findByBrand(brand);
    }

    public List<Car> findCarsByColor(String color)
    {
        return carRepository.findByColor(color);
    }

    public Optional<Car> getCarById(Long id)
    {
        return carRepository.findById(id);
    }

    public void saveCar(Car car)
    {
        carRepository.save(car);
    }

    @Transactional
    public void deleteCarById(Long id)
    {
        carRepository.deleteById(id);
    }
}
