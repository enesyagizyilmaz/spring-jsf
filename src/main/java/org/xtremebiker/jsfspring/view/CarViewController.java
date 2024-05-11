package org.xtremebiker.jsfspring.view;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.xtremebiker.jsfspring.model.Car;
import org.xtremebiker.jsfspring.service.CarService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
@Data
public class CarViewController implements Serializable
{
    @Autowired
    CarService carService;
    private List<Car> cars;
    private Car car;
    private Car carToUpdate;
    private Long updateCarId;
    private boolean openRightMenuForUpdate;
    private boolean openRightMenuForCreate;
    private long selectedCarId;
    private String yearForSearch;
    private String brandForSearch;
    private String colorForSearch;

    public void setSelectedCarId(long selectedCarId)
    {
        this.selectedCarId = selectedCarId;
        deleteCar();
    }

    public long getSelectedCarId() {return selectedCarId;}

    public boolean isOpenRightMenuForUpdate() {
        return openRightMenuForUpdate;
    }

    public void setOpenRightMenuForUpdate(boolean openRightMenu) {
        this.openRightMenuForUpdate = openRightMenu;
    }

    public boolean isOpenRightMenuForCreate() {return openRightMenuForCreate;}

    public void setOpenRightMenuForCreate(boolean openRightMenuForCreate)
    {
        this.openRightMenuForCreate = openRightMenuForCreate;
    }

    @PostConstruct
    public void init()
    {
        openRightMenuForCreate = false;
        openRightMenuForUpdate = false;
        cars = carService.getAllCars();
        resetCar();
    }

    public String search()
    {
        int year = 0;
        if (!yearForSearch.isEmpty())
        {
            year = Integer.parseInt(yearForSearch);
        }
        boolean yearNotEmpty = !yearForSearch.isEmpty();
        boolean brandNotEmpty = !brandForSearch.isEmpty();
        boolean colorNotEmpty = !colorForSearch.isEmpty();

        if (yearNotEmpty && brandNotEmpty && colorNotEmpty) {
            cars = carService.findCarsByYearAndBrandAndColor(year, brandForSearch, colorForSearch);
        } else if (yearNotEmpty && brandNotEmpty) {
            cars = carService.findCarsByYearAndBrand(year, brandForSearch);
        } else if (yearNotEmpty && colorNotEmpty) {
            cars = carService.findCarsByYearAndColor(year, colorForSearch);
        } else if (brandNotEmpty && colorNotEmpty) {
            cars = carService.findCarsByBrandAndColor(brandForSearch, colorForSearch);
        } else if (yearNotEmpty) {
            cars = carService.findCarsByYear(year);
        } else if (brandNotEmpty) {
            cars = carService.findCarsByBrand(brandForSearch);
        } else if (colorNotEmpty) {
            cars = carService.findCarsByColor(colorForSearch);
        } else {
            cars = carService.getAllCars();
        }
        return "index.xhtml?faces-redirect=true";
    }


    public String gotoCreateCar()
    {
        resetCar();
        setOpenRightMenuForCreate(true);
        setOpenRightMenuForUpdate(false);
        return "index.xhtml?faces-redirect=true";
    }

    public String gotoMainPage()
    {
        setOpenRightMenuForUpdate(false);
        setOpenRightMenuForCreate(false);
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
            setOpenRightMenuForCreate(false);
        }
        return "index.xhtml?faces-redirect=true";
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

    public void deleteCar()
    {
        carService.deleteCarById(selectedCarId);
        cars = carService.getAllCars();
    }

    private void resetCar()
    {
        car = new Car();
        carToUpdate = new Car();
    }
}
