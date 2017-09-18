package com.comcast.WebTopCases;

import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.comcast.commons.ComcastTest;
import com.comcast.utils.ComcastTestMain;
import com.comcast.utils.DataTable;


public class TestNGMain extends ComcastTest{	
	
	@Test(priority = 100 , groups={"New Connect", "All"})
	public void Method1()
	{		
		System.out.println("This is method1");
		report.reportPassEvent("Method 1", "Method 1 Passed");
	}
	
	@Test(priority = 200, groups={"New Connect", "All"})
	public void Method2()
	{
		
		System.out.println("This is method2");
		report.reportPassEvent("Method 2", "Method 2 Passed");
	}
	
	@Test(priority = 300, groups={"New Connect", "All"})
	public void Method3()
	{
		System.out.println("This is method3");
		//DataTable dataSet = null;
		//System.out.println(dataSet.getValue("TEST"));
		report.reportPassEvent("Method 3", "Method 3 Passed");
		
	}
	
	@Test(priority = 400, groups={"New Connect", "All"})
	public void Method4()
	{
		System.out.println("This is method4");
		report.reportPassEvent("Method 4", "Method 4 Passed");
	}
	
	@Test(priority = 500, groups={"New Connect", "All"})
	public void Method5()
	{
		System.out.println("This is method5");
		report.reportPassEvent("Method 5", "Method 5 Passed");}
	
	@Test(priority = 600, groups={"MACD", "All"})
	public void Method6()
	{
		System.out.println("This is method6");	
		report.reportPassEvent("Method 6", "Method 6 Passed");
	}
	
	@Test(priority = 700, groups={"MACD", "All"})
	public void Method7()
	{
		System.out.println("This is method7");
		report.reportPassEvent("Method 7", "Method 7 Passed");
	}
	
	@Test(priority = 800, groups={"MACD", "All"})
	public void Method8()
	{
		System.out.println("This is method8");
		report.reportPassEvent("Method 8", "Method 8 Passed");
	}
}
