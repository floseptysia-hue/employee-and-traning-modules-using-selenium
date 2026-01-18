package pages.training;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object for Training Feature
 * Scope:
 * - Add Training
 * - Add Chapter
 * - Add Content
 * - Assign Employee
 * - Search
 * - Pagination
 */
public class TrainingPage extends BasePage {
    private final By trainingMenu = By.cssSelector("a[href='/dibimbingqa/admin/program/training']");

    // ADD TRAINING
    private final By addTrainingBtn = By.id("add-training-button");
    private final By trainingNameInput = By.id("title");
    private final By trainingDescriptionInput = By.id("description");
    private final By submitTrainingBtn = By.id("add-training-submit-button");
    private final By closeTrainingModalBtn = By.id("add-training-close-button");

    // Detail Training
    private final By detailTrainingBtn = By.id("button-detail-training-0");

    // ADD CHAPTER
    private final By addChapterBtn = By.id("add-chapter-button");
    private final By addChapterModal = By.id("chakra-modal-add-chapter-modal");
    private final By chapterNameInput = By.cssSelector("#chakra-modal-add-chapter-modal input#title");
    private final By chapterDescriptionInput = By.cssSelector("#chakra-modal-add-chapter-modal textarea#description");
    private final By submitChapterBtn = By.id("add-chapter-submit-button");
    private final By closeChapterModalBtn = By.id("add-chapter-close-button");

    // CONTENT
    private final By chapterTitle = By.cssSelector("div.css-95g4uk p.chakra-text");
    private final By addContentBtn = By.cssSelector("button[id^='add-content-button']");
    private final By addContentModal = By.cssSelector("section[id^='chakra-modal-add-content-modal']");
    private final By testContentRadio = By.cssSelector("input[type='radio'][value='test']");
    private final By contentTitleInput = By.cssSelector("section[id^='chakra-modal-add-content-modal'] input[id^='input-title']");
    private final By testDurationInput = By.cssSelector("section[id^='chakra-modal-add-content-modal'] input[id^='input-test-duration']");
    private final By shuffleQuestionCheckbox = By.cssSelector("section[id^='chakra-modal-add-content-modal'] input[id^='checkbox-shuffle-question']");
    private final By submitContentBtn = By.cssSelector("button[id^='submit-button']");
    private final By contentDescriptionEditor = By.cssSelector("section[id^='chakra-modal-add-content-modal'] div[contenteditable='true']");

//    // =========================
//    // ASSIGN EMPLOYEE
//    // =========================
//    private final By assignEmployeeBtn = By.id("button-assign-employee");
//    private final By employeeSearchInput = By.id("search-employee");
//    private final By assignConfirmBtn = By.id("confirm-assign-employee");

    // SEARCH
    private final By searchInput = By.id("search-training-input");

    // TABLE & PAGINATION
    private final By tableRows = By.cssSelector("tbody tr");
    private final By showRowsSelect = By.id("pagination-select");
    private final By nextPageBtn = By.id("pagination-next-button");
    private final By prevPageBtn = By.id("pagination-prev-button");

    // =========================
    // TOAST
    // =========================
    private final By toast =
            By.cssSelector("#chakra-toast-manager-top li[role='alert']");

    public TrainingPage(WebDriver driver) {
        super(driver);
    }

    // NAVIGATION
    public void openTrainingPage() {
        waitModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(trainingMenu));
        jsClick(trainingMenu);
    }

    // ADD TRAINING
    public void addTraining(String name, String description) {
        click(addTrainingBtn);

        type(trainingNameInput, name);
        type(trainingDescriptionInput, description);

        click(submitTrainingBtn);
        waitForToast();
        waitModalGone();
    }

    // CHAPTER
    public void addChapter(String chapterName, String chapterDescription) {
        click(detailTrainingBtn);
        click(addChapterBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(addChapterModal));

        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(chapterNameInput)
        );

        nameInput.clear();
        nameInput.sendKeys(chapterName);

        if (chapterDescription != null) {
            driver.findElement(chapterDescriptionInput)
                    .sendKeys(chapterDescription);
        }
        click(submitChapterBtn);

        waitForToast();
    }

    // CONTENT
    public void addTestContent(
            String title,
            String description,
            String duration
    ) {
        click(detailTrainingBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        if (driver.findElements(chapterTitle).isEmpty()) {
            throw new IllegalStateException(
                    "❌ Cannot add content: No chapter available for this training"
            );
        }

        click(addContentBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addContentModal));

        WebElement testRadio = wait.until(ExpectedConditions.elementToBeClickable(testContentRadio));
        if (!testRadio.isSelected()) {
            testRadio.click();
        }

        type(contentTitleInput, title);

        WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(contentDescriptionEditor));
        editor.click();
        editor.sendKeys(description);

        type(testDurationInput, duration);

        WebElement shuffle = driver.findElement(shuffleQuestionCheckbox);
        if (!shuffle.isSelected()) {
            shuffle.click();
        }

        click(submitContentBtn);

        waitForToast();
    }

    // SEARCH
    public void searchTraining(String keyword) {
        clearAndType(searchInput, keyword);
    }

    // PAGINATION - SHOW ROWS
    private By pageButton(int page) {
        return By.id("pagination-page-button-" + page);
    }

    public void showRows(int rows) {
//        waitForVisibility(showRowsSelect);

        Select select = new Select(driver.findElement(showRowsSelect));
        select.selectByVisibleText(rows + " Row");

        waitForTableReload();
    }

    public void goToNextPage() {
        click(nextPageBtn);
        waitForTableReload();
    }

    public void goToPreviousPage() {
        click(prevPageBtn);
        waitForTableReload();
    }

    public void goToPage(int page) {
        click(pageButton(page));
        waitForTableReload();
    }

    private void waitForTableReload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }

    // ASSERTION
    public boolean isToastDisplayed() {
        return isDisplayed(toast);
    }

    private void waitForToast() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
        } catch (Exception ignored) {
        }
    }
}
