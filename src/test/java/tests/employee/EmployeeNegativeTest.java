package tests.employee;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.employee.EmployeePage;
import utils.TestDataUtil;

public class EmployeeNegativeTest extends BaseTest {

    private EmployeePage employeePage;

    @BeforeMethod
    public void setUpPage() {
        employeePage = new EmployeePage(driver);
        employeePage.waitModalGone();
        employeePage.openEmployeePage();
    }

    @Test(description = "Add employee with empty mandatory fields")
    public void addEmployee_mandatoryFieldEmpty() {
        employeePage.addEmployee("", "", "", "", "", "");

        Assert.assertTrue(
                employeePage.isErrorMessageDisplayed(),
                "Error message should be displayed"
        );
    }

    @Test(description = "Add employee with invalid email format")
    public void addEmployee_invalidEmail() {
        employeePage.addEmployee(
                TestDataUtil.validEmployeeId(),
                TestDataUtil.validName(),
                TestDataUtil.invalidEmail(),
                TestDataUtil.validPhone(),
                "QA",
                "Staff"
        );

        Assert.assertTrue(employeePage.isErrorMessageDisplayed());
    }

    @Test(description = "Add employee with duplicate employee ID")
    public void addEmployee_duplicateEmployeeId() {
        employeePage.addEmployee(
                "EMP1001",
                TestDataUtil.validName(),
                TestDataUtil.validEmail(),
                TestDataUtil.validPhone(),
                "QA",
                "Staff"
        );

        Assert.assertTrue(employeePage.isErrorMessageDisplayed());
    }

    @Test(description = "Add employee with long name edge case")
    public void addEmployee_longName() {
        employeePage.addEmployee(
                TestDataUtil.validEmployeeId(),
                TestDataUtil.longName(),
                TestDataUtil.validEmail(),
                TestDataUtil.validPhone(),
                "QA",
                "Staff"
        );

        Assert.assertTrue(
                employeePage.isSuccessMessageDisplayed(),
                "System currently allows long name (known bug)"
        );
    }

    @Test(description = "Add employee with non-numeric phone number")
    public void addEmployee_nonNumericPhone() {
        employeePage.addEmployee(
                TestDataUtil.validEmployeeId(),
                TestDataUtil.validName(),
                TestDataUtil.validEmail(),
                TestDataUtil.nonNumericPhone(),
                "QA",
                "Staff"
        );

        Assert.assertTrue(employeePage.isErrorMessageDisplayed());
    }

    @Test(description = "Search employee with invalid keyword")
    public void searchEmployee_invalidKeyword() {
        employeePage.searchEmployee("###@@@");

        Assert.assertTrue(
                employeePage.isSearchResultEmpty(),
                "Search result should be empty"
        );
    }

    @Test(description = "Transfer employee to invalid division")
    public void transferEmployee_invalidDivision() {
        employeePage.transferEmployeeToDivision("INVALID_DIVISION");

        Assert.assertTrue(employeePage.isErrorMessageDisplayed());
    }
}
