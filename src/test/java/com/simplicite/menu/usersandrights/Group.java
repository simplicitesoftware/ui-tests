package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.Module;

import static com.codeborne.selenide.Selenide.$;

public class Group implements MainMenuProperties {

    private final String name;
    private final Module module;

    /**
     * Constructor for the component group of Simplicite framework
     *
     * @param name   the name of the group
     * @param module the module associate to the group
     */
    public Group(String name, Module module) {
        this.name = name;
        this.module = module;
    }


    @Override
    public void click() {

    }

    public void createObject() {
        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(name);
    }

    @Override
    public void find() {

        work.find("#grp_name").setValue(name).pressEnter();
        work.find("[data-field=\"grp_name\"]").find(new ByText(name)).click();

    }

    /**
     * @return name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * @return module associate to the group
     */
    public Module getModule() {
        return module;
    }
}
