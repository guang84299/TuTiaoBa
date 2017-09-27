package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_article")
//alter table g_article add index index_tagId(tagId);
//alter table g_article add index index_title(title);
//alter table g_article add index index_type(type);
//alter table g_article add index index_grelease(grelease);
public class GArticle {
	private long id;
	private int type;
	private String title;
	private String content;
	private String summary;
	private long tagId;
	private String keywords;
	private long showNum;
	private String headPath;
	private boolean grelease;
	private Date cdate;
	
	private long loveNum;
	private long commentNum;
	
	private String author;
	
	private GTag tag;
	
	public GArticle(){}
	public GArticle(int type,String title, String content, String summary, long tagId,
			String keywords, long showNum, String headPath,String author) {
		super();
		this.type = type;
		this.title = title;
		this.content = content;
		this.summary = summary;
		this.tagId = tagId;
		this.keywords = keywords;
		this.showNum = showNum;
		this.headPath = headPath;
		this.grelease = false;
		this.loveNum = 0l;
		this.commentNum = 0l;
		this.author = author;
		this.cdate = new Date();
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(nullable=false,length=256)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(length=512)
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	@Column(length=128)
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}
	@Column(length=128)
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	public long getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(long loveNum) {
		this.loveNum = loveNum;
	}
	public long getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(long commentNum) {
		this.commentNum = commentNum;
	}
	public boolean isGrelease() {
		return grelease;
	}
	public void setGrelease(boolean grelease) {
		this.grelease = grelease;
	}
	@Column(length=128)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Transient
	public GTag getTag() {
		return tag;
	}
	public void setTag(GTag tag) {
		this.tag = tag;
	}
	
	
}
