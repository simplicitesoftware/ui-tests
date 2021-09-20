package com.simplicite.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authentication {

    /** Elements needed to login **/
    public SelenideElement usrelement = $("#auth-signin-username");
    public SelenideElement password = $("#auth-signin-password");
    public SelenideElement connection = $("#auth-signin-submit");
    private String usrname;
    private String pssword;

    /** Create an authentification
     *
     * @param usrname Username of the account needed to be connected to the website
     * @param pssword Password of the account needed to be connected to the website
     */
    public Authentication(String usrname, String pssword) {
        this.usrname = usrname;
        this.pssword = pssword;

    }
    public Authentication(){}
    /** Connect the user **/
    public void connect() {
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
        element.should(Condition.exist);
        element.shouldHave(Condition.text(usrname));
        return true;
    }

    public void deconnection()
    {
        $(".logged-user").click();
        $(".user-logout").click();
        $("#dlgmodal_CONFIRM_LOGOUT").find("button[data-action=\"OK\"]").click();
    }

    public void setUsername(String username) {
        usrname = username;
    }

    public void setPassword(String password)
    {
        pssword = password;
    }

    public void connectFirstTime(String newpssword) {
        connect();
        $("#auth-signin-password1").setValue(newpssword);
        $("#auth-signin-password2").setValue(newpssword);
        $("#auth-signin-save").click();
        setPassword(newpssword);
    }

    public void changePassword(String newPassword) {
        $("#auth-signin-password1").setValue(newPassword);
        $("#auth-signin-password2").setValue(newPassword);
        $("#auth-signin-save").click();
        setPassword(newPassword);

    }
}
