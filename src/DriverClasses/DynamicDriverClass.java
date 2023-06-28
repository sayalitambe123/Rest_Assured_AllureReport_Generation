package DriverClasses;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Common_API_Methods.Common_Utility_Method;

public class DynamicDriverClass {
	public static void main(String[] args)
			throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Read the test class names from excel
		ArrayList<String> TestCaseToRun = Common_Utility_Method.readDataExcel("TestCase", "TC2Execute");

		// find size of array list
		int count = TestCaseToRun.size();
		for (int i = 1; i < count; i++) {
			String TestCaseName = TestCaseToRun.get(i);
			// Call test class at runtime using lava.lang.reflect
			Class<?> testclassname = Class.forName("TestClasses." + TestCaseName);

			// Call executor method belonging to test class
			Method executeMethod = testclassname.getDeclaredMethod("extractor");

			// set accessibility to true(accessible)
			executeMethod.setAccessible(true);

			// Create an instance of test class capture in testclassname
			Object instanceofClass = testclassname.getDeclaredConstructor().newInstance();
			
			//execute test class captured in variable name testclassname
			executeMethod.invoke(instanceofClass);
		}

	}
}
