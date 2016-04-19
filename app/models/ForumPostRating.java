package models;

import javax.persistence.*;

@Entity
public class ForumPostRating {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long rid;

	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="postId")
	private ForumPost belongToPost;

	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "authorId", referencedColumnName = "id")//using another table is better
	private User user;
	private int updown;

	public ForumPostRating() {}
	public ForumPostRating(User user, int updown, ForumPost belongToPost) {
		super();
		setBelongToPost(belongToPost);
		setuser(user);
		setUpdown(updown);
	}
	public User getuser() {
		return user;
	}
	public void setuser(User user) {
		this.user = user;
	}
	public int getUpdown() {
		return updown;
	}
	public void setUpdown(int updown) {
		this.updown = updown;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long prid) {
		this.rid = prid;
	}

	public ForumPost getBelongToPost() {
		return belongToPost;
	}

	public void setBelongToPost(ForumPost belongToPost) {
		this.belongToPost = belongToPost;
	}
}
