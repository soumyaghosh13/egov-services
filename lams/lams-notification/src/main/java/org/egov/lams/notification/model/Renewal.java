package org.egov.lams.notification.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Renewal {

	@JsonProperty("renewalOrderNo")
	private String renewalOrderNumber;

	@JsonProperty("renewalOrderDate")
	private Date renewalOrderDate;

	@JsonProperty("reasonForRenewal")
	private String reasonForRenewal;
}
