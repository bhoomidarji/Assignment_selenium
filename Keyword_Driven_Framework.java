import java.io.File;
import java.io.IOException;
import java.sql.Driver;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Keyword_Driven_Framework 
{
	WebDriver driver=null;
	@DataProvider(name="Bhoomi")
	public Object[][] readExcel() throws InvalidFormatException, IOException 
	{
		Object[][] data=null;
		String filepath="C:\\Users\\N B DARJI\\Desktop\\Assign_login.xlsx";  
		
		File file=new File(filepath); 
		
		XSSFWorkbook workbook=new XSSFWorkbook(file); 
		
		Sheet sheet=workbook.getSheet("Sheet2"); 
		
		int nrows=sheet.getPhysicalNumberOfRows(); 
		System.out.println("total number of rows are "+nrows);
		
		data=new Object[nrows][]; 
		
		for (int i=0;i<nrows;i++)
		{
			Row row=sheet.getRow(i); 
			
			int ncols=row.getPhysicalNumberOfCells(); 
			System.out.println("no. of cells are "+ncols);
			
			data[i]=new Object[ncols]; 
			 
			for (int j=0;j<ncols;j++)
			{
				Cell cell=row.getCell(j); 
				
				cell.setCellType(CellType.STRING); 
				
				data[i][j]=cell.getStringCellValue();
			}			 
		}
		return data; 
	}
		@Test(dataProvider = "Bhoomi")
		public void test(String keyword) throws InvalidFormatException, IOException, InterruptedException
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\N B DARJI\\Downloads\\chromedriver-win32\\chromedriver.exe");
			if(keyword.equals("open browser"))
			{
				 driver=new ChromeDriver();
			}
			else if(keyword.equals("enter url"))
			{
		    	driver.get("http://automationpractice.com/index.php");
				Thread.sleep(3000);
			
			driver.findElement(By.xpath("/html/body/center/center/font/a/img")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("a[aria-label=\"Login\"]")).click();
			Thread.sleep(3000);
			}
			else if(keyword.equals("click signin"))
			{
				driver.findElement(By.cssSelector("a[aria-label=\"Login\"]")).click();
				Thread.sleep(3000);
			}
			else if(keyword.equals("enter email"))
			{
				driver.findElement(By.id("username")).sendKeys("rahul.sanghavi.mca@gmail.com");			
				Thread.sleep(2000);
			}
			else if(keyword.equals("enter password"))
			{
				driver.findElement(By.id("password")).sendKeys("rahul12345");
				Thread.sleep(2000);
				Actions actions=new Actions(driver);
				Action a1=actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN)
								 .keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN)
							    .build(); 
		 			   a1.perform(); 
					  Thread.sleep(3000);
			}
			else if(keyword.equals("click login"))
			{
				driver.findElement(By.name("login-button")).click();
				Thread.sleep(2000);
			}
			else if(keyword.equals("click logout"))
			{
				driver.findElement(By.id("username")).sendKeys("rahul.sanghavi.mca@gmail.com");			
				Thread.sleep(2000);
			}
			else if(keyword.equals("close browser"))
			{
				driver.close();		
			}
			
}
}
