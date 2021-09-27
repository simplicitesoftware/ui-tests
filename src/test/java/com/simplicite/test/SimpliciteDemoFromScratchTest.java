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
            properties.load(new FileReader("src/test/resources/config.properties"));
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
        BusinessObject.createObjectAssistant("TrnSupplier", "trn_supplier", "Training", "sup","TrnDomain" );
        assertTrue(BusinessObject.isSuccess("TrnSupplier"));
    }

    @Test
    @Order(3)
    public void editTemplate() {
        BusinessObject.click();
        BusinessObject.find("TrnSupplier");
        BusinessObject.navigateToEditor(true);
        BusinessObject.addField("code", 3, true, true, "20");
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
}
