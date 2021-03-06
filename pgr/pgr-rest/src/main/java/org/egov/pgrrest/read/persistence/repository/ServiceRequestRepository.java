package org.egov.pgrrest.read.persistence.repository;

import org.egov.pgrrest.common.contract.web.SevaRequest;
import org.egov.pgrrest.common.persistence.entity.ServiceType;
import org.egov.pgrrest.read.domain.model.ServiceRequest;
import org.egov.pgrrest.read.domain.model.ServiceRequestSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ServiceRequestRepository {
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private ServiceRequestMessageQueueRepository serviceRequestMessageQueueRepository;
    private SubmissionRepository submissionRepository;
    private ServiceRequestESRepository serviceRequestESRepository;
    private ServiceRequestTypeRepository serviceRequestTypeRepository;

    @Autowired
    public ServiceRequestRepository(ServiceRequestMessageQueueRepository serviceRequestMessageQueueRepository,
                                    SubmissionRepository submissionRepository, ServiceRequestESRepository
                                        serviceRequestESRepository,
                                    ServiceRequestTypeRepository serviceRequestTypeRepository) {
        this.serviceRequestMessageQueueRepository = serviceRequestMessageQueueRepository;
        this.submissionRepository = submissionRepository;
        this.serviceRequestESRepository = serviceRequestESRepository;
        this.serviceRequestTypeRepository = serviceRequestTypeRepository;
    }

    public void save(SevaRequest sevaRequest) {
        sevaRequest.getRequestInfo().setAction(POST);
        final Date now = new Date();
        sevaRequest.getServiceRequest().setCreatedDate(now);
        sevaRequest.getServiceRequest().setLastModifiedDate(now);
        String serviceTypeCode = sevaRequest.getServiceRequest().getServiceTypeCode();
        String tenantId = sevaRequest.getServiceRequest().getTenantId();
        String serviceName = getServiceName(serviceTypeCode, tenantId);
        sevaRequest.getServiceRequest().setServiceTypeName(serviceName);
        this.serviceRequestMessageQueueRepository.save(sevaRequest);
    }

    public List<ServiceRequest> find(ServiceRequestSearchCriteria searchCriteria) {
        final List<String> serviceRequestIds = serviceRequestESRepository.getMatchingServiceRequestIds(searchCriteria);
        if (CollectionUtils.isEmpty(serviceRequestIds)) {
            return Collections.emptyList();
        }
        return submissionRepository.findBy(serviceRequestIds, searchCriteria.getTenantId());
    }

    public Long getCount(ServiceRequestSearchCriteria serviceRequestSearchCriteria) {
        return serviceRequestESRepository.getCount(serviceRequestSearchCriteria);
    }

    public void update(SevaRequest sevaRequest) {
        sevaRequest.getRequestInfo().setAction(PUT);
        sevaRequest.getServiceRequest().setLastModifiedDate(new Date());
        this.serviceRequestMessageQueueRepository.save(sevaRequest);
    }

    public List<ServiceRequest> findFromDb(ServiceRequestSearchCriteria serviceRequestSearchCriteria) {
        return submissionRepository.find(serviceRequestSearchCriteria);
    }

    public String getServiceName(String serviceCode, String tenantId) {
        ServiceType serviceRequestType = serviceRequestTypeRepository.getServiceRequestType(serviceCode, tenantId);
        return !isEmpty(serviceRequestType) ? serviceRequestType.getName() : "";
    }
}
