package models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by 11707698d on 4/14/16.
 */
@Named
@Singleton
public interface ForumPostRatingRepository extends CrudRepository<ForumPostRating, Long> {
    /**
     * Get upvote count for one post
     */
    @Query (value = "select count(*) from ForumPostRating where postId=?1 and updown=1", nativeQuery = true)
    Integer getUpvoteCount(Long id);

    /**
     * Get downvote count for one post
     */
    @Query (value = "select count(*) from ForumRepository where postId=?1 and updown=-1", nativeQuery = true)
    Integer getDownvoteCount(Long id);

}
