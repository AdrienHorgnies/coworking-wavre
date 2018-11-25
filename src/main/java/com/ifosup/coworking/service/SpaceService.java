package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {

    public List<Space> search(List<SpaceCriterion> criteria) {
        for (SpaceCriterion criterion : criteria) {
            System.out.println(criterion.key + " " + criterion.operator + " " + criterion.value);
            new SpaceSpecification(criterion);
        }
        return null;
    }
}
