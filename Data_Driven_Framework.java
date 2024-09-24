import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Data_Driven_Framework
{
 		WebDriver driver=null;
		@Test
		public String[][] readExcel() throws InvalidFormatException, IOException
		{
			String[][] data=null;
			String filepath="C:\\Users\\N B DARJI\\Desktop\\Assign_login.xlsx";  
			
			File file=new File(filepath); 
			
			XSSFWorkbook workbook=new XSSFWorkbook(file); 
			
			Sheet sheet=workbook.getSheet("Sheet1"); 
			
			int nrows=sheet.getPhysicalNumberOfRows(); 
			System.out.println("total number of rows are "+nrows);
			
			data=new String[nrows][]; 
			
			for (int i=0;i<nrows;i++)
			{
				Row row=sheet.getRow(i); 
				
				int ncols=row.getPhysicalNumberOfCells(); 
				System.out.println("no. of cells are "+ncols);
				
				data[i]=new String[ncols]; 
				 
				for (int j=0;j<ncols;j++)
				{
					Cell cell=row.getCell(j); 
					
					cell.setCellType(CellType.STRING); 
					
					data[i][j]=cell.getStringCellValue();
				}			 
			}
			return data; 
		}
		@Test
		public void test() throws InvalidFormatException, IOException, InterruptedException
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\N B DARJI\\Downloads\\chromedriver-win32\\chromedriver.exe");
			
			String[][] data=readExcel();
			
			for(int i=0;i<data.length;i++)
			{
				WebDriver driver=new ChromeDriver();
				driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("/html/body/center/center/font/a/img")).click();
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("a[aria-label=\"Login\"]")).click();
				Thread.sleep(3000);
				
				
				driver.findElement(By.id("username")).sendKeys(data[i][0]);
				Thread.sleep(2000);	  
				driver.findElement(By.id("password")).sendKeys(data[i][1]);
				Thread.sleep(2000);
				
				
				Actions actions=new Actions(driver);
				Action a1=actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN)
								 .keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN)
							    .build(); 
		 			   a1.perform(); 
					  Thread.sleep(3000);
					  
				
				driver.findElement(By.xpath("//*[@id=\"recaptcha\"]/div")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
				Thread.sleep(2000);
				driver.close();
		}	
}
		}

