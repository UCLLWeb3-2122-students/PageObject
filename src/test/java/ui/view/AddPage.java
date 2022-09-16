package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPage extends Page {

    @FindBy(id="name")
    private WebElement nameField;

    @FindBy(id="type")
    private WebElement typeField;

    @FindBy(id="food")
    private WebElement foodField;

    @FindBy(id="submit")
    private WebElement submitButton;

    public AddPage (WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"add.jsp");
    }

    public void setFirstName(String firstName) {
        nameField.clear();
        nameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        typeField.clear();
        typeField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        foodField.clear();
        foodField.sendKeys(email);
    }

    public void add() {
        submitButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyType (String type) {
        return type.equals(typeField.getAttribute("value"));
    }

    public boolean hasStickyFood (String food) {
        return food.equals(foodField.getAttribute("value"));
    }

    public boolean hasEmptyName () {
        return nameField.getAttribute("value").isEmpty();
    }

}
