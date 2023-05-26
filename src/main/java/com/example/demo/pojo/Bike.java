package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bike {
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @ApiModelProperty(notes = "The database generated bike chasiee o")
	private int bikeChasieeNo;
	@ApiModelProperty(notes = "bike name is mandatory not blank")
	@NotBlank(message = "bike name is mandatory")
	private String bikeName;
	@ApiModelProperty(notes = "The price of bike", required = true)
	@Range(min = 50000 ,max = 700000 ,message = "bike price must be between 50000 and 700000")
	private int bikePrice;
	

	
}
