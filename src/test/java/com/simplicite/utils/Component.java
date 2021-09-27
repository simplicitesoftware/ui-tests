package com.simplicite.utils;

import com.codeborne.selenide.SelenideElement;

public class Component {
    public static void sendFormControl(SelenideElement selenideElement, String name) {
        selenideElement.click();
        selenideElement.setValue(name);
        selenideElement.pressEnter();
    }
}
