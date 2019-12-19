package superDownloader163.hugesea.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import mdlaf.MaterialLookAndFeel;

public class View {
	public static void main(String[] args) {
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel (new MaterialLookAndFeel ());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}
		
        JFrame frame = new JFrame("superDownloader163");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        JPanel panel = new JPanel();    
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    @SuppressWarnings("deprecation")
	private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        JLabel labelCode = new JLabel("Song:");
        labelCode.setBounds(100,40,50,40);
        labelCode.setFont(new Font("Monaco",Font.BOLD,14));
        panel.add(labelCode);
        
        JTextField fieldSongCode = new JTextField();
        fieldSongCode.setBounds(160,40,300,40);
        fieldSongCode.setFont(new Font("Monaco",Font.ITALIC,14));
        panel.add(fieldSongCode);
        
        JLabel labelSongName = new JLabel("Name:");
        labelSongName.setBounds(100,85,50,40);
        labelSongName.setFont(new Font("Monaco",Font.BOLD,14));
        panel.add(labelSongName);
        
        JTextField fieldSongName = new JTextField();
        fieldSongName.setBounds(160,85,300,40);
        fieldSongName.setFont(new Font("宋体",Font.ITALIC,14));
        fieldSongName.disable();
        panel.add(fieldSongName);
        
        JLabel labelSongAuthor = new JLabel("Author:");
        labelSongAuthor.setBounds(85,130,70,40);
        labelSongAuthor.setFont(new Font("Monaco",Font.BOLD,14));
        panel.add(labelSongAuthor);
        
        JTextField fieldSongAuthor = new JTextField();
        fieldSongAuthor.setBounds(160,130,300,40);
        fieldSongAuthor.setFont(new Font("宋体",Font.ITALIC,14));
        fieldSongAuthor.disable();
        panel.add(fieldSongAuthor);
        
        JButton buttonCheck = new JButton("Check");
        buttonCheck.setBounds(240,200,100,40);
        buttonCheck.setFont(new Font("Monaco",Font.BOLD,15));
        buttonCheck.setFocusPainted(false);
        buttonCheck.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String SongCode = fieldSongCode.getText();
        		String SongName = superDownloader163.hugesea.handle.Handle.getSongName(SongCode);
        		String SongAuthor = superDownloader163.hugesea.handle.Handle.getSongAuthor(SongCode);
        		
        		fieldSongName.setText(SongName);
        		fieldSongAuthor.setText(SongAuthor);
        	}
        });
        panel.add(buttonCheck);
        
        JButton buttonDownload = new JButton("Download");
        buttonDownload.setBounds(360,200,100,40);
        buttonDownload.setFont(new Font("Monaco",Font.BOLD,15));
        buttonDownload.setFocusPainted(false);
        buttonDownload.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if ((fieldSongName.getText().length()) == 0 || (fieldSongAuthor.getText().length()) == 0) {
        			JOptionPane.showMessageDialog(null, "Please enter the Song Code and try again!", "Error", JOptionPane.ERROR_MESSAGE);
        		} else {
        			buttonCheck.setEnabled(true);
        			superDownloader163.hugesea.download.Download.Download(fieldSongName.getText(),fieldSongAuthor.getText(),fieldSongCode.getText());
        			fieldSongCode.setText("");
        			fieldSongName.setText("");
        			fieldSongAuthor.setText("");
        			JOptionPane.showMessageDialog(null,"download success!");
        		}
        	}
        });
        panel.add(buttonDownload);
        
    }
}
