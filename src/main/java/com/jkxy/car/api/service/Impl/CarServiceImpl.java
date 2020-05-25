package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public List<Car> queryLikeCarName(String carName, int pageNo, int pageSize) {
        int starIndex = (pageNo - 1) * pageSize;
        return carDao.queryLikeCarName(carName,starIndex,pageSize);
    }

    @Override
    public boolean purchaseCar(String carName, int purchaseNum) {
        synchronized(this){
            List<Car> cars = carDao.findByCarName(carName);
            if(CollectionUtils.isNotEmpty(cars)){
                Car car = cars.get(0);
                int stockQty = car.getStockQty();
                if(purchaseNum > stockQty){
                    return false;
                }else{
                    stockQty -= purchaseNum;
                    car.setStockQty(stockQty);
                    carDao.updateById(car);
                    return true;
                }
            }
            return false;
        }

    }
}
