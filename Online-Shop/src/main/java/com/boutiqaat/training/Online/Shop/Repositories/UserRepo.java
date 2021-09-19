package com.boutiqaat.training.Online.Shop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boutiqaat.training.Online.Shop.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	
}
