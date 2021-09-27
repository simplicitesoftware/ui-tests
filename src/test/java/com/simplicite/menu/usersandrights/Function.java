package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.Module;
import com.simplicite.utils.Component;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.*;

public class Function {


    /**
     * READ | CREATE | UPDATE | DELETE | ACTION | VIEW
     * L : READ
     * C : READ / CREATE
     * M : READ / UPDATE
     * S : READ / DELETE
     * CM : READ / CREATE / UPDATE
     * CS : READ / CREATE / DELETE
     * MS : READ / UPDATE / DELETE
     * CMS : READ / CREATE / UPDATE / DELETE
     * A   : ACTION
     * V   : VIEW
     *
     *
     */

    private String addSuffix(String str, String suf) {
        String suffix;
        switch (suf) {
            case "CMS" -> suffix = "CRUD";
            case "MS" -> suffix = "RUD";
            case "CS" -> suffix = "CRD";
            case "CM" -> suffix = "CRU";
            case "L" -> suffix = "R";
            case "C" -> suffix = "CR";
            case "M" -> suffix = "RU";
            case "S" -> suffix = "RD";
            default -> suffix = suf;
        }
        return str + "_" + suffix;
    }

    public void click() {
        domain.click();
        mainmenu.find("[data-obj=\"Function\"]").click();
    }

    public void createObject(String mdl_name, String field_fct_name, String field_fct_function) {
        work.find("#field_fct_name").setValue(field_fct_name);
        work.find("#select2-field_fct_function-container").click();
        work.find("#select2-field_fct_function-results").find("[id$=\"" + field_fct_function + "\"]").click();
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
    }

    public void addGrant(String fct_name, String grp_name, String mdl_name) {
        Grant.click();
        Grant.createObject(grp_name, mdl_name,fct_name );
    }

    /*public void associateGroup(Group... groups) {
        work.find(".objlinks").find("[data-action=\"associate-Function-grt_function_id-Group-grt_group_id\"]").click();
        SelenideElement dlgmodal = $("#dlgmodal_selectObj_Group");
        for (Group group : groups) {
            Grant grant = new Grant(group, this, module);
            dlgmodal.find("#grp_name").setValue(group.getName());
            dlgmodal.find("[data-field=\"grp_name\"]").shouldHave(Condition.textCaseSensitive(group.getName())).click();
            grants.add(grant);
        }
        dlgmodal.find("button[data-action=\"multiselect\"]").click();
        dlgmodal.find("button[data-action=\"saveclose\"]").click();
    }*/

    public void find(String field_fct_function, String field_fct_name) {
        work.find("#fct_name").setValue(field_fct_function).pressEnter();
        work.find("[data-field=\"fct_name\"]").shouldHave(Condition.textCaseSensitive(field_fct_name)).click();
    }
}
