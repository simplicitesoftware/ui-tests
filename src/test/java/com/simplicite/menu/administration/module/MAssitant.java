package com.simplicite.menu.administration.module;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.administration.Domain;
import com.simplicite.menu.usersandrights.Group;
import com.simplicite.utils.Icon;
import com.simplicite.utils.Traduction;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;

public class MAssitant extends Module {

    /** Create a module with the assistant system
     *
     * @param name the name of the module
     * @param prefix the prefix of the module
     */
    public MAssitant(String name, String prefix) {
        super(name, prefix);
    }

    @Override
    public void save() {
        work.find("*[data-action=\"validate\"]").click();
    }

    /** Click on the Create Assistant button then create the module
     * in the database on the web
     */
    public void createModule() {
        SelenideElement createassistant = work.find("*[data-action=\"ModuleCreateProcess\"]");
        createassistant.click();
        createObject();
        save();
    }

    /** Create the group in the database on the web
     *
     * @param group the group to be created
     */
    public void createGroup(Group group) {
        group.createObject();
        save();
    }

    /** Create the domain in the database on the web
     *
     * @param domain the domain to be created
     * @param fr the traduction in french
     * @param en the traduction in english
     */

    public void createDomain(Domain domain, Traduction fr, Traduction en) {
        domain.createObject();
        save();
        en.doTraduction(fr);
        save();
    }

    /** Find the line of the checkbox to check to add the group to the domain.
     * The line is find with the name of the group.
     *
     * @param group the group to add to the domain
     */
    public void addGroupToDomain(Group group) {
        SelenideElement table = work.find(".table");
        SelenideElement line = table.find(byText(group.getName())).parent();
        SelenideElement checkbox = line.find(byCssSelector("input[type=\"checkbox\"]"));
        checkbox.click();

        save();
    }

    /** Add the icon to the domain
     *
     * @param icon the icon need to be add to the domain
     */

    public void addIconToDomain(Icon icon) {
        SelenideElement search = work.find("*[data-action=\"dmsel_field_viw_icon\"][data-toggle=\"tooltip\"]");

        search.click();
        icon.addIcon();

        save();
    }
}