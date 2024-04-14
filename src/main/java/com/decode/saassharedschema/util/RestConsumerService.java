package com.decode.saassharedschema.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;


@Service
public class RestConsumerService {

	@Autowired 
	RestTemplate restTemplate;
	
	public RestConsumerService() {
	}
	
	public Object invokeExtRestWS(String webService, Map<String, String> params, Map<String, String> paramsH, Object response) {
		
		Object result = null;
		long timeStart = System.currentTimeMillis();
		try {
			
			String urlWS = webService;
			if(params.size()>0) {
				urlWS = webService + "/" + RestUtil.convertStringPathVar(params);
			}
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        for (Map.Entry<String, String> entry : paramsH.entrySet()) {
	            headers.set(entry.getKey(), entry.getValue());
	        }
			HttpEntity<String> request = new HttpEntity<>(headers);
			ResponseEntity<String> entity = restTemplate.exchange(urlWS, HttpMethod.GET, request, String.class);
			if(entity.getStatusCode().toString().contains("200") || entity.getStatusCode().toString().contains("201")){
				String resultString = entity.getBody();
				result = resultString;
				//log.info("result: "+result);
			}else {
				//log.info("error status: "+entity.getStatusCode().toString()+" -> "+entity.getBody());
			}
		}catch(Exception e) {
			e.printStackTrace();
			//log.error("Ocurrio un error apiManagement - core: " + e);
		}
		return result;
	}
	
	public Object invokeExtRestWS(String webService, String requestJson, Map<String, String> paramsH, Object response) {
		
		Object result = null;
		long timeStart = System.currentTimeMillis();
		try {
			
			String urlWS = webService;
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        for (Map.Entry<String, String> entry : paramsH.entrySet()) {
	            headers.set(entry.getKey(), entry.getValue());
	        }
			HttpEntity<String> request = new HttpEntity<>(requestJson,headers);
			ResponseEntity<String> entity = restTemplate.exchange(urlWS, HttpMethod.POST, request, String.class);
			if(entity.getStatusCode().toString().contains("200") || entity.getStatusCode().toString().contains("201")){
				String resultString = entity.getBody();
				result = resultString;
			}else {
				//log.info("error status: "+entity.getStatusCode().toString()+" -> "+entity.getBody());
			}
		}catch(Exception e) {
			//log.error("Ocurrio un error apiManagement - core: " + e.getMessage());
			if(e.getMessage().trim().indexOf("{")>0) {
				result = e.getMessage().trim().substring(e.getMessage().trim().indexOf("{"),e.getMessage().trim().length()-1);
			}
		}
		return result;
	}
}
