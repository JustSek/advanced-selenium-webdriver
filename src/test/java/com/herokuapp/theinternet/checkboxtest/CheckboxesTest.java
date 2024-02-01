package com.herokuapp.theinternet.checkboxtest;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTest extends TestUtilities {

    @Test
    public void selectingTwoCheckboxesTest() {

        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        // click on Checkboxes link
        CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();
        // select All Checkboxes
        checkboxesPage.selectAllCheckboxes();
        // Verify all checkboxes
        Assert.assertTrue(checkboxesPage.areAllCheckboxesChecked(), "Not all checkboxes are checked");
    }

}
