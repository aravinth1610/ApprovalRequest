package com.signin.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.signin.request.entity.AuthUser;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

	AuthUser findByGmail(String gmail);
	
	Boolean existsByGmail(String gmail);
	
}
