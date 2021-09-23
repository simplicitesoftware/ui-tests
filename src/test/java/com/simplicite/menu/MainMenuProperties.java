package com.simplicite.menu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Create a base for the menu, default action useful to every item in the menu
 **/
public interface MainMenuProperties {

    /**
     * Element needed to accesto the menu and to restrain the search of new elements
     */
    SelenideElement mainmenu = $("#menu");
    SelenideElement work = $("#work");
    SelenideElement administration = mainmenu.find("*[data-domain=\"DomainAdmin\"]");
    SelenideElement domain = mainmenu.find("[data-domain=\"DomainGrant\"]");

    /**
     * Click on the component, Navigate to the element needed in the menu
     */
    void click();

    /**
     * Access to the menu of creation then create the component
     */
    void createObject();

    /**
     * Find the component in the list of the component type
     */
    void find();

    /**
     * Save the component in the menu of creation
     */
    default void save() {
        $("button[data-action=\"save\"]").click();
    }

    /**
     * Save an close the component in the menu of creation
     */
    default void saveAndClose() {
        $("button[data-action=\"saveclose\"]").click();
    }

    default void create() {
        $("button[data-action=\"create\"]").click();
        createObject();
    }

    /**
     * Delete the component of the programme
     */
    default void delete() {
        find();
        work.find("div.card > div.card-header > div > div.form-actionbar > div > div > button").click();
        SelenideElement delete = work.find("div.card > div.card-header > div > div.form-actionbar > div " +
                "> div.dropdown.show > ul > li[data-action=\"delete\"]");
    }

    default void close() {
        $("button[data-action=\"close\"]").click();
    }
}
