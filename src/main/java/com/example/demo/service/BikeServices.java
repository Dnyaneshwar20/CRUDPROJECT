package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.BikeDto;
import com.example.demo.pojo.Bike;


public interface BikeServices {
	public Bike addInfo(BikeDto bike);
	public Bike updateData(int id, int bp);
	public void deleteData(int id);
	public List<Bike> getAllData();
	public List<Bike> gettAllById(Iterable<Integer> list);
	public Bike findByName(String name);
	public Bike updateDataByUsingPutMapping(int id, String bikeName);
	public Bike updateDataUsingRequestBody(Bike bike);
	public BikeDto getAllDataByUsingFindAllByName(String name);
	public void findByBikeNameIn(String name);
	
}
