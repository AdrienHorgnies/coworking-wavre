package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import com.ifosup.coworking.repository.SpaceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {

    private SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public List<Space> search(List<SpaceCriterion> criteria) {
        SpaceSpecificationBuilder builder = new SpaceSpecificationBuilder();
        for (SpaceCriterion criterion : criteria) {
            builder.with(criterion);
        }
        Specification specification = builder.build();

        return spaceRepository.findAll(specification);
    }
}
