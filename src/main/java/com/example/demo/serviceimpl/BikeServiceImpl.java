package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.dto.BikeDto;
import com.example.demo.exception.BikeNotFound;
import com.example.demo.pojo.Bike;

import com.example.demo.repo.BikeRepo;
import com.example.demo.service.BikeServices;
import com.google.common.reflect.Parameter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

// service implementation class
public class BikeServiceImpl implements BikeServices {
	@Autowired
	BikeRepo bk;
//	@Autowired
//    Bike biketable;

	@Transactional
	@Override
	/** these method use to save data in database accept the object of pojo class */
	public Bike addInfo(BikeDto bike) {
		System.out.println(bike);
		Bike bike2 = new Bike();
		BeanUtils.copyProperties(bike, bike2);

		Bike save = bk.save(bike2);

		if (save == null) {
			log.error("database transaction problem");

		} else

			log.info("data operation successfully done");
		return save;
	}

	@Transactional
	@Override
	/**
	 * this method use for data update purpose accept the two parameters integer
	 * id and bike price for bike price
	 */
	public Bike updateData(int id, int bikePrice) {
		Optional<Bike> findById = bk.findById(id);
		Bike bike = findById.get();
		bike.setBikePrice(bikePrice);
		bk.save(bike);

		return bike;

	}

	@Transactional
	@Override
	/** these method is use for delete the specific entity in our database */
	public void deleteData(int id) {
		bk.deleteById(id);

	}

	@Override
	/** this method use for show all data which is present in table */
	public List<Bike> getAllData() {

		List<Bike> all = bk.findAll();
return all;
	
	}

	@Override
	/**
	 * these method is use for find the group of bikes details with the help of bike
	 * chasie no ,accept data in list [1,2,3....] and these no act as primary key
	 * for table and find the details and print using list
	 */
	public List<Bike> gettAllById(Iterable<Integer> list) {
		List<Bike> id = bk.findAllById(list);

		return id;
	}

	/**
	 * these method is use for find the the specific data these method accept only
	 * integer value peform some operation and give result
	 */
	public Bike findElement(int id) {

		Optional<Bike> id2 = bk.findById(id);
		if (id2 == null) {
			throw new BikeNotFound("bike details not found");
		} else {

			Bike bike = id2.get();

			return bike;
		}
	}

	@Override
	/**
	 * these method accept the string value find the data by using custom method and
	 * show result
	 */
	public Bike findByName(String name) {
	 Optional<Bike> findBybikeName = bk.findBybikeName(name);
		 System.out.println(findBybikeName);
		 
	Bike bike = findBybikeName.get();
		return bike;
		 
		 
		
	}

	/**
	 * these method accept 2 parameters and first value is use to find the data and
	 * String value is use to set the bike name
	 */
	@Transactional
	public Bike updateDataByUsingPutMapping(int id, String bikeName) {
		Optional<Bike> findById = bk.findById(id);
		Bike bike = findById.get();

		bike.setBikeName(bikeName);
		bk.save(bike);
		return bike;
	}

	/**
	 * these method accept the data in object and and search in database by using
	 * chasie no and then we can edit data and save data and show updated
	 * information back to client
	 */
	@Transactional
	public Bike updateDataUsingRequestBody(Bike bike) {

		Optional<Bike> findById = bk.findById(bike.getBikeChasieeNo());
		Bike bike2 = findById.get();
		bike2.setBikeName(bike.getBikeName());
		bike2.setBikePrice(bike.getBikePrice());
		bk.save(bike2);
		return bike2;
	}

	@Override
	public BikeDto getAllDataByUsingFindAllByName(String name) {
		return null;
	}

	@Override
	public void findByBikeNameIn(String name) {
	
		
	}

	

	
	}

		
	
	
	
	
	
		
	


