package com.example.kinesiscommercesystemsample.purchase.consumer;

import com.example.kinesiscommercesystemsample.purchase.consumer.mom.purchase.PurchaseStreamReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerRunner implements CommandLineRunner {

	@Autowired
	private PurchaseStreamReader purchaseStreamReader;

	@Override
	public void run(String... args) throws Exception {

		try {

			purchaseStreamReader.run();

			log.info("batch end."); // 通常はここに来ない。

		} catch (Exception e) {

			log.error("error occured. exitCode=1.", e);

			System.exit(1);
		}

	}
}
