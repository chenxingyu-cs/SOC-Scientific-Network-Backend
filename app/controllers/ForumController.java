package controllers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.PersistenceException;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;
import models.ForumPost;
import models.ForumRepository;

import java.util.List;

/*
	Alfred Huang
	Apr 13
*/
@Named
@Singleton
public class ForumController extends Controller {
    
    private final ForumRepository forumRepository;
	
	@Inject
	public ForumController(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}

	public Result createPost () {
		ForumPost post = new ForumPost();
        post = forumRepository.save(post);
		return created(new Gson().toJson(post.getPostId()));
	}
	
	public Result getPosts (Integer start, Integer limit) {
	   List<ForumPost> posts = forumRepository.getPostsInRange(start, limit);
	   return ok(new Gson().toJson(posts));
	}
}
