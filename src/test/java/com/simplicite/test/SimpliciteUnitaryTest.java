package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.businessobject.SimpliciteTemplateEditor;
import com.simplicite.menu.administration.module.SimpliciteModuleAssitant;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.ConfigTest;
import com.simplicite.utils.Icon;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteUnitaryTest {

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
        if ($("#auth-main").exists()) {
            configTest.auth.connect();
        }
    }

    @AfterEach
    public void close() {
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
    }

    @Test
    public void newFieldTemplate() {
        boassistant.click();
        boassistant.find();
        boassistant.setEditor(new SimpliciteTemplateEditor("new"));
        boassistant.getEditor().addField("testa", "20", false, true);
    }

}

