package com.kh.mvc.news.model.vo;

public class News {
	private String title;        // 제목
	private String originallink; // 언론사 하이퍼텍스트 link
	private String link;         // 네이버 하이퍼텍스트 link
	private String pubDate;      // 네이버에 제공된 시간
	private String description;  // 문서의 내용을 요약한 패시지 정보

	public News() {
		super();
	}

	public News(String title, String originallink, String link, String pubDate, String description) {
		super();
		this.title = title;
		this.originallink = originallink;
		this.link = link;
		this.pubDate = pubDate;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginallink() {
		return originallink;
	}

	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", originallink=" + originallink + ", link=" + link + ", pubDate=" + pubDate
				+ ", description=" + description + "]";
	}

}
