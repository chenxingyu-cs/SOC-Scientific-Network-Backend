package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

/*
	Alfred Huang
	Apr 13
*/
@Entity
public class ForumPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long postId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User userId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private String postTitle;

	@Column(columnDefinition = "TEXT")
	private String postContent;

	private String paperLink;

	private long bestCommentId;

	public String getPaperLink() {
		return paperLink;
	}

	public void setPaperLink(String paperLink) {
		this.paperLink = paperLink;
	}

	public ForumPost() {

	}
	
	public ForumPost(User userId,
					 Date timestamp,
					 String postTitle,
					 String postContent,
					 String paperLink,
					 long bestCommentId) {
		super();
		setUserId(userId);
		setTimestamp(timestamp);
		setPostTitle(postTitle);
		setPostContent(postContent);
		setPaperLink(paperLink);
		setBestCommentId(bestCommentId);
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public long getBestCommentId() {
		return bestCommentId;
	}

	public void setBestCommentId(long bestCommentId) {
		this.bestCommentId = bestCommentId;
	}
}
