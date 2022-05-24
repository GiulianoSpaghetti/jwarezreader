package org.altervista.numerone.JWarez;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8194381088320438542L;
	private JMenuItem esci;
	private JMenuItem informazioni;
	private static String pathOpzioni="JWarezReader.json";
	private static String path;
	private String versione="0.5";
	private JTextArea testo;
	private JTextField pattern;
	protected JWarezReader reader;
	protected static WarezOpzioni wo;
	private static Gson gson = new Gson();
	public MainFrame() {
		super("JWarezReader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		ResourceBundle bundle = ResourceBundle.getBundle("JWarezReader", Locale.getDefault());
		JMenuBar menuBar=new JMenuBar();
		JMenu menuFile=new JMenu(bundle.getString("File"));
		esci=new JMenuItem(bundle.getString("Esci"));
		esci.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispatchEvent(new WindowEvent(MainFrame.this, WindowEvent.WINDOW_CLOSING));
			}});
		menuFile.add(esci);
		JMenu menuInformazioni=new JMenu(bundle.getString("?"));
		informazioni=new JMenuItem(bundle.getString("Informazioni"));
		informazioni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				InformationDialog d=new InformationDialog(MainFrame.this, versione, bundle);
			}	});
		menuInformazioni.add(informazioni);
		menuBar.add(menuFile);
		menuBar.add(menuInformazioni);
		setJMenuBar(menuBar);
		setSize(400, 400);
		setVisible(true);
		path="JWarezReader";
		if (System.getProperty("os.name").contains("Windows"))
			path=System.getenv("APPDATA")+File.separator+path+File.separator+pathOpzioni;		
		else
			path=System.getProperty("user.home")+File.separator+".config"+File.separator+path+File.separator+pathOpzioni;
		try {
			wo=leggiStato();
		} catch (IOException e) {
			wo=new WarezOpzioni();
			wo.path="/home/numerone/Documenti";
			wo.righe="0,1,2,3,4,5,6,7,8,9,10,11,12";
			wo.colonne="1";
		} 
		JOptionDialog d=new JOptionDialog(this, wo, bundle);
		try {
			salvaStato(gson.toJson(wo));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		testo=new JTextArea(80,60);
		reader=new JWarezReader(wo, testo, bundle);
		JPanel p=new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		Scanner scan;
		p.add(new JLabel(bundle.getString("TestoDaCercare")+": "), c);
		c.gridx=1;
		p.add(pattern=new JTextField(""), c);
		c.gridy=1;
		c.gridx=0;
		reader.EnumerateFiles();
		for (JCheckBox i: reader.getFileList()) {
			p.add(i, c);
			c.gridy++;
		}
		c.gridx=1;
		p.add(testo, c);
		c.gridx=0;
		c.gridy++;
		JButton Cerca=new JButton(bundle.getString("Cerca"));
		Cerca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (pattern.getText().isEmpty() || pattern.getText()=="" || pattern.getText()==null) {
					JOptionPane.showMessageDialog(null, bundle.getString("patternVuoto"), bundle.getString("Errore"), JOptionPane.ERROR_MESSAGE);
					return;
				}
				reader.setPattern(pattern.getText());
				reader.readODS();
			}
		});
		p.add(Cerca,c);
		add(p);
		pack();
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
	private static WarezOpzioni leggiStato() throws IOException {
		File f = new File(path);
		WarezOpzioni wo=null;
		if (!f.exists())
			throw new FileNotFoundException();
		else {
			JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			wo=gson.fromJson(reader, WarezOpzioni.class);
		}
		return wo;
	}
	
	private static void salvaStato(String stato) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			File d = new File(f.getParent());
			if (!f.exists()) {
				d.mkdirs();
			}
			f.createNewFile();
		}
 
		BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile(), false));
		bw.write(stato.toString());
		bw.close();
 
	}
}
