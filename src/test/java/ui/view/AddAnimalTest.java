package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddAnimalTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = DriverHelper.getDriver();
	}
	
	@After
	public void clean() {
		driver.quit();
	}

	@Test
	public void given_allFieldsFilledInCorrectly_when_animalAdded_then_animalIsAdded() {
		// GIVEN STEP = context
		AddPage addPage = PageFactory.initElements(driver, AddPage.class);
		addPage.setFirstName("Blub");
		addPage.setLastName("Vis");
		addPage.setEmail("3");

		// WHEN STEP = action
		addPage.add();

		// THEN STEP = result
		OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
		assertEquals("Bekijk alle dieren", overviewPage.getTitle());
		assertTrue(overviewPage.containsAnimalWithName("Blub"));
	}

	@Test
	public void given_nameNotFilledIn_when_animalAdded_then_errorMessageGivenForNameAndOtherFieldsValueAreKept(){
		// GIVEN STEP = context
		AddPage addPage = PageFactory.initElements(driver, AddPage.class);
		addPage.setFirstName("");
		addPage.setLastName("Vis");
		addPage.setEmail("3");

		// WHEN STEP = action
		addPage.add();

		// THEN STEP = result
		assertEquals("Voeg een huisdier toe", addPage.getTitle());
		assertTrue(addPage.hasEmptyName());
		assertTrue(addPage.hasErrorMessage("Geen geldige naam"));
		assertTrue(addPage.hasStickyType("Vis"));
		assertTrue(addPage.hasStickyFood("3"));
	}
}
