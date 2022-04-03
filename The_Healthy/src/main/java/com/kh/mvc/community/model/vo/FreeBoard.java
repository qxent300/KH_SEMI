package com.kh.mvc.community.model.vo;
import java.util.Date;
import java.util.List;

public class FreeBoard {
	private int no;
	private int rowNum;
	private int writerNo;
	private String writerId;
	private String title;
	private String content;
	private String originalFileName;
	private String renamedFileName;
	private int readCount;
	private String status;
	private List<Reply> replies;
	private Date createDate;
	private Date modifyDate;
	public FreeBoard(int no, int rowNum, int writerNo, String writerId, String title, String content,
			String originalFileName, String renamedFileName, int readCount, String status, List<Reply> replies,
			Date createDate, Date modifyDate) {
		super();
		this.no = no;
		this.rowNum = rowNum;
		this.writerNo = writerNo;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.readCount = readCount;
		this.status = status;
		this.replies = replies;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}
	public FreeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", rowNum=" + rowNum + ", writerNo=" + writerNo + ", writerId=" + writerId
				+ ", title=" + title + ", content=" + content + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + ", readCount=" + readCount + ", status=" + status
				+ ", replies=" + replies + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getRenamedFileName() {
		return renamedFileName;
	}
	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}