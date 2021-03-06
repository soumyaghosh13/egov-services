package org.egov.tl.commons.web.contract;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.egov.tl.commons.web.contract.enums.ApplicationTypeEnum;
import org.egov.tl.commons.web.contract.enums.BusinessNatureEnum;
import org.egov.tl.commons.web.contract.enums.OwnerShipTypeEnum;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TradeLicenseContract {

	private Long id;

	@JsonProperty("tenantId")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.tenantId.emptyspaces}")
	@NotEmpty(message="{error.license.tenantId.empty}")
	@Length(min = 4, max = 128, message="{error.license.tenantId.empty}")
	private String tenantId;

	
	private ApplicationTypeEnum applicationType;

	@JsonProperty("applicationNumber")
	private String applicationNumber;

	@JsonProperty("licenseNumber")
	private String licenseNumber;

	@JsonProperty("oldLicenseNumber") 
	@Pattern(regexp = ".*[^ ].*",message="{error.oldLicenseNumber.emptyspaces}")
	@Length( min =4, max =20, message="{error.oldLicenseNumber.empty}")
	private String oldLicenseNumber;

	
	@JsonProperty("applicationDate")
	private Long applicationDate;

	@JsonProperty("adhaarNumber")
	@Pattern(regexp = "[0-9]{12}", message = "{error.license.aadhaarnumber}")
	@Length(min = 12, max = 12, message = "{error.license.aadhaarnumber}")
	private String adhaarNumber;

	@NotEmpty(message = "{error.license.mobilenumber}")
	@JsonProperty("mobileNumber")
	@Length(min = 10, max = 10, message = "{error.license.mobilenumber}")
	@Pattern(regexp = "[0-9]{10}",  message = "{error.license.mobilenumber}")
	private String mobileNumber;

	@NotEmpty(message = "{error.license.ownername}")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.ownername}")
	@Length(min = 4, max = 100, message = "{error.license.ownername}")
	@JsonProperty("ownerName")
	private String ownerName;

	@NotEmpty(message = "{error.license.fatherspousename}")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.fatherspousename.empty}")
	@Length(min = 4, max = 100, message = "{error.license.fatherspousename}")
	@JsonProperty("fatherSpouseName")
	private String fatherSpouseName;

	@NotEmpty(message="{error.license.emailid}")
	@Length( min = 1, max = 50, message="{error.license.emailid}")
	@Email(message="{error.license.emailid}")
	@JsonProperty("emailId")
	private String emailId;

	@NotEmpty(message = "{error.license.owneraddress}")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.owneraddress.emptyspaces}")
	@Length( min = 4, max = 250, message = "{error.license.owneraddress}")
	@JsonProperty("ownerAddress")
	private String ownerAddress;

	@JsonProperty("propertyAssesmentNo")
	private String propertyAssesmentNo;

	@NotNull(message = "{error.license.locality}")
	@JsonProperty("localityId")
	private Integer localityId;

	@NotNull(message = "{error.license.revenueward}")
	@JsonProperty("revenueWardId")
	private Integer revenueWardId;

	@NotNull(message = "{error.license.adminward}")
	@JsonProperty("adminWardId")
	private Integer adminWardId;

	@NotEmpty(message = "{error.license.tradeaddress}")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.tradeaddress}")
	@JsonProperty("tradeAddress")
	@Length( min = 4, max = 250)
	private String tradeAddress;

	@NotNull(message="{error.license.ownershiptype}")
	@JsonProperty("ownerShipType")
	private OwnerShipTypeEnum ownerShipType;

	@NotEmpty(message = "{error.license.tradetitle}")
	@Pattern(regexp = ".*[^ ].*",message="{error.license.tradetitle.emptyspaces}")
	@JsonProperty("tradeTitle")
	@Length( min =4, max = 100, message = "{error.license.tradetitle}")
	private String tradeTitle;

	@NotNull(message = "{error.license.tradetype}")
	@JsonProperty("tradeType")
	private BusinessNatureEnum tradeType;

	@NotNull(message = "{error.license.category}")
	@JsonProperty("categoryId")
	private Long categoryId;

	@NotNull(message = "{error.license.subcategory}")
	@JsonProperty("subCategoryId")
	private Long subCategoryId;

	@NotNull(message = "{error.license.uom}")
	@JsonProperty("uomId")
	private Long uomId;

	@NotNull(message = "{error.license.quantity}")
	@Digits(integer=10, fraction=2,message="{error.license.quantity}")
	@JsonProperty("quantity")
	private Double quantity;
	
	@NotNull(message = "{error.license.validityyears}")
	@Min(1)
	@Max(10)
	@JsonProperty("validityYears")
	private Long validityYears;

	@JsonProperty("remarks")
	@Length( min = 1, max = 1000)
	private String remarks;

	@NotNull(message="{error.license.tradecommencementdate}")
	@JsonProperty("tradeCommencementDate")
	private Long tradeCommencementDate;

	@NotNull(message="{error.license.licensevalidfrom}")
	@JsonProperty("licenseValidFromDate")
	private Long licenseValidFromDate;

	@JsonProperty("agreementDate")
	private Long agreementDate;

	@JsonProperty("agreementNo")
	private String agreementNo;

	@JsonProperty("isLegacy")
	@NotNull(message="{error.license.islegacy}")
	private Boolean isLegacy = false;

	@JsonProperty("isPropertyOwner")
	@NotNull(message="{error.license.ispropertyowner}")
	private Boolean isPropertyOwner = false;
	
	@JsonProperty("active")
	@NotNull(message="{error.license.active}")
	private Boolean active = true;

	@JsonProperty("expiryDate")
	private Long expiryDate;

	@JsonProperty("feeDetails")
	@Valid
	private List<LicenseFeeDetailContract> feeDetails;

	@JsonProperty("supportDocuments")
	@Valid
	private List<SupportDocumentContract> supportDocuments;

	@JsonProperty("status")
	private Long status;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails;

}