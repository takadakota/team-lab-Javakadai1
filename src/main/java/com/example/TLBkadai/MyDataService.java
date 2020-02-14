package com.example.TLBkadai;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

@Service
public class MyDataService {
	
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
		List<MyData> list = null;
		String qstr = "from MyData where id = fstr";
		Query query = entityManager.createQuery(qstr);.setParameter("fstr",Long.parseLong(fstr));
		list = query.getResultList();
		return list;
	}
	
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}
}

