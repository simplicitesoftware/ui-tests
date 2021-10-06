package com.simplicite.menu.administration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.administration;
import static com.simplicite.menu.MainMenuProperties.work;

public class Domain{

    public void click() {
        administration.click();
    }

    public void createObject(String name) {

        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(name);
    }

    public void find(String name) {
        work.find("#obd_name").setValue(name).pressEnter();
        work.find("[data-field=\"obd_name\"]").find(new ByText(name)).click();
    }

}
