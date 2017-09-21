package com.hmhco.scoring;

import com.google.common.collect.Lists;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jayachandranj on 9/21/17.
 */
public class ProcessorInvoker {

    private final static String url = "https://aggregator-processor.br.hmheng.io/v1/rescore/responses/sessions";

    public void processSession(List<String> sessionIds){
        //process sessionIds
    }

    public void ConvertIntoChunks(List<UUID> sessions){

        List<List<UUID>> smallersessions = Lists.partition(sessions,30);

        RestTemplate restTemplate = new RestTemplate();

        for(int i=0; i< smallersessions.size();i++) {
            String result = convertListToString(smallersessions.get(i));
            System.out.println(i+"====>"+result);
            String requestJson = "{\"session_id\":["+result+"]}";
            System.out.println("requestJson====>"+requestJson.length()+"  "+requestJson);
            invokeRescore(restTemplate, url, requestJson);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String convertListToString(List<UUID> smallersessions){
        String result = smallersessions.stream()
                .map((s) -> "\"" + s + "\"")
                .collect(Collectors.joining(", "));

        return result;
    }

    private static void invokeRescore(RestTemplate restTemplate, String url, String requestJson) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            String answer = restTemplate.postForObject(url, entity, String.class);
            System.out.println(answer);
        } catch (RestClientException e) {
            System.out.println("==="+e.fillInStackTrace());
            e.printStackTrace();
        }
    }
}
