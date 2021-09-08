package com.gregoire.testv1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageTest {
    MainPage mainPage = new MainPage(new ChromeDriver(), "http://localhost:80");

    @BeforeAll
    public static void setUpAll() {
        // set up the browser
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void AuthTest() {
        mainPage.MainPageAuthName("designer", "designer1903");
    }
}
