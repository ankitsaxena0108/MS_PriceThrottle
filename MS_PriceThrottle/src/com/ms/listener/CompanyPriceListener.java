package com.ms.listener;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class CompanyPriceListener implements PriceListener{

	private Map<String, Stack<Double>> symbolMap;

	public CompanyPriceListener() {
		symbolMap = new ConcurrentHashMap<>();
	}
	
	/* (non-Javadoc)
	 * This method will put the company prices in stack corresponding to symbol
	 * @see com.ms.listener.PriceListener#priceUpdate(java.lang.String, double)
	 */
	@Override
	public void priceUpdate(String symbol, double price) {
		Stack<Double> priceList = symbolMap.get(symbol);
		if (priceList != null && priceList.size() > 0) {
			priceList.add(price);
		} else {
			Stack<Double> newPriceList = new Stack<Double>();
			newPriceList.add(price);
			symbolMap.put(symbol, newPriceList);
		}
	}
	
	/**
	 * This method will get the latest prices corresponding to symbol
	 * @param symbol
	 * @return
	 */
	public Double getLatestPrice(String symbol) {
		Stack<Double> queue = symbolMap.get(symbol);
		if (queue != null && queue.size() > 0) {
			return queue.pop();
		}
		return null;
	}

}
