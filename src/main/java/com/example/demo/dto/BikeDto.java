package com.example.demo.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.example.demo.pojo.Bike;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/** its my pojo class   */
public class BikeDto {
	private int id;
	
	@NotBlank(message = "bike name is mandatory")
	private String bikeName;
     
	@Range(min = 50000 ,max = 700000 ,message = "bike price must be between 50000 and 700000")
	private int bikePrice;
	 
	
	
   private List<BikeDto> bikeList;
}
