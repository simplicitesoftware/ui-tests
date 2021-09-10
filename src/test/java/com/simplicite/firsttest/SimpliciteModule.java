package com.gregoire.gooddependency;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class SimpliciteModule {

    public SelenideElement mainmenu = $("#menu");
    public SelenideElement administration = mainmenu.find("*[data-domain=\"DomainAdmin\"]");
    public SelenideElement module = mainmenu.find("*[data-obj=\"Module\"]");

    public SimpliciteModule() {
        administration.click();
        module.click();
    }

    public SelenideElement work = $("#work");

    public void next() {
        work.find("*[data-action=\"validate\"]").click();
    }

    public void createModule(String name, String prefix) {

        SelenideElement createassistant = work.find("*[data-action=\"ModuleCreateProcess\"]");
        createassistant.click();

        SelenideElement fieldname = $("#field_mdl_name");
        fieldname.clear();
        fieldname.sendKeys(name);

        SelenideElement fieldprefix = $("#field_mdl_prefix");
        fieldprefix.clear();
        fieldprefix.sendKeys(prefix);

        next();
    }

    public void createGroup(String name, String prefix) {

        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(prefix.toUpperCase() + "_" + name);

        next();
    }

    public void createDomain(String name, String ftrans, String etrans) {

        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(name);

        next();

        work.find("input[name*=\"EN\"]").sendKeys(etrans);
        work.find("input[name*=\"FR\"]").sendKeys(ftrans);

        next();
    }

    public void addGroupToDomain(String group) {

        SelenideElement table = work.find(".table");
        SelenideElement line = table.find(byText(group)).parent();
        SelenideElement checkbox = line.find(byCssSelector("input[type=\"checkbox\"]"));
        checkbox.click();

        next();
    }

    public void addIconToDomain(String icon, int type) {
        SelenideElement selectdatamap = $("#dlgmodal_selectDatamap");
        SelenideElement search = work.find("*[data-action=\"dmsel_field_viw_icon\"][data-toggle=\"tooltip\"]");
        search.click();

        SelenideElement serachtxt = selectdatamap.find("input");
        serachtxt.sendKeys(icon + Keys.ENTER);

        $("#iptab0").find("*[data-tab=\""+ type + "\"]").click();
        SelenideElement subtable = selectdatamap.find("#iptab0_" + type);
        ElementsCollection test = subtable.findAll("img");
        test.find(Condition.visible).click();

        next();
    }
}
