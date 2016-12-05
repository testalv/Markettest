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
public class AlfaTestStepsHP extends Steps{

//  Set variable for ChromeDriver
    private static ChromeDriver driver;

//  Set variable for checking URL
    String currentUrlHP;

//  Set url for yandex
    String urlHP = "https://yandex.ru/";

//  Set variable for First item
    String checkFirstHP;

//  Set variable for item name
    WebElement searchByNameHP;

// Set variable for found name
    String foundNameHP;

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
        driver.get(urlHP);
        System.out.println("Step02 PASSED");
    }

    @Test
    @Then("URL is opened")
    public void s03_UrlCcheck() {
//      Check that opened page has desired URL
        currentUrlHP = driver.getCurrentUrl();
        Assert.assertEquals(urlHP, currentUrlHP);
        System.out.println("Step03 PASSED");
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

    @Test
    @When("I navigate to Headphones")
    public void s07_HeadphonesOpen(){
//      Navigate to TV
        WebElement hpLink = driver.findElementByLinkText("Наушники и Bluetooth-гарнитуры");
        hpLink.click();
        System.out.println("Step07 PASSED");
    }

    @Test
    @Then("Headphones page is opened")
    public void s08_HPUrlCheck() {
//      Check that opened page has desired URL
        String currentHPTitle = driver.getTitle();
        if (currentHPTitle.contains("Наушники")) {
            System.out.println("Step08 PASSED");
        }
        else
        {
            System.out.println("Step08 FAILED");
        }
        System.out.println("Step08 PASSED");
    }

    // "Extended filters" page in current verion of Yandex.Market is opened by default.

    @Test
    @When("Set min sum")
    public void s09_MinSumSet(){
//      Set min price 5000
        WebElement minSumHP = driver.findElementById("glf-pricefrom-var");
        minSumHP.sendKeys("5000");
        System.out.println("Step09 PASSED");
    }

    @Test
    @When("I set brands")
    public void s10_SetBrand(){
//      Set brands
        WebElement brandBeats = driver.findElementByXPath("//label[contains(text(),'Beats')]");
        brandBeats.click();
        System.out.println("Step10 PASSED");
    }

    @Test
    @Then("Apply button is pressed")
    public void s11_PressApply(){
//      Press Apply button
        WebElement pressApplyHP = driver.findElementByClassName("n-filter-panel-aside__apply");
        pressApplyHP.click();
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
        checkFirstHP = driver.findElementByTagName("h3").getText();
        System.out.println("Step13 PASSED");
    }

    @Test
    @Then("Name of first item is entered in search field")
    public void s14_SearchByName(){
//      Enter name of first element in search field
        searchByNameHP = driver.findElementById("header-search");
        searchByNameHP.sendKeys(checkFirstHP);
        System.out.println("Step14 PASSED");
    }

    @Test
    @Then("Search made and name verified")
    public void s15_CheckFoundItem(){
//      Submit search and verify results
        searchByNameHP.submit();
        foundNameHP = driver.findElementByClassName("n-product-title").findElement(By.tagName("H1")).getText();
        if (checkFirstHP.equals(foundNameHP)) {
            System.out.println("Step15 PASSED, names are equal");
        }
        else
        {
            System.out.println("Step15 FAILED, names are not equal");
        }
    }
}