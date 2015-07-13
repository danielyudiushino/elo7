package com.elo7.marte.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.repository.ExplorerRepository;
import com.elo7.marte.domain.repository.PlateauRepository;
import com.elo7.marte.domain.state.StateFactory;
import com.elo7.marte.domain.vo.CoordinatesVO;

@Service("com.elo7.marte.domain.ria.NasaService")
@Transactional
public class NasaService {

	@Autowired
	private PlateauRepository plateauRepository;
	@Autowired
	private ExplorerRepository explorerRepository;
	
	public Plateau definePlateau(CoordinatesVO coordinates) {
		return new Plateau(coordinates).save(plateauRepository);
	}
	
	public Explorer createExplorer(Plateau plateau, CoordinatesVO coordinates, String state) {
		return new Explorer(plateau, coordinates, StateFactory.directionFactory(state)).save(explorerRepository);
	}
	
	public Explorer move(Explorer explorer, String command) throws Exception {
		explorer.move(command).save(explorerRepository);
		return explorer;
	}
	
	public Plateau findPlateau(int id) {
		return plateauRepository.find(id);
	}
	
	public Explorer findExplorer(int id) {
		return explorerRepository.find(id);
	}
}
