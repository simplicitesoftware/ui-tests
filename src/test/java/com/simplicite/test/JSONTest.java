package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.google.gson.Gson;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.module.SimpliciteModule;
import com.simplicite.menu.administration.module.SimpliciteModuleAssitant;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.Icon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class JSONTest {

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
    }

    @Test
    void createModuleWithJSON() {
        try {

            Gson gson = new Gson();
            SimpliciteModule module = gson.fromJson(new FileReader("src/test/resources/MetaData.json"), SimpliciteModule.class);
            System.out.println(module.getMdl_name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createJSONFromModule() {
        SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("Training", "trn");
        Gson gson = new Gson();
        String json = gson.toJson(moduleAssitant);
        System.out.println(json);
    }
}
