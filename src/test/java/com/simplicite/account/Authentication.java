package com.simplicite.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authentication {

    public SelenideElement usrelement = $("#auth-signin-username");
    public SelenideElement password = $("#auth-signin-password");
    public SelenideElement connection = $("#auth-signin-submit");
    private final String usrname;
    private final String pssword;

    public Authentication(String usrname, String pssword) {
        this.usrname = usrname;
        this.pssword = pssword;

    }

    public void Connect() {
        usrelement.sendKeys(usrname);
        password.sendKeys(pssword);
        connection.click();
    }

    public String getUsrelement() {
        return usrname;
    }

    public String getPassword() {
        return pssword;
    }

    public boolean authentificationSucced() {
        SelenideElement element = $("span.user-name");
        if (!element.exists())
            return false;
        element.shouldHave(Condition.text(usrname));
        return true;
    }
}
