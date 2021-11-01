package ru.domain.netology;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.format.DateTimeFormatter.ofPattern;

public class CardDeliveryTest {

    @Test
    void shouldTestCardDeliveryWithCorrectFields() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Иваново");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(20));
    }


    @Test
    void shouldTestCardDeliveryWithDate() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Улан-Удэ");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(20))
                .shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    void shouldTestCardDeliveryWithEmptyCity() {
        open("http://localhost:9999");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        String actualMessage = $("[data-test-id = 'city'] .input__sub").getText();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void shouldTestCardDeliveryWithEmptyDate() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Иваново");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        String actualMessage = $("[data-test-id = 'date'] .input__sub").getText();
        String expectedMessage = "Неверно введена дата";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void shouldTestCardDeliveryWithEmptyName() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Иваново");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        String actualMessage = $("[data-test-id = 'name'] .input__sub").getText();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void shouldTestCardDeliveryWithEmptyPhone() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Иваново");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".checkbox__box").click();
        $(".button").click();
        String actualMessage = $("[data-test-id = 'phone'] .input__sub").getText();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void shouldTestCardDeliveryWithUnmarkedCheckbox() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Иваново");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".button").click();
        String actualMessage = $("[data-test-id = 'agreement'] .checkbox__text").getText();
        String expectedMessage = "Я соглашаюсь с условиями обработки и использования моих персональных данных";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

}

