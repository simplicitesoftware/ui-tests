package com.simplicite.optionmenu;

import com.simplicite.account.Authentication;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Cache {

    //not functionnal TODO
    public static void shortcut(RemoteWebDriver driver, String type) {
        Actions keyAction = new Actions(driver);
        keyAction.keyDown(Keys.ALT).sendKeys("c").sendKeys().keyUp(Keys.ALT).keyUp(Keys.SHIFT).perform();
    }

    public static void click(char type) {
        if (type == 'u')
            $("*[src=\"http://localhost/images/image/cc1.png\"]").click();

        else if (type == 's')
            $("*[src=\"http://localhost/images/image/cc2.png\"]").click();

        else if (type == 'c') {
            $("*[src=\"http://localhost/images/image/cc3.png\"]").click();
            $(".btn-OK[data-action=\"OK\"]").click();
        }
    }
}
