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
import com.simplicite.utils.Icon;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteUnitaryTest {

    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("Training", "trn");
    SimpliciteGroup group = new SimpliciteGroup("TRN_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrnDomain", Icon.CONSOLE, moduleAssitant);
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
        open(properties.getProperty("url"));
        if ($("#auth-main").exists()) {
            auth.connect();
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

