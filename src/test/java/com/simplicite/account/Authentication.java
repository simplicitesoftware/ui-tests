package com.simplicite.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authentication {

    /** Elements needed to login **/
    public SelenideElement usrelement = $("#auth-signin-username");
    public SelenideElement password = $("#auth-signin-password");
    public SelenideElement connection = $("#auth-signin-submit");
    private final String usrname;
    private final String pssword;

    /** Create an authentification
     *
     * @param usrname Username of the account needed to be connected to the website
     * @param pssword Password of the account needed to be connected to the website
     */
    public Authentication(String usrname, String pssword) {
        this.usrname = usrname;
        this.pssword = pssword;

    }

    /** Connect the user **/
    public void Connect() {
        usrelement.setValue(usrname);
        password.setValue(pssword);
        connection.click();
    }

    /**
     *
     * @return the username of the account needed to be connected
     */
    public String getUsername() {
        return usrname;
    }

    /**
     *
     * @return the password of the account needed to be connected
     */
    public String getPassword() {
        return pssword;
    }

    /** Test if the authentification succed if a pop-up error appear return false */
    public boolean authentificationSucced() {
        SelenideElement element = $("span.user-name");
        if (!element.exists())
            return false;
        element.shouldHave(Condition.text(usrname));
        return true;
    }
}
