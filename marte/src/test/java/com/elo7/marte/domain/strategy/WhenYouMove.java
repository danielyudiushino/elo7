package com.elo7.marte.domain.strategy;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.state.WestState;
import com.elo7.marte.domain.strategy.LinearStrategy;
import com.elo7.marte.domain.strategy.SpinStrategy;
import com.elo7.marte.domain.strategy.Strategy;
import com.elo7.marte.domain.vo.CoordinatesVO;

@RunWith(PowerMockRunner.class)
public class WhenYouMove {

	@Test
	public void aLinearMoveShouldBeExecuted() {
		Explorer explorer = new Explorer(null, new CoordinatesVO(0, 0), "N");
		Strategy strategy = new LinearStrategy(explorer);
		strategy.move();
		Assert.assertEquals(new CoordinatesVO(0, 1), explorer.getCoordinates());
	}
	
	@Test
	public void aRightSpinMoveShouldBeExecuted() {
		Explorer explorer = new Explorer(null, new CoordinatesVO(0, 0), "S");
		Strategy strategy = new SpinStrategy(explorer, "R");
		strategy.move();
		Assert.assertThat(explorer.getState(), CoreMatchers.instanceOf(WestState.class));
	}
	
	@Test
	public void aLeftSpinMoveShouldBeExecuted() {
		Explorer explorer = new Explorer(null, new CoordinatesVO(0, 0), "N");
		Strategy strategy = new SpinStrategy(explorer, "L");
		strategy.move();
		Assert.assertThat(explorer.getState(), CoreMatchers.instanceOf(WestState.class));
	}
	
}