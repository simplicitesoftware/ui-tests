package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;

import static com.simplicite.menu.MainMenuProperties.*;

public class SystemProperties {

    public static void find(String name) {
        work.find("#sys_code").setValue(name).pressEnter();
        work.find("[data-field=\"sys_code\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static void click() {
        if (!administration.isDisplayed())
            administration.click();
        mainmenu.find("*[data-obj=\"SystemParam\"]").click();
    }
}
