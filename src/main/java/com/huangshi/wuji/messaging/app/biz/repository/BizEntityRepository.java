package com.huangshi.wuji.messaging.app.biz.repository;

import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BizEntityRepository extends CrudRepository<BizEntity, Long> {
    List<BizEntity> findByBusinessId(Long businessId);
}
