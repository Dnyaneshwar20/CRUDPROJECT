package com.example.demo.responseentity;

import com.example.demo.pojo.Bike;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponceEntityBike {
	private boolean error;
	private Object data;
	private String message;	
	
	
	
	

}
