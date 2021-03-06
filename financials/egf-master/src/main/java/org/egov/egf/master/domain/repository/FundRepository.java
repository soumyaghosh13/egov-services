package org.egov.egf.master.domain.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.common.constants.Constants;
import org.egov.common.contract.request.RequestInfo;
import org.egov.common.domain.model.Pagination;
import org.egov.egf.master.domain.model.Fund;
import org.egov.egf.master.domain.model.FundSearch;
import org.egov.egf.master.domain.service.FinancialConfigurationService;
import org.egov.egf.master.persistence.entity.BankEntity;
import org.egov.egf.master.persistence.entity.FundEntity;
import org.egov.egf.master.persistence.queue.MastersQueueRepository;
import org.egov.egf.master.persistence.repository.FundJdbcRepository;
import org.egov.egf.master.web.contract.FundContract;
import org.egov.egf.master.web.contract.FundSearchContract;
import org.egov.egf.master.web.requests.FundRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FundRepository {

    private FundJdbcRepository fundJdbcRepository;

    private MastersQueueRepository fundQueueRepository;

    private FinancialConfigurationService financialConfigurationService;

    private FundESRepository fundESRepository;

    private String persistThroughKafka;

    @Autowired
    public FundRepository(FundJdbcRepository fundJdbcRepository,
            MastersQueueRepository fundQueueRepository, FinancialConfigurationService financialConfigurationService,
            FundESRepository fundESRepository,
            @Value("${persist.through.kafka}") String persistThroughKafka) {
        this.fundJdbcRepository = fundJdbcRepository;
        this.fundQueueRepository = fundQueueRepository;
        this.financialConfigurationService = financialConfigurationService;
        this.fundESRepository = fundESRepository;
        this.persistThroughKafka = persistThroughKafka;

    }

    @Transactional
    public List<Fund> save(List<Fund> funds,
            RequestInfo requestInfo) {

        ModelMapper mapper = new ModelMapper();
        FundContract contract;
        
        if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
                && persistThroughKafka.equalsIgnoreCase("yes")) {

            FundRequest request = new FundRequest();
            request.setRequestInfo(requestInfo);
            request.setFunds(new ArrayList<>());

            for (Fund f : funds) {

                contract = new FundContract();
                contract.setCreatedDate(new Date());
                mapper.map(f, contract);
                request.getFunds().add(contract);

            }

            addToQue(request);

            return funds;
        } else {

            List<Fund> resultList = new ArrayList<Fund>();

            for (Fund f : funds) {

                resultList.add(save(f));
            }

            FundRequest request = new FundRequest();
            request.setRequestInfo(requestInfo);
            request.setFunds(new ArrayList<>());

            for (Fund f : resultList) {

                contract = new FundContract();
                contract.setCreatedDate(new Date());
                mapper.map(f, contract);
                request.getFunds().add(contract);

            }

            addToSearchQueue(request);

            return resultList;
        }

    }

    @Transactional
    public List<Fund> update(List<Fund> funds,
            RequestInfo requestInfo) {

        ModelMapper mapper = new ModelMapper();
        FundContract contract;
        
        if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
                && persistThroughKafka.equalsIgnoreCase("yes")) {

            FundRequest request = new FundRequest();
            request.setRequestInfo(requestInfo);
            request.setFunds(new ArrayList<>());

            for (Fund f : funds) {

                contract = new FundContract();
                contract.setCreatedDate(new Date());
                mapper.map(f, contract);
                request.getFunds().add(contract);

            }

            addToQue(request);

            return funds;
        } else {

            List<Fund> resultList = new ArrayList<Fund>();

            for (Fund f : funds) {

                resultList.add(update(f));
            }

            FundRequest request = new FundRequest();
            request.setRequestInfo(requestInfo);
            request.setFunds(new ArrayList<>());

            for (Fund f : resultList) {

                contract = new FundContract();
                contract.setCreatedDate(new Date());
                mapper.map(f, contract);
                request.getFunds().add(contract);

            }

            addToSearchQueue(request);

            return resultList;
        }

    }

    public String getNextSequence(){
        return fundJdbcRepository.getSequence(FundEntity.SEQUENCE_NAME);
    }
    
    public Fund findById(Fund fund) {
        FundEntity entity = fundJdbcRepository.findById(new FundEntity().toEntity(fund));
        return entity.toDomain();

    }

    @Transactional
    public Fund save(Fund fund) {
        FundEntity entity = fundJdbcRepository.create(new FundEntity().toEntity(fund));
        return entity.toDomain();
    }

    @Transactional
    public Fund update(Fund fund) {
        FundEntity entity = fundJdbcRepository.update(new FundEntity().toEntity(fund));
        return entity.toDomain();
    }

    public void addToQue(FundRequest request) {
        Map<String, Object> message = new HashMap<>();

        if (request.getRequestInfo().getAction().equalsIgnoreCase(Constants.ACTION_CREATE)) {
            message.put("fund_create", request);
        } else {
            message.put("fund_update", request);
        }
        fundQueueRepository.add(message);
    }

    public void addToSearchQueue(FundRequest request) {
        Map<String, Object> message = new HashMap<>();

        message.put("fund_persisted", request);

        fundQueueRepository.addToSearch(message);
    }

    public Pagination<Fund> search(FundSearch domain) {
        if (!financialConfigurationService.fetchDataFrom().isEmpty()
                && financialConfigurationService.fetchDataFrom().equalsIgnoreCase("es")) {
            FundSearchContract fundSearchContract = new FundSearchContract();
            ModelMapper mapper = new ModelMapper();
            mapper.map(domain, fundSearchContract);
            return fundESRepository.search(fundSearchContract);
        } else {
            return fundJdbcRepository.search(domain);
        }

    }

}
