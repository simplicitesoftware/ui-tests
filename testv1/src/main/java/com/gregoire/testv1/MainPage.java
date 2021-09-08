package com.gregoire.testv1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    private final WebElement usernameInput;
    private final WebElement passwordInput;
    private final WebElement authsigninsubmitButton;

    public MainPage(ChromeDriver chromeDriver, String url) {

        // connect to the web site
        chromeDriver.get(url);

        // find elements on the page for authentification
        usernameInput = chromeDriver.findElement(By.id("auth-signin-username"));
        passwordInput = chromeDriver.findElement(By.id("auth-signin-password"));
        authsigninsubmitButton = chromeDriver.findElement(By.id("auth-signin-submit"));

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
