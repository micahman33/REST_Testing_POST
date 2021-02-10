package com.company;


import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        //String url = "https://ap17.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9LBJLApeX_PBOpTSfmwDnyIq24.tKO1CnhLq.2IyGVh3pZEZcw2anUjij32CDzKvXtrxduFoAo6kwvN0W&client_secret=72D5E41F836F0330B401D90B24888AC48EA5673676019286EB8F96F0EB898D42&username=micahdanielsmith@gmail.com&password=Q3mgk6zf-EJCx9";
        String url = "https://ap17.salesforce.com/services/data/v49.0/sobjects/Case";
        String line;
        StringBuffer responseContent = new StringBuffer();
        String token = "00D6g000006MyrF!ASAAQNhhySnPKrosZAb983aITbyIcNMUMQsomcq6K1DgE4LC5V7hRDHMUZouTzOVkLwLFZ0APsIgi5n1B4.wcd3Qxj_Zksfr";
        String jsonBody = "{\"OwnerId\": \"0056g000003eaZOAAY\",\n" +
                "       \"Subject\": \"Delete\",\n" +
                "       \"Description\": \"PO Tracker Document Status Notification: The following document(s) have been identified as being associated to notification name: New POs are available on Automation Anywhere Test PO Tracker.\\r\\n\\r\\nDateSender (EID)Receiver (EID)PO#PO Line #Control NumberBusiness ProcessFlow ID\\r\\nJuly 13, 2020 9:01:57 AM Deckow Inc 5548 (10553474) Automation Anywhere, INC. (3937663) 44922241 34-840-0560 Purchase Order d6fhghh3gf2addfdfdsd7hdf29dj4gh3h\\r\\n\\r\\nContact Automation Anywhere Test PO Tracker team if you have any questions regarding this or other issues. Customer Care analysts are available at 1-888-555-5555\"}";
        String updatedValue = "JSON works!!";
        String message = "";
        String errorCode = "";

        //Create JSON Body and convert to String Entity
        DocumentContext document = JsonPath.parse(jsonBody);
        System.out.println(document.toString());
        JSONObject jsonINput = new JSONObject((jsonBody));
        StringEntity entity = new StringEntity(jsonBody,
                ContentType.APPLICATION_FORM_URLENCODED);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-type", "application/json");
        request.setHeader("Authorization", "Bearer " + token);
        request.setEntity(entity);

        HttpResponse response = client.execute(request);
        int actualResponseCode = response.getStatusLine().getStatusCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

//            StringBuffer result = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        System.out.println(responseContent.toString());
        JSONObject jsonResponse = new JSONObject(responseContent.toString());



        if (actualResponseCode == 204) {
            System.out.println("Success");
        } else {

//            JSONArray jsonResponse = new JSONArray(responseContent.toString());
//            for (int i = 0; i < jsonResponse.length(); i++) {
//                JSONObject obj = jsonResponse.getJSONObject(i);
//                if (obj.has("message")) {
//                    message = obj.getString("message");
//                }
//
//                if (obj.has("errorCode")) {
//                    errorCode = obj.getString("errorCode");
//                }
//            }
//
//            System.out.println("Error Occured. with code: " + errorCode + ". Message: " + message);
        }

//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//
//        while((line = reader.readLine())!=null){
//            responseContent.append(line);
//        }

        //reader.close();
        //System.out.println(responseContent.toString());


    }
}
