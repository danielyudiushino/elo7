package com.elo7.marte.domain.ria.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.ria.Explorer;

@Repository
public class ExplorerRepository {
	
	@PersistenceUnit
	private EntityManager entityManager;
	
	public Explorer save(Explorer explorer) {
		return entityManager.merge(explorer);
	}

	public Explorer find(int id) {
		return entityManager.find(Explorer.class, id);
	}
	
}
