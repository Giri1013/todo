package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunHelper {

        public static Class getClassName(String pageObject) {
            Class cls = null;
            try {
                if(pageObject.contains("PageObjects."))
                    cls = Class.forName(pageObject);
                else
                    cls = Class.forName("PageObjects." + pageObject);
            } catch (ClassNotFoundException e) {
                throw new CustomException(e,"Exception in finding class "+pageObject);
            }
            return cls;
        }

        public static WebElement getWebElementByName(final WebDriver webdriver, String page, String elementName) {
            WebElement webElement = null;
            Object genericObject = RunHelper.getPageObject(webdriver,page);
            webElement = RunHelper.getWebElementByName(genericObject,page,elementName);
            return webElement;
        }

        public static WebElement getWebElementByName(final Object obj, String page, String elementName) {
            WebElement webElement = null;
            try {
                Field field = obj.getClass().getField(elementName);
                webElement = (WebElement) field.get(obj);
            } catch (Exception e) {
                throw new CustomException(e,"Exception in getting variable access of -"+ elementName+" in the class "+page);
            }
            return webElement;
        }

        public static Object getPageObject(final WebDriver webdriver,String page) {
            Object genericObject = null;
            try {
                Class<?> c = RunHelper.getClassName(page);
                genericObject = c.getDeclaredConstructor(WebDriver.class).newInstance(webdriver);
            } catch (Exception e) {
                throw new CustomException( e,"Exception in getting instance of class "+page);
            }
            return genericObject;
        }

        public static void executePageObjectMethod(final WebDriver webdriver, String page, String methodName,final Object... param) {
            Object genericObject = RunHelper.getPageObject(webdriver,page);
            RunHelper.executePageObjectMethod(genericObject,webdriver,page,methodName,param);
        }

        public static void executePageObjectMethod(final Object genericObject, final WebDriver driver, String page,String methodName, final Object... params) {
            List<Class<?>> parameterTypes = new ArrayList<>();
            for(Object paramType:params) {

                if(paramType.getClass().isAssignableFrom(HashMap.class))
                    parameterTypes.add(Map.class);
                else if(paramType.getClass().isAssignableFrom(ArrayList.class))
                    parameterTypes.add(List.class);
                else if(paramType.getClass().isAssignableFrom(String.class))
                    parameterTypes.add(String.class);
                else if(paramType.getClass().getName().contains("selenium")&&paramType.getClass().getName().toLowerCase().contains("driver"))
                    parameterTypes.add(WebDriver.class);
                else
                    parameterTypes.add(paramType.getClass());
            }

            Method methodToExecute;
            try{
                switch (params.length){

                    case 1:
                        methodToExecute= genericObject.getClass().getDeclaredMethod(methodName,parameterTypes.get(0));
                        methodToExecute.invoke(genericObject, new Object[] {params[0]});
                        break;

                    case 2:
                        methodToExecute= genericObject.getClass().getDeclaredMethod(methodName,parameterTypes.get(0),parameterTypes.get(1));
                        methodToExecute.invoke(genericObject, new Object[] {params[0],params[1]});
                        break;

                    case 3:
                        methodToExecute= genericObject.getClass().getDeclaredMethod(methodName,parameterTypes.get(0),parameterTypes.get(1),parameterTypes.get(2));
                        methodToExecute.invoke(genericObject, new Object[] {params[0],params[1],params[2]});
                        break;
                    case 0:
                    default:
                        methodToExecute= genericObject.getClass().getDeclaredMethod(methodName);
                        methodToExecute.invoke(genericObject, new Object[] {});
                        break;


                }
            } catch (Exception e) {
                throw new CustomException(e,"Exception in executing a method -"+ methodName+" in the class - "+ page);
            }

        }




    }


