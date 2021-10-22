package com.simplicite.menu.domaininterface;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.simplicite.menu.MainMenuProperties.*;

public class Styles {

    public static void click() {
        Component.clickMenu(domaininterface,"Crosstab");
    }
}
