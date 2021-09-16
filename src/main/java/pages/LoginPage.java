package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errMessage = By.xpath("//div[@class=\"alert alert-danger\"]");
    private By logoutButton = By.xpath("//a[@class=\"btn btn-black navbar-btn\"]");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton(){
        WebElement buttonElem = driver.findElement(loginButton);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonElem));
        buttonElem.click();


    }
    public String getErrorMessage(){
        WebElement errMsgElem = driver.findElement(errMessage);
        //wait until the error message is ready
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMsgElem));
        return errMsgElem.getText();
    }

    public boolean isErrorMessageExist(){
        WebElement errMsgElem = driver.findElement(errMessage);
        //wait until the error message is ready
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMsgElem));
        return errMsgElem.isDisplayed();
    }

    public boolean checkImLogged(){
        WebElement logoutElem = driver.findElement(logoutButton);
        //wait until the error message is ready
        return logoutElem.isDisplayed();
    }


}
