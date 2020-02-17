package com.example.TLBkadai;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.TLBkadai.repository.MyDataRepository;

public class MyDataBean {
	
	@Autowired
	MyDataRepository repository;
	
	public String getTableTagById(Long id) {
		Optional<MyData> opt = repository.findById(id);
		MyData data = opt.get();
		String result = "<tr><td>" + data.getTitle()
					  + "</td><td>" + data.getDescription()
					  + "</td><td>" + data.getPrice()
					  + "</td></tr>";
		return result;
	}
}
