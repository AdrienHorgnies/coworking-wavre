package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import com.ifosup.coworking.domain.enumeration.SpaceType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;

import static com.ifosup.coworking.service.ConditionOperator.*;

public class SpaceSpecification implements Specification<Space> {
    private SpaceCriterion criterion;

    public SpaceSpecification(SpaceCriterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public Predicate toPredicate(Root<Space> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Path<String> path = path(root);

        if (criterion.operator == MIN) {
            return criteriaBuilder.greaterThanOrEqualTo(
                path, criterion.value
            );
        } else if (criterion.operator == MAX) {
            return criteriaBuilder.lessThanOrEqualTo(
                path, criterion.value
            );
        } else if (criterion.operator == EQUALS) {
            SpaceType value = null;
            if (criterion.key.equals("type")) {
                value = SpaceType.valueOf(criterion.value);
            }

            return criteriaBuilder.equal(
                path, value == null ? criterion.value : value
            );
        } else if (criterion.operator == CONTAINS) {
            return criteriaBuilder.like(
                path, "%" + criterion.value + "%"
            );
        }
        return null;
    }

    private Path<String> path(Root<Space> root) {
        String[] attributes = criterion.key.split("\\.");

        if (attributes.length == 1) {
            return root.get(criterion.key);
        }

        String[] relationships = Arrays.copyOf(attributes, attributes.length - 1);
        String finalAttribute = attributes[attributes.length - 1];

        Join<Space, ?> join = root
            .join(relationships[0], JoinType.INNER);

        for (int idx = 1; idx < relationships.length; idx++) {
            join = join.join(relationships[idx], JoinType.INNER);
        }

        return join.get(finalAttribute);
    }
}
