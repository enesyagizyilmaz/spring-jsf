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
