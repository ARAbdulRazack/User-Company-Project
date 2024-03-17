package com.example.LiquibaseProject.Specification;

import com.example.LiquibaseProject.Model.UserCompany;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class UserCompanySpecification {
    public static Specification<UserCompany> getUserCompaniesSpec(String userName, String companyName) {
        return (Root<UserCompany> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userName != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("name"), userName));
            }

            if (companyName != null) {
                predicates.add(criteriaBuilder.equal(root.get("company").get("name"), companyName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
