package com.asset.tool;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpTools {
	
	public static String getCookie(URI uri,BasicNameValuePair user,BasicNameValuePair password) throws URISyntaxException, 
	 ClientProtocolException,IOException{
		
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	List<NameValuePair> postData = new ArrayList<NameValuePair>();
    	//这里可能有多个参数
    	postData.add(user);
    	postData.add(password);
    	//URL是实际的post地址，使用httpFox得到
    	HttpPost httppost = new HttpPost(uri); 
        httppost.setEntity(new UrlEncodedFormEntity(postData, "UTF8"));
    	HttpResponse  httpResponse = httpclient.execute(httppost);
    
        Map<String,String> cookieMap = new HashMap<String, String>(64);
        System.out.println("----setCookieStore");
        Header headers[] = httpResponse.getHeaders("Set-Cookie");
        if (headers == null || headers.length==0)
        {
            System.out.println("----there are no cookies");
            return null;
        }
        String cookie = "";
        for (int i = 0; i < headers.length; i++) {
            cookie += headers[i].getValue();
            if(i != headers.length-1)
            {
                cookie += ";";
            }
        }
 
        String cookies[] = cookie.split(";");
        for (String c : cookies)
        {
            c = c.trim();
            if(cookieMap.containsKey(c.split("=")[0]))
            {
                cookieMap.remove(c.split("=")[0]);
            }
            cookieMap.put(c.split("=")[0], c.split("=").length == 1 ? "":(c.split("=").length ==2?c.split("=")[1]:c.split("=",2)[1]));
        }
        System.out.println("----setCookieStore success");
        String cookiesTmp = "";
        for (String key :cookieMap.keySet())
        {
            cookiesTmp +=key+"="+cookieMap.get(key)+";";
        }
 
        return cookiesTmp.substring(0,cookiesTmp.length()-2);
    }
	
	public static String getContent(URI uri,String cookie) throws ClientProtocolException, IOException{
		HttpGet g = new HttpGet(uri);
    	g.setHeader("Cookie",cookie);
    	RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    	CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    	CloseableHttpResponse r = httpClient.execute(g);
        String content = EntityUtils.toString(r.getEntity());
        r.close();
        return content;
	}
}
