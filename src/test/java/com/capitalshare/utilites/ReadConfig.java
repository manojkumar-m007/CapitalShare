package com.capitalshare.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 
{
      Properties prop;
      public ReadConfig()
      {
    	  File src=new File("./Configuration/config.properties");
    	  try
    	  {
    	  FileInputStream fis=new FileInputStream(src);
    	  prop=new Properties();
    	  prop.load(fis);
    	  } 
    	  catch(Exception e)
    	  {
    		  System.out.println("Exception is "+e.getMessage());
    	  }
      }
      public String getUrl()
      {
    	  String url = prop.getProperty("baseURL");
    	  return url;
      }
      public String getUserID()
      {
    	  String uid=prop.getProperty("userID");
    	  return uid;
      }
      public String getUserPassword()
      {
    	  String upw=prop.getProperty("userPassword");
    	  return upw;
      }
}
