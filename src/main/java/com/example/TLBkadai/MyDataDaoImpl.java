package com.example.TLBkadai;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	public MyDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	
	@Override
	public List<MyData> getAll(){
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		return list;
	}
	
	@Override
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}
	
	@Override
	public List<MyData> find(String fstr){
		List<MyData> list = null;
		String qstr = "from MyData where id = ?1 or title like ?2 or description like ?3  or price = ?4";
		Long fid = 0L;
		Long fprice = 0L;
		try {
			fid = Long.parseLong(fstr);
			fprice = Long.parseLong(fstr);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		Query query = entityManager.createQuery(qstr).setParameter(1,fid).setParameter(2,"%" + fstr + "%").setParameter(3,"%" + fstr + "%").setParameter(4,fprice);
		list = query.getResultList();
		return list;
	}
}
