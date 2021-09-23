package com.simplicite.menu.usersandrights;

import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.Module;

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
        domain.click();
        mainmenu.find("[data-obj=\"Grant\"]").click();
    }

    @Override
    public void createObject() {
        work.find("#field_grt_group_id__grp_name").pressEnter().setValue(group.getName()).pressEnter();
        work.find("#field_row_module_id__mdl_name").pressEnter().setValue(module.getName()).pressEnter();
        work.find("#ield_grt_function_id__fct_name").pressEnter().setValue(function.getName()).pressEnter();
    }

    @Override
    public void find() {

    }
}
