package com.gregoire.testv1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage {

    private final WebElement usernameInput;
    private final WebElement passwordInput;
    private final WebElement authsigninsubmitButton;
    public final WebDriver driver;

    public MainPage(RemoteWebDriver driver, String url) {

        // connect to the web site
        this.driver = driver;
        this.driver.get(url);

        // find elements on the page for authentification
        usernameInput = this.driver.findElement(By.id("auth-signin-username"));
        passwordInput = this.driver.findElement(By.id("auth-signin-password"));
        authsigninsubmitButton = this.driver.findElement(By.id("auth-signin-submit"));

    }

    public void MainPageAuthName(String usr, String psw) {

        // fill login data on sign-in page
        if (usernameInput != null)
            usernameInput.sendKeys(usr);
        if (passwordInput != null)
            passwordInput.sendKeys(psw);

        // click on the button to validate the information
        authsigninsubmitButton.click();
    }
}
