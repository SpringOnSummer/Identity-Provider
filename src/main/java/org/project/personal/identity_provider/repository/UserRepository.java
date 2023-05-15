package org.project.personal.identity_provider.repository;

import org.project.personal.identity_provider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
