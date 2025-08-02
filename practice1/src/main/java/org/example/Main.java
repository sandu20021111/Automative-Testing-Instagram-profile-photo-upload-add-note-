package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Main {

    public static void main(String[] args) throws Exception {

        // Step 1: Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Browser drivers\\chromedriver.exe");

        // Step 2: Launch Chrome in normal mode
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 3: Go to Instagram login page
        driver.get("https://www.instagram.com/accounts/login/");
        driver.manage().window().maximize();

        // Step 4: Wait for username and password fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Step 5: Enter your credentials
        usernameField.sendKeys("YOUR_USERNAME");
        passwordField.sendKeys("YOUR_PASSWORD");

        // Step 6: Click Log In button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));
        loginButton.click();
        takeScreenshot(driver, "1_login");

        // Step 7: Wait for home screen to load
        Thread.sleep(5000);

        System.out.println("✅ Logged in successfully.");

        // ✅ Step 8: Navigate to your profile
        //navigate home page after upload post
        driver.get("https://www.instagram.com/sandu.vihanga22/");
        System.out.println("✅ Navigated to profile.");
        Thread.sleep(5000);
        takeScreenshot(driver, "2_profile");


        // Step 9: Click the note button using correct XPath
        WebElement newPostButton = driver.findElement(By.xpath("//div[@role='button' or @aria-label='New post']"));
        newPostButton.click();
        System.out.println("✅ Clicked the Note button.");
        Thread.sleep(3000);
        takeScreenshot(driver, "3_opennote");

        // Step 10: Add a note using correct XPath
        WebElement noteTextArea = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='textbox' or @aria-label='Share a thought...']")));
        noteTextArea.click();
        noteTextArea.sendKeys("Hi I am sanduni vihanga. Happy to meet you!!");
        Thread.sleep(3000);
        takeScreenshot(driver, "6_fillnote");


        // Step 11: Click the note share button using correct XPath
        WebElement shareButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='button' and normalize-space(text())='Share']")));
        shareButton.click();
        System.out.println("✅ Share button clicked successfully");
        Thread.sleep(5000);
        takeScreenshot(driver, "6_update");

        // Step 12: Reopen note using correct XPath
        WebElement editPostButton = driver.findElement(By.xpath("//div[@role='button' or @aria-label='New post']"));
        editPostButton.click();
        System.out.println("✅ Clicked again the Note button.");
        Thread.sleep(4000);
        takeScreenshot(driver, "7_reopen");

        // Step 13: Click the Delete note button using correct XPath
        WebElement deletePostButton = driver.findElement(
                By.xpath("//*[normalize-space(text())='Delete note']"));
        deletePostButton.click();
        System.out.println("✅ Clicked Delete Note button.");
        Thread.sleep(4000);
        takeScreenshot(driver, "8_delete");


        // Step 14: Click 'Add a profile photo' button
        WebElement addProfilePhotoBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@title='Add a profile photo']")));
        addProfilePhotoBtn.click();
        System.out.println("✅ Clicked 'Add a profile photo' button.");
        Thread.sleep(2000);  // Wait for file dialog to open

        // Step 15: Upload image using Robot
        String imagePath = "C:\\Users\\Acer\\Desktop\\selenium\\practice1\\src\\image\\pet1.jpg";

        // Copy image path to clipboard
        StringSelection selection = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Use Robot to paste and press Enter
        Robot robot = new Robot();
        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("✅ Uploaded profile image using Robot.");
        Thread.sleep(6000);
        takeScreenshot(driver, "9_complate");


        // Step 16: close the chromet
        driver.quit();
    }
    // Screenshot method
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            File dest = new File("C:\\Users\\Acer\\Desktop\\selenium\\practice1\\src\\screenshot\\" + fileName + ".jpg");
            dest.getParentFile().mkdirs(); // create folder if not exists
            org.openqa.selenium.io.FileHandler.copy(src, dest);
            System.out.println(" Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(" Failed to save screenshot: " + e.getMessage());
        }
    }
}


