package yammer_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class YammerPostingContentPeriodically{
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver",
				"./driver/chromedriver.exe");
		ChromeOptions option= new ChromeOptions();
		option.addArguments("--disable-notifications");

		driver= new ChromeDriver(option);
		driver.get("https://web.yammer.com/main/capgemini.com/");
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.name("loginfmt")).sendKeys("praveen-uttam.wakchaure@capgemini.com");
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(5000);

		// Technical error in wait
		WebElement yesButton= driver.findElement(By.xpath("//input[@type='submit']"));
		yesButton.click();
		Thread.sleep(15000);
		
		//Click on OneTeamONeFamily
		driver.findElement(By.xpath("//div[contains(text(),'Yammer Demo Community')]")).click();

		Thread.sleep(5000);
	
		String str = null;
		File file =new File("C:\\Users\\PWAKCHAU\\eclipse-workspace\\com.cg.ngt.seleniumassignment\\resources\\Book1.xlsx");
		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);

		//Read sheet inside the workbook by its name

		Sheet sheet = workbook.getSheet("Sheet1");

		//Find number of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		//Create a loop over all the rows of excel file to read it

		for (int i = 0; i <=rowCount+1; i++) {

			Row row = sheet.getRow(i);

			//Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {
				WebElement content1=driver.findElement(By.xpath("//div[contains(text(),'Share thoughts, ideas, or updates')]"));
				content1.click();

				str=row.getCell(0).getStringCellValue().toString();
				WebElement typeContent= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div/span/div/div[2]/div/div/div/div/span"));

				typeContent.sendKeys(str);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div[5]/div[2]/div/ul/li/div/div/button")).click();

				Thread.sleep(10000);

				driver.navigate().refresh();	

				TimeUnit.SECONDS.sleep(60);
			}
		}
		workbook.close();
	}
}