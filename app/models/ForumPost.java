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
	private long userId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private String postTitle;

	@Column(columnDefinition = "TEXT")
	private String postContent;
	private String paperLink;

	@OneToMany(mappedBy = "belongToPost", fetch = EAGER, cascade = CascadeType.ALL)
	private List<ForumPostRating> ratings;

	@OneToMany(mappedBy = "belongToPost", fetch = EAGER, cascade = CascadeType.ALL)
	private List<ForumPostComment> comments;

	private long bestCommentId;

	public String getPaperLink() {
		return paperLink;
	}

	public void setPaperLink(String paperLink) {
		this.paperLink = paperLink;
	}

	public ForumPost() {

	}
	
	public ForumPost(long userId, Date timestamp,
					 String postTitle, String postContent, String paperLink, List<ForumPostComment> comments, long bestCommentId) {
		super();
		this.comments = comments;
		this.bestCommentId = bestCommentId;
		setUserId(userId);
		setTimestamp(timestamp);
		setPostTitle(postTitle);
		setPostContent(postContent);
		setPaperLink(paperLink);
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public List<ForumPostRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<ForumPostRating> ratings) {
		this.ratings = ratings;
	}

	public List<ForumPostComment> getComments() {
		return comments;
	}

	public void setComments(List<ForumPostComment> comments) {
		this.comments = comments;
	}

	public long getBestCommentId() {
		return bestCommentId;
	}

	public void setBestCommentId(long bestCommentId) {
		this.bestCommentId = bestCommentId;
	}
}
