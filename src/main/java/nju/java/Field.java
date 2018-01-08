package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Field extends JPanel {

	private final int OFFSET = 30;
	private final int SPACE = 50;
	private Player player[];
	private laoyeye laoyeye;
	private shejing shejing;
	private xiezijing xiezijing;
	private xiaolouluo xiaolouluo[];
	private Saver saver;
	private boolean record_ornot;
	private Reader reader;
	private Record_reader record_reader;
	
	private int w = 0;
	private int h = 0;
	private boolean completed = false;

    
	public boolean record_ornot()
	{
		return record_ornot;
	}
	public void saver_save(String content)
	{
		saver.save(content);
	}
	public Field() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}

	public int getBoardWidth() {
		return this.w;
	}

	public int getBoardHeight() {
		return this.h;
	}

	public final void initWorld() {

		int x = OFFSET;
		int y = OFFSET;

		//Tile a;
		record_reader=new Record_reader(this);
		saver=new Saver();
		shejing = new shejing(800, 50, this,"shejing");
		xiezijing = new xiezijing(750, 100, this,"xiezijing");
		laoyeye = new laoyeye(150, 50 + 50 * 7, this,"laoyeye");
		player = new Player[7];
		xiaolouluo = new xiaolouluo[6];
		record_ornot=true;
		for (int i = 0; i < 7; i++) {
			player[i] = new Player(150, 50 + 50 * i, this, i,"player["+i+"]");
		}

		for (int i = 0; i < 6; i++) {
			xiaolouluo[i] = new xiaolouluo(750 - 50 * (i + 1), 100 + 50 * (i + 1), this, i,"xiaolouluo["+i+"]");
		}
		w = 1000;
		h = 606;

	}

	public void set_run_again() {
		for (int i = 0; i < 7; i++) {
			if (player[i].alive())
				player[i].setrun();
		}

		for (int i = 0; i < 6; i++) {
			if (xiaolouluo[i].alive())
				xiaolouluo[i].setrun();
		}
		if (xiezijing.alive())
			xiezijing.setrun();
		if (shejing.alive())
			shejing.setrun();
		if (laoyeye.alive())
			laoyeye.setrun();

	}

	public void fighting(Creature x1, Creature x2) {
		
		
		if (x1.isFighting() || x2.isFighting() || !x1.alive() || !x2.alive())
			return;
		//System.out.println(x1.name()+" fighting with "+x2.name());
		x1.fight();
		x2.fight();
		if(record_ornot)
		  saver.save(x1.name()+" fighting with "+x2.name()+"\n");		
		x1.stop(2000);
		x2.stop(2000);
		Random rand = new Random();
		int win_lose = rand.nextInt(10);
		if (x1 instanceof shejing) {
			if (win_lose < 6)
				x1.loseLife();
			else
				x2.loseLife();
		} else if (x1 instanceof xiaolouluo) {
			if (win_lose < 6)
				x1.loseLife();
			else
				x2.loseLife();
		} else if (x1 instanceof xiezijing) {
			if (win_lose < 6)
				x1.loseLife();
			else
				x2.loseLife();

		} else if (x1 instanceof Player) {
			if (win_lose >= 6)
				x1.loseLife();
			else
				x2.loseLife();
		}

		else if (x1 instanceof laoyeye) {
			if (win_lose >= 6)
				x1.loseLife();
			else
				x2.loseLife();

		}
		if(record_ornot) {
			if(x1.alive())
			{
			  saver.save(x1.name()+" win "+x2.name()+" death\n");
			}
			else
			{
			  saver.save(x2.name()+" win "+x1.name()+" death\n");
			}
		}
		if(x1.alive())
			x1.peace();
		else
			x2.peace();
		
	}

	public synchronized boolean meet_ornot(int nx, int ny) {
		boolean symbol = false;

		for (int i = 0; i < 7; i++) {
			if (player[i].alive() && player[i].x() == nx && player[i].y() == ny) {
				symbol = true;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (xiaolouluo[i].alive() && xiaolouluo[i].x() == nx && xiaolouluo[i].y() == ny) {
				symbol = true;
			}
		}
		if (shejing.alive() && shejing.x() == nx && shejing.y() == ny) {
			symbol = true;
		}
		if (xiezijing.alive() && xiezijing.x() == nx && xiezijing.y() == ny) {
			symbol = true;
		}
		if (laoyeye.alive() && laoyeye.x() == nx && laoyeye.y() == ny) {
			symbol = true;
		}
		return symbol;

	}

	public boolean judgement_enemy(Creature move_thing, int nx, int ny) {

		boolean symbol = false;
		if (move_thing instanceof shejing) {
			for (int i = 0; i < 7; i++) {

				if (player[i].alive() && player[i].x() == nx && player[i].y() == ny) {
					symbol = true;
					fighting(move_thing, player[i]);
				}
			}
			if (laoyeye.alive() && laoyeye.x() == nx && laoyeye.y() == ny) {
				symbol = true;
				fighting(move_thing, laoyeye);
			}

		} else if (move_thing instanceof xiaolouluo) {
			for (int i = 0; i < 7; i++) {
				if (player[i].alive() && player[i].x() == nx && player[i].y() == ny) {
					symbol = true;
					fighting(move_thing, player[i]);
				}
			}
			if (laoyeye.alive() && laoyeye.x() == nx && laoyeye.y() == ny) {
				symbol = true;
				fighting(move_thing, laoyeye);
			}
		} else if (move_thing instanceof xiezijing) {
			for (int i = 0; i < 7; i++) {
				if (player[i].alive() && player[i].x() == nx && player[i].y() == ny) {
					symbol = true;
					fighting(move_thing, player[i]);
				}
			}
			if (laoyeye.alive() && laoyeye.x() == nx && laoyeye.y() == ny) {
				symbol = true;
				fighting(move_thing, laoyeye);
			}

		} else if (move_thing instanceof Player) {
			for (int i = 0; i < 6; i++) {
				if (xiaolouluo[i].alive() && xiaolouluo[i].x() == nx && xiaolouluo[i].y() == ny) {
					symbol = true;
					fighting(move_thing, xiaolouluo[i]);
				}
			}
			if (xiezijing.alive() && xiezijing.x() == nx && xiezijing.y() == ny) {
				symbol = true;
				fighting(move_thing, xiezijing);
			}

			if (shejing.alive() && shejing.x() == nx && shejing.y() == ny) {
				symbol = true;
				fighting(move_thing, shejing);
			}
		}

		else if (move_thing instanceof laoyeye) {
			for (int i = 0; i < 6; i++) {
				if (xiaolouluo[i].alive() && xiaolouluo[i].x() == nx && xiaolouluo[i].y() == ny) {
					symbol = true;
					fighting(move_thing, xiaolouluo[i]);
				}
			}
			if (shejing.alive() && shejing.x() == nx && shejing.y() == ny) {
				symbol = true;
				fighting(move_thing, shejing);
			}
			if (xiezijing.alive() && xiezijing.x() == nx && xiezijing.y() == ny) {
				symbol = true;
				fighting(move_thing, xiezijing);
			}

		}

		return symbol;

	}

	public void check_enmey(Creature crea) {
		boolean find_flag=false;
		if (crea.get_team() == 0) {
			if (shejing.alive()) {
				find_flag=true;
				find(crea, shejing);
			} else if (xiezijing.alive()) {
				find_flag=true;
				find(crea, xiezijing);
			} else {
				for (int i = 0; i < 6; i++) {
					if (xiaolouluo[i].alive()) {
						find_flag=true;
						find(crea, xiaolouluo[i]);
						break;
					}

				}
			}
		} else {
			if (laoyeye.alive()) {
				find_flag=true;
				find(crea, laoyeye);
			} else {
				for (int i = 0; i < 7; i++) {
					if (player[i].alive()) {
						find_flag=true;
						find(crea, player[i]);
						break;
					}
				}

			}
		}
		if(!find_flag)
			completed=true;
	}

	public void find(Creature finder, Creature finded) {
		if (((Thing2D) finded).x() > ((Thing2D) finder).x()) {
			finder.move_right();

		} else if (((Thing2D) finded).x() < ((Thing2D) finder).x()) {
			finder.move_left();
		}

		else if (((Thing2D) finded).y() > ((Thing2D) finder).y()) {
			finder.move_up();
		} else if (((Thing2D) finded).y() < ((Thing2D) finder).y()) {
			finder.move_down();
		}

	}

	public void buildWorld(Graphics g) {

		g.setColor(new Color(250, 240, 170));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		URL loc = this.getClass().getClassLoader().getResource("background1.png");
		ImageIcon bg = new ImageIcon(loc);
		g.drawImage(bg.getImage(), 0, 0, this);
		ArrayList world = new ArrayList();

		for (int i = 0; i < 7; i++) {
			world.add(player[i]);
		}
		world.add(laoyeye);
		world.add(shejing);
		world.add(xiezijing);
		for (int i = 0; i < 6; i++) {
			world.add(xiaolouluo[i]);
		}

		for (int i = 0; i < world.size(); i++) {

			Thing2D item = (Thing2D) world.get(i);
			//if (((Creature) item).alive()) {
				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			//}
			//
			// }
			// else {
			// //g.drawImage(item.getImage(), item.x(), item.y(), this);
			// }

			if (completed) {
				g.setColor(new Color(0, 0, 0));
				g.drawString("Completed", 25, 20);
				//saver.closeFile();
				//reader.closeFile();
			}

		}
	}
    public boolean read_record()
    {
    	String line=reader.read();
    	if(line==null)
    		return false;
        if(line.indexOf("death")!=-1)
        {  
        	Creature t1=null,t2=null;
        	System.out.println(line);
        	String x1 =line.split(" ")[0];
        	String x2 =line.split(" ")[2];
        	System.out.println(x1+" win "+x2+" death");
        	for(int i=0;i<7;i++)
        	{
        		if(x1.equals("player["+i+"]"))
        		{
        			t1=player[i];
        			break;
        		}
        		else if(x2.equals("player["+i+"]"))
        		{
        			t2=player[i];
        			break;
        		}
        	}
        	for(int i=0;i<6;i++)
        	{
        		if(x1.equals("xiaolouluo["+i+"]"))
        		{
        			t1=xiaolouluo[i];
        			break;
        		}
        		else if(x2.equals("xiaolouluo["+i+"]"))
        		{
        			t2=xiaolouluo[i];
        			break;
        		}
        	}
        	if(x1.equals("xiezijing"))
        		t1=xiezijing;
        	else if(x2.equals("xiezijing"))
        		t2=xiezijing;
        	
        	if(x1.equals("shejing"))
        		t1=shejing;
        	else if(x2.equals("shejing"))
        		t2=shejing;
        	
        	if(x1.equals("laoyeye"))
        		t1=laoyeye;
        	else if(x2.equals("laoyeye"))
        		t2=laoyeye;
        	
        	t1.peace();
            t2.loseLife();
        	
        }
        else if(line.indexOf("fight")!=-1)
        {
        	Creature t1=null,t2=null;
        	String x1 =line.split(" ")[0];
        	String x2 =line.split(" ")[3];
        	System.out.println(x1+" "+x2);
        	for(int i=0;i<7;i++)
        	{
        		if(x1.equals("player["+i+"]"))
        		{
        			t1=player[i];
        			break;
        		}
        		else if(x2.equals("player["+i+"]"))
        		{
        			t2=player[i];
        			break;
        		}
        	}
        	for(int i=0;i<6;i++)
        	{
        		if(x1.equals("xiaolouluo["+i+"]"))
        		{
        			t1=xiaolouluo[i];
        			break;
        		}
        		else if(x2.equals("xiaolouluo["+i+"]"))
        		{
        			t2=xiaolouluo[i];
        			break;
        		}
        	}
        	if(x1.equals("xiezijing"))
        		t1=xiezijing;
        	else if(x2.equals("xiezijing"))
        		t2=xiezijing;
        	
        	if(x1.equals("shejing"))
        		t1=shejing;
        	else if(x2.equals("shejing"))
        		t2=shejing;
        	
        	if(x1.equals("laoyeye"))
        		t1=laoyeye;
        	else if(x2.equals("laoyeye"))
        		t2=laoyeye;
          
        	//fighting(t1,t2);
        	t1.fight();
        	t2.fight();
        	repaint();
        	try {
        		Thread.sleep(1000);
        	}catch(Exception e) {
        		
        	}
        	
        }
        else 
        {
        	String temp =line.split(" ")[0];
        	String x=line.split(" ")[1];
        	String y=line.split(" ")[2];
            
        	for(int i=0;i<7;i++)
        	{
        		if(temp.equals("player["+i+"]"))
        		 player[i].move((Integer.valueOf(x)-player[i].x())/50, (Integer.valueOf(y)-player[i].y())/50);
        	}
        	for(int i=0;i<6;i++)
        	{
        		if(temp.equals("xiaolouluo["+i+"]"))
        		 xiaolouluo[i].move((Integer.valueOf(x)-xiaolouluo[i].x())/50, (Integer.valueOf(y)-xiaolouluo[i].y())/50);
        	}
        	if(temp.equals("laoyeye"))
        	{
        		 laoyeye.move((Integer.valueOf(x)-laoyeye.x())/50, (Integer.valueOf(y)-laoyeye.y())/50);
        	}
        	else if(temp.equals("shejing"))
        	{
        		 shejing.move((Integer.valueOf(x)-shejing.x())/50, (Integer.valueOf(y)-shejing.y())/50);
        	}
        	else if(temp.equals("xiezijing"))
        	{
        		xiezijing.move((Integer.valueOf(x)-xiezijing.x())/50, (Integer.valueOf(y)-xiezijing.y())/50);
        	}
        	
        	
        }
        repaint();
    	 return true;
    }
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buildWorld(g);
	}

	class TAdapter extends KeyAdapter {
      List<Thread> t_list=new ArrayList<Thread>();
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (completed) {
	            if (key == KeyEvent.VK_A) {
	            	JFileChooser jFileChooser = new JFileChooser(".");
		            int i = jFileChooser.showDialog(new JLabel(), "选择");
		            if(i== jFileChooser.APPROVE_OPTION){ //打开文件
		                String path = jFileChooser.getSelectedFile().getAbsolutePath();
		                String name = jFileChooser.getSelectedFile().getName();
		                saver.saveToFile(path);
		                System.out.println("当前文件路径："+path+";\n当前文件名："+name);
		            }else{
		                System.out.println("没有选中文件");
		            }
				}
			}

			
			if (key == KeyEvent.VK_L) {
				JFileChooser jFileChooser = new JFileChooser(".");
	            int i = jFileChooser.showDialog(new JLabel(), "选择");
	            if(i== jFileChooser.APPROVE_OPTION){ //打开文件
	                String path = jFileChooser.getSelectedFile().getAbsolutePath();
	                String name = jFileChooser.getSelectedFile().getName();
	                reader=new Reader(path);
	                record_ornot=false;
	                new Thread(record_reader).start();
	                System.out.println("当前文件路径："+path+";\n当前文件名："+name);
	            }else{
	                System.out.println("没有选中文件");
	            }
			}

			else if (key == KeyEvent.VK_SPACE) {
				Thread temp;
				for (int i = 0; i < 7; i++) {
					temp=new Thread(player[i]);
					t_list.add(temp);
					temp.start();
				}
				for (int i = 0; i < 6; i++) {
					temp=new Thread(xiaolouluo[i]);
					t_list.add(temp);
					temp.start();
				}
				temp=new Thread(laoyeye);
				t_list.add(temp);
				temp.start();
				temp=new Thread(xiezijing);
				t_list.add(temp);
				temp.start();
				temp=new Thread(shejing);
				t_list.add(temp);
				temp.start();


			} else if (key == KeyEvent.VK_R) {
				for(Thread temp:t_list){
					temp.stop();
				}
				restartLevel();
			}

			repaint();
		}
	}

	public void restartLevel() {


		initWorld();
		if (completed) {
			completed = false;
		}
	}
}