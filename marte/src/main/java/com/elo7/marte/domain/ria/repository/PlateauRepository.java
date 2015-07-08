package com.elo7.marte.domain.ria.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.ria.Plateau;

@Repository
public class PlateauRepository {
	
	@PersistenceUnit
	private EntityManager entityManager;
	
	public Plateau save(Plateau plateau) {
		return entityManager.merge(plateau);
	}
	
	public Plateau find(int id) {
		return entityManager.find(Plateau.class, id);
	}

}
