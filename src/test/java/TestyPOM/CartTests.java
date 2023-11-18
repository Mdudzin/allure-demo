package TestyPOM;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart Tests")
public class CartTests extends BaseTest {

    @Test
    @DisplayName("Add product to cart from product page test")
    @Description("Add product to cart from product page test")
    public void addToCartFromProductPageTest() {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        boolean isProductInCart = productPage.addToCart().viewCart().isProductInCart(testData.getProduct().getId());

        assertTrue(isProductInCart,
                "Remove button was not found for a product with id=" + testData.getProduct().getId() + ". " +
                        "Was the product added to cart?");
    }

    @Test
    public void addToCartFromCategoryPageTest() {
        CategoryPage categoryPage = new CategoryPage(driver).goTo(configuration.getBaseUrl() + testData.getCategoryURL());
        categoryPage.demoNotice.close();
        boolean isProductInCart = categoryPage
                .addToCart(testData.getProduct().getId())
                .viewCart()
                .isProductInCart(testData.getProduct().getId());


        assertTrue(isProductInCart,
                "Remove button was not found for a product with  id=" + testData.getProduct().getId() + ". " +
                        "Was the product added to cart?");
    }

    @Test
    public void addOneProductTenTimesTest() {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        int productQuantity = productPage.addToCart(10).viewCart().getProductQuantity();

        assertEquals(10, productQuantity,
                "Quantity of the product is not what expected. Expected: 10, but was " + productQuantity);
    }

    @Test
    public void addTenProductsToCartTest() {
        ProductPage productPage = new ProductPage(driver);
        for (String product : testData.getProductPages()) {
            productPage.goTo(configuration.getBaseUrl() + "/product" + product).addToCart();
        }
        int numberOfItems = productPage.header.viewCart().getNumberOfProducts();

        assertEquals(10, numberOfItems,
                "Number of items in the cart is not correct. Expected: 10, but was: " + numberOfItems);
    }

    @Test
    public void changeNumberOfProductsTest() {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        int quantity = productPage.addToCart().viewCart().changeQuantity(8).updateCart().getProductQuantity();

        assertEquals(8, quantity,
                "Quantity of the product is not what expected. Expected: 8, but was " + quantity);
    }

    @Test
    public void removePositionFromCartTest() {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        boolean isCartEmpty = productPage.addToCart().viewCart().removeProduct(testData.getProduct().getId()).isCartEmpty();

        assertTrue(isCartEmpty,
                "Cart is not empty after removing the product");
    }
}
