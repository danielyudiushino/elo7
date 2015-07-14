package com.elo7.marte.domain.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.repository.PlateauRepository;
import com.elo7.marte.domain.vo.CoordinatesVO;

@RunWith(PowerMockRunner.class)
public class WhenYouDefineAPlateau {

	@Mock
	private PlateauRepository plateauRepository;
	
	NasaService nasaService;
	
	@Before
	public void init() {
		nasaService = new NasaService();
		Whitebox.setInternalState(nasaService, "plateauRepository", plateauRepository);
	}
	
	@Test
	public void aPerfectPlateauShouldBeCreated() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));

		Mockito.when(plateauRepository.save(Mockito.any(Plateau.class))).thenReturn(plateau);
		
		Plateau savedPlateau = nasaService.definePlateau(new CoordinatesVO(1, 1));

		Assert.assertEquals(plateau, savedPlateau);
	}
	
}