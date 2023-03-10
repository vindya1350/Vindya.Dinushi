package com.planit.utils;

import com.planit.pageObjects.BasePage;
import com.planit.pageObjects.LoginHomePage;
import com.planit.pageObjects.carting.CartPage;
import com.planit.pageObjects.contact.ContactSubmissionPage;
import com.planit.pageObjects.products.ProductsListPage;

public class PageObjectManager {

    private BasePage basePage;
    private ContactSubmissionPage contactSubmissionPage;
    private CartPage cartPage;
    private ProductsListPage productsListPage;
    private LoginHomePage loginHomePage;

    public PageObjectManager() {
    }

    public BasePage basePage() {
        return (basePage == null) ? basePage = new BasePage() : basePage;
    }

    public ContactSubmissionPage contactSubmissionPage() {
        return (contactSubmissionPage == null) ? contactSubmissionPage = new ContactSubmissionPage() : contactSubmissionPage;
    }

    public LoginHomePage loginHomePage() {
        return (loginHomePage == null) ? loginHomePage = new LoginHomePage() : loginHomePage;
    }

    public CartPage cartPage() {
        return (cartPage == null) ? cartPage = new CartPage() : cartPage;
    }

    public ProductsListPage productsListPage() {
        return (productsListPage == null) ? productsListPage = new ProductsListPage() : productsListPage;
    }


}
