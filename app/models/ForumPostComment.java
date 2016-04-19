package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 11707698d on 4/14/16.
 */
@Entity
public class ForumPostComment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long cid;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private ForumPost postId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    private String commentTitle;

    @Column (columnDefinition = "TEXT")
    private String commentText;

    private String replyTo;

    public ForumPostComment () {

    }

    public ForumPostComment(ForumPost belongToPost,
                            User uid, Date timestamp, String commentTitle,
                            String commentText, String replyTo) {
        super();
        setPostId(belongToPost);
        setUserId(uid);
        setTimestamp(timestamp);
        setCommentTitle(commentTitle);
        setCommentText(commentText);
        setReplyTo(replyTo);
    }


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public ForumPost getPostId() {
        return postId;
    }

    public void setPostId(ForumPost postId) {
        this.postId = postId;
    }



    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
