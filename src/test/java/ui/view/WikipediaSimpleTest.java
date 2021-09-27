package ui.view;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipediaSimpleTest {

	@Test
	public void test_Wikipedia_with_KHL_results () {
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		WebDriver driver = new ChromeDriver();
        driver.get("http://en.wikipedia.org/wiki/Main_Page");
        
        WebElement searchField = driver.findElement(By.id("searchInput"));
        searchField.clear();
        searchField.sendKeys("KHL");
        
        WebElement goButton = driver.findElement(By.id("searchButton"));
        goButton.click();

        String h2Text = driver.findElement(By.id("firstHeading")).getText();
        assertEquals("Kontinental Hockey League",h2Text);

        String captionText = driver.findElement(By.cssSelector("caption")).getText();
        assertEquals("Kontinental Hockey League", captionText);
        
        driver.quit();
	}

}
