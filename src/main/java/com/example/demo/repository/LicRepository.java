package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LicUserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface LicRepository extends JpaRepository<LicUserEntity, Integer>{
	

	@Transactional
	@Query(value="SELECT * from lic_user_details where user_name=:userName and password=:password",nativeQuery =true)
	public LicUserEntity getData(String userName, String password);

	@Query(value="SELECT * from lic_user_details",nativeQuery =true)
	public List<LicUserEntity> getAll();

}
