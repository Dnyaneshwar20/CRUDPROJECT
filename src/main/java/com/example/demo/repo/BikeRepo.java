package com.example.demo.repo;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Bike;

import lombok.extern.slf4j.Slf4j;
@Repository

public interface BikeRepo extends JpaRepository<Bike, Integer>{
 
	List<Bike> findByBikeNameIn(List<String> name);	
	Optional<Bike> findBybikeName(String name);
	
	
	
}
