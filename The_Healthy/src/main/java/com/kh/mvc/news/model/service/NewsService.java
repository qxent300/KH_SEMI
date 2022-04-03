package com.kh.mvc.news.model.service;

import java.util.List;

import com.kh.mvc.news.api.NewsApi;
import com.kh.mvc.news.model.vo.News;

public class NewsService {
	private NewsApi api = new NewsApi();
	
	public List<News> getNewsList() {
		List<News> list = api.callNewsList();
		return list;
	}
}
