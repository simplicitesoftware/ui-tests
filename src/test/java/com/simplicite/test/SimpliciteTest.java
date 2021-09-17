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
import com.simplicite.utils.ConfigTest;
import com.simplicite.utils.Icon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

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
    static ConfigTest configTest = new ConfigTest();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = configTest.browsersize;
        Configuration.browser = configTest.browser;
        Configuration.headless = configTest.headless;
    }

    @BeforeEach
    public void setUp() {
        open(configTest.url);
    }


    @Test
    public void authentication() {
        configTest.auth.connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldNot(exist, Duration.ofSeconds(2));
        assertTrue(configTest.auth.authentificationSucced());
        configTest.auth.deconnection();
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
        configTest.auth.connect();
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
        configTest.auth.connect();
        configTest.auth.authentificationSucced();
        configTest.auth.deconnection();
    }

}
