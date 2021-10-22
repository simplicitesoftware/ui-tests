package com.simplicite.menu.templateeditor;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.work;

public class TemplateEditor {

    public static void navigateToEditor() {
        $("button[data-action*=\"editTemplate\"]").shouldBe(Condition.exist, Duration.ofSeconds(20)).click();
        SelenideElement element = $("#dlgmodal").find(".img-flow > img:nth-child(1)").shouldBe(Condition.appear);
        actions().pause(Duration.ofSeconds(1)).moveToElement(element).click(element).perform();
    }

    public static void addButton(String areaname, String type) {
        SelenideElement area;
        if (areaname == null)
            area = work.find(".dock-zone[data-dz=\"1\"]").should(Condition.exist);
        else
            area = work.find("[data-areaname=\"" + areaname + "\"]").should(Condition.exist);

        SelenideElement bouton = area.findAll("button").shouldBe(CollectionCondition.sizeGreaterThan(0)).last();

        area.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"nearest\"}");

        SelenideElement element = area.findAll("[data-menu=\"" + type + "\"]").shouldBe(CollectionCondition.sizeGreaterThan(0)).last();
        while(!element.isDisplayed()){
            bouton.hover().click();
        }
        element.click();
    }

    public static void addRow() {
        SelenideElement area1 = work.find(".dock-zone[data-dz=\"1\"]");
        area1.find("button").click();
        area1.find("[data-menu=\"rowcols row2\"]").click();
    }

    public static void addArea() {
        addButton(null, "area");
    }

    public static void clickEditor() {
        $("button[data-action*=\"editTemplate\"]").click();
    }


    public static void setEditorTemplate(String html) {
        $("button[data-action=\"save\"]").click();
        SelenideElement dlgmodal = $("#dlgmodal_saveAll");
        SelenideElement text = dlgmodal.find("textarea").shouldBe(Condition.visible);
        text.clear();
        actions().moveToElement(text).click(text).sendKeys(html).build().perform();
        SelenideElement element = dlgmodal.find("button[data-action=\"SAVE\"]");
        actions().moveToElement(element).click(element).perform();
    }

    public static void modifyArea(String area) {
        SelenideElement edit = work.find(".area[data-area=\"" + area + "\"]").find("[data-action=\"edit\"]");
        edit.hover().click();
        $("#dlgmodal").shouldBe(cssClass("show"));
    }

    public static void modifyField(String field) {
        SelenideElement edit = work.find("[data-field=\"" + field + "\"]").find("[data-action=\"edit\"]");
        edit.hover().click();
        $("#dlgmodal").shouldBe(cssClass("show"));

        Selenide.sleep(1000);
    }

    public static void closeEditor() {
        MainMenuProperties.close();
    }

}
