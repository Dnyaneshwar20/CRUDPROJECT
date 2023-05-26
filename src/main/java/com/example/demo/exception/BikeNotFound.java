package com.example.demo.exception;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class BikeNotFound extends RuntimeException {

	public BikeNotFound(String g) {
		super(g);
		log.error("custum exception Bike not Found");
	}
	BikeNotFound(){
		super();
		log.error("custum exception Bike not Found");
	}
}
