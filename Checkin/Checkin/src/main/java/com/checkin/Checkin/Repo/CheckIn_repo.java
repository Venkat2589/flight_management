package com.checkin.Checkin.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkin.Checkin.Entity.CheckIn;

public interface CheckIn_repo extends JpaRepository<CheckIn, Integer>{

}
