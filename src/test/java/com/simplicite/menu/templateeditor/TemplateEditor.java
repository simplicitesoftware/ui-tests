package com.simplicite.menu.templateeditor;

import com.codeborne.selenide.*;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.work;
import static java.time.Duration.ofSeconds;

public class TemplateEditor {

    public static void navigateToEditor() {
        $("button[data-action*=\"editTemplate\"]").shouldBe(Condition.exist, ofSeconds(20)).click();
        SelenideElement element = $("#dlgmodal").find(".img-flow > img:nth-child(1)").shouldBe(Condition.appear);
        actions().pause(ofSeconds(1)).moveToElement(element).click(element).perform();
        Selenide.sleep(1000);
    }

    public static void addButton(String areaname, String type) {
        SelenideElement area;
        if (areaname == null)
            area = work.find(".dock-zone[data-dz=\"1\"]").should(Condition.exist);
        else
            area = work.find("[data-areaname=\"" + areaname + "\"]").should(Condition.exist);

        SelenideElement bouton = area.findAll("button").shouldBe(CollectionCondition.sizeGreaterThan(0)).last();


        SelenideElement element = area.findAll("[data-menu=\"" + type + "\"]").shouldBe(CollectionCondition.sizeGreaterThan(0)).last();
        int count = area.findAll(".field").size();
        if (count > 0)
            area.findAll(".field").last().scrollIntoView(true);

        while(!element.isDisplayed()){
            actions().moveToElement(bouton).click().perform();
            Selenide.sleep(500);
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
        Selenide.sleep(1000);
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

    public static void saveDlgmodal(){
        Component.clickOnButtonEndDlgmodal("[data-action=\"SAVE\"]");
    }
}
