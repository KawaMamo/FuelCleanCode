package org.example.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.TransportationEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TransSpecifications implements Specification<TransportationEntity> {
    private List<SearchCriteria> criteriaList;

    public TransSpecifications(List<SearchCriteria> searchCriteria) {
        this.criteriaList = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<TransportationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            }else if (criteria.getOperation().equalsIgnoreCase("<")) {
                predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            }else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                } else {
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                }
            }else if(Character.isAlphabetic(criteria.getField().charAt(0))){
                if(criteria.getOperation().equalsIgnoreCase(">")){
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()).get(criteria.getField()), criteria.getValue().toString()));
                }else if(criteria.getOperation().equalsIgnoreCase("<")){
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()).get(criteria.getField()), criteria.getValue().toString()));
                }else if(criteria.getOperation().equalsIgnoreCase(":")){
                    predicates.add(builder.equal(root.get(criteria.getKey()).get(criteria.getField()), criteria.getValue()));
                }
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
