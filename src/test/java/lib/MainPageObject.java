package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class MainPageObject {
    protected AppiumDriver android;

    public MainPageObject(AppiumDriver android){
        this.android = android;
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(android, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by,errorMessage, 5);
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorMessage)
    {
        return waitForElementAndClick(by, errorMessage, 5);
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage)
    {
        return waitForElementAndSendKeys(by, value, errorMessage, 5);
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(android, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void swipeWithRatio(double startXRatio, double startYRatio, double endXRatio, double endYRatio, int durationMiliSec) {

        Dimension size = android.manage().window().getSize();
        int height = size.height;
        int width = size.width;

        int swipeStartWidth = (int) (width * startXRatio);
        int swipeStartHeight = (int) (height * startYRatio);
        int swipeEndWidth = (int) (width * endXRatio);
        int swipeEndHeight = (int) (height * endYRatio);

        new TouchAction(android)
                .press(point(swipeStartWidth, swipeStartHeight))
                .waitAction(waitOptions(Duration.ofMillis(durationMiliSec)))
                .moveTo(point(swipeEndWidth, swipeEndHeight))
                .release()
                .perform();
    }

    public void swipeUp(int durationMiliSec) {

        Dimension size = android.manage().window().getSize();
        int height = size.height;
        int width = size.width;

        int swipeStartWidth = (int) (width * 0.5);
        int swipeStartHeight = (int) (height * 0.8);
        int swipeEndWidth = (int) (width * 0.5);
        int swipeEndHeight = (int) (height * 0.2);

        new TouchAction(android)
                .press(point(swipeStartWidth, swipeStartHeight))
                .waitAction(waitOptions(Duration.ofMillis(durationMiliSec)))
                .moveTo(point(swipeEndWidth, swipeEndHeight))
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToElement(By by, String error_message, int max_swipes) {
        int already_swipes = 0;
//        цикл while работает, пока мы ничего не находим
        while (android.findElements(by).size() == 0) {
//             переменная already_swipes указывает кол-во совершенных свайпов
//             пишем if для того, чтобы ограничить максимальное кол-во свайпов
//             т.е. если мы в max_swipes ограничили их 5 штуками, то
//             в момент, когда already_swipes > max_swipes, совершится последний свайп
//             и если он что-то найдет - то он вернет значение через return, а если нет, то выдаст ошибку
            if (already_swipes > max_swipes) {
                waitForElementPresent(by, "не найден элемент при помощи свайпа + \n" + " " + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }

    public void swipeToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        new TouchAction(android)
                .press(point(right_x, middle_y))
                .waitAction(waitOptions(Duration.ofMillis(300)))
                .moveTo(point(left_x, middle_y))
                .release()
                .perform();
    }

    //  вставляем в аргумент Xpath, получаем назад количество
//  элементов, которое найдено по данному XPath
    public int getAmounOfElements(By by){
        List elements = android.findElements(by);
        return elements.size();
    }

    //  вставляем XPath, получаем текст из элемента
    public String getTextOfElement(By by){
        WebElement element = android.findElement(by);
        return element.getText();
    }

// метод смотрит, что на странице найде 1 элемент с текстом "No results, это сделано потому, что
// wiki, когда ничего не находит, выводит в поиске 1 строку с таким названием
// если такой элемент найден, ассерт проходит, в остальных случаях выдаем ошибку


    public void assertElementWithOnePresent(By by, String error_message){
        int amountOfElements = getAmounOfElements(by);
        if(amountOfElements > 0 && getTextOfElement(by).equals("No results")){
            Assertions.assertEquals("No results", getTextOfElement(by));
        }
        else{
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

// метод смотрит, что на странице ничего не найдено. Если что-то найдено, то в if написано исключение
// которое выдаст ошибку и скажет, что на странице все же что-то нашлось

    public void assertElementWithNoPresent(By by, String error_message){
        int amountOfElements = getAmounOfElements(by);
        if(amountOfElements > 0){
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }
}
