package org.altervista.numerone.JWarez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8194381088320438542L;
	private JMenuItem opzioni;
	private JMenuItem esci;
	private JMenuItem informazioni;
	private String pathOpzioni="./JWarezReader.ini";
	private String versione="0.2";
	public MainFrame() {
		super("JWarezReader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar=new JMenuBar();
		JMenu menuFile=new JMenu("File");
		opzioni=new JMenuItem("Opzioni");
		esci=new JMenuItem("Esci");
		esci.addActionListener(this);
		menuFile.add(opzioni);
		opzioni.addActionListener(this);
		menuFile.add(esci);
		JMenu menuInformazioni=new JMenu("?");
		informazioni=new JMenuItem("Informazioni");
		informazioni.addActionListener(this);
		menuInformazioni.add(informazioni);
		menuBar.add(menuFile);
		menuBar.add(menuInformazioni);
		setJMenuBar(menuBar);
		setSize(400, 400);
		setVisible(true);
		File f=new File (pathOpzioni);
		if (!f.exists()) {
			JOptionDialog d=new JOptionDialog(this, new File(pathOpzioni));
			d.CloseFrame();
		}
	}	
	
	public void actionPerformed(ActionEvent e) {
			if (e.getSource()==esci)
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			else if (e.getSource()==opzioni) {
        		JOptionDialog d=new JOptionDialog(this, new File(pathOpzioni));
        		d.CloseFrame();
			}
			else if (e.getSource()==informazioni) {
        		InformationDialog d=new InformationDialog(this, versione);
			}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	      		MainFrame f=new MainFrame();
	          }
	      });
	}

	
}
