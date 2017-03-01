package org.egov.pgr.contracts.position;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDesignationResponse {

	@JsonProperty("designation")
	private DesignationResponse designation;

	@JsonProperty("department")
	private DepartmentResponse department;

	public DesignationResponse getDesignation() {
		return designation;
	}

	public DepartmentResponse getDepartment() {
		return department;
	}
}
