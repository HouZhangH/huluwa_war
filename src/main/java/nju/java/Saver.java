package nju.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
	private FileWriter fileWritter;
	private BufferedWriter bufferWritter;
	private File file;
	private boolean first=true;
	private String content="";
	public Saver() {
		
	}

//	public void clean()
//	{
//		try
//		{
//			FileWriter t=new FileWriter(file);
//			t.write("");
//			t.close();
//		
//		}catch (Exception e)
//		{
//			
//		}
//	}
	public void save(String content) {
			this.content+=content;
			//bufferWritter.write(content);


	}
	public void saveToFile(String name) {

		try {
			 file= new File(name);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWritter = new FileWriter(file.getName());
			bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content);
			bufferWritter.close();
			fileWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
