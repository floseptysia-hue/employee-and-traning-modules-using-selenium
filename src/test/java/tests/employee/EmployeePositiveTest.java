package tests.employee;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.employee.EmployeePage;
import utils.TestDataUtil;

public class EmployeePositiveTest extends BaseTest {

    private EmployeePage employeePage;

    @BeforeMethod
    public void setUpPage() {
        employeePage = new EmployeePage(driver);
        employeePage.waitModalGone();
        employeePage.openEmployeePage();
    }

    @Test(description = "Add new employee with valid data")
    public void addEmployee_success() {
        employeePage.addEmployee(
                TestDataUtil.validEmployeeId(),
                TestDataUtil.validName(),
                TestDataUtil.validEmail(),
                TestDataUtil.validPhone(),
                "QA",
                "Staff"
        );

        Assert.assertTrue(
                employeePage.isSuccessMessageDisplayed(),
                "Employee creation should show success toast"
        );
    }

    @Test(description = "Search employee by valid keyword")
    public void searchEmployee_success() {
        employeePage.searchEmployee("John");

        Assert.assertFalse(
                employeePage.isSearchResultEmpty(),
                "Employee search result should be displayed"
        );
    }

    @Test(description = "Delete employee successfully")
    public void deleteEmployee_success() {
        employeePage.deleteEmployee();

        Assert.assertTrue(
                employeePage.isSuccessMessageDisplayed(),
                "Succes delete employee"
        );
    }
}
