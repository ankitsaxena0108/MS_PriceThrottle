package com.ms.service;

import java.util.ArrayList;
import java.util.List;

import com.ms.listener.PriceListener;

public class BankPriceUpdateService implements PriceUpdateService{

	List<PriceListener> bankPriceRetrieverList;
	
	@Override
	public void subscribeToBankPriceUpdates(PriceListener priceListener) {
		if (bankPriceRetrieverList != null) {
			bankPriceRetrieverList.add(priceListener);
		}else{
			bankPriceRetrieverList = new ArrayList<>();
			bankPriceRetrieverList.add(priceListener);
		}
	}

	@Override
	public void subscribeToCompanyPriceUpdates(PriceListener priceListener) {
	}
	
}
