package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class shejing extends Thing2D implements Runnable, Creature {
	private Field field;
	private boolean run;
	private boolean alive;
	private boolean fight_ornot;
	private boolean fighting;
    private String name;
	int team;

	public shejing(int x, int y, Field field,String name) {
		super(x, y);
		fight_ornot = false;
		alive = true;
		this.name=name;
		team = 1;
		run = true;
		this.field = field;
		fighting = false;
		URL loc = this.getClass().getClassLoader().getResource("shejing.png");
		ImageIcon iia = new ImageIcon(loc);
		Image image = iia.getImage();
		this.setImage(image);
	}
//	public int flag() {
//		return -1;
//	}
	public int get_team() {
		return team;
	}
	public String name()
	{
	    	return name;
	}
	public boolean alive() {
		return alive;
	}

	public boolean isFighting() {
		return fighting;
	}

	public void setFighting(boolean fighting) {
		this.fighting = fighting;
	}

	public void fight() {
		URL loc = this.getClass().getClassLoader().getResource("shejing_fight.png");
		ImageIcon iia = new ImageIcon(loc);
		Image image = iia.getImage();
		this.setImage(image);
		fight_ornot = true;
		fighting = true;
	}

	public void peace() {
		URL loc = this.getClass().getClassLoader().getResource("shejing.png");
		ImageIcon iia = new ImageIcon(loc);
		Image image = iia.getImage();
		this.setImage(image);
		fighting = false;
	}

	public void stop(int time) {
		try {

			Thread.sleep(time);
			this.field.repaint();

		} catch (Exception e) {

		}
	}

	public void move_right() {
		this.move(1, 0);
	}

	public void move_left() {
		this.move(-1, 0);
	}

	public void move_up() {
		this.move(0, 1);
	}

	public void move_down() {
		this.move(0, -1);
	}

	public void loseLife() {
//		if(field.record_ornot())
//		  field.saver_save("shejing"+" death"+"\n");
		URL loc = this.getClass().getClassLoader().getResource("shejing_death.png");
		ImageIcon iia = new ImageIcon(loc);
		Image image = iia.getImage();
		this.setImage(image);
		alive = false;
		run = false;
	}

	public int team() {
		return team;
	}

	public void setrun() {
		run = true;
	}

	public void move(int x, int y) {
		int nx = this.x() + 50 * x;
		int ny = this.y() + 50 * y;
		if (nx < 0 || nx > 950)
			return;
		if (ny < 0 || ny > 500)
			return;
		  if(!field.meet_ornot(nx, ny)) 
	         {
	         	this.setX(nx);
	         	this.setY(ny);
				if (field.record_ornot()) {
					field.saver_save("shejing" + " " + nx + " " + ny + "\n");

					if (!this.fight_ornot)
						field.judgement_enemy(this, nx + 50, ny);
					else {
						field.judgement_enemy(this, nx + 50, ny);
						field.judgement_enemy(this, nx - 50, ny);
						field.judgement_enemy(this, nx, ny + 50);
						field.judgement_enemy(this, nx, ny - 50);
					}
				}
	         }
			else {
				if (field.record_ornot()) {
					if (!this.fight_ornot)
						field.judgement_enemy(this, this.x() + 50, this.y());
					else {
						field.judgement_enemy(this, this.x() + 50, this.y());
						field.judgement_enemy(this, this.x() - 50, this.y());
						field.judgement_enemy(this, this.x(), this.y() + 50);
						field.judgement_enemy(this, this.x(), this.y() - 50);
					}
				}
			}
	}

	public void run() {
		while (run) {
			Random rand = new Random();
			if (!fighting) {
				if (fight_ornot) {
					field.check_enmey(this);
				} else {
					this.move(-1, 0);
				}
			}
			try {

				Thread.sleep(rand.nextInt(1000) + 1000);
				this.field.repaint();

			} catch (Exception e) {

			}
		}
	}
}