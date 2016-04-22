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
	private final ForumPostRatingRepository forumPostRatingRepository;

	@Inject
	public ForumController(ForumRepository forumRepository,
                           ForumPostRatingRepository forumPostRatingRepository) {
		this.forumRepository = forumRepository;
        this.forumPostRatingRepository = forumPostRatingRepository;
	}

	public Result createPost () {
        try {
            JsonNode postNode = request().body().asJson();
            Long userId = postNode.findPath("userId").asLong();
//            String timestamp = postNode.findPath("timestamp").asText();
            String postTitle = postNode.findPath("postTitle").asText();
            String postContent = postNode.findPath("postContent").asText();
            String paperLink = postNode.findPath("paperLink").asText();
            ForumPost forumPost = new ForumPost(userId, new Date(),
                    postTitle, postContent, paperLink, new ArrayList<ForumPostComment>(), 1);
            ForumPost post = forumRepository.save(forumPost);
            if (post == null) {
                return badRequest();
            }
            return created(new Gson().toJson(post.getPostId()));
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
		ForumPostRating rating = new ForumPostRating(uid, updown, post);
		ForumPostRating response = forumPostRatingRepository.save(rating);
		return created(new Gson().toJson(response.getPrid()));
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
