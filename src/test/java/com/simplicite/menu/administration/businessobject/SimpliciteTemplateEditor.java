package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.SimpliciteField;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.work;

public class SimpliciteTemplateEditor implements MainMenuProperties {

    public SimpliciteTemplateEditor()
    {
        click();
        $(".img-flow > img:nth-child(1)").click();
    }

    public SimpliciteTemplateEditor(String tmp) {
        click();
    }

    public void addField(String name, String width, boolean functionalkey, boolean required)
    {
        SelenideElement area = work.find("#area1");
        area.find("button").click();
        area.find("[data-menu=\"field\"]").click();
        SelenideElement typed = $("#dlgmodal_field").find("#typed");
        typed.click();
        typed.find("[data-field-type=\"3\"]").click();
        SelenideElement modal = $("#dlgmodal");
        modal.find("#label").setValue(name);
        modal.find("#size").setValue(width);
        if (functionalkey)
            modal.find("label[for=\"key\"]").click();
        if (required)
            modal.find("label[for=\"req\"]").click();
        modal.find("button[data-action=\"SAVE\"]").pressEnter().click();
        save();
    }

    @Override
    public void save()
    {
        $("button[data-action=\"save\"]").click();
        $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").doubleClick();
        close();
    }
    @Override
    public void click() {
        $("button[data-action=\"editTemplate\"]").click();
    }

    @Override
    public void create() {

    }

    @Override
    public void find() {

    }
}
