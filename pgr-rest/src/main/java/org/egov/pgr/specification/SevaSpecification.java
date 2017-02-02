package org.egov.pgr.specification;

import org.egov.pgr.entity.*;
import org.egov.pgr.model.SevaSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SevaSpecification implements Specification<Complaint> {
    private SevaSearchCriteria criteria;

    public SevaSpecification(SevaSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Complaint> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Path<String> crn = root.get(Complaint_.crn);
        Path<ComplaintType> complaintType = root.get(Complaint_.complaintType);
        Path<String> code = complaintType.get(ComplaintType_.code);
        Path<ComplaintStatus> complaintStatus = root.get(Complaint_.status);
        Path<String> status = complaintStatus.get(ComplaintStatus_.name);
        Path<Date> createdDate = root.get(Complaint_.createdDate);
        Path<Date> lastModifiedDate = root.get(Complaint_.lastModifiedDate);

        final List<Predicate> predicates = new ArrayList<>();
        if (criteria.getServiceRequestId() != null) {
            predicates.add(criteriaBuilder.equal(crn, criteria.getServiceRequestId()));
        }

        if (criteria.getServiceCode() != null) {
            predicates.add(criteriaBuilder.equal(code, criteria.getServiceCode()));
        }

        if (criteria.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(status, criteria.getStatus()));
        }

        if (criteria.getStartDate() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(createdDate, criteria.getStartDate()));
        }

        if (criteria.getEndDate() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(createdDate, criteria.getEndDate()));
        }

        if (criteria.getLastModifiedDatetime() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(lastModifiedDate, criteria.getLastModifiedDatetime()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
