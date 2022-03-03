package pages;

import base.BaseUtils;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;


public class AndroidSettingAppPages extends BaseUtils {

    //**** Locators ****//
    By searchHeader = MobileBy.id("com.android.settings:id/search_action_bar_title");
    By title = MobileBy.id("android:id/title");




    //**** Pages actions ****//
    public String searchText()  {
       waitForElement(searchHeader);
        return getText(searchHeader);
    }
    public void tapOnSecond(){

    }

}
