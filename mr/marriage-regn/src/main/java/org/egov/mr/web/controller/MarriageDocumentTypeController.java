package org.egov.mr.web.controller;

import javax.validation.Valid;

import org.egov.mr.service.MarriageDocumentTypeService;
import org.egov.mr.web.contract.MarriageDocTypeRequest;
import org.egov.mr.web.contract.MarriageDocumentTypeSearchCriteria;
import org.egov.mr.web.contract.RequestInfo;
import org.egov.mr.web.contract.RequestInfoWrapper;
import org.egov.mr.web.errorhandler.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/marriageRegns/documents")
@Slf4j
public class MarriageDocumentTypeController {

	@Autowired
	private ErrorHandler errorHandler;

	@Autowired
	private MarriageDocumentTypeService marriageDocumentTypeService;

	/**
	 * @SEARCH
	 * 
	 * @param requestInfoWrapper
	 * @param bindingResultsForRequestInfoWrapper
	 * @param marriageDocumentTypeSearchCriteria
	 * @param bindingResultForRegnDocumentTypeSearchCriteria
	 * @return
	 */
	@PostMapping
	@RequestMapping("/_search")
	public ResponseEntity<?> search(@RequestBody @Valid RequestInfoWrapper requestInfoWrapper,
			BindingResult bindingResultsForRequestInfoWrapper,
			@ModelAttribute @Valid MarriageDocumentTypeSearchCriteria marriageDocumentTypeSearchCriteria,
			BindingResult bindingResultForRegnDocumentTypeSearchCriteria) {

		log.info("requestInfoWrapper : " + requestInfoWrapper);
		log.info("regnDocumentTypeSearchCriteria : " + marriageDocumentTypeSearchCriteria);

		RequestInfo requestInfo = requestInfoWrapper.getRequestInfo();

		ResponseEntity<?> errorResponseEntity = errorHandler.handleBindingErrorsForSearch(requestInfo,
				bindingResultsForRequestInfoWrapper, bindingResultForRegnDocumentTypeSearchCriteria);
		if (errorResponseEntity != null)
			return errorResponseEntity;

		ResponseEntity<?> marriageDocumentTypeResponse = null;
		try {
			marriageDocumentTypeResponse = marriageDocumentTypeService.search(marriageDocumentTypeSearchCriteria,
					requestInfo);
		} catch (Exception e) {
			log.info(" Error While Procssing the Request!");
			return errorHandler.getUnExpectedErrorResponse(bindingResultsForRequestInfoWrapper, requestInfo,
					"Encountered : " + e.getMessage());
		}
		return marriageDocumentTypeResponse;
	}

	/**
	 * @CREATE
	 * 
	 * @param marriageDocTypeRequest
	 * @param bindingResult
	 * @return
	 */
	@PostMapping
	@RequestMapping("/_create")
	public ResponseEntity<?> createMarriageDocumentType(
			@RequestBody @Valid MarriageDocTypeRequest marriageDocTypeRequest, BindingResult bindingResult) {

		log.info("Controller:: MarriageDocTypeRequest: " + marriageDocTypeRequest);

		RequestInfo requestInfo = marriageDocTypeRequest.getRequestInfo();
		/**
		 * @Validate Binding Errors
		 */
		if (bindingResult.hasErrors()) {
			ResponseEntity<?> errorResponseEntity = errorHandler.handleBindingErrorsForCreate(requestInfo,
					bindingResult);
			if (errorResponseEntity != null) {
				return errorResponseEntity;
			}
		}
		return marriageDocumentTypeService.createAsync(marriageDocTypeRequest);

	}

	/**
	 * @UPDATE
	 * 
	 * @param marriageDocTypeRequest
	 * @param bindingResult
	 * @return
	 */
	@PostMapping
	@RequestMapping("/_update")
	public ResponseEntity<?> updateMarriageDocumentType(
			@RequestBody @Valid MarriageDocTypeRequest marriageDocTypeRequest, BindingResult bindingResult) {
		RequestInfo requestInfo = marriageDocTypeRequest.getRequestInfo();
		if (bindingResult.hasErrors()) {
			ResponseEntity<?> errorResponseEntity = errorHandler.handleBindingErrorsForCreate(requestInfo,
					bindingResult);
			if (errorResponseEntity != null) {
				return errorResponseEntity;
			}
		}
		return marriageDocumentTypeService.updateAsync(marriageDocTypeRequest);
	}

}
