package com.douzon.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String contents;
	private String writeDate;
	private int hit;
	private int gNo;
	private int oNo;
	private int depth;
	private long userNo;
	private String userName;
	private final static int pageCount = 5;
	private final static int pageRowCount = 5;
	private int blockStartNum = 0;
	private int blockLastNum = 0;
	private int lastPageNum = 0;
	private int page;
	private int totalCount;
	private int rownum;
	private String fileName;
	private String OriFileName;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getBlockStartNum() {
		return blockStartNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}
	public int getBlockLastNum() {
		return blockLastNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public static int getPagecount() {
		return pageCount;
	}
	
	public static int getPagerowcount() {
		return pageRowCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriFileName() {
		return OriFileName;
	}
	public void setOriFileName(String oriFileName) {
		OriFileName = oriFileName;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", writeDate=" + writeDate
				+ ", hit=" + hit + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", userNo=" + userNo
				+ ", userName=" + userName + ", blockStartNum=" + blockStartNum + ", blockLastNum=" + blockLastNum
				+ ", lastPageNum=" + lastPageNum + ", page=" + page + ", totalCount=" + totalCount + ", rownum="
				+ rownum + ", fileName=" + fileName + ", OriFileName=" + OriFileName + ", getNo()=" + getNo()
				+ ", getTitle()=" + getTitle() + ", getContents()=" + getContents() + ", getWriteDate()="
				+ getWriteDate() + ", getHit()=" + getHit() + ", getgNo()=" + getgNo() + ", getoNo()=" + getoNo()
				+ ", getDepth()=" + getDepth() + ", getUserNo()=" + getUserNo() + ", getUserName()=" + getUserName()
				+ ", getBlockStartNum()=" + getBlockStartNum() + ", getBlockLastNum()=" + getBlockLastNum()
				+ ", getLastPageNum()=" + getLastPageNum() + ", getPage()=" + getPage() + ", getTotalCount()="
				+ getTotalCount() + ", getRownum()=" + getRownum() + ", getFileName()=" + getFileName()
				+ ", getOriFileName()=" + getOriFileName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
