package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 通过车辆名称购买车辆
     * @param carName
     * @param purchaseNum
     * @return
     */
    @PostMapping("purchaseCar")
    public JSONResult purchaseCar(String carName,int purchaseNum){
        boolean result = carService.purchaseCar(carName, purchaseNum);
        return result?JSONResult.ok():JSONResult.errorException("库存不足，不支持购买");
    }

    /**
     * 根据品牌模糊查询
     * @param carName 车辆名称
     * @return
     */
    /**
     * 根据品牌模糊分页查询
     * @param carName 车辆名称
     * @param pageNo 查询页数
     * @param pageSize 每页大小
     * @return
     */
    @GetMapping("queryLikeCarName")
    public JSONResult queryByCarName(@RequestParam String carName,@RequestParam int pageNo,
                                     @RequestParam int pageSize){
        List<Car> cars = carService.queryLikeCarName(carName,pageNo, pageSize);
        return JSONResult.ok(cars);
    }
}
