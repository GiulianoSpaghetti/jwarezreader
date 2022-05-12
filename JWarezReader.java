package org.altervista.numerone.JWarez;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class JWarezReader {

 private ArrayList<File> FileList;
 private ArrayList<JCheckBox> FileBoxed;
 private String pattern;
 private WarezOpzioni w;
 private JTextArea testo;
 private ResourceBundle bundle;
 
 public JWarezReader(WarezOpzioni wo, JTextArea t, ResourceBundle bundle) {
	 w=wo;
	 testo=t;
	 this.bundle=bundle;
 }
 

 
  public void readODS() {
         //Getting the 0th sheet for manipulation| pass sheet name as string
    	int i;
    	Vector<Integer>righe=WriterParser.explode(w.righe);
    	Vector<Integer>colonne=WriterParser.explode(w.colonne);
    	for (i=0; i<FileList.size(); i++) {
    		if (!FileBoxed.get(i).isSelected())
    			continue;
    		WarezThread t=new WarezThread(FileList.get(i), testo, colonne, righe, pattern, bundle);
    		t.run();
    	}
  }

  public static void Intestazione(String vers) {
		System.out.println("JWarezReader "+vers+" by numerone: tutti i diritti riservati\nNasce da una idea di Antonio Adamo di Warez. Via solimena 25\nQuesto programma è in versione 0.1\nE' sotto licenza GPL v3 o, secondo la tua opinione qualsiasi versione successiva");
  }
  
  public void EnumerateFiles() {
	    File directory = new File(w.path);
		
	    FileFilter odsFileFilter = new FileFilter() {
	        public boolean accept(File directory) {
	            return directory.getName().endsWith(".ods");
	        }
	    };
			
	    File[] files = directory.listFiles(odsFileFilter);
	    if (files==null || files.length==0) {
			  JOptionPane.showMessageDialog(null, bundle.getString("pathODSVuota"), bundle.getString("Errore"), JOptionPane.ERROR_MESSAGE);
			  System.exit(0);
	    }
	    FileList = new ArrayList<File>(files.length);
	    FileBoxed=new ArrayList<JCheckBox>(files.length);
	    for (File file : files) {
	    	FileList.add(file);
	    	FileBoxed.add(new JCheckBox(file.getName(), true));
	    }
  }
  
  public void PrintFiles() {
	  if (FileList==null || FileList.size()==0) {
		  System.out.println(bundle.getString("FileNonTrovato"));
		  System.exit(0);
	  }
	  System.out.println("CaricaFile");
	  for (File f: FileList) {
		  System.out.println(""+f.getName());
	  }
  }
  
  public ArrayList<JCheckBox> getFileList() {
	  return FileBoxed;
  }
  

  public void setPattern(String p) {
	  pattern=p.toLowerCase();
  }
  
  public String getPattern() {
	  return pattern;
  }
  /*  public static void main(String[] args) {
	  	Intestazione("0.1");
	  	System.out.print("Inserire la path dei files: ");
	  	Scanner scan=new Scanner(System.in);
	  	JWarezReader reader=new JWarezReader(scan.nextLine());
	  	reader.EnumerateFiles();
	  	reader.PrintFiles();
	  	System.out.print("Indicare il pattern di ricerca: ");
	  	reader.SetPattern(scan.nextLine().toLowerCase());
	  	reader.readODS();
  }*/
}