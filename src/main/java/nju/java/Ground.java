package nju.java;


import javax.swing.JFrame;


public final class Ground extends JFrame {

    private final int OFFSET = 1000;


    public Ground() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,606);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }


    public static void main(String[] args) {
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}