package com.netease.java.downloadpic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/*
 * 1. ������ĵ�ַ����ͼƬ�����������ļ��С�
 http://img.coocaa.com/www/attachment/forum/201602/16/085938u86ewu4l8z6flr6w.jpg
 *Ҫ�󣺷�װ��Ӧ�Ĺ�����*
 */

public class Test {
	static int count = 0;
	
	public static List<String> readTXT(String fileName){
		File file = new File(fileName);
        BufferedReader reader = null;
        List<String> fileList = new LinkedList<String>();
        try {
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
//            System.out.println(System.getProperty("user.dir"));
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                System.out.println("line " + line + ": " + tempString);
                fileList.add(tempString.trim());
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
		return fileList;
	}
	
	public static void WriteTXT(String url ,String aliasurl){
		File file = new File("E:\\11\\aliaurl.txt");
		if (!file.exists()){
			try {
			Boolean	bool = file.createNewFile();
				if (bool = true){
					System.out.println("create E:\\11\\aliaurl.txt success");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(url + "--" + aliasurl);
			writer.newLine();
	        writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String url = "http://img.coocaa.com/www/attachment/forum/201602/16/085938u86ewu4l8z6flr6w.jpg";
//		String[] split = url.split("\\/");
//		String fileName = split[split.length - 1];
//		File file = new File("f:/", fileName);

		
		for (String url : Test.readTXT("nos.txt")){
			count ++;
			
			String[] split = url.split("\\/");
			String fileName = split[split.length - 1];
			File file = new File("E:\\11\\", "testimg" + count);
			
			InputStream inputStream = HttpURLConnectionUtil.getInputStreamByGet(url);
			HttpURLConnectionUtil.saveData(inputStream, file);
			
			Test.WriteTXT(fileName, "testimg" + count);
			
		}
			
	}
}