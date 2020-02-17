package com.example.TLBkadai;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.example.TLBkadai.repository.MyDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Service
public class MyDataService {
	
	@Autowired
	MyDataRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("untracked")
	public List<MyData> getAll(){
		return (List<MyData>) entityManager.createQuery("from MyData").getResultList();
	}
	
	public MyData get(int num){
		return (MyData)entityManager.createQuery("from MyData where id = " + num).getSingleResult();
	}
	
	public List<MyData> find(String fstr){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(builder.equal(root.get("name"),fstr));
		List<MyData> list = null;
		list = (List<MyData>) entityManager.createQuery(query).getResultList();
		return list;
	}
	
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}
	
	public List<MyData> findAll(){
		return repository.findAll();
	}
	public void delete(long id){
		repository.deleteById(id);
	}
	public void update(MyData mydata){
		repository.saveAndFlush(mydata);
	}
	public void create(List<MyData> mydata){
		repository.saveAll(mydata);
	}
}

