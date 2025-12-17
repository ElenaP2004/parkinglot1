package com.ejb;

import com.common.CarDto;
import com.common.CarPhotoDto;
import com.entities.Car;
import com.entities.CarPhoto;
import com.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery =entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

    }
    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> dtos = new ArrayList<>();
        for (Car car : cars) {
            CarDto dto = new CarDto(
                    car.getOwner().getUsername(),
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot()

            );
            dtos.add(dto);
        }
        return dtos;
    }
    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        car.setOwner(user);
        user.getCars().add(car);
        entityManager.persist(car);
    }

    public CarDto findById(Long id){
        LOG.info("findById");
        Car car = entityManager.find(Car.class, id);
        CarDto dto = new CarDto(
                car.getOwner().getUsername(),
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot()
        );

        return dto;
    }
    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");
        Car car = entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser = car.getOwner();
        oldUser.getCars().remove(car);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
    }
    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");
        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            if (car != null) {
                User owner = car.getOwner();
                if (owner != null) {
                    owner.getCars().remove(car);
                }
                entityManager.remove(car);
            }
        }
    }
    public void addPhotoToCar(Long carId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToCar");
        CarPhoto photo = new CarPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Car car = entityManager.find(Car.class, carId);
        if (car.getPhoto() != null) {
            entityManager.remove(car.getPhoto());
        }
        car.setPhoto(photo);
        photo.setCar(car);
        entityManager.persist(photo);
    }
    public CarPhotoDto findPhotoByCarId(Integer carId) {
        List<CarPhoto> photos = entityManager
                .createQuery("SELECT p FROM CarPhoto p where p.car.id = :id", CarPhoto.class)
                .setParameter("id", carId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        CarPhoto photo = photos.get(0);
        return new CarPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                photo.getFileContent());
    }
    public int getFreeParkingSpots() {
        LOG.info("getFreeParkingSpots");
        try {
            // Count all entities in the Car table
            Long count = entityManager.createQuery("SELECT COUNT(c) FROM Car c", Long.class)
                    .getSingleResult();

            // Calculate free spots (Total capacity 10 - occupied spots)
            int freeSpots = 10 - count.intValue();

            // Optional: Ensure we don't return negative numbers if overbooked
            return Math.max(0, freeSpots);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}