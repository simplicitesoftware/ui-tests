package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.utils.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.simplicite.menu.MainMenuProperties.*;
import static com.codeborne.selenide.Condition.*;

public class BusinessObject {

    public SelenideElement objectbutton = mainmenu.find("[data-obj=\"ObjectInternal\"]");

    public static void click() {
        if (!administration.isDisplayed())
            administration.click();
        mainmenu.find("[data-obj=\"ObjectInternal\"]").click();
    }

    public static void next() {
        work.find("button[data-action=\"validate\"]").click();
        Selenide.sleep(1000);
    }

    public static void createObjectAssistant(String obj_name, String obj_table, String mdl_name, String prefix, String dmn_name) {

        work.find("button[data-action=\"ObjectCreateProcess\"]").click();


        work.find("#field_obo_name").setValue(obj_name);
        work.find("#field_obo_dbtable").setValue(obj_table);
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
        work.find("#field_obo_prefix").setValue(prefix);

        next();
        next();
        next();

        work.find("#obd_name").setValue(dmn_name).pressEnter();
        work.find("#obd_nohome").selectOptionByValue("true");
        work.find(".text-left").shouldHave(Condition.exactTextCaseSensitive(dmn_name)).click();
        next();
        if (work.find("button[data-action=\"validate\"]").exists())
            next();
    }

    public static void find(String name) {
        $("#obo_name").setValue(name).pressEnter();
        $("[data-field=\"obo_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static boolean isSuccess(String name) {
        return work.find("#field_obo_name").shouldHave(Condition.value(name)).exists();
    }

    public static void navigateToEditor() {
        $("button[data-action=\"editTemplate\"]").shouldBe(Condition.exist,Duration.ofSeconds(20)).click();
        SelenideElement element = $("#dlgmodal").find(".img-flow > img:nth-child(1)").shouldBe(Condition.appear);
        actions().pause(Duration.ofSeconds(1)).moveToElement(element).click(element).perform();
    }

    /**
     * 3 => Short text
     * 13 => Long text
     * 1 => Integer
     * 2 => Decimal (double)
     * 26 => Decimal (100,32)
     * 4 => Date
     * 5 => Date and time
     * 6 => Time
     * 7 => Enumeration
     * 14 => Multiple enumeration
     * 8 => Boolean
     * 10 => URL
     * 11 => HTML content
     * 12 => Email
     * 15 => Validated text
     * 17 => Document
     * 0 => Internal ID
     * 24 => Object
     * 9 => Password
     * 19 => External file
     * 20 => Image
     * 21 => Notepad
     * 22 => Phone number
     * 23 => Color
     * 25 => Geographical coordinates
     *
     * @param name          name
     * @param label         label
     * @param type          type
     * @param required      required
     * @param functionalkey fonctionnalkey
     */
    public static void addField(String areaname, String name, String label, int type, boolean required, boolean functionalkey) {

        addButton(areaname, "field");
        SelenideElement typed = $("#dlgmodal_field").find("#typed");
        typed.click();

        typed.find("[data-field-type=\"" + type + "\"]").click();

        SelenideElement modal = $("#dlgmodal");
        modal.find("#label").setValue(name);
        modal.find("#name").setValue(label);
        if (functionalkey)
            modal.find("label[for=\"key\"]").click();
        if (required)
            modal.find("label[for=\"req\"]").click();
        modal.find("button[data-action=\"SAVE\"]").click();
    }

    private static void addButton(String areaname, String type) {
        SelenideElement area;
        if (areaname == null)
            area = work.find(".dock-zone[data-dz=\"1\"]").should(Condition.exist);
        else
            area = work.find("[data-areaname=\"" + areaname + "\"]").should(Condition.exist);

        SelenideElement bouton = area.find("button").should(Condition.exist);
        SelenideElement element = area.find("[data-menu=\"" + type + "\"]");
        while(!element.isDisplayed())
            actions().moveToElement(bouton).click().build().perform();
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

    public static void saveEditor() {
        $("button[data-action=\"save\"]").shouldBe(and("Clickable", visible, enabled)).click();
        SelenideElement element = $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").shouldBe(Condition.appear);
        actions().moveToElement(element).click(element).perform();
    }

    public static void clickEditor() {
        $("button[data-action=\"editTemplate\"]").click();
    }

    public static void addFieldUnusedJoin(String areaname, String join, String field) {
        addButton(areaname, "field");

        SelenideElement type = $("#dlgmodal_field").find("#" + join);
        type.click();
        type.find("[data-field=\"" + field + "\"]").click();
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

    public static void modifyField(String field) {
        SelenideElement edit = work.find("[data-field=\"" + field + "\"]").find("[data-action=\"edit\"]");
        edit.hover().click();

    }

    public static void editEnum(String... args) {
        if (args.length < 3)
            throw new IllegalArgumentException();
        $("#dlgmodal").find("#editlist").click();
        $("#dlgmodal").shouldBe(hidden);

        SelenideElement dlgedit = $("#dlgmodal_editlist");
        int count = 0;
        for (var arg : args) {
            if (count < 3) {
                String str = Character.toString('A' + count);
                dlgedit.find("[name=\"code\"][value=\"" + str + "\"]").setValue(arg.toUpperCase(Locale.ROOT));
                dlgedit.find("[name=\"value\"][value=\"" + str + "\"]").setValue(arg);
            } else {
                dlgedit.find(".edit-lov > button").click();
                dlgedit.findAll(byName("code")).last().setValue(arg.toUpperCase(Locale.ROOT));
                dlgedit.findAll(byName("value")).last().setValue(arg);
            }
            count++;
        }
        System.out.println(Selenide.screenshot("src/test/resources"));
        SelenideElement save = dlgedit.find("[data-action=\"SAVE\"]");
        actions().moveToElement(save).click().perform();
        //bug UI on dlgmodal didn't disapear
        if ($("#dlgmodal").exists()){
            save = $("#dlgmodal").find("button[data-action=\"SAVE\"]");
            actions().moveToElement(save).click().perform();
        }
    }

    public static void closeEditor() {
        MainMenuProperties.close();
    }

    public static void addStateModel(int[][] listaccessorderstate, String group, String[][] trad) {
        work.find("button[data-action=\"OBJ_ADD_STM\"]").click();
        next();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
            if (listaccessorderstate[y][x] == 1)
                work.find("input[name=\"chk" +  y + "." + x + "\"]").click();
            }
        }
        next();
        ElementsCollection list = work.find(byText(group)).parent().findAll("input[type=\"checkbox\"]").filterBy(Condition.visible);
        list.forEach(SelenideElement::click);
        next();
        Arrays.stream(trad).forEach(e -> work.find("[name*=\"EN\"][value=\"" + e[0] + "\"]").setValue(e[1]));
        next();
    }

    public static void accessToOption(){
        SelenideElement area = work.find("[data-areaname=\"ObjectInternal-Btn\"]");
        area.find("[data-tab=\"1\"]").click();
        area.find("#field_obo_historic_2").click();
    }
}
