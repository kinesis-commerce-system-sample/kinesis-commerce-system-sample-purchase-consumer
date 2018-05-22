package com.example.kinesiscommercesystemsample.purchase.consumer.infrastracture.dao.itempurchase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Table(name = "item_purchase")
@Entity
@Getter
@Setter
@ToString
public class ItemPurchaseEntity {

	@Id
	private String id;

	private String itemId;

	private Integer quantity;

	private String status;
}
