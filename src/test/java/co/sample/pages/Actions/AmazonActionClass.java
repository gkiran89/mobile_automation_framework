package co.sample.pages.Actions;

import co.sample.config.ContextManager;
import co.sample.config.DeviceHelper;
import co.sample.config.WebDriverListener;
import co.sample.pages.Objects.AmazonObjectClass;
import co.sample.utils.ConfigurationManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Action class
 */
public class AmazonActionClass extends WebDriverListener {
    DeviceHelper deviceHelper;
    AppiumDriver driver;
    AmazonObjectClass amazonObjectClass = new AmazonObjectClass();

    public AmazonActionClass() {
        this.driver = ContextManager.getDriver();
        deviceHelper = new DeviceHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), amazonObjectClass);
    }

    public void tapOnSkipSignIn() {
        deviceHelper.waitAndClick(amazonObjectClass.skipSignIn);
    }

    public void tapOnAddToCartBtn() {
        deviceHelper.scrollToMobileElement(amazonObjectClass.addToCartBtn,"6");
        deviceHelper.waitAndClick(amazonObjectClass.addToCartBtn);
    }

    public void tapOnProceedToCheckoutBtn() {
        deviceHelper.waitAndClick(amazonObjectClass.proceedToCheckoutBtn);
    }

    public void searchForParticularModel(String name) {
        deviceHelper.waitAndClick(amazonObjectClass.searchBar);
        amazonObjectClass.searchBar.sendKeys(name);
        deviceHelper.waitAndClick(amazonObjectClass.selectSearchedItem);
    }

    public void tapOnSearchedItem() {
        deviceHelper.waitAndClick(amazonObjectClass.searchedItem);
    }

    public String getTextOfSearchedItem() {
        return deviceHelper.getText(amazonObjectClass.searchedItem);
    }

    public String getTextOfSelectedItem() {
        return deviceHelper.getText(amazonObjectClass.selectedItemInScreen);
    }

    public String getCellData(String sheetName,int columnNum,int rowNum) throws IOException {
        prop = ConfigurationManager.getInstance();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/" + prop.getProperty("excelPath"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(columnNum);
        String value = cell.getStringCellValue();
        return value;
    }
}
