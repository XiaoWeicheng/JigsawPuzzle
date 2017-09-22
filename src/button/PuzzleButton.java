package button;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PuzzleButton extends JButton {

	private Point pos;

	private boolean isBlank;

	private PuzzleButton(){}

	public PuzzleButton(int width, Point point, BufferedImage bufferedImage, boolean b){
		setSize(width,width);
		pos=point;
		isBlank=b;
		setLocation(point.x*width,point.y*width);
		if(bufferedImage!=null){
			setIcon(new ImageIcon(bufferedImage));
			setContentAreaFilled(true);
		}
		setBorder(BorderFactory.createLineBorder(new Color(0xEEEEEE),1));
		setFocusable(false);
		System.out.println(getSize());
    }

	public Point getPos() {
		return pos;
	}

	public boolean isBlank() {
		return isBlank;
	}

	public void swapPos(PuzzleButton other){
		Point tmpPos,tmpLocation;
		tmpPos=pos;
		tmpLocation=getLocation();
		pos=other.pos;
		other.pos=tmpPos;
		setLocation(other.getLocation());
		other.setLocation(tmpLocation);
	}

	static public PuzzleButton[][] getButtonMatrix (BufferedImage srcImage, int n){
		List<PuzzleButton[]> puzzleButtonMatrices =new ArrayList<>();
		List<PuzzleButton> puzzleButtonArray =new ArrayList<>();
		PuzzleButton puzzleButton;
		BufferedImage chBImage;
		int chWidth=srcImage.getWidth()/n;
		System.out.println(chWidth);
		for (int i=0;i<n;++i){
			puzzleButtonArray.clear();
			for(int j=0;j<n;++j){
				if(i==n-1 && j==n-1){
					puzzleButton =
							new PuzzleButton(chWidth,new Point(i,j),null,true);
					puzzleButton.setBackground(new Color(100,100,100));
				}
				else{
					chBImage=srcImage.getSubimage(i*chWidth,j*chWidth,chWidth,chWidth);
					puzzleButton =
							new PuzzleButton(chWidth,new Point(i,j),chBImage,false);
				}
				puzzleButtonArray.add(puzzleButton);
			}
			puzzleButtonMatrices.add( puzzleButtonArray.toArray(new PuzzleButton[n]));
		}
		return puzzleButtonMatrices.toArray(new PuzzleButton[n][n]);
	}


}
