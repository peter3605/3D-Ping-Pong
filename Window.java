import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Window extends JPanel implements ActionListener,KeyListener{
	private static int dx = 300;
	private static int scalar = 120;
	private HumanPaddle p,p1;
	private static Point2D[] tablePoints = new Point2D[8];
	private boolean start=false,bounce=false,hitright,hitleft,serve=true,player1serve=true,player2serve=false,initialturn=true,endgame=false;
	private Timer t = new Timer(15,this);
	private JFrame frame;
	private int servecounter=1;
	private Ball1 b1;
	private boolean space = true;
	
	Window(ArrayList<HumanPaddle> paddles)
	{
		p=paddles.get(0);
		p1=paddles.get(1);
	}
	public void start(){
		createTablePoints();

		b1=new Ball1();
		t.start();
		setFocusable(true);
		frame = new JFrame();
		frame.add(this);
		frame.setSize(1000,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static void createTablePoints(){
		Point3D p1 = new Point3D(3.7,4,1);
		Point3D p2 = new Point3D(1,4,1);
		Point3D p3 = new Point3D(3.7,3.5,1);
		Point3D p4 = new Point3D(1,3.5,1);
		
		Point3D p5 = new Point3D(6,4,9);
		Point3D p6 = new Point3D(1,4,9);
		Point3D p7 = new Point3D(6,3,9);
		Point3D p8 = new Point3D(1,3,9);
		
		Point3D[] points = new Point3D[]{p1,p2,p3,p4,p5,p6,p7,p8};
		Table3D t = new Table3D(points);
		Table2D t1 = new Table2D();
		Point2D[] p = t1.projectPoints(t);
		
		for(int i=0;i<p.length;i++){
			tablePoints[i] = new Point2D((p[i].getX()*scalar)+dx, (p[i].getY()*scalar));
		}
	}

	boolean backwards = false;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);
		fillTable(g);
		drawTable(g);
		drawNet(g);
		g.setColor(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Graphics2D g1 = (Graphics2D) g;
		g.setColor(Color.WHITE);
		g.drawString(p1.getName(),350,20);
		g.drawString(p.getName(),550,20);
		g.drawString(""+p.getScore(), 350, 40);
		g.drawString(""+p1.getScore(), 550, 40);
		p.paint(g);
		p1.paint(g);
		b1.paint(g);

	}
	private void drawNet(Graphics g){
		g.setColor(Color.BLUE);
		int x = (int)(tablePoints[7].getX() + tablePoints[1].getX())/2;
		int y = ((int)(tablePoints[7].getY() + tablePoints[1].getY())/2)-30;		
		g.drawRect(x,y,250,20);
		g.fillRect(x, y, 250, 20);
	}
	
	private void drawTable(Graphics g){
		g.setColor(Color.WHITE);
		
		g.drawLine((int)tablePoints[0].getX() ,(int)tablePoints[0].getY() , (int)tablePoints[1].getX() , (int)tablePoints[1].getY() );
		g.drawLine((int)tablePoints[0].getX() ,(int)tablePoints[0].getY() , (int)tablePoints[2].getX() , (int)tablePoints[2].getY() );
		g.drawLine((int)tablePoints[2].getX() ,(int)tablePoints[2].getY() , (int)tablePoints[3].getX() , (int)tablePoints[3].getY() );
		g.drawLine((int)tablePoints[3].getX() ,(int)tablePoints[3].getY() , (int)tablePoints[1].getX() , (int)tablePoints[1].getY() );
		g.drawLine((int)tablePoints[6].getX() ,(int)tablePoints[6].getY() , (int)tablePoints[7].getX() , (int)tablePoints[7].getY() );
		g.drawLine((int)tablePoints[7].getX() ,(int)tablePoints[7].getY() , (int)tablePoints[5].getX() , (int)tablePoints[5].getY() );
		
		
		g.drawLine((int)tablePoints[1].getX() ,(int)tablePoints[1].getY() , (int)tablePoints[5].getX() , (int)tablePoints[5].getY() );
		g.drawLine((int)tablePoints[2].getX() ,(int)tablePoints[2].getY() , (int)tablePoints[6].getX() , (int)tablePoints[6].getY() );
		g.drawLine((int)tablePoints[3].getX() ,(int)tablePoints[3].getY() , (int)tablePoints[7].getX() , (int)tablePoints[7].getY() );
		
		g.drawLine((int)(tablePoints[4].getX()+tablePoints[7].getX())/2 ,(int)tablePoints[6].getY() ,((int)tablePoints[0].getX()+(int)tablePoints[1].getX())/2 , (int)tablePoints[2].getY() );

	}
	private void fillTable(Graphics g){
		g.setColor(Color.RED);
		g.fillPolygon(new int[]{(int)tablePoints[5].getX(), (int)tablePoints[1].getX(),(int)tablePoints[3].getX(), (int)tablePoints[7].getX()},new int[]{(int)tablePoints[5].getY(),(int)tablePoints[1].getY(), (int)tablePoints[3].getY(),(int)tablePoints[7].getY()},4);
		g.fillPolygon(new int[]{(int)tablePoints[1].getX(), (int)tablePoints[0].getX(),(int)tablePoints[2].getX(), (int)tablePoints[3].getX()},new int[]{(int)tablePoints[1].getY(),(int)tablePoints[0].getY(), (int)tablePoints[2].getY(),(int)tablePoints[3].getY()},4);
		g.fillPolygon(new int[]{(int)tablePoints[5].getX(), (int)tablePoints[4].getX(),(int)tablePoints[0].getX(), (int)tablePoints[1].getX()},new int[]{(int)tablePoints[7].getY(),(int)tablePoints[7].getY(), (int)tablePoints[2].getY(),(int)tablePoints[1].getY()},4);
	}
	
	private boolean forwards = true;
	private boolean topCollide = false;
	private int counter = 0;
	private int counter1 = 0;
	private int x = 0;
	private int win = 0;
	
	public void actionPerformed(ActionEvent arg0) {
		if (p.getScore()==11||p1.getScore()==11)
		{
			if(p.getScore()==11){
				win = 1;
			}else{
				win = 2;
			}
			end++;
			endgame();
		}
		else {
			if (serve)
			{
				if (player2serve)
				{
					b1.setxPos(450);
					b1.setyPos(105);
					b1.setxVel(0);
					b1.setyVel(0);
				}
				if (player1serve)
				{
					b1.setxPos(550);
					b1.setyPos(330);
					b1.setxVel(0);
					b1.setyVel(0);
				}	
			}
			else
			{	
				if (initialturn&&player1serve)
				{
					b1.setxVel(-2);
					b1.setyVel(-5);	
					initialturn=false;
				}	
				if (initialturn&&player2serve)
				{
					b1.setxVel(2);
					b1.setyVel(5);	
					initialturn=false;
				}
				
				if (b1.calcIntersect(p, p1)&&!initialturn)
				{
					if (b1.gettopintersect()&&!(p.getPosx()<350||(p.getPosx()>510)))
					{
						 x = b1.generatexVel();
						 counter++;
						 if(counter==1)
						 {
							/*if(p.getPosx()<250||p.getPosx()>700){
								 if(b1.getxVel()<0){
									b1.setxVel(3);
								 }else{
									b1.setxVel(-3);
								 }
									
							}else{*/
								 if(b1.getxVel()<0){
									b1.setxVel(1*x);
								 }else{
									b1.setxVel(-1*x);
								 }
							//}
							 counter = 0;
						 }
						 topCollide = true;
						 b1.setyVel(-1*b1.getyVel());
					}else if(b1.gettopintersect()){
						x = b1.generatexVel();
						counter1++;
						if(counter1==1)
						{				
							if(p.getPosx()<250||p.getPosx()>570){
								 if(b1.getxVel()<0){
									b1.setxVel((int)(Math.random()*2)+3);
								 }else{
									b1.setxVel(-1*((int)(Math.random()*2)+3));
								 }
									
							}else{
								 if(b1.getxVel()<0){
									b1.setxVel(1*x);
								 }else{
									b1.setxVel(-1*x);
								 }
							}
							counter1=0;
						}
						b1.setyVel(-1*b1.getyVel());
						
					}
					if (b1.getbotintersect()&&!(p1.getPosx()<400||(p1.getPosx()>560)))
					{ 
						
						x = b1.generatexVel();
						counter1++;
						if(counter1==1)
						{				
							/*if(p1.getPosx()<250||p1.getPosx()>700){
								 if(b1.getxVel()<0){
									b1.setxVel(-3);
								 }else{
									b1.setxVel(3);
								 }
									
							}else{*/
								 if(b1.getxVel()<0){
									b1.setxVel(-1*x);
								 }else{
									b1.setxVel(1*x);
								 }
							//}
							counter1=0;
						}
						b1.setyVel(-1*b1.getyVel());
					}else if(b1.getbotintersect()){
						x = b1.generatexVel();
						counter1++;
						if(counter1==1)
						{				
							if(p1.getPosx()<300||p1.getPosx()>620){
								 if(b1.getxVel()<0){
									b1.setxVel((int)(Math.random()*2)+3);
								 }else{
									b1.setxVel(-1*((int)(Math.random()*2)+3));
								 }
									
							}else{
								 if(b1.getxVel()<0){
									b1.setxVel(1*x);
								 }else{
									b1.setxVel(-1*x);
								 }
							}
							counter1=0;
						}
						b1.setyVel(-1*b1.getyVel());
						
					}
				}
			}
			b1.move();
			p.move(b1);
			p1.move(b1);
			frame.repaint();
			if (b1.getY()<0)
			{
				space = true;
				p.incrementscore();
				if (servecounter<2)
				{
					p.setxPos(400);
					p1.setxPos(600);
					player1serve=true;
					player2serve=false;
					servecounter++;
				}
				else if (servecounter>=2)
				{
					p.setxPos(400);
					p1.setxPos(600);				
					player2serve=true;
					player1serve=false;
					servecounter++;
					if (servecounter>3)
						servecounter=0;
				}
				serve=true;
			}
			else if (b1.getY()>500)
			{	
				space = true;
				p1.incrementscore();
				if (servecounter<2)
				{
					p.setxPos(400);
					p1.setxPos(600);
					player1serve=true;
					player2serve=false;
					servecounter++;
				}
				else if (servecounter>=2)
				{
					p.setxPos(400);
					p1.setxPos(600);				
					player2serve=true;
					player1serve=false;
					servecounter++;
					if (servecounter>3)
						servecounter=0;
				}
				serve=true;
			}
		}
	}
	

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_A)
		{		
			hitright=true;
			hitleft=false;
			p.moveLeft(true);
			frame.repaint();
		}
		//left arrow key
		if (key==KeyEvent.VK_D)
		{
			hitleft=true;
			hitright=false;
			p.moveRight(true);
			frame.repaint();
		}
		if (key==KeyEvent.VK_LEFT)
		{
			p1.moveLeft(true);
			frame.repaint();
		}
		//left arrow key
		 if (key==KeyEvent.VK_RIGHT)
		{
			p1.moveRight(true);
			frame.repaint();
		}
		if(key == KeyEvent.VK_SPACE){
			if(space){
				space = false;
				serve = false;
				initialturn = true;
				frame.repaint();
			}
		}
	}
	private int end = 0;
	public void endgame()
	{
		if(end==1){
			frame.dispose();
			JFrame endFrame = new JFrame();
			JLabel end = new JLabel("THE END");
			end.setHorizontalAlignment(JLabel.CENTER);
			end.setForeground(Color.RED);
			end.setFont(new Font("Serif", Font.PLAIN, 80));
			JLabel winner = new JLabel();
			if(win==1){
				winner.setText(p1.getName()+" wins!");
			}else{
				winner.setText(p.getName()+" wins!");
			}
			winner.setHorizontalAlignment(JLabel.CENTER);
			winner.setForeground(Color.WHITE);
			winner.setFont(new Font("Serif", Font.PLAIN, 60));
			JLabel score = new JLabel();
			if(win==1){
				score.setText(p.getScore()+" - "+p1.getScore());
			}else{
				score.setText(p1.getScore()+" - "+p.getScore());
			}
			score.setHorizontalAlignment(JLabel.CENTER);
			score.setForeground(Color.WHITE);
			score.setFont(new Font("Serif", Font.PLAIN, 90));
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setPreferredSize(new Dimension(500,500));
			panel.add(end);
			panel.add(winner);
			panel.add(score);
			endFrame.add(panel);
			endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			endFrame.pack();
			endFrame.setVisible(true);
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_A)
		{
			p.moveLeft(false);
			frame.repaint();
		}
		//left arrow key
		if (key==KeyEvent.VK_D)
		{
			p.moveRight(false);
			frame.repaint();
		}
		if (key==KeyEvent.VK_LEFT)
		{
			p1.moveLeft(false);
			frame.repaint();
		}
		//left arrow key
		 if (key==KeyEvent.VK_RIGHT)
		{
			p1.moveRight(false);
			frame.repaint();
		}		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub	
	}
}
