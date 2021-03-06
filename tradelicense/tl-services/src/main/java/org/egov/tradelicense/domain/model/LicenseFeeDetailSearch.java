package org.egov.tradelicense.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenseFeeDetailSearch {

	public static final String TABLE_NAME = "egtl_fee_details";
	public static final String SEQUENCE_NAME = "seq_egtl_fee_details";

	private Long id;

	private Long licenseId;

	private String financialYear;

	private Double amount;

	private Boolean paid;

	private String createdBy;

	private String lastModifiedBy;

	private Long createdTime;

	private Long lastModifiedTime;
	
}