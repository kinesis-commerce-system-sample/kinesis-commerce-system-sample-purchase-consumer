package com.example.kinesiscommercesystemsample.purchase.consumer.infrastracture.dao.message;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface MessageDao {

	@Select
	Optional<MessageEntity> selectById(String messageId);

	@Insert
	int insert(MessageEntity messageEntity);
}
