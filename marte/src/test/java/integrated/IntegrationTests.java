package integrated;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.elo7.marte.Main;
import com.elo7.marte.resources.ro.ExplorerRO;
import com.elo7.marte.resources.ro.PlateauRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class IntegrationTests {

	@Value("${local.server.port}")
	int port;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void aPlateauShouldBeReturned() {
		PlateauRO plateau = createPlateau(1, 1);
		
		PlateauRO body = findPlateau(plateau.getId());
		
		Assert.assertNotNull(body.getId());
		Assert.assertEquals(1, body.getLat());
		Assert.assertEquals(1, body.getLng());
	}
	
	@Test
	public void aPlateauShouldNotBeReturned() {
		try {
			restTemplate.getForEntity("http://localhost:"+port+"/plateau/{id}", PlateauRO.class, 1);
		} catch (HttpClientErrorException ex) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}
	
	@Test
	public void aPlateauShouldBeCreated() {
			PlateauRO body = createPlateau(1, 1);
			
			Assert.assertNotNull(body.getId());
			Assert.assertEquals(1, body.getLat());
			Assert.assertEquals(1, body.getLng());
	}
	
	@Test
	public void aPlateauShouldNotBeCreated() {
		try {
			restTemplate.postForEntity("http://localhost:"+port+"/plateau", new PlateauRO(), PlateauRO.class);
		} catch (HttpClientErrorException ex) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
		}
	}
	
	@Test
	public void anExplorerShouldBeCreated() {
		PlateauRO plateau = createPlateau(1, 1);
		ExplorerRO body = createExplorer(plateau, 1, 1, "N");
		
		Assert.assertNotNull(body.getId());
		Assert.assertEquals(1, body.getLat());
		Assert.assertEquals(1, body.getLng());
		Assert.assertEquals("N", body.getDirection());
	}
	
	@Test
	public void anExplorerShouldNotBeCreated() {
		try {
			restTemplate.postForEntity("http://localhost:"+port+"/explorer", new ExplorerRO(), ExplorerRO.class);
		} catch (HttpClientErrorException ex) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
		}
	}
	
	@Test
	public void anExplorerShouldBeMoved() {
		PlateauRO plateau = createPlateau(1, 1);
		ExplorerRO explorer = createExplorer(plateau, 0, 0, "N");
		
		restTemplate.put("http://localhost:"+port+"/explorer/{id}/move", "M", explorer.getId());
		
		ExplorerRO body = findExplorer(explorer.getId());
		
		Assert.assertNotNull(body.getId());
		Assert.assertEquals(0, body.getLat());
		Assert.assertEquals(1, body.getLng());
		Assert.assertEquals("N", body.getDirection());
	}
	
	@Test
	public void anExplorerShouldBeReturned() {
		PlateauRO plateau = createPlateau(1, 1);
		ExplorerRO explorer = createExplorer(plateau, 0, 0, "N");
		
		ExplorerRO body = findExplorer(explorer.getId());
		
		Assert.assertNotNull(body.getId());
		Assert.assertEquals(0, body.getLat());
		Assert.assertEquals(0, body.getLng());
		Assert.assertEquals("N", body.getDirection());
	}

	@Test
	public void anExplorerShouldNotBeReturned() {
		try {
			restTemplate.getForEntity("http://localhost:"+port+"/explorer/{id}", ExplorerRO.class, 1);
		} catch (HttpClientErrorException ex) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}
	
	private PlateauRO findPlateau(int id) {
		ResponseEntity<PlateauRO> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/plateau/{id}", PlateauRO.class, id);
		PlateauRO body = responseEntity.getBody();
		return body;
	}
	
	private ExplorerRO findExplorer(int id) {
		ResponseEntity<ExplorerRO> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/explorer/{id}", ExplorerRO.class, id);
		ExplorerRO body = responseEntity.getBody();
		return body;
	}
	
	private ExplorerRO createExplorer(PlateauRO plateau, int lat, int lng, String direction) {
		ExplorerRO ro = new ExplorerRO();
		ro.setPlateauId(plateau.getId());
		ro.setLat(lat);
		ro.setLng(lng);
		ro.setDirection(direction);
		ResponseEntity<ExplorerRO> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/explorer", ro, ExplorerRO.class);
		ExplorerRO body = responseEntity.getBody();
		return body;
	}

	private PlateauRO createPlateau(int lat, int lng) {
		PlateauRO ro = new PlateauRO();
		ro.setLat(lat);
		ro.setLng(lng);
		ResponseEntity<PlateauRO> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/plateau", ro, PlateauRO.class);
		PlateauRO body = responseEntity.getBody();
		return body;
	}

}