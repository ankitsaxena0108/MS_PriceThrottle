package com.ms.bank.pricevalidator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ms.listener.BankPriceListener;
import com.ms.listener.CompanyPriceListener;


/**
 * This is a test class to validate whether the latest prices are available from the bank and company
 * @author Ankit Saxena
 *
 */
public class PricePublisherTest {

	PricePublisher pricePublisher;
	BankPriceListener bankPriceListener;
	CompanyPriceListener companyPriceListener;
	
	@Before
	public void setup() throws InterruptedException{
		bankPriceListener = new BankPriceListener();
		companyPriceListener = new CompanyPriceListener();
		pricePublisher = new PricePublisher(bankPriceListener, companyPriceListener);
		pricePublisher.pricePublish();
	}
	
	@Test
	public void validateBothPriceListener() throws InterruptedException{
		
		// This is used to validate if the latest prices are coming from bank
		Thread.sleep(3000);
		Assert.assertEquals(31.9, bankPriceListener.getLatestPrice("TCS"),0);
		Assert.assertEquals(21.8, bankPriceListener.getLatestPrice("INFOSYS"), 0);
		Assert.assertEquals(10.9, bankPriceListener.getLatestPrice("HCL"), 0);
		
		
		// This is used to validate if latest prices from bank to company have still not arrived due to Thread.sleep
		Assert.assertTrue(companyPriceListener.getLatestPrice("TCS").compareTo(31.9) < 0);
		Assert.assertTrue(companyPriceListener.getLatestPrice("INFOSYS").compareTo(21.8) < 0);
		Assert.assertTrue(companyPriceListener.getLatestPrice("HCL").compareTo(10.9) < 0);

		
		// This is used to validate that latest prices from bank to company has arrived after thread sleep is over.
		Thread.sleep(5000);
		Assert.assertEquals(31.9, companyPriceListener.getLatestPrice("TCS"), 0);
		Assert.assertEquals(21.8, companyPriceListener.getLatestPrice("INFOSYS"), 0);
		Assert.assertEquals(10.9, companyPriceListener.getLatestPrice("HCL"), 0);
		
	}
}
