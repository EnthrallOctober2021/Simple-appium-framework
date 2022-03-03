package base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class BaseUtils extends DriverManager {


    public List<MobileElement> elementList(By by){
        return androidDriver.findElements(by);
    }

    // Android only, will not work for ios
    public void scrollAndClick(String visibleText) {
        androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))").click();
    }

    public void scrollDown() {
        TouchAction action = new TouchAction(androidDriver);
        Dimension size = androidDriver.manage().window().getSize();
        int width = size.width;
        int height = size.height;
        int middleOfX = width / 2;
        int startYCoordinate = (int) (height * .7);
        int endYCoordinate = (int) (height * .2);

        action.press(PointOption.point(middleOfX, startYCoordinate))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
    }

    public void scrollUp() {
        TouchAction action = new TouchAction(androidDriver);
        Dimension size = androidDriver.manage().window().getSize();
        int width = size.width;
        int height = size.height;
        int middleOfX = width / 2;
        int startYCoordinate = (int) (height * .2);
        int endYCoordinate = (int) (height * .7);

        action.press(PointOption.point(middleOfX, startYCoordinate))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
    }

    public void tapWithText(String text) {
        List<MobileElement> elements = androidDriver.findElementsById("android:id/title");
        for (MobileElement s : elements) {
            if (s.getText().equals(text)) {
                s.click();
            }
        }
    }

    public String getAllElementText() {
        String s = "";
        List<MobileElement> elements = androidDriver.findElementsById("android:id/text1");
        for (MobileElement el : elements) {
            s = el.getText();
            System.out.println(s);
        }
        return s;
    }

    public List<String> getAllElementTextInList() {
        List<String> list = new ArrayList<>();
        List<MobileElement> elements = androidDriver.findElementsById("android:id/text1");
        for (MobileElement el : elements) {
            list.add(el.getText());
        }
        System.out.println(list);
        return list;
    }

    public boolean retryingFindClick(String text) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                List<MobileElement> elements = androidDriver.findElementsById("android:id/title");
                for (MobileElement s : elements) {
                    if (s.getText().equals(text)) {
                        s.click();
                    }
                }
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public void setWifiOff() {
        if (androidDriver.getConnection().isWiFiEnabled()) {
            androidDriver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        }
    }

    public void setWifiOn() {
        if (!androidDriver.getConnection().isWiFiEnabled()) {
            androidDriver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        }
    }

    public boolean checkBatteryStatus() {
        androidDriver.getBatteryInfo();
        return false;
    }

    public void openNotification() {
        androidDriver.openNotifications();
    }

    public String getText(By by) {
        MobileElement element = androidDriver.findElement(by);
        return element.getText();
    }

    protected void tapJs(MobileElement element){
        HashMap<String, Integer> points = new HashMap<>();
        points.put("x", element.getLocation().getX());
        points.put("y", element.getLocation().getY());
        androidDriver.executeScript("mobile: tap", points);
    }

    public void tapOnElement(By by){
        MobileElement  element = androidDriver.findElement(by);
        element.click();
    }


    public static void scrollToElement( String elementName, boolean scrollDown) {
        String listID = ((RemoteWebElement) androidDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ListView\")")).getId();
        String direction;
        if (scrollDown) {
            direction = "down";
        } else {
            direction = "up";
        }
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", direction);
        scrollObject.put("element", listID);
        scrollObject.put("text", elementName);
        androidDriver.executeScript("mobile: scrollTo", scrollObject);
    }

    public MobileElement scrollToElementByName(String elementName, String listId) {
        return androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()" +
                ".resourceId(\"" + listId + "\"))" +
                ".scrollIntoView(new UiSelector().text(\"" + elementName + "\"));");
    }


    //*** Wait actions *****//
    public void waitForElement(By element) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

}
