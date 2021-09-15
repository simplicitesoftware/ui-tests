package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjects;
import com.simplicite.menu.administration.module.SimpliciteModule;
import com.simplicite.menu.administration.module.SimpliciteModuleAssitant;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.Icon;
import com.simplicite.utils.Traduction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
public class SimpliciteTest {
    String url = "http://localhost";
    int port = 80;
    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("trainv2", "trv");
    SimpliciteGroup group = new SimpliciteGroup("TRV_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrvDomain", Icon.CONSOLE, moduleAssitant);
    SimpliciteBusinessObjectAssistant boassistant = new SimpliciteBusinessObjectAssistant("TrnSupplier", "trn_supplier", moduleAssitant, "sup");

    @BeforeAll
    public static void setUpAll() {

        Configuration.browserSize = "1280x800";
        Configuration.browser = FIREFOX;
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        open(url + ":" + port);
    }

    @Test
    public void authentication() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldNot(exist, Duration.ofSeconds(2));
        assertTrue(auth.authentificationSucced());
    }

    @Test
    public void nonAuthentication() {
        Authentication auth = new Authentication("error", "error");
        auth.Connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldBe(exist);
        assertNotEquals(url + "/ui/", url());
    }

    @Test
    public void createModule() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        moduleAssitant.click();
        moduleAssitant.createModule();
        moduleAssitant.createGroup(group);
        moduleAssitant.createDomain(domain, Traduction.FORMATION, Traduction.TRAINING);
        moduleAssitant.addGroupToDomain(group);
        moduleAssitant.addIconToDomain(domain.getIcon());
        assertTrue(moduleAssitant.isSuccess());
    }

    @Test
    public void deleteModule() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        moduleAssitant.click();
        moduleAssitant.delete();
    }

    @Test
    public void createBusinessObject() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        boassistant.click();
        boassistant.createObject();
        boassistant.makeTraduction(Traduction.FORMATION, Traduction.SUPPLIER);
        boassistant.grantObject();
        boassistant.addDomain(domain);
        assertTrue(boassistant.isSuccess());
    }

    @Test
    public void editTemplate()
    {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        boassistant.click();
        boassistant.find();
        boassistant.getEditor().addField();
    }
    @Test
    public void clearCache() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click(auth, 'u');
        auth.authentificationSucced();
    }

}
