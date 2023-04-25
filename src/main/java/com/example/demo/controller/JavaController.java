package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LicUserEntity;
import com.example.demo.service.DatetimeService;
import com.example.demo.service.EncodeDecodeService;
import com.example.demo.service.TerminalService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class JavaController {
	
	@Autowired
	TerminalService terminalService;
	
	@Autowired
	EncodeDecodeService service;
	
	@Autowired
	DatetimeService datetimeService;
	
	@GetMapping("/getAll")
	public List<LicUserEntity> getAll() throws IOException   	{ 
		return terminalService.getData();
	} 
	
	@PostMapping("/save")
	public LicUserEntity saveData(@RequestBody LicUserEntity licUserEntity)   	{ 
		terminalService.saveData(licUserEntity);
		return licUserEntity;
	} 
	
	@PutMapping("/update/{userId}")
	public LicUserEntity updateData(@PathVariable int userId,@RequestBody LicUserEntity licUserEntity){ 
		return terminalService.getById(userId,licUserEntity);
	} 
	
	@DeleteMapping("/delete/{userId}")
	public String deleteData(@PathVariable int userId){ 
		return terminalService.delete(userId);
	} 
	
	@GetMapping("/dateTime")
	public String dateTime(){
		return datetimeService.dateTime();
	}
	
	@GetMapping("/encodeDecode")
	public String encodeDecode() throws IOException {
//	return service.base64String();
		return service.base64File();
	}
	@GetMapping("/compute")
	public ConcurrentHashMap<Integer, String> compute() {
		return datetimeService.compute();
	}
	
	
	@GetMapping("/forEach")
	public List<String> getAllData()   	{ 
		return terminalService.forEach();
	} 
	
	@GetMapping("/forEachOrdered")
	public String forEachOrdered()   	{ 
		return terminalService.forEachOrdered();
	} 
	
	@GetMapping("/toArray")
	public Integer[] toArray()   	{ 
		return terminalService.toArray();
	} 
	
	@GetMapping("/reduce")
	public String reduce()   	{ 
		return terminalService.reduce();
	} 
	
	@GetMapping("/minAndMax")
	public String minAndMax()   	{ 
		return terminalService.minAndMax();
	} 
	
	@GetMapping("/anyAndAllMatch")
	public boolean anyAndAllMatch()   	{ 
		return terminalService.anyAndAllMatch();
	} 
	
	@GetMapping("/callDefault")
	public String callDefault()   	{ 
		 return terminalService.callDefault();
		 
	} 
}
