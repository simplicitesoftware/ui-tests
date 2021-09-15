package com.simplicite.menu.administration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.module.SimpliciteModule;
import com.simplicite.utils.Icon;

import static com.codeborne.selenide.Selenide.$;

public class SimpliciteDomain implements MainMenuProperties {


    private final String name;
    private Icon icon;
    private SimpliciteModule module;

    /**
     * Constructor of the domain for designer in Simplicite
     *
     * @param name   the name of the domain
     * @param icon   the icon of the domain
     * @param module the module link to the domain
     */
    public SimpliciteDomain(String name, Icon icon, SimpliciteModule module) {
        this.name = name;
        this.icon = icon;
        this.module = module;
    }

    @Override
    public void click() {
        administration.click();
    }

    @Override
    public void create() {

        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(name);
    }


    @Override
    public void find() {
        work.find("#obd_name").setValue(name).pressEnter();
        work.find("[data-field=\"obd_name\"]").find(new ByText(name)).click();
    }

    /**
     * @return the domain name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the icon of the domain
     */
    public Icon getIcon() {
        return icon;
    }
}
