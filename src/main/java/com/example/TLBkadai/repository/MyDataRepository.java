package com.example.TLBkadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TLBkadai.MyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{
	public Page<MyData> findAll(Pageable pageable);
}
