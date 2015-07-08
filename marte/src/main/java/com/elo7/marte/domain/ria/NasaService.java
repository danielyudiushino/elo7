package com.elo7.marte.domain.ria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elo7.marte.domain.ria.Explorer;
import com.elo7.marte.domain.ria.repository.ExplorerRepository;
import com.elo7.marte.domain.ria.repository.PlateauRepository;
import com.elo7.marte.domain.ria.state.StateFactory;
import com.elo7.marte.domain.ria.vo.CoordinatesVO;

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
		return new Explorer(plateau, coordinates, StateFactory.methodFactory(state)).save(explorerRepository);
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
