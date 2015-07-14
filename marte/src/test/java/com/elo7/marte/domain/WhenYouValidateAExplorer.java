package com.elo7.marte.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.exception.BusinessException;

@RunWith(PowerMockRunner.class)
public class WhenYouValidateAExplorer {

	@Test
	public void aPerfectExplorerShouldBeCreated() {
		Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
		Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, 1), "N");
		Assert.assertNotNull(explorer.validate());
	}
	
	@Test
	public void aCoordinateErrorShouldBeReturned() {
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
			Explorer explorer = new Explorer(plateau, new CoordinatesVO(-1, 0), "N");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(1, bex.getErrors().size());
		}
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
			Explorer explorer = new Explorer(plateau, new CoordinatesVO(-2, -2), "N");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(2, bex.getErrors().size());
		}
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
			Explorer explorer = new Explorer(plateau, new CoordinatesVO(1, -1), "N");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(1, bex.getErrors().size());
		}
	}
	
	@Test
	public void anInvalidPlateauErrorShouldBeReturned() {
		try {
			Explorer explorer = new Explorer(null, new CoordinatesVO(0, 0), "N");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals(1, bex.getErrors().size());
		}
	}
	
	@Test
	public void anInvalidDirectionErrorShouldBeReturned() {
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
			Explorer explorer = new Explorer(plateau, new CoordinatesVO(0, 0), "X");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals("Invalid direction", bex.getMessage());
		}
	}
	
	@Test
	public void anOutOfRangeErrorShouldBeReturned() {
		try {
			Plateau plateau = new Plateau(new CoordinatesVO(1, 1));
			Explorer explorer = new Explorer(plateau, new CoordinatesVO(2, 2), "N");
			explorer.validate();
			Assert.assertTrue("Nao deveria chegar aqui", false);
		} catch(BusinessException bex) {
			Assert.assertEquals("Out of plateau range", bex.getMessage());
		}
	}
	
}