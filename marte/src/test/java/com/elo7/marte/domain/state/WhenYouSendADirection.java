package com.elo7.marte.domain.state;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.elo7.marte.domain.state.EastState;
import com.elo7.marte.domain.state.NorthState;
import com.elo7.marte.domain.state.SouthState;
import com.elo7.marte.domain.state.State;
import com.elo7.marte.domain.state.StateFactory;
import com.elo7.marte.domain.state.WestState;
import com.elo7.marte.exception.BusinessException;

@RunWith(PowerMockRunner.class)
public class WhenYouSendADirection {

	@Test
	public void theNorthDirectionShouldByReturned() {
		State state = StateFactory.directionFactory("N");
		Assert.assertThat(state, CoreMatchers.instanceOf(NorthState.class));
	}
	
	@Test
	public void theSouthDirectionShouldByReturned() {
		State state = StateFactory.directionFactory("S");
		Assert.assertThat(state, CoreMatchers.instanceOf(SouthState.class));
	}
	
	@Test
	public void theEastDirectionShouldByReturned() {
		State state = StateFactory.directionFactory("L");
		Assert.assertThat(state, CoreMatchers.instanceOf(EastState.class));
	}
	
	@Test
	public void theWestDirectionShouldByReturned() {
		State state = StateFactory.directionFactory("O");
		Assert.assertThat(state, CoreMatchers.instanceOf(WestState.class));
	}
	
	@Test(expected=BusinessException.class)
	public void aDirectionShouldNotByReturned() {
		StateFactory.directionFactory("XXX");
	}

}