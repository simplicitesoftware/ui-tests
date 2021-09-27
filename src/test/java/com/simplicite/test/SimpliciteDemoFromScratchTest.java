package com.simplicite.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.BusinessObject;
import com.simplicite.menu.administration.Module;
import com.simplicite.menu.usersandrights.User;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteDemoFromScratchTest {
    static Properties properties = new Properties();

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = properties.getProperty("browsersize");
        Configuration.browser = properties.getProperty("browser");
        Configuration.headless = properties.getProperty("headless").equals("true");
    }

    @BeforeEach
    public void setUp() {
        open(properties.getProperty("url"));
        if ($("#auth-main").exists()) {
            Authentication.connect(properties.getProperty("name"), properties.getProperty("password"));
        }
    }

    @AfterEach
    public void close() {
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
    }

    @AfterAll
    public static void setDownAll() {
        try {
            var out = new FileWriter("src/test/resources/config.properties");
            properties.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(0)
    public void newSession() {
        String name = properties.getProperty("name");
        String newpassword = "designer1903";
        properties.setProperty("password", newpassword);

        Authentication.changePassword(newpassword);
        $(".logged-scope").click();
        $(".logged-scope").find("[data-home=\"Home\"]").click();
        $(".scope-icon > img[src*=\"code=VIEW_ADMIN\"]").shouldBe(Condition.exist, Duration.ofSeconds(6));
        assertTrue(Authentication.authentificationSucced(name));
    }

    @Test
    @Order(1)
    public void createModule() {
        Module.click();
        Module.createModuleAssistant("Training", "trn", "SUPERADMIN", "TrnDomain", "img/color/console");
        assertTrue(Module.isSuccess("Training"));
    }

    @Test
    @Order(2)
    public void createBusinessObject() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant("TrnSupplier", "trn_supplier", "Training", "sup", "TrnDomain");
        assertTrue(BusinessObject.isSuccess("TrnSupplier"));
    }

    @Test
    @Order(3)
    public void editTemplate() {
        BusinessObject.click();
        BusinessObject.find("TrnSupplier");
        BusinessObject.navigateToEditor();
        BusinessObject.addField("code", 3, true, true);
        BusinessObject.save();
    }

    @Test
    @Order(4)
    public void createUser() {
        User.click();
        String password = User.createUser(properties.getProperty("firstusername"));

        properties.setProperty("firstuserpassword", password);
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');

        Authentication.connect(properties.getProperty("firstusername"), password);
        Authentication.changePassword(properties.getProperty("firstuserpassword"));
        assertTrue(Authentication.authentificationSucced("usertest"));
        Authentication.deconnection();
        Authentication.connect(properties.getProperty("name"), properties.getProperty("password"));
    }

    @Test
    @Order(4)
    public void enrichModelSupplier() {
        BusinessObject.click();
        BusinessObject.find("TrnSupplier");
        BusinessObject.clickEditor();
        BusinessObject.addField("nom", 3, false, false);
        BusinessObject.addField("téléphone", 22, false, false);
        BusinessObject.addField("logo", 20, false, false);
        BusinessObject.addField("site", 10, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    public void enrichModelProduct() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant("TrnProduct", "trn_product", "Training", "prd", "TrnDomain");
        BusinessObject.navigateToEditor();
        BusinessObject.addField("référence", 3, true, true);
        BusinessObject.addField("prix", 2, true, false);
        BusinessObject.addField("stock", 1, true, false);
        BusinessObject.addField("nom", 3, false, false);
        BusinessObject.addField("description", 13, false, false);
        BusinessObject.addField("photo", 20, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(6)
    public void enrichModelClient() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant("TrnClient", "trn_client", "Training", "cli", "TrnDomain");
        BusinessObject.navigateToEditor();
        BusinessObject.addField("nom", 3, true, true);
        BusinessObject.addField("prénom", 3, true, true);
        BusinessObject.addField("mail", 12, false, false);
        BusinessObject.addField("téléphone", 22, false, false);
        BusinessObject.addField("adresse", 25, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(7)
    public void enrichModelOrder() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant("TrnOrder", "trn_order", "Training", "ord", "TrnDomain");
        BusinessObject.navigateToEditor();
        BusinessObject.addField("numéro", 3, true, true);
        BusinessObject.addField("quantité", 1, true, false);
        BusinessObject.addField("date", 4, false, false);
        BusinessObject.save();
    }
}
