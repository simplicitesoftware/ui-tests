package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.menu.administration.Module;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.work;

public class Group {

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

    public void click() {

    }

    public void createObject() {
        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(name);
    }

    public void find() {

        work.find("#grp_name").setValue(name).pressEnter();
        work.find("[data-field=\"grp_name\"]").find(new ByText(name)).click();

    }

}
