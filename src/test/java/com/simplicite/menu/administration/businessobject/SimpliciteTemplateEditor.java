package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.administration.SimpliciteField;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.work;

public class SimpliciteTemplateEditor {

    public SimpliciteTemplateEditor()
    {

    }

    public void addField()
    {
        SelenideElement area = work.find("#area1");
        area.find("button").click();
        area.find("[data-menu=\"field\"]").click();
        SelenideElement modal = $("#dlgmodal_field");
        SelenideElement typed = modal.find("#typed");
        typed.click();
        typed.find("[data-field-type=\"3\"]").click();
    }
}
