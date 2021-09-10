package com.gregoire.gooddependency;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpliciteModule {

    public ElementsCollection mainmenu = $$(".main-menu");
    public SelenideElement administration = $x("/html/body/div[1]/div/div[2]/div[1]/nav/ul/li[1]/a");
    public SelenideElement module = $x("/html/body/div[1]/div/div[2]/div[1]/nav/ul/li[1]/ul/li[2]/a");

    public SimpliciteModule()
    {
        administration.click();
        module.click();
    }

    public void createModule(String name, String prefix)
    {
        SelenideElement createassistant = $("button.btn:nth-child(4)");
        createassistant.click();
        SelenideElement fieldname = $("#field_mdl_name");
        fieldname.clear();
        fieldname.sendKeys(name);
        SelenideElement fieldprefix = $("#field_mdl_prefix");
        fieldprefix.clear();
        fieldprefix.sendKeys(prefix);
        $(".btn-primary").click();
    }

    public void createGroup(String name, String prefix)
    {
        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(prefix.toUpperCase() + "_" + name);
        $(".btn-primary").click();
    }

    public void createDomain(String name, String ftrans, String etrans)
    {
        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(name);
        $(".btn-primary").click();
        $(".table > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > input:nth-child(1)").sendKeys(etrans);
        $(".table > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > input:nth-child(1)").sendKeys(ftrans);
        $(".btn-primary").click();
    }

    public void addGroupToDomain(String group)
    {
        SelenideElement table = $(".table");
        SelenideElement line = table.find(byText(group)).parent();
        SelenideElement checkbox =line.find(byCssSelector("input[type=\"checkbox\"]"));
        checkbox.click();
        $(".btn-primary").click();
    }

    public void addIconToDomain(String icon, int type)
    {
        SelenideElement search = $(".btn-dmsel_field_viw_icon");
        search.click();
        SelenideElement serachtxt = $("div.col-md-3:nth-child(1) > input:nth-child(1)");
        serachtxt.sendKeys(icon + Keys.ENTER);
        $("#iptab0 > ul:nth-child(1) > li:nth-child(" + type + ") > a:nth-child(1)").click();
        SelenideElement table = $("#iptab0_1");
        ElementsCollection test = table.findAll("img");
        test.find(Condition.visible).click();
        $(".btn-primary").click();
    }
}
