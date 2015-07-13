package com.elo7.marte.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.Explorer;

@Repository
public class ExplorerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Explorer save(Explorer explorer) {
		return entityManager.merge(explorer);
	}

	public Explorer find(int id) {
		return entityManager.find(Explorer.class, id);
	}
	
}
