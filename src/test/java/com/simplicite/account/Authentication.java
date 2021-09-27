package com.simplicite.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class Authentication {

    /**
     * Elements needed to login
     **/


    /**
     * Connect the user
     **/
    public static void connect(String usr, String password) {
        $("#auth-signin-username").setValue(usr);
        $("#auth-signin-password").setValue(password);
        $("#auth-signin-submit").click();
    }


    /**
     * Test if the authentification succed if a pop-up error appear return false
     */
    public static boolean authentificationSucced(String usr) {
        SelenideElement element = $("span.user-name");
        element.should(Condition.exist);
        element.shouldHave(Condition.text(usr));
        return true;
    }

    public static void deconnection() {
        $(".logged-user").click();
        $(".user-logout").click();
        $("#dlgmodal_CONFIRM_LOGOUT").find("button[data-action=\"OK\"]").click();
    }

    public static void changePassword(String newPassword) {
        $("#auth-signin-password1").setValue(newPassword);
        $("#auth-signin-password2").setValue(newPassword);
        $("#auth-signin-save").click();
    }
}
