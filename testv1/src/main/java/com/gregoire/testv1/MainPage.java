package com.gregoire.testv1;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class MainPage {

    public WebElement usernameInput;

    public WebElement passwordInput;

    public WebElement authsigninsubmitButton;

    public MainPage(ChromeDriver chromeDriver) {
        chromeDriver.get("https://ggally.demo.simplicite.io");
        usernameInput = chromeDriver.findElement(By.id("auth-signin-username"));
        passwordInput = chromeDriver.findElement(By.id("auth-signin-password"));
        authsigninsubmitButton = chromeDriver.findElement(By.id("auth-signin-submit"));
    }

    public void MainPageAuthName() {
        if (usernameInput != null)
            usernameInput.sendKeys("usertest");
        if (passwordInput != null)
            passwordInput.sendKeys("usertest");
        authsigninsubmitButton.click();
    }
}
