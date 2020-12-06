package com.mycom.blog.dto.enumtype;

public enum Price_Coin {
	A(1000,100),B(5000,550),C(10000,1200),D(50000,5150);

	int price,coin;

	private Price_Coin(int price, int coin) {
		this.price = price;
		this.coin = coin;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	
};
