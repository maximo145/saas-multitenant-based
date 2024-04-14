package com.decode.saassharedschema.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RestUtil {
	
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	private static final String ERRORS = "ERRORS";
	private static final String UNAUTHORIZED = "UNAUTHORIZED";

	public static List executeServiceExtParams(String urlWS, HashMap<String, String> params, HashMap<String, String> paramsH, RestConsumerService servService, Object obj, Object objError) 
			throws JsonMappingException, JsonProcessingException, IOException {
		List lista = new ArrayList();
		Object objData = null;
		Object objMessage = null;
		String resultString = (String) servService.invokeExtRestWS(urlWS, params, paramsH, new String());
		if(resultString!=null) {
			if(resultString.toUpperCase().contains(RestUtil.ERRORS) || resultString.toLowerCase().contains(RestUtil.UNAUTHORIZED)) objMessage = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(resultString, objError.getClass());
			else objData = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(resultString, obj.getClass());
			lista.add(objMessage);
			lista.add(objData);
		}
		return lista;
	}
	
	public static List executeServiceExtParams(String urlWS, String jsonReq, HashMap<String, String> paramsH, RestConsumerService servService, Object obj, Object objError) 
			throws JsonMappingException, JsonProcessingException, IOException {
		List lista = new ArrayList();
		Object objData = null;
		Object objMessage = null;
		String resultString = (String) servService.invokeExtRestWS(urlWS, jsonReq, paramsH, new String());
		if(resultString!=null) {
			//System.out.println("resultString: "+resultString);
			//System.out.println("mensaje error: "+resultString.toUpperCase());
			//System.out.println("mensaje error contains: "+resultString.toUpperCase().contains(RestUtil.ERRORS));
			if(resultString.toUpperCase().contains(RestUtil.ERRORS) || resultString.contains(RestUtil.UNAUTHORIZED)) objMessage = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(resultString, objError.getClass());
			else objData = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(resultString, obj.getClass());
			//System.out.println("objMessage: "+objMessage.toString());
			//System.out.println("objData: "+objData.toString());
			lista.add(objMessage);
			lista.add(objData);
		}
		//System.out.println("lista retorno: "+lista.size());
		return lista;
	}
	
	public static String convertStringPathVar(Map<String, ?> map) {
	    StringBuilder mapAsString = new StringBuilder("");
	    for (String key : map.keySet()) {
	        mapAsString.append(map.get(key) + "/ ");
	    }
	    mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("");
	    return mapAsString.toString();
	}
}
