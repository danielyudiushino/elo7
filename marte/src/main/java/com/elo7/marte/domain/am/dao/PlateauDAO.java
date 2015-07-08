package com.elo7.marte.domain.am.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.am.entity.Plateau;

@Repository("PlateauDAO")
public class PlateauDAO {
	
	@PersistenceUnit
	private EntityManager entityManager;
	
	public int save(Plateau plateau) {
		plateau = entityManager.merge(plateau);
		return plateau.getId();
	}
	
	public Plateau find(int id) {
		return entityManager.find(Plateau.class, id);
	}

}
