package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BikeDto;
import com.example.demo.pojo.Bike;

import com.example.demo.responseentity.ResponceEntityBike;
import com.example.demo.service.BikeServices;

import com.example.demo.serviceimpl.BikeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Slf4j
@Api(value = "allBikesDetails")
public class BikeController {

	@Autowired
	BikeServices bs;
	
	/**
	 * this is simple hello message printing statement method to check whether
	 * application is working properly or not
	 */
	@GetMapping("hello")
	public String hello() {
		return "hello ........... do the task";
	}

	/**
	 * this is the method which accept the all entity data(bike) and save in the
	 * database
	 */
	@ApiOperation(value = "add Bike to database")
	@PostMapping("/addInfo")
	public ResponseEntity<ResponceEntityBike> addInfo(@Valid @RequestBody BikeDto bike) {
		Bike addInfo = bs.addInfo(bike);
		log.info("data added successfully ");
		if (addInfo == null) {
			log.warn("bike object is null");
		}
		return new ResponseEntity<>(new ResponceEntityBike(false, addInfo, "bike data successfully inserted"),
				HttpStatus.OK);
	}

	/**
	 * this method is used to update the bike price and these method accept the 2
	 * value first one is the id which is find the appropriate bike detail and then
	 * update the bike price with given value
	 */
	@ApiOperation(value = "update the bike detail")
	@PostMapping("updateInfo")
	public ResponseEntity<ResponceEntityBike> updateData(@RequestParam int bikeId, @RequestParam int bikePrice) {
		Bike data = bs.updateData(bikeId, bikePrice);
		log.info("update data");
		if (data == null) {
			log.trace("problem in fetching data after saving details");
		}

		return new ResponseEntity<>(new ResponceEntityBike(false, data, "data updated successfully"), HttpStatus.OK);

	}

	/**
	 * these method is accept the integer value and find the bike detail and remove
	 * from database
	 */
	@ApiOperation(value = "delete the bike details")
	@DeleteMapping("/deleteData")
	public ResponseEntity<ResponceEntityBike> DeleteData(@RequestParam int id) {
		bs.deleteData(id);
		log.info("data delete");
		log.warn("we are delete entity ");
		return new ResponseEntity<>(new ResponceEntityBike(false, id, "data deleted successfully"), HttpStatus.OK);

	}

	/**
	 * this method is does not accept any data it just print the all bike details in
	 * postman
	 */
	@ApiOperation(value = "view list of available products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getAllData")
	public ResponseEntity<ResponceEntityBike> getAllData() {

		List<Bike> bike = bs.getAllData();

		return new ResponseEntity(new ResponceEntityBike(false, bike, "data fetch successfully"), HttpStatus.OK);

	}

	/**
	 * this method accept the string and find the bike detail by using custom method
	 * which give output in the form of object if data not present it will print the
	 * message
	 */
	@ApiOperation(value = "find the details by using bike name")
	@GetMapping("/findByName")
	public ResponseEntity<ResponceEntityBike> findByName(@RequestParam String name) {
		Bike bike = bs.findByName(name);
		
		
		
		
		return new ResponseEntity<>(new ResponceEntityBike(false, bike, "data fetch successfully"), HttpStatus.OK);
	}

	/**
	 * this method is accept the value in list format [1,2,3,4] if all value are
	 * consider as primary key and we are fetch data by using bike name it will give
	 * data in form of list and if particular value not present it will throw and
	 * error that error will handle by advice class
	 */
	@ApiOperation(value = "find the bikes by using chasie no")
	@GetMapping("/getDetailsByChasieNo")
	public ResponseEntity<ResponceEntityBike> getSpecificData(@RequestBody List<Integer> s) {

		List<Bike> gettallbyid = bs.gettAllById(s);
		if (gettallbyid == null) {
			log.trace("getting issue with fetching data");
		}
		log.info("data find using chasie no");

		return new ResponseEntity(new ResponceEntityBike(false, gettallbyid, "data fetch successfully"), HttpStatus.OK);

	}

	@ApiOperation(value = "update the bike data minimum bike chasie no required,using put mapping")
	@PutMapping("/updateDataPutmapping")
	public ResponseEntity<ResponceEntityBike> byUsingPutMappingFind(@RequestParam int id,
			@Valid @RequestParam String bikeName) {
		Bike find = bs.updateDataByUsingPutMapping(id, bikeName);
		if (find == null) {
			log.trace("something went wrong while fetching data after update");
		}
		log.info("update data usinng put mapping with id and change the bike name ");
		return new ResponseEntity<>(new ResponceEntityBike(false, find, "data successfully updated"), HttpStatus.OK);

	}

	@ApiOperation(value = "update the data in form of object and using put mapping")
	@PutMapping("/updateDataUsingRequestBody")
	public ResponseEntity<ResponceEntityBike> updateDataUsingRequestBody(@Valid @RequestBody Bike bike) {
		Bike body = bs.updateDataUsingRequestBody(bike);
		if (body == null) {
			log.trace("while updating information after saving something went wrong");
		}

		log.info("update the data in form of object and using put mapping");
		return new ResponseEntity<>(new ResponceEntityBike(false, body, "data  successfully updated"), HttpStatus.OK);

	}
	@ApiOperation(value="getAllDataByUsingFindAllByName")
	@GetMapping("/getAllBikeDataByUsingFindAllByName")
	public BikeDto getAllDataByUsingFindAllByName(String name) {
		BikeDto byName = bs.getAllDataByUsingFindAllByName(name);
		return byName;
	}










}