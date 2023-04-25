
package com.dev.dispensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dispensa.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
