package com.huangshi.wuji.messaging.app.messaging.repository;

import com.huangshi.wuji.messaging.app.messaging.entity.MessagingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagingEntityRepository extends CrudRepository<MessagingEntity, Long> {
}
