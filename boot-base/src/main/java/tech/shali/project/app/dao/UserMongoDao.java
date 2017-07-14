package tech.shali.project.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.shali.project.app.entity.MongoUser;

public interface UserMongoDao extends MongoRepository<MongoUser, Long>{
}
