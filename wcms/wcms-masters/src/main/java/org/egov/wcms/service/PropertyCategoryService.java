/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.wcms.service;

import org.egov.wcms.config.PropertiesManager;
import org.egov.wcms.producers.WaterMasterProducer;
import org.egov.wcms.repository.PropertyTypeCategoryTypeRepository;
import org.egov.wcms.web.contract.PropertyCategoryGetRequest;
import org.egov.wcms.web.contract.PropertyTypeCategoryTypeReq;
import org.egov.wcms.web.contract.PropertyTypeCategoryTypesRes;
import org.egov.wcms.web.contract.PropertyTypeResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PropertyCategoryService {

	@Autowired
	private PropertyTypeCategoryTypeRepository propertyCategoryRepository;

	@Autowired
	private WaterMasterProducer waterMasterProducer;

	@Autowired
	private PropertiesManager propertiesManager;

	@Autowired
	private RestPropertyTaxMasterService restPropertyTaxMasterService;

	public static final Logger logger = LoggerFactory.getLogger(PropertyCategoryService.class);

	public PropertyTypeCategoryTypeReq createPropertyCategory(final String topic, final String key,
			final PropertyTypeCategoryTypeReq propertyCategoryRequest) {

		final ObjectMapper mapper = new ObjectMapper();
		String propertyCategoryValue = null;
		try {
			logger.info("createPropertyCategory service::" + propertyCategoryRequest);
			propertyCategoryValue = mapper.writeValueAsString(propertyCategoryRequest);
			logger.info("propertyCategoryValue::" + propertyCategoryValue);
		} catch (final JsonProcessingException e) {
			logger.error("Exception Encountered : " + e);
		}
		try {
			waterMasterProducer.sendMessage(topic, key, propertyCategoryValue);
		} catch (final Exception ex) {
			logger.error("Exception Encountered : " + ex);
		}
		return propertyCategoryRequest;
	}

	public PropertyTypeCategoryTypeReq create(final PropertyTypeCategoryTypeReq propertyCategoryRequest) {
		return propertyCategoryRepository.persistCreatePropertyCategory(propertyCategoryRequest);
	}

	public PropertyTypeCategoryTypeReq update(final PropertyTypeCategoryTypeReq propertyCategoryRequest) {
		return propertyCategoryRepository.persistUpdatePropertyCategory(propertyCategoryRequest);
	}

	public PropertyTypeCategoryTypesRes getPropertyCategories(
			final PropertyCategoryGetRequest propertyCategoryGetRequest) {
		return propertyCategoryRepository.findForCriteria(propertyCategoryGetRequest);

	}

	public boolean checkIfMappingExists(final PropertyTypeCategoryTypeReq propertyCategoryRequest) {

		getPropertyTypeByName(propertyCategoryRequest);
		return propertyCategoryRepository.checkIfMappingExists(
				propertyCategoryRequest.getPropertyTypeCategoryType().getPropertyTypeId(),
				propertyCategoryRequest.getPropertyTypeCategoryType().getCategoryTypeName(),
				propertyCategoryRequest.getPropertyTypeCategoryType().getTenantId());
	}

	public Boolean getPropertyTypeByName(final PropertyTypeCategoryTypeReq propertyCategoryRequest) {
		Boolean isValidProperty = Boolean.FALSE;
		String url = propertiesManager.getPropertTaxServiceBasePathTopic()
				+ propertiesManager.getPropertyTaxServicePropertyTypeSearchPathTopic();
		url = url.replace("{name}", propertyCategoryRequest.getPropertyTypeCategoryType().getPropertyTypeName());
		url = url.replace("{tenantId}", propertyCategoryRequest.getPropertyTypeCategoryType().getTenantId());
		final PropertyTypeResponseInfo propertyTypes = restPropertyTaxMasterService.getPropertyTypes(url);
		if (propertyTypes.getPropertyTypesSize()) {
			isValidProperty = Boolean.TRUE;
			propertyCategoryRequest.getPropertyTypeCategoryType().setPropertyTypeId(
					propertyTypes.getPropertyTypes() != null && propertyTypes.getPropertyTypes().get(0) != null
							? propertyTypes.getPropertyTypes().get(0).getId() : "");

		}
		return isValidProperty;

	}

}
