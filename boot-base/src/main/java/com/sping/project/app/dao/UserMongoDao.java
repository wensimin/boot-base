package com.sping.project.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sping.project.app.entity.User;

public interface UserMongoDao extends MongoRepository<User, Long>{
}
