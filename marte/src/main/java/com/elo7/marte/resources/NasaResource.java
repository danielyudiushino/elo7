package com.elo7.marte.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.service.NasaService;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.resources.ro.ExplorerRO;
import com.elo7.marte.resources.ro.PlateauRO;
import com.elo7.marte.util.Converter;

@RestController
@RequestMapping("/")
public class NasaResource {

	@Autowired
	private NasaService nasaService;

	@RequestMapping(value = "/plateau", method = RequestMethod.POST)
	Object createPlateau(@RequestBody PlateauRO input) {
		return nasaService.definePlateau(new CoordinatesVO(input.getLat(),
				input.getLng()));
	}

	@RequestMapping(value = "/explorer", method = RequestMethod.POST)
	ExplorerRO createExplorer(@RequestBody ExplorerRO input) {
		Plateau plateau = nasaService.findPlateau(input.getPlateauId());
		Explorer explorer = nasaService.createExplorer(plateau,
				new CoordinatesVO(input.getLat(), input.getLng()), input.getDirection());
		return Converter.convert(explorer, ExplorerRO.class);
	}

	@RequestMapping(value = "/explorer/{id}/move", method = RequestMethod.POST)
	void move(@PathVariable int id,	@RequestBody String command) throws Exception {
		Explorer explorer = nasaService.findExplorer(id);
		nasaService.move(explorer, command);
	}

	@RequestMapping(value = "/explorer/{id}", method = RequestMethod.GET)
	ExplorerRO get(@PathVariable int id) {
		Explorer explorer = nasaService.findExplorer(id);
		return Converter.convert(explorer, ExplorerRO.class);
	}

}
