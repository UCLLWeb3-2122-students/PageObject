package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddAnimalTestWithoutPageObjects {
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = DriverHelper.getDriver();
		driver.get(Config.BASE_URL +"add.jsp");
	}
	
	@After
	public void clean() {
		driver.quit();
	}

	@Test
	public void given_allFieldsFilledInCorrectly_when_animalAdded_then_animalIsAdded() {
		submitForm("Blub", "Vis", "3");

		driver.get(Config.BASE_URL +"Controller?command=Overview");
		String title = driver.getTitle();
		assertEquals("Bekijk alle dieren", title);

		ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("td"));
		boolean found=false;
		for (WebElement listItem:listItems) {
				if (listItem.getText().contains("Blub")) {
				    found=true;
			}
		}
		assertTrue(found);
	}

	@Test
	public void given_nameNotFilledIn_when_animalAdded_then_errorMessageGivenForNameAndOtherFieldsValueAreKept(){
		submitForm("", "ezel", "5");
		
		String title = driver.getTitle();
		assertEquals("Voeg een huisdier toe",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("Geen geldige naam", errorMsg.getText());

		WebElement fieldName=driver.findElement(By.id("name"));
		assertEquals("",fieldName.getAttribute("value"));
		
		WebElement fieldType=driver.findElement(By.id("type"));
		assertEquals("ezel",fieldType.getAttribute("value"));
		
		WebElement fieldFood=driver.findElement(By.id("food"));
		assertEquals("5",fieldFood.getAttribute("value"));
	}

	@Test
	public void given_typeNotFilledIn_when_animalAdded_then_errorMessageGivenForTypeAndOtherFieldsValueAreKept(){
		submitForm("Mina", "", "5");

		String title = driver.getTitle();
		assertEquals("Voeg een huisdier toe",title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("Geen geldige soort", errorMsg.getText());

		WebElement fieldName=driver.findElement(By.id("name"));
		assertEquals("Mina",fieldName.getAttribute("value"));

		WebElement fieldType=driver.findElement(By.id("type"));
		assertEquals("",fieldType.getAttribute("value"));

		WebElement fieldFood=driver.findElement(By.id("food"));
		assertEquals("5",fieldFood.getAttribute("value"));
	}

	@Test
	public void given_foodNotFilledIn_when_animalAdded_then_errorMessageGivenForFoodAndOtherFieldsValueAreKept(){
		submitForm( "Mina", "ezel", "");

		String title = driver.getTitle();
		assertEquals("Voeg een huisdier toe",title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("Geen geldige hoeveelheid voor voedsel", errorMsg.getText());

		WebElement fieldName=driver.findElement(By.id("name"));
		assertEquals("Mina",fieldName.getAttribute("value"));

		WebElement fieldType=driver.findElement(By.id("type"));
		assertEquals("ezel",fieldType.getAttribute("value"));

		WebElement fieldFood=driver.findElement(By.id("food"));
		assertEquals("",fieldFood.getAttribute("value"));
	}



	private void fillOutField(String name, String value) {
		WebElement field=driver.findElement(By.id(name));
		field.clear();
		field.sendKeys(value);
	}

	private void submitForm(String name, String type, String food) {
		fillOutField("name", name);
		fillOutField("type",type);
		fillOutField("food", food);

		WebElement button=driver.findElement(By.id("submit"));
		button.click();
	}

}
