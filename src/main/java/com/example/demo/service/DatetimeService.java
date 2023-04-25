package com.example.demo.service;

import java.time.LocalDate;
//import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LicUserEntity;
import com.example.demo.repository.LicRepository;

@Service
public class DatetimeService {

	@Autowired
	LicRepository licRepository;  

	public String dateTime() {
		LocalDate localDate = LocalDate.now().plusMonths(1);
		System.out.println(LocalDate.parse("2016-06-12").getMonthValue());
		System.out.println(localDate);
//		LocalTime.now();
		ZonedDateTime.now();
		return null;
	}

	public ConcurrentHashMap<Integer, String> compute() {
		// Using compute()
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
		licUserEntityList.stream().filter(licUserEntity->licUserEntity.getUserId()==1).forEach(licUserEntity->{
			map.put(licUserEntity.getUserId(), licUserEntity.getUserName());
			System.out.println("ConcurrentHashMap: " + map);

			map.compute(licUserEntity.getUserId(), (key, val) -> val + " is one of the user");
			System.out.println("New ConcurrentHashMap: " + map);
			
		});
		// Using merge()
		ConcurrentHashMap<String, Integer> mapOne = new ConcurrentHashMap<>();
		mapOne.put("A", 1);
		mapOne.put("B", 2);
		mapOne.put("C", 3);
		mapOne.put("F", 4);

		ConcurrentHashMap<String, Integer> mapTwo = new ConcurrentHashMap<>();
		mapTwo.put("A", 0);
		mapTwo.put("B", 4);
		mapTwo.put("D", 5);
		mapTwo.forEach((key, value) ->mapOne.merge(key, value, (v1, v2) -> v1 + v2) );
        System.out.println(mapOne);
		
     // Using search()
		ConcurrentHashMap<String, Integer> numbers = new ConcurrentHashMap<>();
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);
        System.out.println("ConcurrentHashMap: " + numbers);
        String key = numbers.search(4, (k, v) -> {
        	return v == 3 ? k: null;
        	});
        System.out.println("Searched value: " + key);
		
		return map;
	}
}
