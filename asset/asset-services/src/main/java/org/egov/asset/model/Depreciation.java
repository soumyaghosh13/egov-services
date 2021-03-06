package org.egov.asset.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Depreciation extends DepreciationCriteria {

    private AuditDetails auditDetails;

    private List<DepreciationDetail> depreciationDetails = new ArrayList<>();

    @Builder
    private Depreciation(final DepreciationCriteria depreciationCriteria, final Long voucherReference,
            final AuditDetails auditDetails, final List<DepreciationDetail> depreciationDetails) {
        super(depreciationCriteria);
        this.auditDetails = auditDetails;
        this.depreciationDetails = depreciationDetails;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Depreciation other = (Depreciation) obj;
        if (auditDetails == null) {
            if (other.auditDetails != null)
                return false;
        } else if (!auditDetails.equals(other.auditDetails))
            return false;
        if (depreciationDetails == null) {
            if (other.depreciationDetails != null)
                return false;
        } else if (!depreciationDetails.equals(other.depreciationDetails))
            return false;
        return true;
    }

}
