package com.ms.bank.pricevalidator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ms.dao.Product;
import com.ms.listener.BankPriceListener;
import com.ms.listener.CompanyPriceListener;

public class PricePublisher {

	private BankPriceListener bankPriceListener;
	private CompanyPriceListener companyPriceListener;
	
	public PricePublisher(BankPriceListener bankPriceListener, CompanyPriceListener companyPriceListener) {
		super();
		this.bankPriceListener = bankPriceListener;
		this.companyPriceListener = companyPriceListener;
	}
	
	public void pricePublish() throws InterruptedException{
		
		ExecutorService service= Executors.newFixedThreadPool(11);
		
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",30.4)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",30.5)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",30.6)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("INFOSYS",20.4)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("INFOSYS",20.6)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("HCL",10.4)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",30.8)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("INFOSYS",20.8)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("HCL",10.5)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",30.9)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("INFOSYS",20.9)));
	
		Thread.sleep(3000);
	
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("TCS",31.9)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("INFOSYS",21.8)));
		service.submit(new PricePublishMonitor(companyPriceListener, bankPriceListener, new Product("HCL",10.9)));
		
		service.shutdown();
	}
	
}
