package nju.java;

public interface Creature  {
	void loseLife();
	void fight();
    boolean isFighting();
    void setFighting(boolean fighting);
	boolean alive();
	int get_team();
	void stop(int time);
	void move_right();
	void move_left();
	void move_up();
	void move_down();
	void peace();
	String name();
//	int flag();
}
