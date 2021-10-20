package com.simplicite.optionmenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class DropDownMenu {

    private final SelenideElement dropmenubutton;

    public DropDownMenu() {
        dropmenubutton = $(".btn-header.btn-shortcut").parent();
    }

    public void click(int type) {
        dropmenubutton.click();
        switch (type) {
            case 0 -> dropmenubutton.find(byCssSelector("[data-shortcut=\"SocialPosts\"]")).click();
            case 1 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"Feedback\"]")).click();
            case 2 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"News\"]")).click();
            case 3 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"ScriptEditor\"]")).click();
            case 4 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"Cache\"]")).click();
            case 5 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"Logs\"]")).click();
            case 6 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"DBAccess\"]")).click();
            case 7 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"Resources\"]")).click();
            case 8 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"JavaDoc\"]")).click();
            case 9 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"ImportCSV\"]")).click();
            case 10 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"ImportXML\"]")).click();
            case 11 -> dropmenubutton.find(byCssSelector("*[data-shortcut=\"About\"]")).click();
            default -> dropmenubutton.click();
        }
    }
}
