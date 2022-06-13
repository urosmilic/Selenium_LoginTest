package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecurePage {
    WebDriver driver;
    public SecurePage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement logoutButton;
    WebElement pageHeading;
    WebElement notificationSuccessfulLogin;
    WebElement welcomeMessage;


    public WebElement getLogoutButton() {
        return driver.findElement(By.cssSelector(".button.secondary.radius"));
    }

    public WebElement getPageHeading() {
        return driver.findElement(By.xpath("//div[@id = 'content']//h2"));  //ovde sam rucno pravio Smart xPath
    }

    public WebElement getNotificationSuccessfulLogin() {
        return driver.findElement(By.cssSelector(".flash.success"));
    }


    public WebElement getWelcomeMessage() {
        return driver.findElement(By.className("subheader"));
    }
    //-----------------------------------------------------------

    public void clickOnLogoutButton () {
        getLogoutButton().click();
    }










}
