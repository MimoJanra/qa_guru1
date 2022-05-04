package com.demoqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class BaseLambdaTest {

    private static final String BRAND = "crocs";

    @Test
    public void testLambdaSteps() {

        step("Open home page", () -> {
            open("https://www.amazon.com/");
        });
        step("Search" + BRAND, () -> {
            $(byId("twotabsearchtextbox")).click();
            $(byId("twotabsearchtextbox")).sendKeys("crocs");
            $(byId("twotabsearchtextbox")).submit();
        });

        step("Open woman's page" + BRAND, () -> {
            $(By.linkText("Women's")).click();
        });
        step("Crocs", () -> {
            $(By.partialLinkText(BRAND)).shouldBe(Condition.visible);

        });
        step("Checking", () -> {
            $(By.partialLinkText("Crocs")).shouldHave(text("Crocs"));
        });
    }

    @Test
    void webSteps() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForBrand(BRAND);
        steps.findBrand(BRAND);
        steps.checking();
    }
}