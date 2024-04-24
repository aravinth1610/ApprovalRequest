package com.signin.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.signin.request.entity.RoleBaseApi;

@Repository
public interface RoleBaseApiRepository extends JpaRepository<RoleBaseApi, Long> {

}
