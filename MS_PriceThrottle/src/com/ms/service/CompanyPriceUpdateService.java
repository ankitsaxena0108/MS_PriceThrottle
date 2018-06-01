package com.ms.service;

import java.util.ArrayList;
import java.util.List;

import com.ms.listener.PriceListener;

public class CompanyPriceUpdateService implements PriceUpdateService{

	List<PriceListener> clientPriceRetrieverList;
	
	@Override
	public void subscribeToBankPriceUpdates(PriceListener priceListener) {
	}

	/* (non-Javadoc)
	 * This method will subscribe all the client to get latest prices from company
	 * @see com.ms.service.PriceUpdateService#subscribeToCompanyPriceUpdates(com.ms.listener.PriceListener)
	 */
	@Override
	public void subscribeToCompanyPriceUpdates(PriceListener priceListener) {
		if (clientPriceRetrieverList != null) {
			clientPriceRetrieverList.add(priceListener);
		} else {
			clientPriceRetrieverList = new ArrayList<>();
			clientPriceRetrieverList.add(priceListener);
		}
	}

}
