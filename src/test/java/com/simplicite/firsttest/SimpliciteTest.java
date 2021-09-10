package com.gregoire.gooddependency;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith({ScreenShooterExtension.class})
public class SimpliciteTest {
    String url = "http://localhost";
    int port = 80;

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
        auth.authentificationSucced();
        //assertEquals(url + "/ui/", url());
    }

    @Test
    public void nonAuthentication() {
        Authentication auth = new Authentication("error", "error");
        auth.Connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldBe(exist);
        assertFalse(auth.authentificationSucced());
        assertNotEquals(url + "/ui/", url());
    }

    @Test
    public void createModule() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        SimpliciteModule module = new SimpliciteModule();
        module.createModule("trainv2", "trv");
        module.createGroup("SUPERADMIN", "trv");
        module.createDomain("TrnDomain", "Formation", "Training");
        module.addGroupToDomain("TRV_SUPERADMIN");
        module.addIconToDomain("book", 2);
    }
}
