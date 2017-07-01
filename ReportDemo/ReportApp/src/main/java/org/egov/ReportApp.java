package org.egov;
import java.io.File;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.egov.domain.model.ReportYamlMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;



@SpringBootApplication
public class ReportApp {
	
	
	@Autowired
	public ReportYamlMetaData reportYamlMetaData;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ReportApp.class, args);
		 
	        
	}
	
	@Bean
    public MappingJackson2HttpMessageConverter jacksonConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH));
        converter.setObjectMapper(mapper);
        return converter;
    }
	 
	  @Bean("reportYamlMetaData")
	  public static ReportYamlMetaData loadYaml() {
	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    try {
    	ReportYamlMetaData reportYamlMetaData = mapper.readValue(new File("/ws/ReportDemo/ReportApp/src/main/resources/application.yml"), ReportYamlMetaData.class);
        System.out.println(ReflectionToStringBuilder.toString(reportYamlMetaData,ToStringStyle.MULTI_LINE_STYLE));
        //new ReportController(reportMetaData);
        
        return reportYamlMetaData;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
    }
}