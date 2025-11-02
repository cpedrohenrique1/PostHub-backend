package edu.posthub.posthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.posthub.posthub.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
