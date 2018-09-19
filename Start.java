import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Start{
	private JFrame frame;
	private JPanel container, buttonPanel, computer, strategy;
	private JRadioButton button1, button2;
	private ButtonGroup group;
	private JLabel l1, l2, label;
	private JTextField t1,t2;
	private JComboBox strategies;
	
	private Boolean easy = true;
	private Boolean hidden = true;
	
	Start(){
		frame = new JFrame("Pong");
		
		container = new JPanel();
		container.setBackground(Color.BLACK);
		container.setPreferredSize(new Dimension(500,500));
		container.setLayout(new FlowLayout());
		JLabel title = new JLabel("PONG");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.RED);
		title.setFont(new Font("Serif", Font.PLAIN, 50));
		container.add(title, container.CENTER_ALIGNMENT);
		
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500,50));
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		button1 = new JRadioButton("One Player", true);
		button1.setBackground(Color.BLACK);
		button1.setForeground(Color.RED);
		button1.setActionCommand("button one");
		button1.addActionListener(new RadioListener());
		button2 = new JRadioButton("Two Player");
		button2.setBackground(Color.BLACK);
		button2.setForeground(Color.RED);
		button2.setActionCommand("button two");
		button2.addActionListener(new RadioListener());
		
		group = new ButtonGroup();
		group.add(button1);
		group.add(button2);
		
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPanel.add(button1);
		buttonPanel.add(Box.createRigidArea(new Dimension(200, 0)));
		buttonPanel.add(button2);
	
		computer = new JPanel();
		computer.setPreferredSize(new Dimension(450,100));
		computer.setLayout(new GridLayout(2,2));
		computer.setBackground(Color.BLACK);
		l1 = new JLabel("Name: ");
		l1.setForeground(Color.WHITE);
		t1 = new JTextField("Player 1");
		l2 = new JLabel("Name: ");
		l2.setForeground(Color.WHITE);
		t2 = new JTextField("Player 2");
		l2.setVisible(false);;
		t2.setVisible(false);
		computer.add(l1); computer.add(t1); computer.add(l2); computer.add(t2);
		
		strategy = new JPanel();
		strategy.setVisible(true);
		strategy.setPreferredSize(new Dimension(500,50));
		strategy.setBackground(Color.BLACK);
		strategies = new JComboBox(new String[]{"Easy Strategy","Hard Strategy"});
		strategies.addActionListener(new StrategyListener());
		strategy.add(strategies);
		
		
		
		label = new JLabel("Strategy");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		
		JButton start = new JButton("Start");
		start.setBackground(Color.BLACK);
		start.setBackground(Color.RED);
		start.setActionCommand("start");
		start.addActionListener(new StartListener());
	
		container.add(buttonPanel);
		container.add(computer);
		container.add(label);
		container.add(strategy);
		container.add(Box.createRigidArea(new Dimension(500,100)));
		container.add(start);
		
		frame.setContentPane(container);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void runGUI(){
		Start test = new Start();
	}
	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				runGUI();
			}
		});
	}
	
	class StrategyListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JComboBox cb = (JComboBox)event.getSource();
	        String command = (String)cb.getSelectedItem();
			if(command.equals("Easy Strategy")){
				easy = true;
			}else if(command.equals("Hard Strategy")){
				easy = false;
			}
		}
	}
	
	class RadioListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if(command.equals("button two")){
				l2.setVisible(true);
				t2.setVisible(true);
				strategy.setVisible(false);
				label.setVisible(false);
				hidden = false;
			}else if(command.equals("button one")){
				l2.setVisible(false);
				t2.setVisible(false);
				strategy.setVisible(true);
				label.setVisible(true);
				hidden = true; 
			}	
		}
	}
	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand().equals("start")){
				frame.dispose();
				ArrayList<HumanPaddle> a = new ArrayList<HumanPaddle>();
				if(hidden){
					if(easy){
						a.add(new EasyAIPaddle(1, "CPU"));
					}else{
						a.add(new HardAIPaddle(1, "CPU"));
					}
				}else{
					a.add(new HumanPaddle(1, t2.getText()));
				}
				a.add(new HumanPaddle(2,t1.getText()));
				Window window = new Window(a);
				window.start();
			}
		}
	}

	
}
