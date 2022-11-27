package com.jupitertoys.page;

import org.openqa.selenium.By;

public class CartPage extends CommonPage {
	
	By checkoutButton = By.xpath("//a[text()='Check Out']");
	By totalOfProducts = By.xpath("//strong[contains(text(),'Total:')]");
	
	public Double getSubTotal(String ProductName) {

		double subTotal = 0.0;

		try {
			waitForElementPresent(checkoutButton);
			String sub_Total = driver
					.findElement(By.xpath("//td[contains(text(),'" + ProductName + "')]//parent::tr//td[4]")).getText()
					.trim();
			
			sub_Total = sub_Total.replace("$", "");
			subTotal = Double.parseDouble(sub_Total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subTotal;
	}
	
	public Double getTotal() {
		
		double totalAmount = 0.0;
		try {
			String total = driver.findElement(totalOfProducts).getText().trim();
			total = total.replace("Total:", "");
			totalAmount = Double.parseDouble(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalAmount;
	}
}
