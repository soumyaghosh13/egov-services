package org.egov.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.enums.UnitTypeEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A logical group of rooms on a floor Author : Narendra
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("unitNo")
	@NotNull
	private Integer unitNo = null;

	@JsonProperty("units")
	private List<Unit> units;

	@JsonProperty("unitType")
	@NotNull
	private UnitTypeEnum unitType = null;

	@JsonProperty("length")
	private Double length = null;

	@JsonProperty("width")
	private Double width = null;

	@JsonProperty("builtupArea")
	@NotNull
	private Double builtupArea = null;

	@JsonProperty("assessableArea")
	@NotNull
	private Double assessableArea = null;

	@JsonProperty("bpaBuiltupArea")
	private Double bpaBuiltupArea = null;

	@JsonProperty("bpaNo")
	@Size(min = 1, max = 16)
	private String bpaNo = null;

	@JsonProperty("bpaDate")
	private String bpaDate = null;

	@JsonProperty("usage")
	@NotNull
	@Size(min = 1, max = 16)
	private String usage = null;

	@JsonProperty("occupancyType")
	@NotNull
	@Size(min = 1, max = 64)
	private String occupancyType = null;

	@JsonProperty("occupierName")
	@Size(min = 1, max = 4000)
	private String occupierName = null;

	@JsonProperty("firmName")
	@Size(min = 1, max = 128)
	private String firmName = null;

	@JsonProperty("rentCollected")
	private Double rentCollected = null;

	@JsonProperty("structure")
	@NotNull
	@Size(min = 1, max = 64)
	private String structure = null;

	@JsonProperty("age")
	@Size(min = 1, max = 64)
	private String age = null;

	@JsonProperty("exemptionReason")
	@Size(min = 1, max = 32)
	private String exemptionReason = null;

	@JsonProperty("isStructured")
	private Boolean isStructured = true;

	@JsonProperty("occupancyDate")
	private String occupancyDate = null;

	@JsonProperty("constCompletionDate")
	private String constCompletionDate = null;

	@JsonProperty("manualArv")
	private Double manualArv = null;

	@JsonProperty("arv")
	private Double arv = 0.0d;

	@JsonProperty("electricMeterNo")
	@Size(min = 1, max = 64)
	private String electricMeterNo = null;

	@JsonProperty("waterMeterNo")
	@Size(min = 1, max = 64)
	private String waterMeterNo = null;

	@JsonProperty("parentid")
	private Long parentId = null;

	@JsonProperty("isAuthorised")
	private Boolean isAuthorised = true;

	@JsonProperty("constructionStartDate")
	private String constructionStartDate = null;

	@JsonProperty("landCost")
	private Double landCost = null;

	@JsonProperty("buildingCost")
	private Double buildingCost = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;


}
