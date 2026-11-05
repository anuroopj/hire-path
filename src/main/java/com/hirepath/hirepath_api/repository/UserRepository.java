package com.hirepath.hirepath_api.repository;

import com.hirepath.hirepath_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);
}
