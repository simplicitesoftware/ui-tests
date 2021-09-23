package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.Module;
import com.simplicite.utils.Component;

public class Grant implements MainMenuProperties {

    private Group group;
    private Function function;
    private Module module;

    public Grant(Group group, Function function, Module module)
    {

        this.group = group;
        this.function = function;
        this.module = module;
    }
    @Override
    public void click() {
        SelenideElement element =mainmenu.find("[data-obj=\"Grant\"]");
        if (!element.isDisplayed())
            domain.click();
        mainmenu.find("[data-obj=\"Grant\"]").click();
    }

    @Override
    public void createObject() {
        Component.sendFormControl(work.find("#field_grt_group_id__grp_name"), group.getName());
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), module.getName());
        Component.sendFormControl(work.find("field_grt_function_id__fct_name"), module.getName());
    }

    @Override
    public void find() {

    }
}
