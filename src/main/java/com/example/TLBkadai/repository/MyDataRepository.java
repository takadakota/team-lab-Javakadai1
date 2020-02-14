package com.example.TLBkadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TLBkadai.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{
	
}
