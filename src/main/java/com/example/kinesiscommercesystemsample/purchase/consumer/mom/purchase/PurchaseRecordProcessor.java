package com.example.kinesiscommercesystemsample.purchase.consumer.mom.purchase;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.model.Record;
import com.example.kinesiscommercesystemsample.common.exception.BusinessRuleException;
import com.example.kinesiscommercesystemsample.common.exception.ProceededMessageException;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.PurchaseMessage;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.v1.PurchaseChangeStatusMessage;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.v1.PurchaseRegistMessage;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.mom.processor.AbstractPurchaseRecordProcessor;
import com.example.kinesiscommercesystemsample.purchase.consumer.service.MessageService;
import com.example.kinesiscommercesystemsample.purchase.consumer.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PurchaseRecordProcessor extends AbstractPurchaseRecordProcessor {

	@Autowired
	private MessageService messageService;

	@Autowired
	private PurchaseService purchaseService;

	@Override
	public void initialize(String shardId) {
		super.initialize(shardId);
	}

	@Override
	public void processRecords(List<Record> records, IRecordProcessorCheckpointer checkpointer) {
		super.processRecords(records, checkpointer);
	}

	@Override
	public void shutdown(IRecordProcessorCheckpointer checkpointer, ShutdownReason reason) {
		super.shutdown(checkpointer, reason);
	}

	@Override
	public void checkpoint(IRecordProcessorCheckpointer checkpointer) {
		super.checkpoint(checkpointer);
	}

	@Override
	public void processRecord(PurchaseMessage message) {

		val messageId = message.getMessageId();

		try {

			messageService.checkMessageConsistency(messageId);

			// Kinesisから受け取った生の文字列を出力
			val json = objectMapper.writeValueAsString(message);
			log.info("json={}, jsonSize={}", json, json.getBytes().length);

			if (message instanceof PurchaseRegistMessage) {

				val purchaseId = message.getPurchaseId();
				val itemId = ((PurchaseRegistMessage) message).getItemId();
				val quantity = ((PurchaseRegistMessage) message).getQuantity();

				purchaseService.regist(purchaseId, itemId, quantity);

			} else if (message instanceof PurchaseChangeStatusMessage) {

				val purchaseId = message.getPurchaseId();
				val status = ((PurchaseChangeStatusMessage) message).getStatus();

				purchaseService.changeStatus(purchaseId, status);

			} else {
				log.error("{}", message.getClass());
				// TODO : ここに入ってくるのはメッセージのバージョンアップ時の想定だが、どう扱うかを決める必要がある。
			}

		} catch (ProceededMessageException e) {

			log.warn(e.getMessage());

		} catch (BusinessRuleException e) {

			log.warn(e.getMessage());
			messageService.recordMessage(messageId);

		} catch (JsonProcessingException e) {

			// TODO : ここに入ってきたときの扱いを決める必要がある。
			log.error("", e);
			messageService.recordMessage(messageId);

		} catch (Exception e) {

			log.error("", e);
			messageService.recordMessage(messageId);

		} finally {

		}
	}
}
