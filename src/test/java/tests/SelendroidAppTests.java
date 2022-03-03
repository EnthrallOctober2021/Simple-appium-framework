package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SelendroidAppPages;

public class SelendroidAppTests extends BaseTest {
    SelendroidAppPages selendroidAppPages = new SelendroidAppPages();



    @Test
    public void testHeader(){
        Assert.assertEquals(selendroidAppPages.getHeaderText(),"selendroid-test-app", "Wrong header");
    }

    @Test
    public  void testTapOnWindow() throws InterruptedException {
        selendroidAppPages.tap();
        Thread.sleep(3000);
        // assert
    }

    @Test
    public  void testTapOnPopupNoButton() throws InterruptedException {
        selendroidAppPages.tapNoButton();
        Thread.sleep(3000);
        // assert
    }

    // Please do 5/more tests on selendroid app
}
