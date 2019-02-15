package com.sdet25.web;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends TestCase {
    private WebDriver chrome;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);

        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values", images);
        prefs.put("proxy", null);

        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--headless");
        chrome_options.addArguments("--fast");
        chrome_options.addArguments("--fast-start");
        chrome_options.addArguments("--disable-cache");
        chrome_options.addArguments("--disable-icon-ntp");
        chrome_options.addArguments("--disk-cache-size=0");
        chrome_options.addArguments("--disable-ntp-favicons");
        chrome_options.addArguments("--aggressive-cache-discard");
        chrome_options.addArguments("--disable-bundled-ppapi-flash");
        chrome_options.addArguments("--disable-cached-picture-raster");
        chrome_options.addArguments("--disable-offline-load-stale-cache");

        chrome_options.setExperimentalOption("prefs", prefs);

        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
        chromeCaps.setCapability(ChromeOptions.CAPABILITY, chrome_options);

        this.chrome = new ChromeDriver(chromeCaps);
        this.chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(chrome, 100);
    }

    @Test
    public void testSimple() {
        this.chrome.get("https://www.google.com/");

        WebElement searchRow = this.chrome.findElement(By.name("q"));
        searchRow.sendKeys("Golang");

        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.sbct:first-child")));
        button.click();
    }

    public void tearDown() {

    }
}
