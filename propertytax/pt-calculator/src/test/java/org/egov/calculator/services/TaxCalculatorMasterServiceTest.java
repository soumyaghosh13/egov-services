package org.egov.calculator.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.egov.calculator.PtCalculatorApplication;
import org.egov.calculator.service.TaxCalculatorMasterService;
import org.egov.enums.CalculationFactorTypeEnum;
import org.egov.models.AuditDetails;
import org.egov.models.CalculationFactor;
import org.egov.models.CalculationFactorRequest;
import org.egov.models.CalculationFactorResponse;
import org.egov.models.GuidanceValue;
import org.egov.models.GuidanceValueRequest;
import org.egov.models.GuidanceValueResponse;
import org.egov.models.RequestInfo;
import org.egov.models.TaxPeriod;
import org.egov.models.TaxPeriodRequest;
import org.egov.models.TaxPeriodResponse;
import org.egov.models.TaxRates;
import org.egov.models.TaxRatesRequest;
import org.egov.models.TaxRatesResponse;
import org.egov.models.UserInfo;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PtCalculatorApplication.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaxCalculatorMasterServiceTest {

    @Autowired
    TaxCalculatorMasterService taxCalculatorMasterService;

    @Autowired
    Environment environment;

    public Long factorId = 1l;

    public Long taxRatesId = 1l;

    @Test
    public void createFactor() {
        String tenantId = "default";
        RequestInfo requestInfo = getRequestInfoObject();

        List<CalculationFactor> calculationFactors = new ArrayList<>();

        CalculationFactor calculationFactor = new CalculationFactor();
        calculationFactor.setTenantId("default");
        calculationFactor.setFactorCode("propertytax");
        calculationFactor.setFactorType(CalculationFactorTypeEnum.AGE);
        calculationFactor.setFactorValue(1234.12);
        calculationFactor.setFromDate("10/06/2007 00:00:00");
        calculationFactor.setToDate("25/06/2017 00:00:00");
        long createdTime = new Date().getTime();

        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("pavan");
        auditDetails.setLastModifiedBy("pavan");
        auditDetails.setCreatedTime(createdTime);
        auditDetails.setLastModifiedTime(createdTime);

        calculationFactor.setAuditDetails(auditDetails);
        calculationFactors.add(calculationFactor);

        CalculationFactorRequest calculationFactorRequest = new CalculationFactorRequest();
        calculationFactorRequest.setCalculationFactors(calculationFactors);
        calculationFactorRequest.setRequestInfo(requestInfo);

        try {
            CalculationFactorResponse calculationFactorResponse = taxCalculatorMasterService.createFactor(tenantId,
                    calculationFactorRequest);
            if (calculationFactorResponse.getCalculationFactors().size() == 0)
                assertTrue(false);

            assertTrue(true);

        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void modifyFactor() {
        String tenantId = "default";
        RequestInfo requestInfo = getRequestInfoObject();
        List<CalculationFactor> calculationFactors = new ArrayList<>();

        CalculationFactor calculationFactor = new CalculationFactor();
        calculationFactor.setTenantId("default");
        calculationFactor.setFactorCode("propertytax");
        calculationFactor.setFactorType(CalculationFactorTypeEnum.AGE);
        calculationFactor.setFactorValue(1234.12);
        calculationFactor.setFromDate("10/06/2007 00:00:00");
        calculationFactor.setToDate("25/06/2017 00:00:00");
        calculationFactor.setId(factorId);
        long createdTime = new Date().getTime();

        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("pavan");
        auditDetails.setLastModifiedBy("pavan");
        auditDetails.setCreatedTime(createdTime);
        auditDetails.setLastModifiedTime(createdTime);

        calculationFactor.setAuditDetails(auditDetails);
        calculationFactors.add(calculationFactor);

        CalculationFactorRequest calculationFactorRequest = new CalculationFactorRequest();
        calculationFactorRequest.setCalculationFactors(calculationFactors);
        calculationFactorRequest.setRequestInfo(requestInfo);

        try {
            CalculationFactorResponse calculationFactorResponse = taxCalculatorMasterService.updateFactor(tenantId,
                    calculationFactorRequest);

            if (calculationFactorResponse.getCalculationFactors().size() > 0 && calculationFactorRequest
                    .getCalculationFactors().equals(calculationFactorResponse.getCalculationFactors()))
                assertTrue(true);
            else
                assertTrue(false);

        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void searchFactor() {

        String tenantId = "default";
        String factorType = "age";
        String validDate = "16/06/2007";
        String code = "propertytax";
        RequestInfo requestInfo = getRequestInfoObject();

        try {
            CalculationFactorResponse calculationFactorResponse = taxCalculatorMasterService.getFactor(requestInfo,
                    tenantId, factorType, validDate, code);
            if (calculationFactorResponse.getCalculationFactors().size() == 0) {
                assertTrue(false);
            }

            assertTrue(true);

        } catch (Exception e) {
            assertTrue(false);
        }

    }

    /**
     * Description: test case for create guidance value
     * 
     * @throws Exception
     */
    @Test
    public void createGuidanceValue() throws Exception {
        try {

            String tenantId = "default";

            List<GuidanceValue> guidanceValue = new ArrayList<GuidanceValue>();

            GuidanceValue master = new GuidanceValue();
            AuditDetails auditDetails = new AuditDetails();
            auditDetails.setCreatedBy("anil");
            auditDetails.setCreatedTime((long) 123456);
            auditDetails.setLastModifiedBy("anil");
            auditDetails.setLastModifiedTime((long) 123456);

            master.setTenantId("default");
            master.setName("anil");
            master.setBoundary("b1");
            master.setStructure("rectangle");
            master.setUsage("propertyuse");
            master.setSubUsage("propertyusage");
            master.setOccupancy("anil");
            master.setValue((double) 252);
            master.setFromDate("25/10/2016 00:00:00");
            master.setToDate("25/10/2017 00:00:00");

            master.setAuditDetails(auditDetails);
            guidanceValue.add(master);

            GuidanceValueRequest guidanceValueRequest = new GuidanceValueRequest();
            guidanceValueRequest.setRequestInfo(getRequestInfoObject());
            guidanceValueRequest.setGuidanceValues(guidanceValue);

            GuidanceValueResponse guidanceValueResponse = taxCalculatorMasterService.createGuidanceValue(tenantId,
                    guidanceValueRequest);
            if (guidanceValueResponse.getGuidanceValues().size() == 0) {
                assertTrue(false);
            }
            assertTrue(true);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Description: Test case for update guidance value
     * 
     * @throws Exception
     */
    @Test
    public void modityGuidanceValue() throws Exception {
        try {

            String tenantId = "default";

            List<GuidanceValue> guidanceValue = new ArrayList<GuidanceValue>();

            GuidanceValue master = new GuidanceValue();
            AuditDetails auditDetails = new AuditDetails();
            auditDetails.setCreatedBy("anil");
            auditDetails.setCreatedTime((long) 123456);
            auditDetails.setLastModifiedBy("anil");
            auditDetails.setLastModifiedTime((long) 123456);
            master.setId((long) 1);
            master.setTenantId("default");
            master.setName("kumar");
            master.setBoundary("b2");
            master.setStructure("rectangle");
            master.setUsage("propertyuse");
            master.setSubUsage("propertyusage");
            master.setOccupancy("anil");
            master.setValue((double) 252);
            master.setFromDate("25/10/2016 00:00:00");
            master.setToDate("25/10/2017 00:00:00");
            master.setAuditDetails(auditDetails);
            guidanceValue.add(master);

            GuidanceValueRequest guidanceValueRequest = new GuidanceValueRequest();
            guidanceValueRequest.setRequestInfo(getRequestInfoObject());
            guidanceValueRequest.setGuidanceValues(guidanceValue);

            GuidanceValueResponse guidanceValueResponse = taxCalculatorMasterService.updateGuidanceValue(tenantId,
                    guidanceValueRequest);
            if (guidanceValueResponse.getGuidanceValues().size() > 0
                    && guidanceValueRequest.getGuidanceValues().equals(guidanceValueResponse.getGuidanceValues()))
                assertTrue(true);
            else
                assertTrue(false);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Description: Test case for guidance value search
     * 
     * @throws Exception
     */
    @Test
    public void searchGuidanceValue() throws Exception {
        try {

            String tenantId = "default";
            String boundary = "b2";
            String validDate = "25-11-2016";
            String structure = "rectangle";
            String usage = "propertyuse";
            String subUsage = "propertyusage";
            String occupancy = "anil";

            GuidanceValueResponse guidanceValueResponse = taxCalculatorMasterService.getGuidanceValue(
                    getRequestInfoObject(), tenantId, boundary, structure, usage, subUsage, occupancy, validDate);
            if (guidanceValueResponse.getGuidanceValues().size() == 0) {
                assertTrue(false);
            }
            assertTrue(true);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * This will test whether the tax period will be created successfully or not
     */
    @Test
    public void createTaxPeriod() {
        List<TaxPeriod> taxPeriods = new ArrayList<>();
        TaxPeriod taxPeriod = new TaxPeriod();

        String tenantId = "1234";
        taxPeriod.setTenantId("1234");
        taxPeriod.setCode("MON");
        taxPeriod.setFinancialYear("2017-18");
        taxPeriod.setFromDate("02/02/2017 00:00:00");
        taxPeriod.setToDate("05/02/2017 00:00:00");
        taxPeriod.setPeriodType("Yearly");

        long createdTime = new Date().getTime();
        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("prasad");
        auditDetails.setLastModifiedBy("prasad");
        auditDetails.setCreatedTime(createdTime);
        auditDetails.setLastModifiedTime(createdTime);

        taxPeriod.setAuditDetails(auditDetails);

        taxPeriods.add(taxPeriod);
        TaxPeriodRequest taxPeriodRequest = new TaxPeriodRequest();
        taxPeriodRequest.setRequestInfo(getRequestInfoObject());
        taxPeriodRequest.setTaxPeriods(taxPeriods);

        TaxPeriodResponse taxPeriodResponse = null;
        try {
            taxPeriodResponse = taxCalculatorMasterService.createTaxPeriod(tenantId, taxPeriodRequest);
        } catch (Exception e) {
            assertTrue(false);
        }
        if (taxPeriodResponse.getTaxPeriods().size() == 0)
            assertTrue(false);

        assertTrue(true);

    }

    /**
     * This will test whether the tax period will update successfully or not
     */
    @Test
    public void modifyTaxPeriod() {

        List<TaxPeriod> taxPeriods = new ArrayList<>();
        TaxPeriod taxPeriod = new TaxPeriod();

        String tenantId = "1234";
        taxPeriod.setTenantId("1234");
        taxPeriod.setCode("YEAR");
        taxPeriod.setFinancialYear("2019-20");
        taxPeriod.setFromDate("02/02/2017 00:00:00");
        taxPeriod.setToDate("05/02/2017 00:00:00");
        taxPeriod.setPeriodType("Yearly");

        long createdTime = new Date().getTime();
        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("pankaj");
        auditDetails.setLastModifiedBy("pankaj");
        auditDetails.setCreatedTime(createdTime);
        auditDetails.setLastModifiedTime(createdTime);

        taxPeriod.setAuditDetails(auditDetails);

        taxPeriods.add(taxPeriod);
        TaxPeriodRequest taxPeriodRequest = new TaxPeriodRequest();
        taxPeriodRequest.setRequestInfo(getRequestInfoObject());
        taxPeriodRequest.setTaxPeriods(taxPeriods);

        TaxPeriodResponse taxPeriodResponse = null;
        try {
            taxPeriodResponse = taxCalculatorMasterService.updateTaxPeriod(tenantId, taxPeriodRequest);

            if (taxPeriodResponse.getTaxPeriods().size() > 0
                    && taxPeriodResponse.getTaxPeriods().equals(taxPeriodRequest.getTaxPeriods()))
                assertTrue(true);
            else
                assertTrue(false);

        } catch (Exception e) {
            assertTrue(false);
        }

    }

    /**
     * This will test whether the search will be successful or not for tax periods
     */
    @Ignore
    public void searchTaxPeriod() {

        String tenantId = "1234";
        String code = "YEAR";
        String fromDate = "02/02/2017";
		String toDate = "05-02-2017";

        TaxPeriodResponse taxPeriodResponse = null;
        try {
            taxPeriodResponse = taxCalculatorMasterService.getTaxPeriod(getRequestInfoObject(), tenantId, null,
                    code, fromDate, toDate, null);

            if (taxPeriodResponse.getTaxPeriods().size() == 0) {
                assertTrue(false);
            }
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void createTaxRateServiceTest() {

        String tenantId = "default";
        RequestInfo requestInfo = getRequestInfoObject();
        List<TaxRates> listOfTaxRates = new ArrayList<>();

        TaxRates taxRates = new TaxRates();
        taxRates.setTenantId("default");
        taxRates.setTaxHead("taxHead-C");
        taxRates.setDependentTaxHead("dependentTaxHead-C");
        taxRates.setFromDate("03/06/2017 00:00:00");
        taxRates.setToDate("10/06/2017 00:00:00");
        taxRates.setFromValue(1000.0);
        taxRates.setToValue(1500.0);
        taxRates.setRatePercentage(33.0);
        taxRates.setTaxFlatValue(2222.0);
        taxRates.setUsage("car usage");
        taxRates.setPropertyType("vaccant land");

        long createdTime = new Date().getTime();

        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("yosadhara");
        auditDetails.setLastModifiedBy("yosadhara");
        auditDetails.setCreatedTime(createdTime);
        auditDetails.setLastModifiedTime(createdTime);

        taxRates.setAuditDetails(auditDetails);
        listOfTaxRates.add(taxRates);

        TaxRatesRequest taxRatesRequest = new TaxRatesRequest();
        taxRatesRequest.setTaxRates(listOfTaxRates);
        taxRatesRequest.setRequestInfo(requestInfo);

        try {
            TaxRatesResponse taxRatesResponse = taxCalculatorMasterService.createTaxRate(tenantId, taxRatesRequest);

            if (taxRatesResponse.getTaxRates().size() == 0)
                assertTrue(false);

            assertTrue(true);
        } catch (Exception e) {

            assertTrue(false);
        }
    }

    @Test
    public void modifyTaxRateServiceTest() {

        String tenantId = "default";
        RequestInfo requestInfo = getRequestInfoObject();
        List<TaxRates> listOfTaxRates = new ArrayList<>();

        TaxRates taxRates = new TaxRates();
        taxRates.setTenantId("default");
        taxRates.setTaxHead("taxHead-UU");
        taxRates.setDependentTaxHead("dependentTaxHead-UU");
        taxRates.setFromDate("03/06/2017 00:00:00");
        taxRates.setToDate("10/06/2017 00:00:00");
        taxRates.setFromValue(1000.0);
        taxRates.setToValue(1500.0);
        taxRates.setRatePercentage(33.0);
        taxRates.setTaxFlatValue(2222.0);
        taxRates.setUsage("car usage");
        taxRates.setPropertyType("vaccant land");
        taxRates.setId(taxRatesId);

        long updatedTime = new Date().getTime();

        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy("yosadhara");
        auditDetails.setLastModifiedBy("yosadhara");
        auditDetails.setCreatedTime(updatedTime);
        auditDetails.setLastModifiedTime(updatedTime);

        taxRates.setAuditDetails(auditDetails);
        listOfTaxRates.add(taxRates);

        TaxRatesRequest taxRatesRequest = new TaxRatesRequest();
        taxRatesRequest.setTaxRates(listOfTaxRates);
        taxRatesRequest.setRequestInfo(requestInfo);

        try {
            TaxRatesResponse taxRatesResponse = taxCalculatorMasterService.updateTaxRate(tenantId, taxRatesRequest);

            if (taxRatesResponse.getTaxRates().size() > 0
                    && taxRatesRequest.getTaxRates().equals(taxRatesResponse.getTaxRates()))
                assertTrue(true);
            else
                assertTrue(false);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
        public void searchTaxRateServiceTest() {

                String tenantId = "default";
                String taxHead = "taxHead-UU";
                String validDate = "04-06-2017";
                Double validARVAmount = 1100.0;
                String parentTaxHead = "dependentTaxHead-UU";
                String usage ="car usage";
                String propertyType="vaccant land";

                TaxRatesResponse taxRatesResponse = null;
                try {
                        taxRatesResponse = taxCalculatorMasterService.getTaxRate(getRequestInfoObject(), tenantId, taxHead,
                                        validDate, validARVAmount, parentTaxHead,usage,propertyType);

                        if (taxRatesResponse.getTaxRates().size() == 0) {
                                assertTrue(false);
                        }

                        assertTrue(true);
                } catch (Exception e) {
                        assertTrue(false);
                }
    }

    /**
     * This will return the request Info Object
     * 
     * @return {@link RequestInfo}
     */
    private RequestInfo getRequestInfoObject() {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setApiId("emp");
        requestInfo.setVer("1.0");
        requestInfo.setTs(new Long(122366));
        requestInfo.setDid("1");
        requestInfo.setKey("abcdkey");
        requestInfo.setMsgId("20170310130900");
        requestInfo.setRequesterId("rajesh");
        requestInfo.setAuthToken("b5da31a4-b400-4d6e-aa46-9ebf33cce933");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(123);
        requestInfo.setUserInfo(userInfo);

        return requestInfo;
    }
}
