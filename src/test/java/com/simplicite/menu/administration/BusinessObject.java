package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.*;

public class BusinessObject {

    public SelenideElement objectbutton = mainmenu.find("[data-obj=\"ObjectInternal\"]");

    public static void click() {
        administration.click();
        mainmenu.find("[data-obj=\"ObjectInternal\"]").click();
    }

    public static void next() {
        work.find("*[data-action=\"validate\"]").click();
    }

    public static void createObjectAssistant(String obj_name, String obj_table, String mdl_name, String prefix, String dmn_name) {

        work.find("button[data-action=\"ObjectCreateProcess\"]").click();


        work.find("#field_obo_name").setValue(obj_name);
        work.find("#field_obo_dbtable").setValue(obj_table);
        SelenideElement modulename = work.find("#field_row_module_id__mdl_name");
        modulename.click();
        modulename.setValue(mdl_name).pressEnter();
        work.find("#field_obo_prefix").setValue(prefix);

        next();
        next();
        next();

        work.find("#obd_name").setValue(dmn_name).pressEnter();
        work.find("#obd_nohome").selectOptionByValue("true");
        work.find(".text-left").shouldHave(Condition.exactTextCaseSensitive(dmn_name)).click();
        next();
        next();
    }

    public static void find(String name) {
        work.find("#obo_name").setValue(name).pressEnter();
        work.find("[data-field=\"obo_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static boolean isSuccess(String name) {
        work.find("#field_obo_name").should(Condition.exist).shouldHave(Condition.value(name));
        return true;
    }

    public static void navigateToEditor() {
        $("button[data-action=\"editTemplate\"]").click();
        SelenideElement element = $("#dlgmodal").find(".img-flow > img:nth-child(1)").shouldBe(Condition.visible);
        actions().moveToElement(element).click(element).perform();
    }

    /**
     *  3 => Short text
     *  13 => Long text
     *  1 => Integer
     *  2 => Decimal (double)
     *  26 => Decimal (100,32)
     *  4 => Date
     *  5 => Date and time
     *  6 => Time
     *  7 => Enumeration
     *  14 => Multiple enumeration
     *  8 => Boolean
     *  10 => URL
     *  11 => HTML content
     *  12 => Email
     *  15 => Validated text
     *  17 => Document
     *  0 => Internal ID
     *  24 => Object
     *  9 => Password
     *  19 => External file
     *  20 => Image
     *  21 => Notepad
     *  22 => Phone number
     *  23 => Color
     *  25 => Geographical coordinates
     * @param name name
     * @param type type
     * @param required required
     * @param functionalkey fonctionnalkey
     */
    public static void addField(String name, int type, boolean required, boolean functionalkey) {


        SelenideElement area = work.find("#area1");

        area.find("button").click();
        area.find("[data-menu=\"field\"]").click();
        SelenideElement typed = $("#dlgmodal_field").find("#typed");
        typed.click();

        typed.find("[data-field-type=\"" + type + "\"]").click();

        SelenideElement modal = $("#dlgmodal");
        modal.find("#label").setValue(name);

        if (functionalkey)
            modal.find("label[for=\"key\"]").click();
        if (required)
            modal.find("label[for=\"req\"]").click();
        modal.find("button[data-action=\"SAVE\"]").click();

    }

    public static void save()
    {
        $("button[data-action=\"save\"]").click();
        SelenideElement element = $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]");
        actions().moveToElement(element).click(element).perform();
    }
    public static void clickEditor() {
        $("button[data-action=\"editTemplate\"]").click();
    }
}