package org.xtremebiker.jsfspring.view;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.xtremebiker.jsfspring.model.Car;
import org.xtremebiker.jsfspring.repository.CarRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ViewScoped
@Data
@RequestScoped
public class CarViewController
{
    private CarRepository carRepository;
    private List<Car> cars;
    private Car car;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostConstruct
    public void init()
    {
        car = new Car();
        cars = carRepository.findAll();
    }

    public String gotoCreateCar()
    {
        return "create-car.xhtml?faces-redirect=true";
    }

    public String saveCar()
    {
        System.out.println("baban");
        carRepository.save(car);
        cars = carRepository.findAll();
        car = new Car();
        return "index.xhtml?faces-redirect=true";
    }

    @Transactional
    public void deleteCar(Long carId)
    {
        System.out.println("anan");
        carRepository.deleteById(carId);
        cars = carRepository.findAll(); // Güncel listeyi tekrar yükleyin
    }

}
