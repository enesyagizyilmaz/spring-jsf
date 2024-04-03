package org.xtremebiker.jsfspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>
{

}
