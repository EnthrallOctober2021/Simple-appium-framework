package pages;

import base.BaseUtils;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class SelendroidAppPages extends BaseUtils {

    By selendroidAppHeader = MobileBy.id("android:id/title");
    By popUp = MobileBy.id("io.selendroid.testapp:id/showPopupWindowButton");
    By enButton  = MobileBy.AccessibilityId("buttonTestCD");
    By popUpNoButton  = MobileBy.id("android:id/button2");



    public String getHeaderText(){
        return getText(selendroidAppHeader);
    }
    public void tap(){
        tapOnElement(popUp);
    }

    public void tapNoButton(){
        tapOnElement(enButton);
        tapOnElement(popUpNoButton);
    }

}
