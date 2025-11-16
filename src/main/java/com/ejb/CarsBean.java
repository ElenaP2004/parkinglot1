package com.ejb;

import com.common.CarDto;
import entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars) {
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setLicensePlate(car.getLicensePlate());
            carDto.setParkingSpot(car.getParkingSpot());

            // Verifică dacă owner există înainte de a accesa username
            if (car.getOwner() != null) {
                carDto.setOwnerName(car.getOwner().getUsername());
            } else {
                carDto.setOwnerName("Unknown");
            }

            carDtos.add(carDto);
        }

        return carDtos;
    }
}