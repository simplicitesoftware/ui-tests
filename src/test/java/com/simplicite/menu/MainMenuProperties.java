package com.simplicite.menu;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Create a base for the menu, default action useful to every item in the menu
 **/
public class MainMenuProperties {

    /**
     * Element needed to accesto the menu and to restrain the search of new elements
     */
    public static SelenideElement mainmenu = $("#menu");
    public static SelenideElement work = $("#work");
    public static SelenideElement administration = mainmenu.find("*[data-domain=\"DomainAdmin\"]");
    public static SelenideElement domain = mainmenu.find("[data-domain=\"DomainGrant\"]");
    public final static SelenideElement domaininterface = mainmenu.find("[data-domain=\"DomainInterface\"]");

    /**
     * Save the component in the menu of creation
     */
    public static void save() {
        $("button[data-action=\"save\"]").click();
    }

    /**
     * Save an close the component in the menu of creation
     */
    public static void saveAndClose() {
        $("button[data-action=\"saveclose\"]").click();
    }

    void naviguateToCreate() {
        $("button[data-action=\"create\"]").click();
    }

    public static void next() {
        work.find("button[data-action=\"validate\"]").click();
        Selenide.sleep(1000);
    }

    /**
     * Delete the component of the programme
     */
    static void delete(String name) {
        work.find("div.card > div.card-header > div > div.form-actionbar > div > div > button").click();
        SelenideElement delete = work.find("div.card > div.card-header > div > div.form-actionbar > div " +
                "> div.dropdown.show > ul > li[data-action=\"delete\"]");
    }

    public static void close() {
        $("button[data-action=\"close\"]").click();
    }

    public static void create() {
        work.find("button[data-action=\"create\"]").click();
    }

    public static void clickInterface(String menu) {
        domaininterface.scrollIntoView(false);
        SelenideElement element = mainmenu.find("[data-obj=\"" + menu + "\"]");
        if (!element.isDisplayed())
            domaininterface.click();
        element.click();
    }

    public static void clickAdmin(String menu) {
        administration.scrollIntoView(false);
        SelenideElement element = mainmenu.find("[data-obj=\"" + menu + "\"]");
        if (!element.isDisplayed())
            administration.click();
        element.click();
    }
}
