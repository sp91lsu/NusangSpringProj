package com.mycom.blog.bo.assistance;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.tomcat.util.json.JSONParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;

@Data
public class MyHttpPost {

	private HttpClient client = null;
	private HttpPost postRequest = null;
	private JsonNode rootNode = null;
	private ObjectMapper m = new ObjectMapper();
	private Map<String, String> header = null;
	private ObjectNode body = null;

	private EContentType cType = null;

	public MyHttpPost(String url, EContentType cType) {
		client = HttpClientBuilder.create().build(); // HttpClient 생성
		postRequest = new HttpPost(url); // POST 메소드 URL 새성
		this.cType = cType;
	}

	public JsonNode request() {

		try {

			headerCheck();
			bodyCheck();

			HttpResponse javaResponse = client.execute(postRequest);

			// Response 출력
			if (javaResponse.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				javaResponse.setHeader("Content-Type", "application/json");
				System.out.println(javaResponse.getEntity());
				String body = handler.handleResponse(javaResponse);
				System.out.println("응답 성공 : " + body);
//				for (int i = 0; i < javaResponse.getAllHeaders().length; i++) {
//					System.out.println("header i  : " + javaResponse.getAllHeaders()[i].getName()
//							+ javaResponse.getAllHeaders()[i].getValue());
//				}

				rootNode = m.readTree(body);

			} else {
				System.out.println("response is error : " + javaResponse.getStatusLine());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rootNode;
	}

	private void headerCheck() throws UnsupportedEncodingException {

		postRequest.addHeader("Content-Type", cType.getText());
		if (header != null) {
//			System.out.println("header :" + header.toJSONString());

			Object[] headArr = header.keySet().toArray();
			for (int i = 0; i < headArr.length; i++) {
				String key = headArr[i].toString();
				postRequest.addHeader(key, header.get(key).toString());
			}

			System.out.println("헤더요청 : " + postRequest.getURI());
		}
	}

	private void bodyCheck() throws UnsupportedEncodingException {

		if (body != null) {

			if (cType == EContentType.JSON) {
				StringEntity sn = new StringEntity(body.toPrettyString());
				System.out.println("body : " + body.toPrettyString());
				sn.setContentType(cType.getText());
				postRequest.setEntity(sn); // json 메시지 입력
				System.out.println("entity : " + postRequest.getEntity());
			} else if (cType == EContentType.FORM) {
				List<NameValuePair> pair = new ArrayList<NameValuePair>();

				Map<String, Object> bodyMap = m.convertValue(body, Map.class);
				try {
					for (Entry<String, Object> entrySet : bodyMap.entrySet()) {

						pair.add(new BasicNameValuePair(entrySet.getKey(), (String) entrySet.getValue()));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UrlEncodedFormEntity ent = new UrlEncodedFormEntity(pair, HTTP.UTF_8);
				postRequest.setEntity(ent);
			}
		}
	}
}
