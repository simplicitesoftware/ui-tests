package com.gregoire.gooddependency;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authentification {

    public SelenideElement username = $("#auth-signin-username");
    public SelenideElement password = $("#auth-signin-password");
    public SelenideElement connection = $("#auth-signin-submit");
    private String usrname;
    private String pssword;

    public Authentification(String usrname, String pssword) {
        this.usrname = usrname;
        this.pssword = pssword;

    }

    public void Connect()
    {
        username.sendKeys(usrname);
        password.sendKeys(pssword);
        connection.click();
    }

    public String getUsername()
    {
        return usrname;
    }

    public String getPassword()
    {
        return pssword;
    }

    public boolean authentificationSucced()
    {
        SelenideElement element = $("span.user-name");
        if (!element.exists())
            return false;
        element.shouldHave(Condition.text(usrname));
        return true;
    }
}
