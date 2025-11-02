package edu.posthub.posthub.repositories;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.posthub.posthub.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
