package ru.domain.netology;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.format.DateTimeFormatter.ofPattern;

public class CardDeliveryTest {

    @Test
    void shouldTestCardDelivery() {
        open("http://localhost:9999");
        $(".input__control[placeholder=\"Город\"]").setValue("Улан-Удэ");
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
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(date);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+79261234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(withText(date)).shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
}

