package com.comcast.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.comcast.utils.DataDump;
import com.comcast.utils.IDataDump;
import com.comcast.utils.TestSettings;

public class TestFilterWebTop implements IMethodInterceptor {

	Logger log = Logger.getLogger(TestFilterWebTop.class);

	@Override
	public synchronized List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		List<IMethodInstance> finalMethods = new ArrayList<IMethodInstance>();
		log.debug("Inside TestFilterWebTop listener class");		
		if (System.getProperty("groupsToRun") != null) {
			log.info("Groups to run: " + System.getProperty("groupsToRun"));			
			String[] GroupNames;	
			String[] groupsToRun = System.getProperty("groupsToRun").split(";");
			System.clearProperty("groupsToRun"); //Clearing the property values as soon as groups is assigned to a variable. Otherwise it will impact during parallel execution
			for(String groups: groupsToRun){
				for (IMethodInstance method : methods) {
					GroupNames = method.getMethod().getGroups();
					boolean methodToRun = false;
					for (int idx = 0; idx < GroupNames.length; idx++) {
						// Filter the test methods which we want to execute based on
						// the Order existence from the DB
						if (GroupNames[idx].equalsIgnoreCase(groups)) {
							finalMethods.add(method);
							log.info("Include this method " + method.getMethod().getMethodName()
									+ " to the test based on the conditional execution of the test");
							methodToRun = true;
						}
						//Skip the method which we don't want to run based on the order existence.					
						if (((idx + 1) == GroupNames.length) && !(methodToRun)) {						
							log.debug("Skip this method " + method.getMethod().getMethodName()
										+ " based on the conditional execution of the test");						
							methodToRun = false;
						}
					}
				}
			}
			
			return finalMethods;
		}
		return methods;
	}
}
