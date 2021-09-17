package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.module.SimpliciteModuleAssitant;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.Icon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
public class SimpliciteTest {
    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("trainv2", "trv");
    SimpliciteGroup group = new SimpliciteGroup("TRV_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrvDomain", Icon.CONSOLE, moduleAssitant);
    SimpliciteBusinessObjectAssistant boassistant = new SimpliciteBusinessObjectAssistant("TrnSupplier", "trn_supplier", moduleAssitant, "sup");
    static Properties properties = new Properties();
    static Authentication auth;

    @BeforeAll
    public static void setUpAll() {
        try {
            properties.load(new FileReader("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = properties.getProperty("browsersize");
        Configuration.browser = properties.getProperty("browser");
        Configuration.headless = properties.getProperty("headless").equals("true");
        auth = new Authentication(properties.getProperty("name")
                , properties.getProperty("password"));
    }

    @BeforeEach
    public void setUp() {
        open(properties.getProperty("url"));}


    @Test
    public void authentication() {
        auth.connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldNot(exist, Duration.ofSeconds(2));
        assertTrue(auth.authentificationSucced());
        auth.deconnection();
    }

    @Test
    public void nonAuthentication() {
        Authentication error = new Authentication("error", "error");
        error.connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldBe(exist);
    }

   @Test
    public void clearCache() {
        auth.connect();
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
        auth.connect();
        auth.authentificationSucced();
        auth.deconnection();
    }

}
