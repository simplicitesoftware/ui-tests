package com.simplicite.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.module.SimpliciteModuleAssitant;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.menu.usersandrights.SimpliciteUser;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.Icon;
import com.simplicite.utils.Traduction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteDemoFromScratchTest {
    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("Training", "trn");
    SimpliciteGroup group = new SimpliciteGroup("TRN_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrnDomain", Icon.CONSOLE, moduleAssitant);
    SimpliciteBusinessObjectAssistant boassistant = new SimpliciteBusinessObjectAssistant("TrnSupplier",
            "trn_supplier", moduleAssitant, "sup");
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
                , properties.getProperty("oldpassword"));
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
    @Order(0)
    public void newSession(){
        String newPassword = properties.getProperty("password");
        auth.changePassword(newPassword);

        //change to Super Admin
        auth.deconnection();
        auth.connect();
        $(".logged-scope").click();
        $(".logged-scope").find("[data-home=\"Home\"]").click();
        $(".scope-icon > img[src*=\"code=VIEW_ADMIN\"]").shouldBe(Condition.exist);
        assertTrue(auth.authentificationSucced());
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
    public void editTemplate() {
        boassistant.click();
        boassistant.find();
        boassistant.getEditor().addField("test", "20", true, true);
    }

    @Test
    @Order(4)
    public void createUser() {
        SimpliciteUser user = new SimpliciteUser("usertest");
        user.create();
        user.associateGroup(group);

        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');

        Authentication newauth = new Authentication(user.getName(), user.getPassword());
        newauth.connect();
        //newauth.connectFirstTime(user.getPassword());
        assertTrue(newauth.authentificationSucced());
        newauth.deconnection();
        auth.connect();
    }
}
