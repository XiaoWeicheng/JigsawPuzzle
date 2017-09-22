package panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import button.PuzzleButton;

import window.Window;

public class PuzzlePanel extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	static final private int [][] DIRECTION;

	static {
		DIRECTION = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	}

	private Window window;
	private Point blankPos;
	private Rectangle rectangle;
	private PuzzleButton[][] buttons;
	private int n;

	public PuzzlePanel(Window window) {
		super(null);
		blankPos = null;
		buttons = null;
		this.window=window;
	}

	public void init(int n, BufferedImage srcImage) {
		this.n=n;
		this.buttons= PuzzleButton.getButtonMatrix(srcImage,n);
		disorderButtons();
		for(int i=0;i<n;++i) {
			for(int j=0;j<n;++j) {
				System.out.println(i+","+j+" "+buttons[i][j].getPos());
				add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
				if(buttons[i][j].isBlank()){
					blankPos=buttons[i][j].getPos();
				}
			}
		}
		rectangle=new Rectangle(0,0,n,n);
	}

	private void disorderButtons(){
		int times=n * n;
		int a,b,c,d;
		PuzzleButton pb;
		for (int i = 0; i < times; i++) {
			a=(int)(Math.random()* n * n);
			b=(int)(Math.random()* n * n);
			c=a%n;
			d=b%n;
			a/=n;
			b/=n;
			pb=buttons[a][c];
			buttons[a][c]=buttons[b][d];
			buttons[b][d]=pb;
			System.out.println("("+a+","+c+"),("+b+","+d+")");
			System.out.println(buttons[a][c].getPos()+","+buttons[b][d].getPos());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Point clickPos=blankPos;
		boolean badPos=true;
		PuzzleButton pb;
		for(int i=0;i<4;++i){
			clickPos=blankPos;
			clickPos.translate(DIRECTION[i][0],DIRECTION[i][1]);
			if(rectangle.contains(clickPos)) {
				if (e.getSource().equals(buttons[clickPos.x][clickPos.y])) {
					badPos = false;
					break;
				}
			}
		}
		if(badPos)return ;
		if(!window.isStarted()){
			window.start();
		}
		buttons[blankPos.x][blankPos.y].swapPos(buttons[clickPos.x][clickPos.y]);
		pb=buttons[blankPos.x][blankPos.y];
		buttons[blankPos.x][blankPos.y]=buttons[clickPos.x][clickPos.y];
		buttons[clickPos.x][clickPos.y]=pb;
		blankPos=clickPos;
		if(checkOK()){
			window.OKAction();
		}
	}

	private boolean checkOK(){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(buttons[i][j].getPos()!=new Point(i,j)){
					return false;
				}
			}
		}
		return true;
	}
}
