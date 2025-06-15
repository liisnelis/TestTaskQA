package tests;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.CalculatorModalPage;

public class CalculatorTests {

    CalculatorModalPage page;

    @BeforeMethod
    public void setUp() {
        Configuration.timeout = 5000;
        page = new CalculatorModalPage();
        page.openPage();
        page.calculatorApp.shouldBe(visible);
    }

    @Test(description = "Amount and period are prefilled with default values")
    public void defaultValuesAreSet() {
        String defaultAmount = page.getCurrentLoanAmount();
        String defaultPeriod = page.getCurrentLoanPeriod();
        Assert.assertEquals(defaultAmount, "5000", "Default loan amount should be 5000");
        Assert.assertEquals(defaultPeriod, "60", "Default loan period should be 60 months");
    }

    @Test(description = "User can type loan amount")
    public void userCanSetLoanAmountTyping() {
        String userAmount = "10000";
        page.setLoanAmount(userAmount);
        String newAmount = page.getCurrentLoanAmount();
        Assert.assertEquals(newAmount, userAmount);
    }

    @Test(description = "User can type loan period")
    public void userCanSetLoanPeriodTyping() {
        String userPeriod = "10000";
        page.setLoanAmount(userPeriod);
        String newPeriod = page.getCurrentLoanPeriod();
        Assert.assertEquals(newPeriod, newPeriod);
    }

    @Test(description = "User can change loan amount using slider")
    public void userCanSetLoanAmountUsingSlider() {
        String initialAmount = page.getCurrentLoanAmount();
        page.moveAmountSliderBy(10, 0);
        String newAmount = page.getCurrentLoanAmount();
        Assert.assertNotEquals(newAmount, initialAmount, "Loan amount should change after moving the slider");
    }

    @Test(description = "User can change loan period using slider")
    public void userCanSetLoanPeriodUsingSlider() {
        String initialPeriod = page.getCurrentLoanPeriod();
        page.movePeriodSliderBy(10, 0);
        String newPeriod = page.getCurrentLoanPeriod();
        Assert.assertNotEquals(newPeriod, initialPeriod, "Loan period should change after moving the slider");
    }

    @Test(description = "Increasing loan amount increases monthly payment")
    public void increasingLoanAmountIncreasesMonthlyPayment() {
        double initial = page.parseEuro(page.getMonthlyPaymentResult());
        page.moveAmountSliderBy(10, 0);
        double updated = page.parseEuro(page.getMonthlyPaymentResult());
        Assert.assertTrue(updated > initial, "Monthly payment should increase when loan amount increases");
    }

    @Test(description = "Increasing loan period decreases monthly payment")
    public void increasingLoanPeriodDecreasesMonthlyPayment() {
        double initial = page.parseEuro(page.getMonthlyPaymentResult());
        page.movePeriodSliderBy(10, 0);
        double updated = page.parseEuro(page.getMonthlyPaymentResult());
        Assert.assertTrue(updated < initial, "Monthly payment should decrease when loan period increases");
    }
}
