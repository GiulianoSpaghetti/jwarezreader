package org.altervista.numerone.JWarez;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	private String versione="0.4";
	private JTextArea testo;
	private JTextField pattern;
	protected JWarezReader reader;
	protected Vector<Integer> righe, colonne;
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
		JPanel p=new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		Scanner scan;
		p.add(new JLabel("Testo da cercare: "), c);
		c.gridx=1;
		p.add(pattern=new JTextField("Crash"), c);
		c.gridy=1;
		c.gridx=0;
		try {
			scan = new Scanner(f);
			reader=new JWarezReader(scan.nextLine());
			righe=WriterParser.explode(scan.nextLine());
			colonne=WriterParser.explode(scan.nextLine());
			reader.EnumerateFiles();
			for (JCheckBox i: reader.getFileList()) {
				p.add(i, c);
				c.gridy++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.add(testo=new JTextArea(80,60), c);
		c.gridy++;
		JButton Cerca=new JButton("Cerca");
		Cerca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (pattern.getText().isEmpty() || pattern.getText()=="") {
					pattern.setText("Il pattern non può essere vuoto");
					return;
				}
				reader.SetPattern(pattern.getText());
				reader.readODS(testo, righe, colonne);
			}
		});
		p.add(Cerca,c);
		add(p);
		pack();
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
