package com.netease.openCV246;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

//  
// Detects faces in an image, draws boxes around them, and writes the results  
// to "faceDetection.png".  
//  
public class DetectFaceDemo {
	
	public static Map<String, String> geturlandalia() {
		File file = new File("E:\\11\\aliaurl.txt");
		BufferedReader reader = null;
		Map<String, String> fileMap = new HashMap<String, String>();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			// System.out.println(System.getProperty("user.dir"));
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			String[] split = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				split = tempString.trim().split("--");
				fileMap.put(split[1], split[0]);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return fileMap;
	}
	
	public static void WriteOKNOSTXT(String url){
		File file = new File("E:\\11\\nos_ok.txt");
		if (!file.exists()){
			try {
			Boolean	bool = file.createNewFile();
				if (bool = true){
					System.out.println("create E:\\11\\nos_ok.txt success");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(url);
			writer.newLine();
	        writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void run(String imgname) {
		System.out.println("\nRunning DetectFaceDemo");
		System.out.println(getClass().getResource("lbpcascade_frontalface.xml").getPath());
		// Create a face detector from the cascade file in the resources
		// directory.
		// CascadeClassifier faceDetector = new
		// CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());
		// Mat image =
		// Highgui.imread(getClass().getResource("lena.png").getPath());
		// 注意：源程序的路径会多打印一个‘/’，因此总是出现如下错误
		/*
		 * Detected 0 faces Writing faceDetection.png libpng warning: Image
		 * width is zero in IHDR libpng warning: Image height is zero in IHDR
		 * libpng error: Invalid IHDR data
		 */
		// 因此，我们将第一个字符去掉
		String xmlfilePath = getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
		CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
		Mat image = Highgui.imread(getClass().getResource(imgname).getPath().substring(1));
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		
		Map<String, String> map = DetectFaceDemo.geturlandalia();
		
		if (faceDetections.toArray().length > 0) {
			String nos_url = map.get(imgname);
			DetectFaceDemo.WriteOKNOSTXT("http://nos.netease.com/yixinpublic/" + nos_url);
		}

		// Draw a bounding box around each face.
		// for (Rect rect : faceDetections.toArray()) {
		// Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +
		// rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		// }

		// Save the visualized detection.
		// String filename = "faceDetection.png";
		// System.out.println(String.format("Writing %s", filename));
		// Highgui.imwrite(filename, image);
	}
}