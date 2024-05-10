package org.xtremebiker.jsfspring.view;

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;
import org.xtremebiker.jsfspring.model.Car;
import org.xtremebiker.jsfspring.repository.CarRepository;
import org.xtremebiker.jsfspring.service.CarService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
@Data
public class CarViewController implements Serializable
{
    private CarService carService;
    private List<Car> cars;
    private Car car;
    private Car carToUpdate;
    private Long updateCarId;
    private boolean openRightMenuForUpdate;

    @Autowired
    public void setCarService(CarService carService)
    {
        this.carService = carService;
    }

    public boolean isOpenRightMenuForUpdate() {
        return openRightMenuForUpdate;
    }

    public void setOpenRightMenuForUpdate(boolean openRightMenu) {
        this.openRightMenuForUpdate = openRightMenu;
    }

    @PostConstruct
    public void init()
    {
        openRightMenuForUpdate = false;
        cars = carService.getAllCars();
        resetCar();
    }

    public String gotoCreateCar()
    {
        resetCar();
        return "create-car.xhtml?faces-redirect=true";
    }

    public String gotoMainPage()
    {
        setOpenRightMenuForUpdate(false);
        resetCar();
        return "index.xhtml?faces-redirect=true";
    }


    public String gotoUpdateCar(long id)
    {
        updateCarId = id;
        Optional<Car> optionalCar = carService.getCarById(updateCarId);
        if (optionalCar.isPresent())
        {
            carToUpdate = optionalCar.get();
            setOpenRightMenuForUpdate(true);
            return "index.xhtml?faces-redirect=true";
        }
        else
        {
            return "index.xhtml?faces-redirect=true";
        }

    }


    public String saveCar()
    {
        carService.saveCar(car);
        cars = carService.getAllCars();
        resetCar();
        return "index.xhtml?faces-redirect=true";
    }

    public String updateCar()
    {
        Optional<Car> carExist = carService.getCarById(updateCarId);
        if (carExist.isPresent())
        {
            Car existingCar = carExist.get();
            existingCar.setBrand(carToUpdate.getBrand());
            existingCar.setColor(carToUpdate.getColor());
            existingCar.setYear(carToUpdate.getYear());
            carService.saveCar(existingCar);
            cars = carService.getAllCars();
            System.out.println("Updated Car: " + existingCar.toString());
            return "index.xhtml?faces-redirect=true";
        }
        else
        {
            System.out.println("Car with ID " + updateCarId + " not found.");
            return null;
        }
    }

    public void deleteCar(Long carId)
    {
        carService.deleteCarById(carId);
        cars = carService.getAllCars();
    }

    private void resetCar()
    {
        car = new Car();
        carToUpdate = new Car();
    }
}
