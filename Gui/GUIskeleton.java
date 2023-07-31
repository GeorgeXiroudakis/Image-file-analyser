package csd.uoc.gr.A24.Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;



public class GUIskeleton extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public  GUIskeleton(){
		setTitle ("252-2022: A4");
		setBounds(200,100,800,600); //x, y, width, height)
		setLayout(new GridLayout(0,4)); // rows, columns
		String[] data = {"1", "2","3","4","5", "6","7","8"};
		for (String s: data) {
			JButton btext = new JButton(s);
			btext.addActionListener(this);
			add(btext);
		 }
		 setVisible(true);
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object so = e.getSource();
		if (so instanceof JButton) {
			JButton sj = (JButton) so;
			String sjt = sj.getText();
			System.out.println(sjt);
		}
	}
	public static void main(String[] args) {
		GUIskeleton g = new GUIskeleton();
		
	}

}
