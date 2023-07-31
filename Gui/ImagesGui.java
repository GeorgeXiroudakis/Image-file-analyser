package csd.uoc.gr.A24.Gui;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImagesGui extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static List<File> Images;
	static List<JButton> buttons = new ArrayList<>();
	int cur = 0;
	public ImagesGui(List<File> images) {
		Images = images;
		setTitle ("252-2022: A4");
		setBounds(200,100,800,600); //x, y, width, height)
		setLayout(new GridLayout(0,4)); // rows, columns
		
		for(cur = 0; cur < Images.size(); cur++) {
			try {
				String s = "";
				s += Images.get(cur).getPath();
				JButton button = new JButton(s);
				buttons.add(button);
				buttons.get(cur).addActionListener(this);
				add(buttons.get(cur));
				JLabel label = new JLabel();
				add(label);
				BufferedImage myPicture = ImageIO.read(new File(s));
				label.setIcon(new ImageIcon(myPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int i;
		for(i = 0; i < buttons.size()-1; i++) {
			if (e.getSource() == buttons.get(i))break;
		}
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(new File(Images.get(i).getPath()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Opened :" +Images.get(i).getName());
	}	
}
