package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.SimpliciteModule;

public class SimpliciteBusinessObjects implements MainMenuProperties {

    private final String code;
    private final String table;
    private final SimpliciteModule module;
    private final String prefix;

    private SimpliciteTemplateEditor editor = null;

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
        modulename.setValue(module.getMdl_name()).pressEnter();
        work.find("#field_obo_prefix").setValue(prefix);
    }

    @Override
    public void find() {
        work.find("#obo_name").setValue(code).pressEnter();
        work.find("[data-field=\"obo_name\"]").shouldHave(Condition.textCaseSensitive(code)).click();
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


    public SimpliciteTemplateEditor getEditor() {
        if (editor == null)
            editor = new SimpliciteTemplateEditor();
        else
            editor.click();
        return editor;
    }

    public void setEditor(SimpliciteTemplateEditor editor) {
        this.editor = editor;
    }
}
