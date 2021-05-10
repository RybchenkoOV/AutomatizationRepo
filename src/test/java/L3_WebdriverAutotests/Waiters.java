package L3_WebdriverAutotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    /** Переброс инициализированного драйвера в другом классе добавляет 0,5 сек к тесту.
     * Возможно от этого лучше отказаться, если тестов будет много */
    public static void waitForVisibleByXpath(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath(xpath)));
    }
    public static void waitForVisibleById(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .id(id)));
    }
    public static void waitForVisibleByCSS(WebDriver driver, String css) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector(css)));
    }

    public static void waitForPresenceByXpath(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath(xpath)));
    }

    public static void waitForPresenceAllByXpath(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
                .xpath(xpath)));
    }

}
