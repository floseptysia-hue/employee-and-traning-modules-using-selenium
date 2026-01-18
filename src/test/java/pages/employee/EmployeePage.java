package pages.employee;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for Employee Feature
 * Scope:
 * - Add
 * - Search
 * - Delete
 * - Import
 * - Transfer Employee
 */
public class EmployeePage extends BasePage {
    private final By employeeMenu = By.cssSelector("a[href='/dibimbingqa/admin/employee']");

    // ADD EMPLOYEE
    private final By addEmployeeBtn = By.id("button-add-employee");
    private final By employeeIdInput = By.id("employeeId");
    private final By employeeNameInput = By.id("name");
    private final By employeeEmailInput = By.id("email");
    private final By employeePhoneInput = By.id("phoneNumber");
    private final By divisionSelect = By.id("division");
    private final By roleInput = By.id("employeeRole");
    private final By submitEmployeeBtn = By.id("button-add-employee-submit");

    // TOAST
    private final By successToast = By.cssSelector("#chakra-toast-manager-top li[role='alert']");
    private final By errorToast = By.xpath("//li[@role='alert' and (contains(.,'error') or contains(.,'failed'))]");

    // SEARCH
    private final By searchInput =
            By.xpath("//div[@id='input-admin-employee-search']//input");
    private final By emptyResultLabel =
            By.xpath("//*[contains(text(),'No data') or contains(text(),'tidak ditemukan')]");

    // DELETE
    By detailButton = By.cssSelector("button[id^='button-detail-employee']");
    private final By deleteButton = By.id("delete-employee-button");
    private final By confirmDeleteButton = By.id("confirm-delete-button");

    // TRANSFER
    private final By transferButton = By.id("TRANSFER_EMPLOYEE_BUTTON");
    private final By confirmTransferButton = By.id("CONFIRM_TRANSFER_BUTTON");

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    public void addEmployee(
            String id,
            String name,
            String email,
            String phone,
            String division,
            String role
    ) {
        waitModalGone();
        click(addEmployeeBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdInput));

        type(employeeIdInput, id);
        type(employeeNameInput, name);
        type(employeeEmailInput, email);
        type(employeePhoneInput, phone);

        if (!division.isEmpty()) {
            new Select(driver.findElement(divisionSelect))
                    .selectByVisibleText(division);
        }

        type(roleInput, role);

        jsClick(submitEmployeeBtn);

        waitForToast();
        waitModalGone();
    }

    public void deleteEmployee() {
        click(detailButton);

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        click(deleteButton);

        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        click(confirmDeleteButton);
    }

    public void openEmployeePage() {
        waitModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(employeeMenu));
        jsClick(employeeMenu);
    }

    public void searchEmployee(String keyword) {
        type(searchInput, keyword);
    }

    public boolean isSearchResultEmpty() {
        return isDisplayed(emptyResultLabel);
    }

    public void transferEmployeeToDivision(String divisionName) {
        click(transferButton);
        type(divisionSelect, divisionName);
        click(confirmTransferButton);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebElement toast =
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.visibilityOfElementLocated(successToast));

            return toast.getText().toLowerCase().contains("success");
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement toast =
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.visibilityOfElementLocated(errorToast));

            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void waitForToast() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#chakra-toast-manager-top li[role='alert']")
            ));
        } catch (TimeoutException ignored) {}
    }
}