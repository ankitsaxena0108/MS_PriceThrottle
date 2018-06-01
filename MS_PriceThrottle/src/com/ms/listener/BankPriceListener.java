package com.ms.listener;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class BankPriceListener implements PriceListener {

	private Map<String, Stack<Double>> symbolMap;

	public BankPriceListener() {
		symbolMap = new ConcurrentHashMap<>();
	}

	/* (non-Javadoc)
	 * This method will put the bank prices in stack corresponding to symbol
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
		Stack<Double> Stack = symbolMap.get(symbol);
		if (Stack != null && Stack.size() > 0) {
			return Stack.pop();
		}
		return null;
	}

}
