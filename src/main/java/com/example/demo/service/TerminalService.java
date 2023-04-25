package com.example.demo.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LicUserEntity;
import com.example.demo.repository.LicRepository;

@Service
public class TerminalService implements Sayable {

	@Autowired
	LicRepository licRepository;  

//	public void saveData(LicUserEntity licUserEntity) {
//		licRepository.save(licUserEntity);
//	}

	public List<LicUserEntity> getData() {
		return licRepository.getAll();
	}
	public void saveData(LicUserEntity licUserEntity) {
		licRepository.save(licUserEntity);
	}

	public List<String> forEach() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		List<String> nameList = licUserEntityList.stream().map(entity->entity.getUserName()).collect(Collectors.toList());
		nameList.forEach(name -> System.out.println(name));
		List<Integer> userIdList = licUserEntityList.stream().map(entity->entity.getUserId()).collect(Collectors.toList());
		userIdList.forEach(System.out::println);
		return nameList;
	}

	public String forEachOrdered() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		List<Integer> contactList = licUserEntityList.stream().map(entity->entity.getContact()).collect(Collectors.toList());
		System.out.println("Original order of list: "+ contactList);
		
		System.out.println("When doing Parallel stream: ");
		contactList.stream().parallel().forEach( System.out::println );   
		System.out.println("When using forEachOrdered getting original list order: ");
		contactList.stream().parallel().forEachOrdered( System.out::println ); 
		return "Ordered successfully";
	}

	public Integer[] toArray() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		List<Integer> contactList = licUserEntityList.stream().map(entity->entity.getContact()).collect(Collectors.toList());
		Integer[] namesArray = contactList.toArray(Integer[] ::new);  
		// print all the elements of the array  
		System.out.println("After converting List into an Array");  
		for (int index = 0; index < namesArray.length; index++) {  
			System.out.println((index+1)+" element of the array is "+namesArray[index]);  
		} 
		return namesArray;
	}

	public String reduce() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		List<String> nameList = licUserEntityList.stream().map(entity->entity.getUserName()).collect(Collectors.toList());
		java.util.Optional<String> longestString = nameList.stream()
				.reduce((nameOne, nameTwo) -> nameOne.length() > nameTwo.length()
						? nameOne : nameTwo);
		longestString.ifPresent(System.out::println);
		return longestString.get();
	}

	public String minAndMax() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();

		List<String> nameList = licUserEntityList.stream().map(entity->entity.getUserName()).collect(Collectors.toList());
		System.out.println("Name list count: "+nameList.stream().count());
		
		nameList.stream().min(Comparator.comparing(String::valueOf))
		.ifPresent(name -> System.out.println("Min String: " + name));

		nameList.stream().max(Comparator.comparing(String::valueOf))
		.ifPresent(name -> System.out.println("Max String: " + name));

		List<Integer> userIdList = licUserEntityList.stream().map(entity->entity.getUserId()).collect(Collectors.toList());
		userIdList.stream().min(Comparator.comparing(Integer::valueOf))
		.ifPresent(name -> System.out.println("Min String: " + name));

		userIdList.stream().max(Comparator.comparing(Integer::valueOf))
		.ifPresent(name -> System.out.println("Max String: " + name));

		return nameList.toString();
	}

	public boolean anyAndAllMatch() {
		List<LicUserEntity> licUserEntityList = licRepository.getAll();
		List<String> nameList = licUserEntityList.stream().map(entity->entity.getUserName()).collect(Collectors.toList());
		boolean match = nameList.stream().allMatch(name -> name.startsWith("R"));
		System.out.println(match);
		return match;
	}

	public void sayMore(String msg) {
		System.out.println(msg);  
		
	}

	public String callDefault() {
		TerminalService service=new TerminalService();
//		return Sayable.super.say();
		return service.say();
	}
	@Override
	public String say() {
		System.out.println("Yes we can override default method");
		return "Default method overidden";  
	}
	
	public void callStatic() {
		Sayable.add(0, 0);
	}
	public LicUserEntity getById(int userId, LicUserEntity licUserEntity) {
		  java.util.Optional<LicUserEntity> licUserEntityOptional = licRepository.findById(userId);
		  LicUserEntity updateLicUserEntity=new LicUserEntity();
		  if(licUserEntityOptional.isPresent()) {
			  updateLicUserEntity.setUserId(licUserEntityOptional.get().getUserId());
			  updateLicUserEntity.setAddress(licUserEntity.getAddress());
			  updateLicUserEntity.setContact(licUserEntity.getContact());
			  updateLicUserEntity.setEmail(licUserEntity.getEmail());
			  updateLicUserEntity.setPassword(licUserEntity.getPassword());
			  updateLicUserEntity.setUserName(licUserEntity.getUserName());
			  licRepository.save(updateLicUserEntity);
		  }else {
			  System.out.println("User Id is not exist");
		  }
		return updateLicUserEntity;
		
	}
	public String delete(int userId) {
		java.util.Optional<LicUserEntity> licUserEntityOptional = licRepository.findById(userId);
		if(licUserEntityOptional.isPresent()) {
		   licRepository.deleteById(userId);
		}else {
			System.out.println("User Id is not exist");
		}
		return "Data deleted successfully";
	}
}
