package edu.posthub.posthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.posthub.posthub.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}