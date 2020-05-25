package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries},stockQty=#{stockQty} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries,stockQty) values(#{carName},#{carType},#{price},#{carSeries},#{stockQty})")
    void insertCar(Car car);

    @Select("select * from carMessage where carName like CONCAT('%',#{carName},'%') limit #{startIndex}, #{pageSize}")
    List<Car> queryLikeCarName(String carName, int startIndex, int pageSize);
}
