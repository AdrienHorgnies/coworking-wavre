package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


        List<Specification> specs = params.stream()
            .map(SpaceSpecification::new)
            .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specifications
                .where(result)
                .and(specs.get(i));
        }
        return result;
    }
}
