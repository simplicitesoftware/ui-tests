package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.Module;
import com.simplicite.menu.usersandrights.Function;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class BusinessObject implements MainMenuProperties {

    private final String code;
    private final String table;
    private final Module module;
    private final String prefix;

    private TemplateEditor editor = null;
    private Function fonction;
    public SelenideElement objectbutton = mainmenu.find("[data-obj=\"ObjectInternal\"]");

    public BusinessObject(String code, String table, Module module, String prefix) {

        this.code = StringUtils.capitalize(module.getPrefix()) + code;
        this.table = module.getPrefix() + "_" + table;
        this.module = module;
        this.prefix = prefix;
    }

    @Override
    public void click() {
        administration.click();
        objectbutton.click();
    }

    @Override
    public void createObject() {
        work.find("#field_obo_name").setValue(code);
        work.find("#field_obo_dbtable").setValue(table);
        SelenideElement modulename = work.find("#field_row_module_id__mdl_name");
        modulename.click();
        modulename.setValue(module.getMdl_name()).pressEnter();
        work.find("#field_obo_prefix").setValue(prefix);
    }

    @Override
    public void find() {
        work.find("#obo_name").setValue(code).pressEnter();
        work.find("[data-field=\"obo_name\"]").shouldHave(Condition.textCaseSensitive(code)).click();
    }

    public boolean isSuccess() {
        work.find("#field_obo_name").should(Condition.exist).shouldHave(Condition.value(code));
        return true;
    }

    public String getPrefix() {
        return prefix;
    }

    public Module getModule() {
        return module;
    }

    public String getTable() {
        return table;
    }

    public String getCode() {
        return code;
    }


    public TemplateEditor getEditor() {
        if (editor == null)
            editor = new TemplateEditor();
        else
            editor.click();
        return editor;
    }

    public void setEditor(TemplateEditor editor) {
        this.editor = editor;
    }

    public void addFunction() {
    }
}
