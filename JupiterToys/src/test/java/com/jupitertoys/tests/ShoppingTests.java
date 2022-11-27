package com.jupitertoys.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jupitertoys.page.CartPage;
import com.jupitertoys.page.CommonPage;
import com.jupitertoys.page.ShoppingPage;

public class ShoppingTests extends CommonPage {

	ShoppingPage shopPage = new ShoppingPage();
	CartPage cartPage = new CartPage();
	int STUFFEDFROGQUANTITY = 2;
	int FLUFFYBUNNYQUANTITY = 5;
	int VALENTINEBEARQUANTITY = 3;

	@Test(priority = 3)
	public void verifyTotalOfProducts() {

		navigatetoShoppingPage();
		double unitpriceOffrog = shopPage.addProductToCart("Stuffed Frog", STUFFEDFROGQUANTITY);
		double unitPricOfBunny = shopPage.addProductToCart("Fluffy Bunny", FLUFFYBUNNYQUANTITY);
		double unitPricOfBear = shopPage.addProductToCart("Valentine Bear", VALENTINEBEARQUANTITY);

		double expected_subTotal_Frog = unitpriceOffrog * Double.valueOf(STUFFEDFROGQUANTITY);
		double expected_subTotal_Bunny = unitPricOfBunny * Double.valueOf(FLUFFYBUNNYQUANTITY);
		double expected_subTotal_Bear = unitPricOfBear * Double.valueOf(VALENTINEBEARQUANTITY);
		navigatetoCartPage();
		double actualFrogSubTotal = cartPage.getSubTotal("Stuffed Frog");
		if (actualFrogSubTotal == expected_subTotal_Frog) {
			Assert.assertTrue(true, "Subtotal of Stuffed Frog is correct");
		} else {
			Assert.fail("Sub Total of Stuffed Frog is incorrect");
		}

		double actualBunnySubTotal = cartPage.getSubTotal("Fluffy Bunny");

		if (actualBunnySubTotal == expected_subTotal_Bunny) {
			Assert.assertTrue(true, "Subtotal of Fluffy Bunny is correct");
		} else {
			Assert.fail("Sub Total of Fluffy Bunny is incorrect");
		}

		double actualBearSubTotal = cartPage.getSubTotal("Valentine Bear");
		if (actualBearSubTotal == expected_subTotal_Bear) {
			Assert.assertTrue(true, "Subtotal of Valentine Bear is correct");
		} else {
			Assert.fail("Sub Total of Valentine Bear is incorrect");
		}

		double actualtotalOfProducts = cartPage.getTotal();
		double expectedTotalofProfucts = actualFrogSubTotal + actualBunnySubTotal + actualBearSubTotal;
		if (actualtotalOfProducts == expectedTotalofProfucts) {
			Assert.assertTrue(true, "The sub total of all products is corect");
		} else {
			Assert.fail("The sub total of all products is incorrect");
		}
	}
}
