package Tests;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilites.ExcelUtility;

@Listeners(utilites.ReportsGenerator.class)
public class LoginTests extends BaseTests {
    private LoginPage loginPage;
    ExcelUtility excel = new ExcelUtility("TestData.xlsx");

    //in this method we call all needed pages for tests
    @BeforeMethod
    public void callPages() {
        loginPage = new LoginPage(driver);

    }

    //in this method we do our tests
    @Test
    public void loginWithInvalidData() {
        //Reading test data from excel
        String testData = excel.getCellDataString("TCS", 1, 4);
        String [] data = testData.split("-");
        String email = data[0];
        String password = data [1];


        //get time before loading
        Long start = System.currentTimeMillis();
        //open the lgoin page
        driver.get("https://www.opencart.com/index.php?route=account/login");
        //get time after loading
        Long finish = System.currentTimeMillis();
        //subtract to get exact loading time
        Long totalTime = finish - start;
        //add loading time to excel sheet
        excel.setCellData("TCS", 3, 1, totalTime.toString() + " milliseconds");

        //fill email
        loginPage.setEmailField(email);
        //fill password
        loginPage.setPasswordField(password);
        //click login
        loginPage.clickLoginButton();
        //check error message appeared
        Assert.assertTrue(loginPage.isErrorMessageExist());

        if (loginPage.isErrorMessageExist()) {
            //set status = pass
            excel.setCellData("TCS", 2, 1, "pass");
        } else {
            //set status = fail
            excel.setCellData("TCS", 2, 1, "fail");
        }

    }

    //in this method we do our tests
    @Test
    public void loginWithValidData() {
        //Read test data from excel
        String testData = excel.getCellDataString("TCS", 2, 4);
        String [] data = testData.split("-");
        String email = data[0];
        String password = data [1];


        //get time before loading
        Long start = System.currentTimeMillis();
        //open the login page
        driver.get("https://www.opencart.com/index.php?route=account/login");
        //get time after loading
        Long finish = System.currentTimeMillis();
        //subtract to get exact loading time
        Long totalTime = finish - start;
        excel.setCellData("TCS", 3, 2, totalTime.toString() + "milliseconds");


        //fill email
        loginPage.setEmailField(email);
        //fill password
        loginPage.setPasswordField(password);
        //click login
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.checkImLogged());

        if (loginPage.checkImLogged()) {
            //set status = pass
            excel.setCellData("TCS", 2, 2, "pass");
        } else {
            //set status = fail
            excel.setCellData("TCS", 2, 2, "fail");
        }

    }

}
