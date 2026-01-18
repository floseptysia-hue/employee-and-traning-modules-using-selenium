package tests.training;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.training.TrainingPage;

public class TrainingPositiveTest extends BaseTest {

    private TrainingPage trainingPage;

    @BeforeMethod
    public void setup() {
        trainingPage = new TrainingPage(driver);
        trainingPage.openTrainingPage();
    }

    @Test(description = "Add New Training - Happy Path")
    public void addTraining_success() {
        trainingPage.addTraining(
                "Automation Training",
                "Training for QA Automation"
        );

        Assert.assertTrue(trainingPage.isToastDisplayed());
    }

    @Test(description = "Add New Chapter")
    public void addChapter_success() {
        trainingPage.addChapter("Introduction");

        Assert.assertTrue(trainingPage.isToastDisplayed());
    }

    @Test(description = "Add New Content")
    public void addContent_success() {
        trainingPage.addContent("Selenium Basics", "10");

        Assert.assertTrue(trainingPage.isToastDisplayed());
    }

    @Test(description = "Assign Employee to Training")
    public void assignEmployee_success() {
        trainingPage.assignEmployee("John");

        Assert.assertTrue(trainingPage.isToastDisplayed());
    }

    @Test(description = "Search Training by Employee Name")
    public void searchByEmployee_success() {
        trainingPage.searchByEmployeeName("John");
    }

    @Test(description = "Search Training by Division")
    public void searchByDivision_success() {
        trainingPage.searchByDivision("QA");
    }

    @Test(description = "Show 5 Rows")
    public void show5Rows() {
        trainingPage.showRows(5);
    }

    @Test(description = "Show 10 Rows")
    public void show10Rows() {
        trainingPage.showRows(10);
    }

    @Test(description = "Show 20 Rows")
    public void show20Rows() {
        trainingPage.showRows(20);
    }
}
