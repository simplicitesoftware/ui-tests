package com.simplicite.menu.administration.module;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;

import static com.codeborne.selenide.Selenide.$;

public class SimpliciteModule implements MainMenuProperties {

    private final String mdl_name;
    private final String mdl_prefix;

    public SelenideElement modulebutton = mainmenu.find("*[data-obj=\"Module\"]");

    /**
     * Constructor of the module in Simplicite
     *
     * @param name   the name of the module
     * @param prefix the prefixe of the module
     */
    public SimpliciteModule(String name, String prefix) {
        this.mdl_name = name;
        this.mdl_prefix = prefix;
    }

    @Override
    public void click() {
        administration.click();
        modulebutton.click();
    }

    @Override
    public void create() {
        SelenideElement fieldname = $("#field_mdl_name");
        fieldname.clear();
        fieldname.sendKeys(mdl_name);

        SelenideElement fieldprefix = $("#field_mdl_prefix");
        fieldprefix.clear();
        fieldprefix.sendKeys(mdl_prefix);
    }

    @Override
    public void find() {
        work.find("#mdl_name").setValue(mdl_name).pressEnter();
        work.find("[data-field=\"mdl_name\"]").shouldHave(Condition.textCaseSensitive(mdl_name)).click();
    }

    @Override
    public void delete() {
        find();
        work.find("div.card > div.card-header > div > div.form-actionbar > div > div.dropdown > button.btn-plus").click();
        work.find("div.card > div.card-header > div > div.form-actionbar > div " +
                "> div.dropdown.show > ul > li[data-action=\"ModuleDelete\"]").click();

        //Bad access with the css selector need to click two times

        $("button[data-action=\"YES\"]").click();
        $("button[data-action=\"YES\"]").click();

        work.find("button.btn-primary").shouldBe(Condition.visible).click();
    }

    /**
     * @return the name of the module
     */
    public String getMdl_name() {
        return mdl_name;
    }

    /**
     * @return the prefix of the module
     */
    public String getMdl_prefix() {
        return mdl_prefix;
    }

    public boolean isSuccess()
    {
        work.find("#field_mdl_name").should(Condition.exist).shouldHave(Condition.value(mdl_name));
        return true;
    }
}
