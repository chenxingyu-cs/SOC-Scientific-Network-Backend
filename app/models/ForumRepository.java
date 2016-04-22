package models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import javax.inject.Named;
import javax.inject.Singleton;


import java.util.List;

/*
	Alfred Huang
	Apr 13
*/
@Named
@Singleton
public interface ForumRepository extends CrudRepository<ForumPost, Long> {

	@Query(value = "select f.* from enterpriseKG.ForumPost f order by postId desc limit ?2 offset ?1", 
		nativeQuery = true)
	public List<ForumPost> getPostsInRange(int start, int limit);

	
}
