package com.example.kinesiscommercesystemsample.purchase.consumer.infrastracture.dao.itempurchase;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface ItemPurchaseDao {

	@Insert
	int insert(ItemPurchaseEntity itemPurchaseEntity);

	@Update(excludeNull = true)
	int update(ItemPurchaseEntity itemPurchaseEntity);
}
