package com.example.kinesiscommercesystemsample.purchase.consumer.mom.purchase;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.mom.processor.AbstractPurchaseRecordProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRecordProcessorFactory implements IRecordProcessorFactory {

	@Autowired
	AbstractPurchaseRecordProcessor purchaseRecordProcessor;

	@Override
	public IRecordProcessor createProcessor() {
		return purchaseRecordProcessor;
	}
}
