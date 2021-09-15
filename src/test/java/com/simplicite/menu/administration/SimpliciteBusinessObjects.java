package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.menu.MainMenuProperties;

public class SimpliciteBusinessObjects implements MainMenuProperties {

    private final String code;
    private final String table;
    private final SimpliciteModule module;
    private final String prefix;

    public SelenideElement objectbutton = mainmenu.find("[data-obj=\"ObjectInternal\"]");

    public SimpliciteBusinessObjects(String code, String table, SimpliciteModule module, String prefix) {
        this.code = code;
        this.table = table;
        this.module = module;
        this.prefix = prefix;
    }

    @Override
    public void click() {
        administration.click();
        objectbutton.click();
    }

    @Override
    public void create() {
        work.find("#field_obo_name").setValue(code);
        work.find("#field_obo_dbtable").setValue(table);
        SelenideElement modulename = work.find("#field_row_module_id__mdl_name");
        modulename.click();
        modulename.setValue(module.getName()).pressEnter();
        work.find("#field_obo_prefix").setValue(prefix);
    }

    @Override
    public void find() {
        work.find("#field_obo_name").setValue(code).pressEnter();
        work.find("[data-field=\"mdl_name\"]").shouldHave(Condition.textCaseSensitive(code)).click();
    }

    public boolean isSuccess(){
        work.find("#field_obo_name").should(Condition.exist).shouldHave(Condition.value(code));
        return true;
    }

    public String getPrefix() {
        return prefix;
    }

    public SimpliciteModule getModule() {
        return module;
    }

    public String getTable() {
        return table;
    }

    public String getCode() {
        return code;
    }
}
