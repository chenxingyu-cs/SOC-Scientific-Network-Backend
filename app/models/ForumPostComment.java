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
    @JoinColumn(name="postId")
    private ForumPost belongToPost;

    private long uid;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    private String commentTitle;

    @Column (columnDefinition = "TEXT")
    private String commentText;

    private String replyTo;

    public ForumPostComment () {

    }

    public ForumPostComment(long cid, ForumPost belongToPost,
                            long uid, Date timestamp, String commentTitle,
                            String commentText, String replyTo) {

        this.cid = cid;
        this.belongToPost = belongToPost;
        this.uid = uid;
        this.timestamp = timestamp;
        this.commentTitle = commentTitle;
        this.commentText = commentText;
        this.replyTo = replyTo;
    }


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public ForumPost getBelongToPost() {
        return belongToPost;
    }

    public void setBelongToPost(ForumPost belongToPost) {
        this.belongToPost = belongToPost;
    }
}
