package com.simplicite.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.actions;

public class Component {
    public static void sendFormControl(SelenideElement selenideElement, String name) {

        actions().moveToElement(selenideElement).click().keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(name).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).perform();
    }
}
