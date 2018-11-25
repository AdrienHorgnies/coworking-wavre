package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpaceSpecificationBuilder {

    private final List<SpaceCriterion> params;

    public SpaceSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpaceSpecificationBuilder with(SpaceCriterion criterion) {
        params.add(criterion);
        return this;
    }

    public Specification<Space> build() {
        if (params.size() == 0) {
            return null;
        }

        return null;
//        List<Specification> specifications = params.stream()
//            .map(SpaceSpecification::new)
//            .reduce();
//        }
    }
}
