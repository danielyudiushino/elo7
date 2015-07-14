package com.elo7.marte.domain.state;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.state.EastState;
import com.elo7.marte.domain.state.NorthState;
import com.elo7.marte.domain.state.SouthState;
import com.elo7.marte.domain.state.WestState;
import com.elo7.marte.domain.vo.CoordinatesVO;

@RunWith(PowerMockRunner.class)
public class WhenYouMove {

	@Test
	public void theLngShouldByAdded() {
		CoordinatesVO coordinates = new NorthState().move(new CoordinatesVO(0, 0));
		Assert.assertEquals(new CoordinatesVO(0, 1), coordinates);
	}
	
	@Test
	public void theLngShouldByDeduct() {
		CoordinatesVO coordinates = new SouthState().move(new CoordinatesVO(0, 0));
		Assert.assertEquals(new CoordinatesVO(0, -1), coordinates);
	}
	
	@Test
	public void theLatShouldByAdded() {
		CoordinatesVO coordinates = new EastState().move(new CoordinatesVO(0, 0));
		Assert.assertEquals(new CoordinatesVO(1, 0), coordinates);
	}
	
	@Test
	public void theLatShouldByDeduct() {
		CoordinatesVO coordinates = new WestState().move(new CoordinatesVO(0, 0));
		Assert.assertEquals(new CoordinatesVO(-1, 0), coordinates);
	}

}