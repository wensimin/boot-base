package com.sping.project.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sping.project.app.entity.MongoUser;

public interface UserMongoDao extends MongoRepository<MongoUser, Long>{
}
