package com.simplicite.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.work;

public enum Icon {

    CONSOLE("img/color/console", 1);


    private String name;
    private int type;

    /**
     * Set an Icon for Simplicite framework
     *
     * @param name name of the icon
     * @param type type of the icon
     */
    Icon(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Add an icon to a component with the data map
     */
    public void addIcon() {
        SelenideElement search = work.find("[data-action*=\"dmsel_field_\"][data-toggle=\"tooltip\"]");
        search.click();
        SelenideElement selectdatamap = $("#dlgmodal_selectDatamap");

        selectdatamap.find("input").sendKeys(name + Keys.ENTER);

        $("#iptab0").find("[data-tab=\"" + type + "\"]").shouldBe(Condition.visible, Duration.ofSeconds(30)).click();
        SelenideElement subtable = selectdatamap.find("#iptab0_" + type);
        ElementsCollection test = subtable.findAll("img");
        test.find(Condition.visible).click();
    }

    /**
     * @return name of the icon
     */
    public String getName() {
        return name;
    }

    /**
     * @param type type of the icon
     */
    public void getType(int type) {
        this.type = type;
    }
}
