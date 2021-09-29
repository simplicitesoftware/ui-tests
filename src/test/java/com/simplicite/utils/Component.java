package com.simplicite.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.actions;

public class Component {
    public static void sendFormControl(SelenideElement selenideElement, String name) {
        actions().moveToElement(selenideElement).click(selenideElement).sendKeys(name).perform();
        selenideElement.pressEnter();
    }
}
