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

package org.egov.wcms.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.request.User;
import org.egov.wcms.model.CategoryType;
import org.egov.wcms.repository.builder.CategoryTypeQueryBuilder;
import org.egov.wcms.repository.rowmapper.CategoryTypeRowMapper;
import org.egov.wcms.web.contract.CategoryTypeGetRequest;
import org.egov.wcms.web.contract.CategoryTypeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CategoryTypeRepository.class)
public class CategoryTypeRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private CategoryTypeQueryBuilder categoryQueryBuilder;

    @Mock
    private CategoryTypeRowMapper categoryRowMapper;

    @InjectMocks
    private CategoryTypeRepository categoryTypeRepository;
    
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void test_Should_Create_Category_Valid() {
        final CategoryTypeRequest categoryRequest = new CategoryTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final User user = new User();
        user.setId(1l);
        requestInfo.setUserInfo(user);
        categoryRequest.setRequestInfo(requestInfo);
        final List<CategoryType> categoryList = new ArrayList<>();
        final CategoryType categoryType = getCategory();
        categoryList.add(categoryType);
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);
        assertTrue(categoryRequest.equals(categoryTypeRepository.persistCreateCategory(categoryRequest)));
    }

   
    @Test
    public void test_Should_Update_Category_Valid() {
        final CategoryTypeRequest categoryRequest = new CategoryTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final User user = new User();
        user.setId(1l);
        requestInfo.setUserInfo(user);
        categoryRequest.setRequestInfo(requestInfo);
        final List<CategoryType> categoryList = new ArrayList<>();
        final CategoryType categoryType = getCategory();
        categoryList.add(categoryType);
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);
        assertTrue(categoryRequest.equals(categoryTypeRepository.persistModifyCategory(categoryRequest)));
    }

   

    @Test
    public void test_Should_Find_CategoryType_Valid() {
        final List<Object> preparedStatementValues = new ArrayList<>();
        final CategoryTypeGetRequest categoryGetRequest = Mockito.mock(CategoryTypeGetRequest.class);
        final String queryString = "MyQuery";
        when(categoryQueryBuilder.getQuery(categoryGetRequest, preparedStatementValues)).thenReturn(queryString);
        final List<CategoryType> connectionCategories = new ArrayList<>();
        when(jdbcTemplate.query(queryString, preparedStatementValues.toArray(), categoryRowMapper))
                .thenReturn(connectionCategories);

        assertTrue(
                connectionCategories.equals(categoryTypeRepository.findForCriteria(categoryGetRequest)));
    }

    private CategoryType getCategory() {
        final CategoryType category = new CategoryType();
        category.setCode("23");
        category.setName("New Category");
        category.setDescription("New Category of Connection");
        category.setActive(true);
        return category;
    }
}
