package com.ms.bank.pricevalidator;

import java.util.logging.Logger;

import com.ms.bank.subscriber.BankPublishMonitor;
import com.ms.client.subscriber.ClientPriceRetriever;
import com.ms.dao.Product;
import com.ms.listener.BankPriceListener;
import com.ms.listener.CompanyPriceListener;
import com.ms.service.BankPriceUpdateService;
import com.ms.service.CompanyPriceUpdateService;


/**
 * This class is used to monitor the prices coming from bank and company
 * @author Ankit Saxena
 *
 */
public class PricePublishMonitor implements BankPublishMonitor, ClientPriceRetriever, Runnable{

	public Logger logger = Logger.getLogger("PricePublishMonitor");
	private BankPriceUpdateService bankPriceUpdateService;
	private CompanyPriceUpdateService companyPriceUpdateService;
	private CompanyPriceListener compPriceListener;
	private BankPriceListener bankPriceListener;
	private Product product;
	
	public PricePublishMonitor(CompanyPriceListener compPriceListener, BankPriceListener bankPriceListener, Product product) {
		super();
		this.compPriceListener = compPriceListener;
		this.bankPriceListener = bankPriceListener;
		this.product = product;
	}

	public PricePublishMonitor(BankPriceUpdateService bankPriceUpdateService,
			CompanyPriceUpdateService companyPriceUpdateService) {
		super();
		this.bankPriceUpdateService = bankPriceUpdateService;
		this.companyPriceUpdateService = companyPriceUpdateService;
	}

	@Override
	public void fetchLatestCompanyPrice() {
		companyPriceUpdateService.subscribeToCompanyPriceUpdates(new CompanyPriceListener());
	}

	@Override
	public void fetchLatestBankPrice() {
		bankPriceUpdateService.subscribeToBankPriceUpdates(new BankPriceListener());
	}

	@Override
	public void run() {
		bankPriceListener.priceUpdate(product.getSymbol(), product.getPrice());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.severe("Interrupter");
		}
		compPriceListener.priceUpdate(product.getSymbol(), product.getPrice());
	}

}
