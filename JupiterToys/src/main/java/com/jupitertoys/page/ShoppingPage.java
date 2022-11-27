package com.jupitertoys.page;

import org.openqa.selenium.By;

public class ShoppingPage extends CommonPage {

	By products = By.xpath("//div[@class='products ng-scope']/ul");

	public Double addProductToCart(String productName, int quantity) {

		double priceOfProduct = 0.0;

		try {

			waitForElementPresent(products);

			String price = driver.findElement(By.xpath("//h4[text()='" + productName + "']//ancestor::li//span"))
					.getText().trim();

			price = price.replace("$", "");

			priceOfProduct = Double.parseDouble(price);

			for (int i = 1; i <= quantity; i++) {

				driver.findElement(By.xpath("//h4[text()='" + productName + "']//ancestor::li//a")).click();
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return priceOfProduct;
	}
}
