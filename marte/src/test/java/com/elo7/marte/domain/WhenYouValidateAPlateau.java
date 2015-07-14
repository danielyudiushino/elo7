package com.elo7.marte.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.exception.BusinessException;

@RunWith(PowerMockRunner.class)
public class WhenYouValidateAPlateau {

	@Test
	public void aPerfectPlateauShouldBeCreated() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Assert.assertNotNull(plateau.validate());
	}
	
	@Test
	public void aCoordinateErrorPlateauShouldBeReturned() {
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(0, 1));
			plateau.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(1, bex.getErrors().size());
		}
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(0, 0));
			plateau.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(2, bex.getErrors().size());
		}
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 0));
			plateau.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(1, bex.getErrors().size());
		}
	}
	
}