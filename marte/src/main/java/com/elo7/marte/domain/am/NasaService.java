package com.elo7.marte.domain.am;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elo7.marte.domain.am.dao.ExplorerDAO;
import com.elo7.marte.domain.am.dao.PlateauDAO;
import com.elo7.marte.domain.am.entity.Explorer;
import com.elo7.marte.domain.am.entity.Plateau;
import com.elo7.marte.domain.ria.Command;

@Service("com.elo7.marte.domain.am.NasaService")
@Transactional
public class NasaService {

	@Autowired
	@Qualifier("PlateauDAO")
	private PlateauDAO plateauDAO;
	@Autowired
	@Qualifier("ExplorerDAO")
	private ExplorerDAO explorerDAO;
	
	public int createPlateau(int lat, int lng) {
		Plateau plateau = new Plateau();
		plateau.setLat(lat);
		plateau.setLng(lng);
		int id = plateauDAO.save(plateau);
		return id;
	}
	
	public int createExplorer(int idPlateu, int lat, int lng, String direction) {
		Explorer explorer = new Explorer();
		explorer.setPlateau(plateauDAO.find(idPlateu));
		explorer.setDirection(direction);
		explorer.setLat(lat);
		explorer.setLng(lng);
		int id = explorerDAO.save(explorer);
		return id;
	}
	
	public void move(int idExplorer, String command) throws Exception {
		Explorer explorer = explorerDAO.find(idExplorer);
		if(Command.MOVE.getValue().equals(command)) {
			changePosition(explorer);
		} else {
			explorer.setDirection(command);
		}
		
		explorerDAO.save(explorer);
	}
	
	public Explorer find(int id) {
		return explorerDAO.find(id);
	}

	private void changePosition(Explorer explorer) {
		switch (Command.get(explorer.getDirection())) {
		case NORTH:
			explorer.setLng(explorer.getLng()+1);
			break;
		case SOUTH:
			explorer.setLng(explorer.getLng()-1);
			break;
		case EAST:
			explorer.setLat(explorer.getLat()+1);
			break;
		case WEST:
			explorer.setLat(explorer.getLat()-1);
			break;
		default:
			break;
		}
	}
}
