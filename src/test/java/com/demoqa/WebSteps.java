package com.demoqa;

import com.codeborne.selenide.Condition;

import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Open new page")
    public void openMainPage(){
        open("https://www.amazon.com/");
    }

    @Step("Search")
    public void searchForBrand(String brand){
        $(byId("twotabsearchtextbox")).click();
        $(byId("twotabsearchtextbox")).sendKeys(brand);
        $(byId("twotabsearchtextbox")).submit();
    }

    @Step("Open")
    public void findBrand(String brand){
        $(By.partialLinkText(brand)).shouldBe(Condition.visible);
    }

    @Step("Checking")
    public void checking(){
        $(By.partialLinkText("Crocs")).shouldHave(text("Crocs"));
    }
}