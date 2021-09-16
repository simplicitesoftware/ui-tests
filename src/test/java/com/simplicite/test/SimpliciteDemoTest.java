package com.simplicite.test;

import com.codeborne.selenide.Configuration;
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
import com.simplicite.utils.Traduction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteDemoTest {
     String url = "http://localhost";
    int port = 80;
    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("Training", "trn");
    SimpliciteGroup group = new SimpliciteGroup("TRN_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrnDomain", Icon.CONSOLE, moduleAssitant);
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
        if ($("#auth-main").exists())
        {
            Authentication auth = new Authentication("designer", "designer1903");
            auth.Connect();
        }
    }

    @AfterEach
    public void close()
    {
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
    }

    @Test
    @Order(1)
    public void createModule() {
        moduleAssitant.click();
        moduleAssitant.createModule();
        moduleAssitant.createGroup(group);
        moduleAssitant.createDomain(domain, Traduction.FORMATION, Traduction.TRAINING);
        moduleAssitant.addGroupToDomain(group);
        moduleAssitant.addIconToDomain(domain.getIcon());
        assertTrue(moduleAssitant.isSuccess());
    }

    @Test
    @Order(2)
    public void createBusinessObject() {
        boassistant.click();
        boassistant.createObject();
        boassistant.makeTraduction(Traduction.FORMATION, Traduction.SUPPLIER);
        boassistant.grantObject();
        boassistant.addDomain(domain);
        assertTrue(boassistant.isSuccess());
    }

    @Test
    @Order(3)
    public void editTemplate()
    {
        boassistant.click();
        boassistant.find();
        boassistant.getEditor().addField("test", "20", true, true);
    }

    @Test
    @Order(4)
    public void deleteModule() {
        moduleAssitant.click();
        moduleAssitant.delete();
    }

}
