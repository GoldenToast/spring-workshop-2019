package de.inmediasp.springws.zoo.buildings.web;

import de.inmediasp.springws.zoo.buildings.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class BuildingResourceProcessor implements ResourceProcessor<Resource<Building>> {

	@Autowired
	private EntityLinks entityLinks;

	@Override
	public Resource<Building> process(Resource<Building> resource) {

		Building building = resource.getContent();

		LinkBuilder linkBuilder = entityLinks.linkForSingleResource(building);
		if (building.isLocked()) {
			resource.add(linkBuilder.withRel("unlock"));
		}else{
			resource.add(linkBuilder.withRel("lock"));
		}

		return resource;
	}
}