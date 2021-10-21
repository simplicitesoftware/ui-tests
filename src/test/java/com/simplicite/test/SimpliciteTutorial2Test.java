package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.ui.Diagram;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.simplicite.ui.BusinessObjectFill.*;
import static com.simplicite.utils.DataStore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.DisplayName.class)
public class SimpliciteTutorial2Test {

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            PROPERTIES.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = PROPERTIES.getProperty("browsersize");
        Configuration.browser = PROPERTIES.getProperty("browser");
        Configuration.headless = PROPERTIES.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = Integer.parseInt(PROPERTIES.getProperty("pageLoadTimeout"));
        Configuration.timeout = Integer.parseInt(PROPERTIES.getProperty("timeout"));
    }

    @BeforeEach
    public void setUp() {
        open(PROPERTIES.getProperty("url"));
        if ($("#auth-main").exists()) {
            Authentication.connect(PROPERTIES.getProperty("firstusername"), PROPERTIES.getProperty("firstuserpassword"));
            Authentication.changePassword(PROPERTIES.getProperty("firstuserpassword"));
        }
    }

    @Test
    @Order(0)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichUI() {
        click(DOMAIN, SUPPLIER);
        createSupplier("https://simplicite.fr/", "test", "0000000000", "simplicite", "simpliciteeu");
        click(DOMAIN, PRODUCT);
        createProduct("simple test", "demoui", "1", "50000", "1548532", "simpliciteeu");
        click(DOMAIN, CLIENT);
        createClient("", "0000000000", "simplicite@demo.fr", "Robot", "Cop");
        click(DOMAIN, ORDER);
        createOrder("1548532", "Cop", "21/03/4200", "1", "15202553");
    }

    @Test
    @Order(1)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void activateAction() {
        click(DOMAIN, PRODUCT);
        search("trnPrdReference", "15202553");
        performAction(INCREASESTOCK);
        assertEquals(switchTo().alert().getText(), "To be implemented...");
        switchTo().alert().accept();
    }

    @Test
    @Order(1)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void verifyDiagram() {
        click(DOMAIN, ORDER);
        search("trnPrdReference", "15202553");
        Diagram.verifyState(PROCESSING);
        performAction(INCREASESTOCK);
        assertEquals(switchTo().alert().getText(), "To be implemented...");
        switchTo().alert().accept();
    }


}
