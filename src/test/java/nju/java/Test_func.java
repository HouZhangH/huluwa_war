package nju.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_func {

	@Test
	public void testJudgement_enemy() {
		Field field=new Field();
		Player huluwa= new Player(650 , 150  ,field, 0,"huluwa"); 
		boolean res1=field.judgement_enemy(huluwa, 700, 150);
		Player huluwa2=new Player(200,100,field,0,"huluwa2");
		boolean res2=field.judgement_enemy(huluwa2, 150, 100);
		
		assertEquals(res1,true);
		assertEquals(res2,false);
		
	}

}
