package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.SimpliciteModule;

public class BusinessObject{

    public String code;
    public String table;
    //public SimpliciteModule module;
    public String prefix;

    //public SimpliciteTemplateEditor editor = null;

    public BusinessObject(String code, String table, String prefix) {
        this.code = code;
        this.table = table;
        //this.module = module;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    /*public SimpliciteModule getModule() {
        return module;
    }*/

    public String getTable() {
        return table;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString(){
        return "code= " + code + ", data = [table=" + table + ", prefix=" + prefix + "]";
    }

    /*public SimpliciteTemplateEditor getEditor() {
        if (editor == null)
            editor = new SimpliciteTemplateEditor();
        else
            editor.click();
        return editor;
    }

    public void setEditor(SimpliciteTemplateEditor editor) {
        this.editor = editor;
    }*/
}

