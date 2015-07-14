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
import com.elo7.marte.exception.NotFoundException;

@RunWith(PowerMockRunner.class)
public class WhenYouFindAExplorer {

	@Mock
	private ExplorerRepository explorerRepository;
	
	NasaService nasaService;
	
	@Before
	public void init() {
		nasaService = new NasaService();
		Whitebox.setInternalState(nasaService, "explorerRepository", explorerRepository);
	}
	
	@Test
	public void aPerfectExplorerShouldBeReturned() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, 0), "N");
		Mockito.when(explorerRepository.find(Mockito.anyInt())).thenReturn(explorer);
		
		Explorer savedExplorer = nasaService.findExplorer(1);
		
		Assert.assertNotNull(savedExplorer);
		Assert.assertEquals(explorer, savedExplorer);
	}
	
	@Test(expected = NotFoundException.class)
	public void aExplorerShouldNotBeReturned() {
		Mockito.when(explorerRepository.find(Mockito.anyInt())).thenReturn(null);
		
		Explorer savedExplorer = nasaService.findExplorer(1);
		
		Assert.assertNull(savedExplorer);
	}
	
}