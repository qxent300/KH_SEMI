package com.kh.mvc.news.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kh.mvc.news.model.vo.News;

public class NewsApi {
    public List<News> callNewsList() {
    	List<News> list = new ArrayList<>();
    	
        String clientId = "ER3MFwuMMBPvp_aP9Jgn";
        String clientSecret = "IUoXBFGbmJ";
        try {
            String text = URLEncoder.encode("건강음식 건강식품", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+text+"&display=9&start=1&sort=sim";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setDoOutput(true);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            
			JSONParser jsonParser = new JSONParser(); 
			JSONObject jsonObj = (JSONObject) jsonParser.parse(br);
			JSONArray officeArray = (JSONArray) jsonObj.get("items");

			for(int i = 0; i < officeArray.size(); i++) {
				JSONObject personObject = (JSONObject) officeArray.get(i);
				String title = personObject.get("title").toString();
				String originallink = personObject.get("originallink").toString();
				String link = personObject.get("link").toString();
				String pubDate = personObject.get("pubDate").toString();
				String description = personObject.get("description").toString().replace("<b>", "").replace("</b>", "");
				
				list.add(new News(title, originallink, link, pubDate, description));
			}
        } catch (Exception e) {
            System.out.println(e);
        }
		return list;
    }
}
