package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.*;

public class SystemProperties {

    public static void find(String name) {
        work.find("#sys_code").setValue(name).pressEnter();
        work.find("[data-field=\"sys_code\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static void click() {
        clickAdmin("SystemParam");
    }

    public static boolean verifyValue() {
        String txt = $("#field_sys_value_edit").getText();
        Pattern p = Pattern.compile(".*\"database\": true.*");
        Matcher m = p.matcher(txt);
        return m.find();
    }
}
