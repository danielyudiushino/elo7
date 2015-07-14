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
import com.elo7.marte.domain.repository.PlateauRepository;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.exception.BusinessException;

@RunWith(PowerMockRunner.class)
public class WhenYouCreateAExplorer {

	@Mock
	private PlateauRepository plateauRepository;
	@Mock
	private ExplorerRepository explorerRepository;
	
	NasaService nasaService;
	
	@Before
	public void init() {
		nasaService = new NasaService();
		Whitebox.setInternalState(nasaService, "plateauRepository", plateauRepository);
		Whitebox.setInternalState(nasaService, "explorerRepository", explorerRepository);
	}
	
	@Test
	public void aPerfectExplorerShouldBeCreated() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Mockito.when(plateauRepository.find(Mockito.anyInt())).thenReturn(plateau);
		Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, 1), "N");
		Mockito.when(explorerRepository.save(Mockito.any(Explorer.class))).thenReturn(explorer);
		
		Explorer savedExplorer = nasaService.createExplorer(1, new CoordinatesVO(1, 1), "N");
		
		Assert.assertEquals(explorer, savedExplorer);
	}
	
	@Test(expected = BusinessException.class)
	public void anImperfectExplorerShouldNotBeCreated() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Mockito.when(plateauRepository.find(Mockito.anyInt())).thenReturn(null);
		Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, 1), "N");
		Mockito.when(explorerRepository.save(Mockito.any(Explorer.class))).thenReturn(explorer);
		
		nasaService.createExplorer(1, new CoordinatesVO(1, 1), "N");
	}
	
}