package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AndroidSettingAppPages;

public class AndroidSettingAppTests extends BaseTest {

    AndroidSettingAppPages pages = new AndroidSettingAppPages() ;

    @Test
    public void testSearch() {
        Assert.assertEquals(pages.searchText(), "Search settings", "Wrong text");
        pages.scrollAndClick("System");
        pages.checkBatteryStatus();
        Assert.assertTrue(pages.checkBatteryStatus(), "status");

    }
}
