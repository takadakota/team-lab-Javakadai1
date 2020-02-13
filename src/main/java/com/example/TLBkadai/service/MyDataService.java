package com.example.TLBkadai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TLBkadai.MyData;
import com.example.TLBkadai.repository.MyDataRepository;

@Service
public class MyDataService {
    
    @Autowired
    private MyDataRepository repository;

    public List<MyData> findAll() {
        return repository.findAll();
    }

    public Optional<MyData> findOne(Long id) {
        return repository.findById(id);
    }

    public MyData save(MyData mydata) {
        return repository.save(mydata);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}