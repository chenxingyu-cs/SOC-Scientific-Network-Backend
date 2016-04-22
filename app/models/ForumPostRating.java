package models;

import javax.persistence.*;

@Entity
public class ForumPostRating {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long prid;

	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="postId")
	private ForumPost belongToPost;

	private long uid;
	private int updown;

	public ForumPostRating() {}
	public ForumPostRating(long uid, int updown, ForumPost belongToPost) {
		super();
		this.belongToPost = belongToPost;
		setUid(uid);
		setUpdown(updown);
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getUpdown() {
		return updown;
	}
	public void setUpdown(int updown) {
		this.updown = updown;
	}

	public long getPrid() {
		return prid;
	}

	public void setPrid(long prid) {
		this.prid = prid;
	}

	public ForumPost getBelongToPost() {
		return belongToPost;
	}

	public void setBelongToPost(ForumPost belongToPost) {
		this.belongToPost = belongToPost;
	}
}
