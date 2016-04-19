package controllers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
	Alfred Huang
	Apr 13
*/
@Named
@Singleton
public class ForumController extends Controller {
    
    private final ForumRepository forumRepository;
	private final ForumPostCommentRepository forumPostCommentRepository;
	private final ForumPostRatingRepository forumPostRatingRepository;

	private final UserRepository userRepository;
	private final PublicationsRepository publicationsRepository;

	@Inject
	public ForumController(ForumRepository forumRepository,
                           ForumPostRatingRepository forumPostRatingRepository,
						   ForumPostCommentRepository forumPostCommentRepository,
						   UserRepository userRepository,
						   PublicationsRepository publicationsRepository
						   ) {
		this.forumRepository = forumRepository;
        this.forumPostRatingRepository = forumPostRatingRepository;
		this.forumPostCommentRepository = forumPostCommentRepository;
		this.userRepository = userRepository;
		this.publicationsRepository = publicationsRepository;

	}

	public Result addNewPost () {
        try {
            JsonNode postNode = request().body().asJson();
			if (postNode == null) {
				return badRequest("Post not saved, expecting json data.");
			}
//            String timestamp = postNode.findPath("timestamp").asText();
            String postTitle = postNode.findPath("title").asText();
            String postContent = postNode.findPath("content").asText();
            String paperLink = postNode.findPath("link").asText();

			/* Now assume the user is the 1st one */
			User user = userRepository.findOne(1L);

			/* Now assume the timestamp is the current time */
			Date timestamp = new Date();

            ForumPost forumPost = new ForumPost(user,
					timestamp,
                    postTitle,
					postContent,
					paperLink,
					-1);

            ForumPost savedForumPost = forumRepository.save(forumPost);
            if (savedForumPost == null) {
                return badRequest("Forum post is not properly saved.");
            }
            return created(new Gson().toJson(savedForumPost.getPostId()));
        } catch (Exception e) {
            return internalServerError();
        }
	}
	
	public Result getPosts (Integer start, Integer limit) {
	   List<ForumPost> posts = forumRepository.getPostsInRange(start, limit);
	   return ok(new Gson().toJson(posts));
	}
	
	public Result getPost (Long id) {
		ForumPost post = forumRepository.findOne(id);
		return ok(new Gson().toJson(post));
	}

	public Result vote (Long pid, Long uid, Integer updown) {
        ForumPost post = forumRepository.findOne(pid);
		User user = userRepository.findOne(uid);
		ForumPostRating rating = new ForumPostRating(user, updown, post);
		ForumPostRating response = forumPostRatingRepository.save(rating);
		return created(new Gson().toJson(response.getRid()));
	}

	public Result getUpvoteCount (Long pid) {
		Integer count = forumPostRatingRepository.getUpvoteCount(pid);

		return ok(new Gson().toJson(count));
	}

	public Result getDownvoteCount (Long pid) {
		Integer count = forumPostRatingRepository.getDownvoteCount(pid);
		return ok(new Gson().toJson(count));
	}
}
