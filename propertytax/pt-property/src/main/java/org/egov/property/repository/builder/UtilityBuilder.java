package org.egov.property.repository.builder;

import java.util.List;

/**
 * 
 * @author Prasad This class will have all the common queries which will be use
 *         in the master & property as well
 *
 */
public class UtilityBuilder {

	public static final String UNIQUE_TENATANT_CODE_CHECK_QUERY = "select count(*) from ?"
			+ " where code = ? AND tenantid = ? AND id != ?";

	public static String getUniqueTenantCodeQuery(String tableName, String code, String tenantId, Long id) {

		StringBuffer uniqueQuery = new StringBuffer("select count(*) from " + tableName);
		uniqueQuery.append(" where code = '" + code + "'");
		uniqueQuery.append(" AND tenantId = '" + tenantId + "'");
		if (id != null) {
			uniqueQuery.append(" AND id !=" + id);
		}

		return uniqueQuery.toString();

	}

	public static String getUniqueTenantIdNameQuery(String tenantId, String code, String application, String tableName,
			Long id, List<Object> preparedStatementValues) {

		StringBuffer uniqueQuery = new StringBuffer("select count(*) from " + tableName);
		if (tenantId != null & !tenantId.isEmpty()) {
			uniqueQuery.append(" where tenantId =?");
			preparedStatementValues.add(tenantId);
		}

		if (code != null & !code.isEmpty()) {
			uniqueQuery.append(" AND code =?");
			preparedStatementValues.add(code);
		}

		if (application != null & !application.isEmpty()) {
			uniqueQuery.append(" AND application =?");
			preparedStatementValues.add(application);
		}		

		if (id != null) {
			uniqueQuery.append(" AND id !=?");
			preparedStatementValues.add(id);
		}

		return uniqueQuery.toString();
	}
}
