package com.signin.request.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.signin.request.entity.Status;
import com.signin.request.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	Optional<?> findUserByStatusId(Long statusId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Status v SET v.status=?1,v.statusMessage=?2 WHERE v.statusId=?3")
	Integer updateUserStatus(String status, String statusMessage, Long userid);

}
