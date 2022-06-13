package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement usernameTextbox;
    WebElement passwordTextbox;
    WebElement loginButton;
    WebElement loginTitle;
    WebElement notificationUnsuccessfulLogin;
    WebElement notificationSuccessfulLogout;

    public WebElement getUsernameTextbox() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getPasswordTextbox() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.className("radius"));
    }

    public WebElement getLoginTitle() {
        return driver.findElement(By.xpath("//div[@class = 'example']/h2"));    //ovde sam rucno pravio Smart xPath
    }

    public WebElement getNotificationUnsuccessfulLogin() {
        return driver.findElement(By.cssSelector(".flash.error"));
    }

    public WebElement getNotificationSuccessfulLogout() {
        return driver.findElement(By.cssSelector(".flash.success"));
    }

    //--------------------------------------------------------------------------------------

    public void enterUsername (String username) {
        getUsernameTextbox().clear();
        getUsernameTextbox().sendKeys(username);
    }

    public void enterPassword (String password) {
        getPasswordTextbox().clear();
        getPasswordTextbox().sendKeys(password);
    }

    public void clickOnLoginButton () {
        getLoginButton().click();
    }













}
