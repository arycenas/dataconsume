package com.training.dataconsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.dataconsume.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
