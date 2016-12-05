package org.test.alfa;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Vladimir on 03.12.2016.
 */
public class AlfaTestStepsTV extends Steps {

//  Set variable for ChromeDriver
    private static ChromeDriver driver;

//  Set variable for checking URL
    String currentUrl;

//  Set url for yandex
    String url = "https://yandex.ru/";

//  Set variable for First item
    String checkFirst;

//  Set variable for item name
    WebElement searchByName;

// Set variable for found name
    String foundName;

    @Test
    @Given("I launch browser")
    public void s01_BrowserMaximizeLaunch() {
//      Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/ChromeDriver.exe");
//      Init new instance of ChromeDriver.
        driver = new ChromeDriver();
//      Maximise browser window
        driver.manage().window().maximize();
        System.out.println("Step01 PASSED");
    }

    @Test
    @When("I open URL")
    public void s02_UrlOpen() {
//      Open URL in browser
        driver.get(url);
        System.out.println("Step02 PASSED");
    }

    @Test
    @Then("URL is opened")
    public void s03_UrlCcheck() {
//      Check that opened page has desired URL
        currentUrl = driver.getCurrentUrl();
        if (url.equals(currentUrl)) {
            System.out.println("Step03 PASSED");
        }
        else
        {
            System.out.println("Step03 FAILED");
        }
    }

    @Test
    @When("I navigate to Market")
    public void s04_MarketOpen(){
//      Navigate to yandex Market
        WebElement marketLink = driver.findElementByLinkText("Маркет");
        marketLink.click();
        System.out.println("Step04 PASSED");
    }

    @Test
    @Then("Market URL is opened")
    public void s05_MarketUrlCheck() {
//      Check that opened page has desired URL
        String currentMUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentMUrl.contains("market.yandex.ru"));
        System.out.println("Step05 PASSED");
    }

    @Test
    @When("I navigate to Electronics")
    public void s06_ElectronicsOpen(){
//      Navigate to Electronics
        driver.getMouse();
        WebElement electronicsLink = driver.findElementByLinkText("Электроника");
        electronicsLink.click();
        System.out.println("Step06 PASSED");
    }

// Commented code contains navigation through pop-up menu which is not as stable as opening Electronics page.
//If unhide this step - other Step6 should be hidden.
//    @Test
//    @When("I navigate to Electronics")
//    public void s06_ElectronicsOpen(){
////      Navigate to Electronics
//        driver.getMouse();
//        WebElement electronicsLink = driver.findElementByLinkText("Электроника");
//        Actions builder = new Actions(driver);
//        builder.moveToElement(electronicsLink).perform();
//        System.out.println("Step06 PASSED");
//    }

    @Test
    @When("I navigate to TV")
    public void s07_TVOpen(){
//      Navigate to TV
        WebElement tvLink = driver.findElementByLinkText("Телевизоры");
        tvLink.click();
        System.out.println("Step07 PASSED");
    }

    @Test
    @Then("TV page is opened")
    public void s08_TVUrlCheck() {
//      Check that opened page has desired URL
        String currentTVTitle = driver.getTitle();
        if (currentTVTitle.contains("Телевизоры")) {
            System.out.println("Step08 PASSED");
        }
        else
        {
            System.out.println("Step08 FAILED");
        }
    }

    // "Extended filters" page in current verion of Yandex.Market is opened by default.

    @Test
    @When("Set min sum")
    public void s09_MinSumSet(){
//      Set min price 20000
        WebElement minSum = driver.findElementById("glf-pricefrom-var");
        minSum.sendKeys("20000");
        System.out.println("Step09 PASSED");
    }

    @Test
    @When("I set brands")
    public void s10_SetBrand(){
//      Set brands
        WebElement brandLG = driver.findElementByXPath("//label[contains(text(),'LG')]");
        brandLG.click();
        WebElement brandS = driver.findElementByXPath("//label[contains(text(),'Samsung')]");
        brandS.click();
        System.out.println("Step10 PASSED");
    }

    @Test
    @When("I press Apply")
    public void s11_PressApply(){
//      Press Apply button
        WebElement pressApply = driver.findElementByClassName("n-filter-panel-aside__apply");
        pressApply.click();
        System.out.println("Step11 PASSED");
    }

    @Test
    @Then("Count items displayed")
    public void s12_CountItems(){
//      Check page is loaded and count items displayed
        try {
            WebElement pageloaded = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(("button[name='Показать ещё']"))));
        }
        catch (Exception e){
        }
        List<WebElement> totalLinks = driver.findElementsByLinkText("Отложить");
        int totalLinkSize = totalLinks.size();
        System.out.println("Step12 PASSED, Total items displayed: " + totalLinkSize);

    }

    @Test
    @When("I find first item")
    public void s13_FindFirstItem(){
//      Find first element
        checkFirst = driver.findElementByTagName("h3").getText();
        System.out.println("Step13 PASSED");
    }

    @Test
    @Then("Name of first item is entered in search field")
    public void s14_SearchByName(){
//      Enter name of first element in search field
        searchByName = driver.findElementById("header-search");
        searchByName.sendKeys(checkFirst);
        System.out.println("Step14 PASSED");
    }

    @Test
    @Then("Search made and name verified")
    public void s15_CheckFoundItem(){
//      Submit search and verify results
        searchByName.submit();
        foundName = driver.findElementByClassName("n-product-title").findElement(By.tagName("H1")).getText();

        if (checkFirst.equals(foundName)) {
            System.out.println("Step15 PASSED");
        }
        else
        {
            System.out.println("Step15 FAILED");
        }

    }
}