package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.ui.Diagram;
import com.simplicite.ui.Historic;
import com.simplicite.ui.Navigation;
import com.simplicite.ui.PivotTable;
import com.simplicite.utils.Component;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.simplicite.ui.BusinessObjectFill.*;
import static com.simplicite.ui.Navigation.search;
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
        Component.clickMenu(DOMAIN, SUPPLIER);
        createSupplier("https://simplicite.fr/", "test", "0000000000", "simplicite", "simpliciteeu");
        Component.clickMenu(DOMAIN, PRODUCT);
        createProduct("simple test", "demoui", "1", "50000", "1548532", "simpliciteeu");
        Component.clickMenu(DOMAIN, CLIENT);
        createClient("", "0000000000", "simplicite@demo.fr", "Robot", "Cop");
        Component.clickMenu(DOMAIN, ORDER);
        createOrder("1548532", "Cop", "21/03/4200", "1", "15202553");
    }

    @Test
    @Order(1)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void activateAction() {
        Component.clickMenu(DOMAIN, PRODUCT);
        search("trnPrdReference", "1548532");
        performAction(INCREASESTOCK);
        assertEquals(switchTo().alert().getText(), "To be implemented...");
        switchTo().alert().accept();
    }

    @Test
    @Order(2)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void verifyDiagram() {
        Navigation.clickState(DOMAIN, ORDER, "");
        search("trnOrdNumero", "15202553");
        Diagram.verifyState(PROCESSING);
        Diagram.switchProcessingState(CANCELED);
        Diagram.switchProcessingState(PROCESSING);
        Diagram.switchProcessingState(VALIDATED);
        Diagram.switchProcessingState(CANCELED);
        Diagram.switchProcessingState(VALIDATED);
        Diagram.switchProcessingState(SENT);
        Diagram.switchProcessingState(VALIDATED);
    }

    @Test
    @Order(3)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void verifyHistoric() {
        Navigation.clickState(DOMAIN, ORDER, "");
        search("trnOrdNumero", "15202553");
        Historic.verifyHistoric("usertest","15202553", "field_trnOrdQuantite", "6");

    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void verifyPivotTable() {
        Navigation.clickState(DOMAIN, ORDER, "");
        PivotTable.verifyPivotTable(PIVOTTABLE);
    }
}
