package window;

import panel.LeftPanel;
import panel.RightPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Window extends JFrame {

	/**
	 * 
	 */
	private RightPanel rightPanel;
	private LeftPanel leftPanel;
	private boolean isStarted;
	private BufferedImage srcImage;
	
	/**
	 * Create the frame.
	 */
	public Window() {
		rightPanel=new RightPanel(this);
		leftPanel=new LeftPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 1000, 600);
		setLayout(new GridLayout(1,2));

		add(leftPanel);
		add(rightPanel);

		setJMenuBar(null);

		try {
			srcImage= ImageIO.read(new File("srcImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void OKAction(){
		leftPanel.stop();
		JDialog OKDialog=new JDialog(this,Dialog.ModalityType.APPLICATION_MODAL);
		OKDialog.setLocation(350,250);
		OKDialog.setSize(300,100);
		OKDialog.setJMenuBar(null);

		JLabel jLabel=new JLabel("恭喜!\n用时:"+leftPanel.getTime(),Label.CENTER);
		jLabel.setSize(200,60);
		jLabel.setLocation(50,0);
		OKDialog.add(jLabel);

		JButton jButton=new JButton("开始");
		jButton.setSize(50,30);
		jButton.setLocation(125,65);
		OKDialog.add(jButton);
		jButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OKDialog.setVisible(false);
				init();
			}
		});
		OKDialog.setVisible(true);
	}

	public void init(){
		isStarted=false;
		rightPanel.init(srcImage);
		leftPanel.init(srcImage);
	}

	public void pause(){
		leftPanel.stop();
		JDialog pauseDialog=new JDialog(this,"暂停中"+ "", Dialog.ModalityType.APPLICATION_MODAL);
		pauseDialog.setLocation(400,250);
		pauseDialog.setSize(200,100);
		pauseDialog.setJMenuBar(null);

		JLabel jLabel=new JLabel("暂停中...",Label.CENTER);
		jLabel.setSize(100,30);
		jLabel.setLocation(50,15);
		pauseDialog.add(jLabel);

		JButton jButton=new JButton("继续");
		jButton.setSize(50,30);
		jButton.setLocation(125,55);
		pauseDialog.add(jButton);
		jButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pauseDialog.setVisible(false);
				start();
			}
		});
		pauseDialog.setVisible(true);
	}
	public void start(){
		isStarted = true;
		leftPanel.start();
	}

	public boolean isStarted() {
		return isStarted;
	}

}
