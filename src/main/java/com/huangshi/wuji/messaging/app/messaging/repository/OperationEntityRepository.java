package com.huangshi.wuji.messaging.app.messaging.repository;

import com.huangshi.wuji.messaging.app.messaging.entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationEntityRepository extends CrudRepository<OperationEntity, Long> {
}
