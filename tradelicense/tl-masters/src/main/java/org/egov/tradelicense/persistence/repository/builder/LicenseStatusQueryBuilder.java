package org.egov.tradelicense.persistence.repository.builder;

import org.egov.tradelicense.util.ConstantUtility;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class LicenseStatusQueryBuilder {

	private static final String licenseStatusTableName = ConstantUtility.LICENSE_STATUS_TABLE_NAME;

	public static final String INSERT_LICENSE_STATUS_QUERY = "INSERT INTO " + licenseStatusTableName
			+ " (tenantId, name, code, active, createdBy, lastModifiedBy, createdTime, lastModifiedTime)"
			+ " VALUES(:tenantId, :name, :code, :active, :createdBy, :lastModifiedBy, :createdTime, :lastModifiedTime)";

	public static final String UPDATE_LICENSE_STATUS_QUERY = "UPDATE " + licenseStatusTableName
			+ " SET tenantId = :tenantId, code = :code, name = :name, active = :active,"
			+ " lastModifiedBy = :lastModifiedBy, lastModifiedTime = :lastModifiedTime"
			+ " WHERE id = :id";

	public static String buildSearchQuery(String tenantId, Integer[] ids, String name, String code, String active,
			Integer pageSize, Integer offSet, MapSqlParameterSource parameters) {

		StringBuffer searchSql = new StringBuffer();
		searchSql.append("select * from " + licenseStatusTableName + " where ");
		searchSql.append(" tenantId = :tenantId ");
		parameters.addValue("tenantId",tenantId);

		if (ids != null && ids.length > 0) {

			String searchIds = "";
			int count = 1;
			for (Integer id : ids) {

				if (count < ids.length)
					searchIds = searchIds + id + ",";
				else
					searchIds = searchIds + id;

				count++;
			}
			searchSql.append(" AND id IN (" + searchIds + ") ");
		}

		if (code != null && !code.isEmpty()) {
			searchSql.append(" AND code = :code ");
			parameters.addValue("code",code);
		}

		if (name != null && !name.isEmpty()) {
			searchSql.append(" AND name = :name ");
			parameters.addValue("name",name);
		}

		if (active != null) {

			if (active.equalsIgnoreCase("False")) {
				searchSql.append(" AND active = :active ");
				parameters.addValue("active",false);
			}

			else if (active.equalsIgnoreCase("True")) {
				searchSql.append(" AND active = :active ");
				parameters.addValue("active",true);
			}

		}

		if (pageSize != null) {
			searchSql.append(" limit :pageSize ");
			parameters.addValue("pageSize" , pageSize);
		}

		if (offSet != null) {
			searchSql.append(" offset :offset ");
			parameters.addValue("offset" , offSet);
		}

		return searchSql.toString();

	}

}
