package panel;

import window.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class RightPanel extends Panel implements ActionListener{

	static private Integer[] steps;

	private int n=3;
	private Window window;
	private PuzzlePanel puzzlePanel;
	private JButton pauseButton,resetButton,exitButton;
	private JLabel step;
	private JComboBox stepCombo;

	static {
		steps = new Integer[]{3, 4, 5};
	}

	public RightPanel(Window window) {
		super(null);
		this.window=window;
		puzzlePanel=new PuzzlePanel(window);
		puzzlePanel.setLocation(50,50);
		puzzlePanel.setSize(400,400);
		add(puzzlePanel);

		step=new JLabel("阶:", JLabel.CENTER);
		step.setLocation(100,470);
		step.setSize(50,30);
		add(step);

		stepCombo=new JComboBox(steps);
		stepCombo.setEditable(false);
		stepCombo.setLocation(150,470);
		step.setSize(50,30);
		add(stepCombo);

		resetButton=new JButton("重置");
		stepCombo.setLocation(300,470);
		step.setSize(100,30);
		add(resetButton);

		pauseButton=new JButton("暂停");
		pauseButton.setLocation(100,520);
		step.setSize(100,30);
		add(pauseButton);

		exitButton=new JButton("退出");
		stepCombo.setLocation(300,520);
		step.setSize(100,30);
		add(exitButton);
	}

	public void init(BufferedImage srcImage){

		puzzlePanel.init(n,srcImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(stepCombo)||e.getSource().equals(resetButton)){
			if(e.getSource().equals(stepCombo)){
				if(n==(Integer)(stepCombo.getSelectedItem()))
					return ;
				n=(Integer)(stepCombo.getSelectedItem());
			}
			window.init();
		}
		else if(e.getSource().equals(pauseButton)){
			window.pause();
		}
		else if(e.getSource().equals(exitButton)){
			window.setVisible(false);
			System.exit(0);
		}
	}
}
