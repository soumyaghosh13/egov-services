package org.egov.models;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandUpdateMisRequest {

	@NotNull
	private String tenantId; 
	
	@NotNull
	private Set<String> id;
	
	@NotNull
	private String consumerCode;

	private RequestInfo requestInfo;
}
