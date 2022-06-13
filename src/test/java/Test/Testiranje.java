package Test;

import Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testiranje extends Base {

    @BeforeMethod
    public void pageSetUp () {
        String loginPageURL = excelReader.getStringData("Login",1,5);
        driver.manage().window().maximize();
        driver.navigate().to(loginPageURL); //https://the-internet.herokuapp.com/login
    }

    @AfterMethod
    public void deleteCookies () {
        driver.manage().deleteAllCookies();
    }

    // U excel fajlu su date razlicite varijante za username i password, tako da ovde pise invalid, a iz samog excela se pozivaju vrednosti koje su
    //upercase, lowercase, sa tackom, sa razmakom, itd...

    @Test (priority = 10, description = "login with valid credentials")
    public void test1 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login",1,2);
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(securePage.getLogoutButton().isDisplayed());  //provera da je Logout dugme prisutno
        Assert.assertTrue(securePage.getNotificationSuccessfulLogin().isDisplayed());   //provera da li je poruka o uspesnom Loginu prikazana
        Assert.assertEquals(securePage.getPageHeading().getText(),"Secure Area");   //provera naslova strane
        Assert.assertEquals(securePage.getWelcomeMessage().getText(), "Welcome to the Secure Area. When you are done click logout below."); //provera poruke - teksta
        boolean check = false;
        try {
            loginPage.getLoginButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);      //provera da dugme Login nije prisutno
    }

    @Test (priority = 20, description = "login with invalid username")
    public void test2 () {
        for (int i = 0; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login",i,3);
            String validPassword = excelReader.getStringData("Login",1,2);
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your username is invalid!"));    //provera kako glasi poruka
            Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
            boolean check = false;
            try {
                check = securePage.getLogoutButton().isDisplayed();
            } catch (Exception e) {
                check = false;
            }
            Assert.assertFalse(check);      //provera da dugme Logout nije prisutno
        }
    }

    @Test (priority = 30, description = "login with invalid password")
    public void test3 () {
        for (int i = 0; i < excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login",1,1);
            String invalidPassword = excelReader.getStringData("Login",i,4);
            loginPage.enterUsername(validUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your password is invalid!"));    //provera kako glasi poruka
            Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
            boolean check = false;
            try {
                check = securePage.getLogoutButton().isDisplayed();
            } catch (Exception e) {
                check = false;
            }
            Assert.assertFalse(check);      //provera da dugme Logout nije prisutno
        }
    }

    @Test (priority = 40, description = "login with blank username")
    public void test4 () {
        String validPassword = excelReader.getStringData("Login",1,2);
        loginPage.enterUsername("");    //mogao sam bez ovog koraka-metode, ali u njoj vec pise clear, tako da sam to hteo da iskoristim
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your username is invalid!"));    //provera kako glasi poruka
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
        boolean check = false;
        try {
            check = securePage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);      //provera da dugme Logout nije prisutno
    }

    @Test (priority = 50, description = "login with blank password")
    public void test5 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("");    //mogao sam bez ovog koraka-metode, ali u njoj vec pise clear, tako da sam to hteo da iskoristim
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your password is invalid!"));    //provera kako glasi poruka
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
        boolean check = false;
        try {
            check = securePage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);      //provera da dugme Logout nije prisutno

    }

    @Test (priority = 60, description = "login with blank username and password")
    public void test6 () {
        loginPage.enterUsername("");    //mogao sam bez ovog koraka-metode, ali u njoj vec pise clear, tako da sam to hteo da iskoristim
        loginPage.enterPassword("");    //mogao sam bez ovog koraka-metode, ali u njoj vec pise clear, tako da sam to hteo da iskoristim
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
        Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your username is invalid!"));    //provera kako glasi poruka
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
        boolean check = false;
        try {
            check = securePage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);      //provera da dugme Logout nije prisutno
    }


    @Test (priority = 70, description = "login with invalid username and password")
    public void test7 () {
        for (int i = 0; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login",i,3);
            String invalidPassword = excelReader.getStringData("Login",i,4);
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().isDisplayed());  //provera da je poruka Unsuccessful login prikazana
            Assert.assertTrue(loginPage.getNotificationUnsuccessfulLogin().getText().contains("Your username is invalid!"));    //provera kako glasi poruka
            Assert.assertTrue(loginPage.getLoginButton().isDisplayed());    //provera da je Login dugme prisutno
            boolean check = false;
            try {
                check = securePage.getLogoutButton().isDisplayed();
            } catch (Exception e) {
                check = false;
            }
            Assert.assertFalse(check);      //provera da dugme Logout nije prisutno
        }
    }

    @Test (priority = 80, description = "logout by clicking logout button")
    public void test8 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login",1,2);
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(securePage.getLogoutButton().isDisplayed());  //provera da je Logout dugme prisutno
        //necu ponavljati dalje assertove koje se odnose na login, jer se u ovom testu proverava logout.
        //test je lociran kao poslednji tako da bi prvi test "successful login" pao i ne bi doslo do ovog
        securePage.clickOnLogoutButton();
        boolean check = false;
        try {
            securePage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);      //provera da Logout dugme nije vidljivo
        Assert.assertTrue(loginPage.getNotificationSuccessfulLogout().isDisplayed());   //provera da li je poruka o uspesnom Logout prikazana
        Assert.assertTrue(loginPage.getNotificationSuccessfulLogout().getText().contains("You logged out of the secure area!")); //provera kako glasi tekst obavestenja o uspesnom Logoutu
        Assert.assertTrue(loginPage.getLoginTitle().isDisplayed()); //provera da li je prikazan naslov Login stranice


    }





















}
