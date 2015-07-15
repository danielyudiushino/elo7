package com.elo7.marte.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.service.NasaService;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.resources.ro.ExplorerRO;
import com.elo7.marte.resources.ro.PlateauRO;
import com.elo7.marte.util.Converter;

@RestController
public class NasaResource {

	@Autowired
	private NasaService nasaService;

	@RequestMapping(value = "/plateau", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<PlateauRO> createPlateau(@RequestBody PlateauRO input) {
		Plateau plateau = nasaService.definePlateau(new CoordinatesVO(input.getLat(), input.getLng()));
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/plateau/{id}").build().expand(plateau.getId()).toUri();
		return ResponseEntity.created(location).body(Converter.convert(plateau, PlateauRO.class));
	}

	@RequestMapping(value = "/explorer", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<ExplorerRO> createExplorer(@RequestBody ExplorerRO input) {
		Explorer explorer = nasaService.createExplorer(input.getPlateauId(), new CoordinatesVO(input.getLat(), input.getLng()), input.getDirection());
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/explorer/{id}").build().expand(explorer.getId()).toUri();
		return ResponseEntity.created(location).body(Converter.convert(explorer, ExplorerRO.class));
	}

	@RequestMapping(value = "/explorer/{id}/move", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<Void> move(@PathVariable int id, @RequestBody String command)
			throws Exception {
		Explorer explorer = nasaService.findExplorer(id);
		nasaService.move(explorer, command);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/explorer/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<ExplorerRO> getExplorer(@PathVariable int id) {
		Explorer explorer = nasaService.findExplorer(id);
		return ResponseEntity.ok(Converter.convert(explorer, ExplorerRO.class));
	}

	@RequestMapping(value = "/plateau/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<PlateauRO> getPlateau(@PathVariable int id) {
		Plateau plateau = nasaService.findPlateau(id);
		return ResponseEntity.ok(Converter.convert(plateau, PlateauRO.class));
	}

}
