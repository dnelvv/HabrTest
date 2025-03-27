package org.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Отображается таб 'Ожидают приглашения' во вкладке 'Стать автором'")
    public void InviteTest() {
        WebElement authorLink = driver.findElement(By
                .xpath("//*[contains(text(), 'Как стать автором')]"));
        authorLink.click();
        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Как стать автором')]"))
                .isDisplayed(), "Ожидают приглашения не найден");
    }

    @Test
    @DisplayName("Во вкладке 'Администрирование' в табе 'Кампании' отображается поле 'Название'")
    public void nameInCompany() {
        WebElement adminLink = driver.findElement(By.xpath("//a[@data-test-id='main-menu-item' " +
                "and contains(text(), 'Администрирование')]"));
        adminLink.click();
        WebElement companyLink = driver.findElement(By
                .xpath("//*[contains(text(), 'Компании')]"));
        companyLink.click();
        assertTrue(driver.findElement(By.xpath("//div[@class='tm-navigation-sorting']//div[contains(@class, " +
                        "'tm-navigation-sorting__row-option') and" + " normalize-space(text())='Название']"))
                .isDisplayed(), "'Текст название не найден'");
    }
}