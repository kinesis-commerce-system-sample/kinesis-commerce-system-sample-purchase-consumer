package com.example.kinesiscommercesystemsample.purchase.consumer.service;

import com.example.kinesiscommercesystemsample.purchase.consumer.infrastracture.dao.itempurchase.ItemPurchaseDao;
import com.example.kinesiscommercesystemsample.purchase.consumer.infrastracture.dao.itempurchase.ItemPurchaseEntity;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PurchaseService {

	@Autowired
	private ItemPurchaseDao itemPurchaseDao;

	public void regist(String purchaseId, String itemId, Integer quantity) {

		val purchaseEntity = new ItemPurchaseEntity();
		purchaseEntity.setId(purchaseId);
		purchaseEntity.setItemId(itemId);
		purchaseEntity.setQuantity(quantity);
		purchaseEntity.setStatus("regist");
		itemPurchaseDao.insert(purchaseEntity);
	}

	public void changeStatus(String purchaseId, String status) {

		val purchaseEntity = new ItemPurchaseEntity();
		purchaseEntity.setId(purchaseId);
		purchaseEntity.setStatus(status);
		itemPurchaseDao.update(purchaseEntity);
	}
}
