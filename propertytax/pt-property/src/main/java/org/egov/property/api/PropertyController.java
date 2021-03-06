package org.egov.property.api;

import javax.validation.Valid;

import org.egov.models.DemandResponse;
import org.egov.models.PropertyRequest;
import org.egov.models.PropertyResponse;
import org.egov.models.RequestInfoWrapper;
import org.egov.models.SpecialNoticeRequest;
import org.egov.models.SpecialNoticeResponse;
import org.egov.models.TitleTransferRequest;
import org.egov.models.TitleTransferResponse;
import org.egov.property.services.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Property Controller have the api's related to property
 * 
 * @author Narendra
 */
@RestController
@RequestMapping(path = "/properties/")
public class PropertyController {

	private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

	@Autowired
	PropertyService propertyService;

	/**
	 * Description: this api will use for creating property
	 * 
	 * @param propertyRequest
	 * @return PropertyResponse
	 */

	@RequestMapping(method = RequestMethod.POST, path = "_create")
	public PropertyResponse createProperty(@Valid @RequestBody PropertyRequest propertyRequest) {
		logger.info("PropertyController    PropertyRequest ---->> " + propertyRequest);
		return propertyService.createProperty(propertyRequest);

	}

	/**
	 * updateProperty method validate each property before update
	 * 
	 * @param PropertyRequest
	 */
	@RequestMapping(method = RequestMethod.POST, path = "_update")
	public PropertyResponse updateProperty(@Valid @RequestBody PropertyRequest propertyRequest) {
		return propertyService.updateProperty(propertyRequest);

	}

	/**
	 * This api for searching property based on input
	 * parameters.demandTo,demandFrom,houseNoBldgApt,revenueZone,revenueWard
	 * paramter's search not present
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param active
	 * @param upicNo
	 * @param pageSize
	 * @param pageNumber
	 * @param sort
	 * @param oldUpicNo
	 * @param mobileNumber
	 * @param aadhaarNumber
	 * @param houseNoBldgApt
	 * @param revenueZone
	 * @param revenueWard
	 * @param locality
	 * @param ownerName
	 * @param demandFrom
	 * @param demandTo
	 * @return
	 */

	@RequestMapping(value = "_search", method = RequestMethod.POST)
	public PropertyResponse propertySearch(@RequestBody RequestInfoWrapper requestInfo,
			@RequestParam(value = "tenantId", required = true) String tenantId,
			@RequestParam(value = "active", required = false) Boolean active,
			@RequestParam(value = "upicNumber", required = false) String upicNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "sort", required = false) String[] sort,
			@RequestParam(value = "oldUpicNo", required = false) String oldUpicNo,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber,
			@RequestParam(value = "aadhaarNumber", required = false) String aadhaarNumber,
			@RequestParam(value = "houseNoBldgApt", required = false) String houseNoBldgApt,
			@RequestParam(value = "revenueZone", required = false) Integer revenueZone,
			@RequestParam(value = "revenueWard", required = false) Integer revenueWard,
			@RequestParam(value = "locality", required = false) Integer locality,
			@RequestParam(value = "ownerName", required = false) String ownerName,
			@RequestParam(value = "demandFrom", required = false) Integer demandFrom,
			@RequestParam(value = "demandTo", required = false) Integer demandTo,
			@RequestParam(value = "propertyId", required = false) String propertyId,
			@RequestParam(value = "applicationNo", required = false) String applicationNo) throws Exception {

		return propertyService.searchProperty(requestInfo.getRequestInfo(), tenantId, active, upicNumber, pageSize,
				pageNumber, sort, oldUpicNo, mobileNumber, aadhaarNumber, houseNoBldgApt, revenueZone, revenueWard,
				locality, ownerName, demandFrom, demandTo, propertyId, applicationNo);

	}

	/**
	 * Description: This api for creating title transfer request for property
	 * 
	 * @param titleTransferRequest
	 * @return titleTransferResponse
	 * @throws Exception
	 */
	@RequestMapping(path = "transfer/_create", method = RequestMethod.POST)
	public TitleTransferResponse createTitleTransfer(@RequestBody TitleTransferRequest titleTransferRequest)
			throws Exception {

		return propertyService.createTitleTransfer(titleTransferRequest);
	}

	/**
	 * 
	 * @param titleTransferRequest
	 * @return titleTransferResponse
	 * @throws Exception
	 */
	@RequestMapping(path = "transfer/_update", method = RequestMethod.POST)
	public TitleTransferResponse updateTitleTransfer(@RequestBody TitleTransferRequest titleTransferRequest)
			throws Exception {

		return propertyService.updateTitleTransfer(titleTransferRequest);
	}

	/**
	 * 
	 * @param specialNoticeRequest
	 * @return {@link SpecialNoticeResponse}
	 */
	@RequestMapping(path = "/specialnotice/_generate", method = RequestMethod.POST)
	public SpecialNoticeResponse generateSpecialNotice(@RequestBody SpecialNoticeRequest specialNoticeRequest)
			throws Exception {

		return propertyService.generateSpecialNotice(specialNoticeRequest);
	}

	/**
	 * API is for Add/Edit DCB feature
	 * @param requestInfo
	 * @param tenantId
	 * @param upicNumber
	 * @return demandResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "_preparedcb", method = RequestMethod.POST)
	public DemandResponse prepareDCB(@RequestBody RequestInfoWrapper requestInfo,
									 @RequestParam(value = "tenantId", required = true) String tenantId,
									 @RequestParam(value = "upicNumber", required = true) String upicNumber)
			throws Exception {

		return propertyService.getDemandsForProperty(requestInfo, tenantId, upicNumber);
	}

}
