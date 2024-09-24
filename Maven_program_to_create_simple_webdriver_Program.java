package com.mvn.MavenP;

import org.openqa.selenium.chrome.ChromeDriver;

public class Maven_program_to_create_simple_webdriver_Program
{
	public static void main(String[] args) throws InterruptedException
	{
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\N B DARJI\\Downloads\\chromedriver-win32\\chromedriver.exe");
	 ChromeDriver Driver = new ChromeDriver();
     Driver.get("https://demoqa.com/automation-practice-form");
     Thread.sleep(2000);
     Driver.close();
	}
}
	
