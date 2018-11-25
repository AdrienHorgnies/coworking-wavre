package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Space;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.ifosup.coworking.service.ConditionOperator.*;

public class SpaceSpecification implements Specification<Space> {
    private SpaceCriterion criterion;

    public SpaceSpecification(SpaceCriterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public Predicate toPredicate(Root<Space> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criterion.operator == MIN) {
            return criteriaBuilder.greaterThanOrEqualTo(
                root.get(criterion.key), criterion.value
            );
        } else if (criterion.operator == MAX) {
            return criteriaBuilder.lessThanOrEqualTo(
                root.get(criterion.key), criterion.value
            );
        } else if (criterion.operator == EQUALS) {
            return criteriaBuilder.equal(
                root.get(criterion.key), criterion.value
            );
        } else if (criterion.operator == CONTAINS) {
            return criteriaBuilder.like(
                root.get(criterion.key), "%" + criterion.value + "%"
            );
        }
        return null;
    }
}
