package com.elo7.marte.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.Plateau;

@Repository
public class PlateauRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Plateau save(Plateau plateau) {
		return entityManager.merge(plateau);
	}
	
	public Plateau find(int id) {
		return entityManager.find(Plateau.class, id);
	}

}
