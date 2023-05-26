package com.example.demo.advice;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.UnexpectedTypeException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.BikeNotFound;
import com.example.demo.pojo.Bike;
import com.example.demo.responseentity.ResponceEntityBike;
import com.google.common.net.HttpHeaders;

import javassist.expr.NewArray;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class BikeAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponceEntityBike> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		log.error("some sysntactical error bad request while accepting error message");
		ResponceEntityBike bike = new ResponceEntityBike(true, errors, ex.getMessage());
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponceEntityBike> handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getMessage();
		ResponceEntityBike bike = new ResponceEntityBike(true, ex, "missing parameter");
		log.error("some parameter are mising wrong input");
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ResponceEntityBike> handleNoSuchElementException(NoSuchElementException ex) {
		String name = ex.getMessage();
		ResponceEntityBike bike = new ResponceEntityBike(true, ex, "No value present");
		log.error("try to find such value wich is not present in database");
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponceEntityBike> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String message = ex.getMessage();
		ResponceEntityBike bike = new ResponceEntityBike(true, ex, "please enter numeric only");
		log.error("some unrelated  input");
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ResponceEntityBike> emptyresult(EmptyResultDataAccessException es) {
		ResponceEntityBike bike = new ResponceEntityBike(true, es.getMessage(), "id not valid");
		log.error("id not valid wrong input ");
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);
	}
@ExceptionHandler(BikeNotFound.class)
public ResponseEntity<ResponceEntityBike> emptyresult(BikeNotFound es) {
	ResponceEntityBike bike = new ResponceEntityBike(true, es.getMessage(), "id not valid");
	log.error("bike not found");
	return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);
}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponceEntityBike> handleException(Exception ex) {

		ResponceEntityBike bike = new ResponceEntityBike(true, ex.getMessage(), "something went wrong plese try again");
		log.error("main exception handler");
		return new ResponseEntity<>(bike, HttpStatus.BAD_REQUEST);
	}
}
