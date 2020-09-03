import data.DataHelper;
import data.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest{
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitIfValidUser(){
        RegistrationDto user = DataHelper.generateValidActiveUser();
        $("[name='login']").setValue(user.getLogin());
        $("[name='password']").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(byText("Личный кабинет")).waitUntil(visible, 15000);
    }

    @Test
    void shouldSubmitIfInvalidUser(){
        RegistrationDto user = DataHelper.generateValidBlockedUser();
        $("[name='login']").setValue(user.getLogin());
        $("[name='password']").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(byText("Пользователь заблокирован")).waitUntil(visible, 15000);
    }

    @Test
    void shouldSubmitIfInvalidLogin(){
        RegistrationDto user = DataHelper.generateInvalidLogin();
        $("[name='login']").setValue(user.getLogin());
        $("[name='password']").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldSubmitIfInvalidPassword(){
        RegistrationDto user = DataHelper.generateInvalidPassword();
        $("[name='login']").setValue(user.getLogin());
        $("[name='password']").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }
}