package com.elo7.marte.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.GZIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elo7.marte.domain.Explorer;
import com.elo7.marte.domain.Plateau;
import com.elo7.marte.domain.service.NasaService;
import com.elo7.marte.domain.vo.CoordinatesVO;
import com.elo7.marte.resources.ro.ExplorerRO;
import com.elo7.marte.resources.ro.PlateauRO;
import com.elo7.marte.util.Converter;

@Path("/")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class NasaResource {

	@Autowired
	private NasaService nasaService;

	@POST
	@GZIP
	@Path("/plateau")
	Object createPlateau(PlateauRO input) {
		return nasaService.definePlateau(new CoordinatesVO(input.getLat(),
				input.getLng()));
	}

	@POST
	@GZIP
	@Path("/explorer")
	ExplorerRO createExplorer(ExplorerRO input) {
		Plateau plateau = nasaService.findPlateau(input.getPlateauId());
		Explorer explorer = nasaService.createExplorer(plateau,
				new CoordinatesVO(input.getLat(), input.getLng()), input.getDirection());
		return Converter.convert(explorer, ExplorerRO.class);
	}

	@POST
	@GZIP
	@Path("/explorer/{id}/move")
	void move(@PathParam(value = "id") int id, String command) throws Exception {
		Explorer explorer = nasaService.findExplorer(id);
		nasaService.move(explorer, command);
	}

	@GET
	@GZIP
	@Path("/explorer/{id}/move")
	ExplorerRO get(@PathParam(value = "id") int id) {
		Explorer explorer = nasaService.findExplorer(id);
		return Converter.convert(explorer, ExplorerRO.class);
	}

}
