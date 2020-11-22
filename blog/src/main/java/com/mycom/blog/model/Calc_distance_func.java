package com.mycom.blog.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;


public class Calc_distance_func {

	@Autowired
	EntityManager em;
	
	public void getValue(double lat1,double long1,double lat2,double long2)
	{
		Query query = em.createNativeQuery("{call CALC_DISTANCE(?,?,?,?)}", Double.class)
				.setParameter(1, lat1).setParameter(2, long1).setParameter(1, lat2).setParameter(2, long2);

		List<Double> result =  query.getResultList();
	}
	
}
