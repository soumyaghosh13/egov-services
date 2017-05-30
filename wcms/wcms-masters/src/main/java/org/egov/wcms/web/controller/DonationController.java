package org.egov.wcms.web.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ErrorField;
import org.egov.common.contract.response.ResponseInfo;
import org.egov.wcms.config.ApplicationProperties;
import org.egov.wcms.model.Donation;
import org.egov.wcms.service.DonationService;
import org.egov.wcms.util.WcmsConstants;
import org.egov.wcms.web.contract.DonationRequest;
import org.egov.wcms.web.contract.DonationResponse;
import org.egov.wcms.web.contract.factory.ResponseInfoFactory;
import org.egov.wcms.web.errorhandlers.Error;
import org.egov.wcms.web.errorhandlers.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation")
public class DonationController {
	
	private static final Logger logger = LoggerFactory.getLogger(DonationController.class);
	
	@Autowired
	private DonationService donationService;
	
    @Autowired
    private ResponseInfoFactory responseInfoFactory;

    @Autowired
    private ApplicationProperties applicationProperties;

    @PostMapping(value = "/_create")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid  final DonationRequest donationRequest,
                                    final BindingResult errors) {
        if (errors.hasErrors()) {
            final ErrorResponse errRes = populateErrors(errors);
            return new ResponseEntity<ErrorResponse>(errRes, HttpStatus.BAD_REQUEST);
        }
        logger.info("Donation Request::" + donationRequest);

        final List<ErrorResponse> errorResponses = validateDonationRequest(donationRequest);
        if (!errorResponses.isEmpty())
            return new ResponseEntity<List<ErrorResponse>>(errorResponses, HttpStatus.BAD_REQUEST);

        final Donation donation= donationService.createPropertyUsageType(applicationProperties.getCreateDonationTopicName(),"donation-create", donationRequest);
        List<Donation> donationList = new ArrayList<>();
        donationList.add(donation);
        return getSuccessResponse(donationList, donationRequest.getRequestInfo());

    }
    
    private ErrorResponse populateErrors(final BindingResult errors) {
        final ErrorResponse errRes = new ErrorResponse();

        final Error error = new Error();
        error.setCode(1);
        error.setDescription("Error while binding request");
        if (errors.hasFieldErrors())
            for (final FieldError fieldError : errors.getFieldErrors()) {
                error.getFields().put(fieldError.getField(), fieldError.getRejectedValue());
            }
        errRes.setError(error);
        return errRes;
    }
    
    private List<ErrorResponse> validateDonationRequest(final DonationRequest donationRequest) {
        final List<ErrorResponse> errorResponses = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        final Error error = getError(donationRequest);
        errorResponse.setError(error);
        if(!errorResponse.getErrorFields().isEmpty())
            errorResponses.add(errorResponse);
        return errorResponses;
    }
    
    private Error getError(final DonationRequest donationRequest) {
        List<ErrorField> errorFields = getErrorFields(donationRequest);
        return Error.builder().code(HttpStatus.BAD_REQUEST.value())
                .message(WcmsConstants.INVALID_DONATION_REQUEST_MESSAGE)
                .errorFields(errorFields)
                .build();
    }

    private List<ErrorField> getErrorFields(final DonationRequest donationRequest) {
    	Donation donation = donationRequest.getDonation();
        List<ErrorField> errorFields = new ArrayList<>();
        checkPropertyTypeValue(errorFields, donation);
        checkUsageTypeValue(errorFields, donation);
        checkCategoryValue(errorFields, donation);
        checkPipeSizeValues(errorFields, donation);
        checkDonationAmountValues(errorFields, donation);
        checkFromToDateValues(errorFields, donation);
        return errorFields;
    }
    
    private void checkPropertyTypeValue(List<ErrorField> errorFields, Donation donation){
    	if (donation.getPropertyType()== null || donation.getPropertyType().isEmpty()) {
            final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.PROPERTYTYPE_MANDATORY_CODE)
                    .message(WcmsConstants.PROPERTYTYPE_MANDATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.PROPERTYTYPE_MANDATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
        }
    }
    
    private void checkUsageTypeValue(List<ErrorField> errorFields, Donation donation){
    	if (donation.getUsageType()== null || donation.getUsageType().isEmpty()) {
            final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.USAGETYPE_NAME_MANDATORY_CODE)
                    .message(WcmsConstants.USAGETYPE_NAME_MANADATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.USAGETYPE_NAME_MANADATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
        }
    }
    
    private void checkCategoryValue(List<ErrorField> errorFields, Donation donation){
    	if (donation.getCategory()== null || donation.getCategory().isEmpty()) {
            final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.CATEGORY_NAME_MANDATORY_CODE)
                    .message(WcmsConstants.CATEGORY_NAME_MANADATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.CATEGORY_NAME_MANADATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
        }
    }
    
    private void checkPipeSizeValues(List<ErrorField> errorFields, Donation donation) {
    	if((donation.getMaxHSCPipeSize() == null || donation.getMaxHSCPipeSize().isEmpty()) && 
    			donation.getMinHSCPipeSize() == null || donation.getMinHSCPipeSize().isEmpty()) {
    		final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.PIPESIZE_SIZEINMM_MANDATORY_CODE)
                    .message(WcmsConstants.PIPESIZE_SIZEINMM__MANADATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.PIPESIZE_SIZEINMM__MANADATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
    	}
    }
    
    private void checkDonationAmountValues(List<ErrorField> errorFields, Donation donation) {
    	if(donation.getDonationAmount() == null || donation.getDonationAmount().isEmpty()){
    		final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.DONATION_MANDATORY_CODE)
                    .message(WcmsConstants.DONATION_MANDATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.DONATION_MANDATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
    	}
    }
    
    private void checkFromToDateValues(List<ErrorField> errorFields, Donation donation) {
    	if(donation.getFromDate() == null || donation.getToDate() == null){
    		final ErrorField errorField = ErrorField.builder()
                    .code(WcmsConstants.FROMTO_MANDATORY_CODE)
                    .message(WcmsConstants.FROMTO_MANDATORY_ERROR_MESSAGE)
                    .field(WcmsConstants.FROMTO_MANDATORY_FIELD_NAME)
                    .build();
            errorFields.add(errorField);
    	}
    }
    
    private ResponseEntity<?> getSuccessResponse(List<Donation> donationList, RequestInfo requestInfo) {
        DonationResponse donationResponse = new DonationResponse();
        donationResponse.setDonations(donationList);
        ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
        responseInfo.setStatus(HttpStatus.OK.toString());
        donationResponse.setResponseInfo(responseInfo);
        return new ResponseEntity<DonationResponse>(donationResponse, HttpStatus.OK);

    }
        
    
	
	

}