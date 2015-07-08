package com.elo7.marte.domain.am.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.elo7.marte.domain.am.entity.Explorer;

@Repository("ExplorerDAO")
public class ExplorerDAO {
	
	@PersistenceUnit
	private EntityManager entityManager;
	
	public int save(Explorer explorer) {
		explorer = entityManager.merge(explorer);
		return explorer.getId();
	}
	
	public Explorer find(int id) {
		return entityManager.find(Explorer.class, id);
	}

}
