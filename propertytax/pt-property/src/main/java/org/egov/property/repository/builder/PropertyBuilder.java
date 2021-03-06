package org.egov.property.repository.builder;

public class PropertyBuilder {

	public static final String AUDIT_DETAILS_FOR_PROPERTY_DETAILS = "select createdBy,lastModifiedBy,createdTime,"
			+ "lastModifiedTime from egpt_propertydetails where property= ?";

	public static final String INSERT_PROPERTY_QUERY = "INSERT INTO egpt_property ("
			+ "tenantId, upicNumber, oldUpicNumber, vltUpicNumber,creationReason, assessmentDate,"
			+ " occupancyDate, gisRefNo,isAuthorised, isUnderWorkflow, channel,"
			+ " createdBy,lastModifiedBy, createdTime,lastModifiedTime,demands,sequenceNo)"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String updatePropertyIsUnderWokflow = "UPDATE egpt_Property SET isUnderWorkflow = ? where upicNumber=?";

	public static String updatePropertyQuery() {

		StringBuffer propertyUpdateSQL = new StringBuffer();

		propertyUpdateSQL.append("UPDATE egpt_Property")
				.append(" SET tenantId = ? , upicNumber = ?, oldUpicNumber = ?, vltUpicNumber = ?,")
				.append("creationReason = ?, assessmentDate = ?, occupancyDate = ?, gisRefNo = ?,")
				.append(" isAuthorised = ?, isUnderWorkflow = ?, channel = ?,")
				.append(" lastModifiedBy = ?, lastModifiedTime = ?,demands = ?,sequenceNo = ?")
				.append(" WHERE id = ? ");

		return propertyUpdateSQL.toString();
	}

	public static final String isPropertyUnderWorkflow = "select isunderworkflow from egpt_property where upicNumber=?";

	public static final String AUDIT_DETAILS_QUERY = "select createdBy,lastModifiedBy,createdTime,"
			+ "lastModifiedTime from egpt_property where id= ?";

	public static final String PROPERTY_BY_TENANT_UPIC_NO = "select * from egpt_property where upicnumber=? and tenantId=?";

	public static final String UPDATE_TITLETRANSFERPROPERTY_QUERY = "UPDATE egpt_property set isUnderWorkflow = ?, lastModifiedBy = ?,"
			+ " lastModifiedTime = ? WHERE id = ?";

	public static final String getDemands = "select demands from egpt_property where upicnumber=?";

}
