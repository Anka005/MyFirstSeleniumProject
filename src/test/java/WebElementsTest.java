import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class WebElementsTest {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://przyklady.javastart.pl/test/full_form.html");
    }

    @Test
    public void typingIntoWebElementTest() {
        WebElement userNameField = driver.findElement(By.id("username"));
        sleep();
        userNameField.sendKeys("Selenium Start");
        String typeUserNameValue = userNameField.getAttribute("value");
        sleep();
        assertEquals(typeUserNameValue, "Selenium Start");
    }

    @Test
    public void filePickingTest() {
        sleep();
        WebElement uploadFilePicker = driver.findElement(By.id("upload_file"));
        uploadFilePicker.sendKeys("C:\\Users\\Anna\\Pictures\\koza.jfif");

        sleep();
    }

    @Test
    public void checkRadioButtonTest() {
        driver.findElement(By.cssSelector("input[value='male']"));
        driver.findElement(By.cssSelector("input[value='female']"));

        WebElement maleRadioButton = driver.findElement(By.cssSelector("input[value='male']"));
        WebElement femaleRadioButton = driver.findElement(By.cssSelector("input[value='female']"));

        sleep();
        maleRadioButton.click();
        sleep();
        assertTrue(maleRadioButton.isSelected());
        sleep();
        femaleRadioButton.click();
        sleep();
        assertTrue(femaleRadioButton.isSelected());
        assertFalse(maleRadioButton.isSelected());
    }

    @Test
    public void checkboxButtonTest() {
        WebElement pizzaCheckbox = driver.findElement(By.cssSelector("input[value='pizza']"));
        WebElement spaghettiCheckbox = driver.findElement(By.cssSelector("input[value='spaghetti']"));
        WebElement hamburgerCheckbox = driver.findElement(By.cssSelector("input[value='hamburger']"));

        assertFalse(pizzaCheckbox.isSelected());
        assertFalse(spaghettiCheckbox.isSelected());
        assertFalse(hamburgerCheckbox.isSelected());
        sleep();

        pizzaCheckbox.click();
        spaghettiCheckbox.click();
        hamburgerCheckbox.click();
        sleep();

        assertTrue(pizzaCheckbox.isSelected());
        assertTrue(spaghettiCheckbox.isSelected());
        assertTrue(hamburgerCheckbox.isSelected());
        sleep();
    }


    @Test
    public void typingAndClearingValueInsideWebElementTest() {
        WebElement userNameField = driver.findElement(By.id("username"));
        sleep();
        userNameField.sendKeys("Selenium Start");
        String typeUserNameValue = userNameField.getAttribute("value");
        sleep();
        assertEquals(typeUserNameValue, "Selenium Start");
        userNameField.clear();
        sleep();
        String emptyUserNameField = userNameField.getAttribute("value");
        assertEquals(emptyUserNameField, "");
    }

    @Test
    public void dropDownListingTest() {
        WebElement countryWebElement = driver.findElement(By.id("country"));
        Select countryDropDown = new Select(countryWebElement);

        List<WebElement> options = countryDropDown.getOptions();
        List<String> namesOfOptions = new ArrayList<>();

        for (WebElement option : options) {
            namesOfOptions.add(option.getText());
            System.out.println(option.getText());
        }
        List<String> expectedNamesOfOptions = new ArrayList<>();
        expectedNamesOfOptions.add("Germany");
        expectedNamesOfOptions.add("Poland");
        expectedNamesOfOptions.add("UK");

        sleep();

        assertEquals(namesOfOptions, expectedNamesOfOptions);
    }

   private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }

    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}
