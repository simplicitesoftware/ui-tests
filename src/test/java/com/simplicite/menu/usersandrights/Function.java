package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.Module;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class Function implements MainMenuProperties {

    private final String field_fct_name;
    private final String field_fct_function;
    private final Module module;
    private ArrayList<Grant> grants = new ArrayList<>();

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
     * @param field_fct_name function name
     * @param field_fct_function right function access
     */

    public Function(String field_fct_name, String field_fct_function, Module module) {
        this.field_fct_name = addSuffix(field_fct_name, field_fct_function);
        this.field_fct_function = field_fct_function;
        this.module = module;
    }

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

    @Override
    public void click() {
        domain.click();
        mainmenu.find("[data-obj=\"Function\"]").click();
    }

    @Override
    public void createObject() {
        work.find("#field_fct_name").setValue(field_fct_name);
        work.find("#field_fct_function").selectOptionByValue(field_fct_function);
        SelenideElement modulename = work.find("#field_row_module_id__mdl_name");
        modulename.click();
        modulename.setValue(module.getName()).pressEnter();
    }

    public void addGrant(Group group) {
        Grant grant = new Grant(group, this, module);
        grant.click();
        grant.create();
        grants.add(grant);
    }

    public void associateGroup(Group...groups){
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
    }

    @Override
    public void find() {
        work.find("#fct_name").setValue(field_fct_function).pressEnter();
        work.find("[data-field=\"fct_name\"]").shouldHave(Condition.textCaseSensitive(field_fct_name)).click();
    }

    public String getName() {
        return field_fct_name;
    }

    public ArrayList<Grant> getGrants() {
        return grants;
    }
}
