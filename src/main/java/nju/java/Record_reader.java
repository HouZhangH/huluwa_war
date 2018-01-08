package nju.java;

public class Record_reader implements Runnable  {

	Field field;
	public Record_reader(Field field) {
		this.field=field;
	}
	public void run() {
		
		boolean over=field.read_record();
		
		while(over)
		{
			 over=field.read_record();
			 try {
			 Thread.sleep(100);
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		}
		
	}
}
