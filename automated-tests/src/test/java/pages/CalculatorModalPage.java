package pages;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class CalculatorModalPage {

    public final SelenideElement calculatorApp = $("#app");
    public final SelenideElement loanAmountInput = $("[name='header-calculator-amount']");
    public final SelenideElement loanPeriodInput = $("[name='header-calculator-period']");
    public final SelenideElement loanAmountSlider = $("[aria-label='Laenusumma'] .vue-slider-dot");
    public final SelenideElement loanPeriodSlider = $("[aria-label='Periood'] .vue-slider-dot");
    public final SelenideElement monthlyPaymentText = $(".bb-labeled-value__value");

    public void openPage() {
        open("https://laenutaotlus.bigbank.ee/?amount=5000&period=60&productName=SMALL_LOAN&loanPurpose=DAILY_SETTLEMENTS");
    }

    public void setLoanAmount(String amount) {
        loanAmountInput.click(); 
        loanAmountInput.sendKeys(Keys.chord(Keys.CONTROL, "a")); 
        loanAmountInput.sendKeys(Keys.BACK_SPACE);  
        loanAmountInput.setValue(amount);
        loanAmountInput.pressTab();
    }

    public void setLoanPeriod(String period) {
        loanPeriodInput.click(); 
        loanPeriodInput.sendKeys(Keys.chord(Keys.CONTROL, "a")); 
        loanPeriodInput.sendKeys(Keys.BACK_SPACE);  
        loanPeriodInput.setValue(period);
        loanPeriodInput.pressTab();

    }


    public void moveAmountSliderBy(int offsetX, int offsetY) {
       new Actions(WebDriverRunner.getWebDriver())
        .clickAndHold(loanAmountSlider)
        .moveByOffset(offsetX, offsetY)
        .release()
        .perform();
    }
    
    public void movePeriodSliderBy(int offsetX, int offsetY) {
       new Actions(WebDriverRunner.getWebDriver())
        .clickAndHold(loanPeriodSlider)
        .moveByOffset(offsetX, offsetY)
        .release()
        .perform();
    }  

    public String getCurrentLoanAmount() {
        return loanAmountInput.getValue().replace(",", "");
    }

    public String getCurrentLoanPeriod() {
        return loanPeriodInput.getValue();
    }

    public String getMonthlyPaymentResult() {
        monthlyPaymentText.shouldBe(visible);
        monthlyPaymentText.shouldNotBe(empty);
        return monthlyPaymentText.getText();
    }

    public double parseEuro(String euroText) {
        return Double.parseDouble(euroText.replace("€", "").trim());
    }

}
       