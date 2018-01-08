package nju.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	FileReader fileReader;
	BufferedReader bufferReader;

	public Reader(String filename) {
		try {
			File file = new File(filename);

			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		String s = null;
		try {

			s = bufferReader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void closeFile() {

		try {

			bufferReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
