package com.elo7.marte.domain.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.repository.ExplorerRepository;
import com.elo7.marte.domain.vo.CoordinatesVO;

@RunWith(PowerMockRunner.class)
public class WhenYouMoveAExplorer {

	@Mock
	private ExplorerRepository explorerRepository;
	
	NasaService nasaService;
	
	@Before
	public void init() {
		nasaService = new NasaService();
		Whitebox.setInternalState(nasaService, "explorerRepository", explorerRepository);
	}
	
	@Test
	public void aPerfectExplorerShouldBeMoved() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, 0), "N");
		Mockito.when(explorerRepository.save(Mockito.any(Explorer.class))).thenReturn(explorer);
		
		Explorer savedExplorer = nasaService.move(explorer, "M");
		
		Assert.assertEquals(explorer, savedExplorer);
	}
	
}