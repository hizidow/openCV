package com.netease.openCV246;

public class TestMain {
	static DetectFaceDemo detec = new DetectFaceDemo();
	  public static void main(String[] args) {  
	    System.out.println("Hello, OpenCV");  
	    // Load the native library.  
	    System.loadLibrary("opencv_java246");  
	    for (int i = 1; i<=5000 ; i++){
	    	detec.run("testimg" + i); 
	    }
	     
	  }  
	}