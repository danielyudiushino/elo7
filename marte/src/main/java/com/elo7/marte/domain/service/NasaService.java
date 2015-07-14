package com.elo7.marte.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.repository.ExplorerRepository;
import com.elo7.marte.domain.repository.PlateauRepository;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.exception.BusinessException;
import com.elo7.marte.exception.NotFoundException;

@Service("com.elo7.marte.domain.ria.NasaService")
@Transactional
public class NasaService {

	@Autowired
	private PlateauRepository plateauRepository;
	@Autowired
	private ExplorerRepository explorerRepository;
	
	public Plateau definePlateau(CoordinatesVO coordinates) {
		return new Plateau(coordinates).validate().save(plateauRepository);
	}
	
	public Explorer createExplorer(int plateauId, CoordinatesVO coordinates, String state) {
		Plateau plateau = plateauRepository.find(plateauId);
		if(plateau == null)
			throw new BusinessException("Plateau not Found");
		return new Explorer(plateau, coordinates, state).validate().save(explorerRepository);
	}
	
	public Explorer move(Explorer explorer, String command) {
		explorer.move(command).validate().save(explorerRepository);
		return explorer;
	}
	
	public Plateau findPlateau(int id) {
		Plateau plateau = plateauRepository.find(id);
		if(plateau == null)
			throw new NotFoundException("Plateau not Found");
		return plateau;
	}
	
	public Explorer findExplorer(int id) {
		Explorer explorer = explorerRepository.find(id);
		if(explorer == null)
			throw new NotFoundException("Explorer not Found");
		return explorer;
	}
}
