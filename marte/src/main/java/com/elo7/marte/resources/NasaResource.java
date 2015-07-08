package com.elo7.marte.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.marte.domain.am.NasaService;
import com.elo7.marte.domain.ria.Explorer;
import com.elo7.marte.domain.ria.Plateau;
import com.elo7.marte.domain.ria.vo.CoordinatesVO;
import com.elo7.marte.resources.am.ro.ExplorerRO;
import com.elo7.marte.resources.am.ro.PlateauRO;

@ComponentScan
@RestController
@RequestMapping("/{type}")
public class NasaResource {
	
	@Autowired
	@Qualifier("com.elo7.marte.domain.am.NasaService")
	private NasaService nasaAmService;
	
	@Autowired
	@Qualifier("com.elo7.marte.domain.ria.NasaService")
	private com.elo7.marte.domain.ria.NasaService nasaRiaService;
	
	@RequestMapping(value="/plateau", method = RequestMethod.POST)
    Object createPlateau(@PathVariable String type, @RequestBody PlateauRO input) {
		if("am".equals(type)) {
			return nasaAmService.createPlateau(input.getLat(), input.getLng());
		} else {
			return nasaRiaService.definePlateau(new CoordinatesVO(input.getLat(), input.getLng()));
		}
    }
	
	@RequestMapping(value="/explorer", method = RequestMethod.POST)
    Object createPlateau(@PathVariable String type, @RequestBody ExplorerRO input) {
		if("am".equals(type)) {
			return nasaAmService.createExplorer(input.getIdPlateau(), input.getLat(), input.getLng(), input.getDirection());
		} else {
			Plateau plateau = nasaRiaService.findPlateau(input.getIdPlateau());
			return nasaRiaService.createExplorer(plateau, new CoordinatesVO(input.getLat(), input.getLng()), input.getDirection());
		}
    }
	
	@RequestMapping(value="/explorer/{id}/move", method = RequestMethod.POST)
	void move(@PathVariable String type, @PathVariable int id, @RequestBody String command) throws Exception {
		if("am".equals(type)) {
			nasaAmService.move(id, command);
		} else {
			Explorer explorer = nasaRiaService.findExplorer(id);
			nasaRiaService.move(explorer, command);
		}
	}
	
	@RequestMapping(value="/explorer/{id}", method = RequestMethod.GET)
    Object get(@PathVariable String type, @PathVariable int id) {
		if("am".equals(type)) {
			return nasaAmService.find(id);
		} else {
			return nasaRiaService.findExplorer(id);
		}
    }

}
