package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;

import static com.codeborne.selenide.Selenide.$;

public class SimpliciteTemplateEditor implements MainMenuProperties {

    public SimpliciteTemplateEditor()
    {
        click();
        $("#dlgmodal").find(".img-flow > img:nth-child(1)").doubleClick();
    }

    public SimpliciteTemplateEditor(String tmp) {
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
        modal.find("button[data-action=\"SAVE\"]").click();
        save();
    }

    @Override
    public void save()
    {
        $("button[data-action=\"save\"]").click();
        $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").click();
        $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").click();
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
