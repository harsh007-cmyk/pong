package pang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class score {
	static int Game_width;
	static int Game_height;
	int player1;
	int player2;
	 score(int Game_width,int Game_Height) {
	// TODO Auto-generated constructor stub
		 score.Game_width=Game_width;
		 score.Game_height=Game_Height;
}
	 public void draw(Graphics g) {
		 g.setColor(Color.white);
		 g.setFont(new Font("Consolas", Font.PLAIN,60));
		 g.drawLine(Game_width/2, 0, Game_width/2, Game_height);
		 g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (Game_width/2)-85,50);
		 g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (Game_width/2)+20, 50);
	 }
}