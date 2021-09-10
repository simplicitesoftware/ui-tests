package com.gregoire.testv1;

import com.codeborne.selenide.Configuration;
import com.gregoire.testv1.module.Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageTest {
    ChromeDriver chromeDriver = new ChromeDriver();
    MainPage mainPage = new MainPage(chromeDriver, "http://localhost:80");

    @BeforeAll
    public static void setUpAll() {
        // set up the browser
        Configuration.browserSize = "1280x800";
    }

    @Test
    public void AuthTest() {
        mainPage.MainPageAuthName("designer", "designer1903");
    }

    @Test
    public void NavigateToModule() throws InterruptedException {
        mainPage.MainPageAuthName("designer", "designer1903");
        MenuSide menuSide = new MenuSide(chromeDriver, 1, 2);
        menuSide.click();
    }

    @Test
    public void CreateModule()
    {
        mainPage.MainPageAuthName("designer", "designer1903");
        MenuSide menuSide = new MenuSide(chromeDriver, 1, 2);
        menuSide.click();
        Module module = new Module(chromeDriver);
        module.create("Trainingv1", "trn");
    }

    @Test
    public void DeleteModule()
    {
        mainPage.MainPageAuthName("designer", "designer1903");
        MenuSide menuSide = new MenuSide(chromeDriver, 1, 2);
        menuSide.click();
        Module module = new Module(chromeDriver);
        module.delete("Training");
    }
}
