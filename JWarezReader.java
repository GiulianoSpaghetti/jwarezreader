package org.altervista.numerone.JWarez;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class JWarezReader {

 private String path;
 private ArrayList<File> FileList;
 private String Pattern;
 
 public JWarezReader(String p) {
	 path=p;
 }
 
 public void SetPattern(String p) {
	 Pattern=p;
 }
 public String GetPattern() {
	 return Pattern;
 }
 
  public void readODS()
  {
    Sheet sheet;
    boolean found=false;
    int ColCount=0;
    int RowCount=0;
    int RowIndex = 0;
    int ColIndex = 0;
    MutableCell cell = null;
    try {
         //Getting the 0th sheet for manipulation| pass sheet name as string
    	for (File f: FileList) {
         sheet = SpreadSheet.createFromFile(f).getSheet(0);

         //Get row count and column count
         ColCount = sheet.getColumnCount();
         RowCount = sheet.getRowCount();
         System.out.println("Ricerca nel file: "+f.getName() + ". Il file contiene "+ RowCount + " righe e "+ ColCount + " colonne.");
         found=false;
         //Iterating through each row of the selected sheet
         for(RowIndex=0; RowIndex < RowCount; RowIndex++)
         {
           //Iterating through each column
           ColIndex = 0;
           for( ;ColIndex < ColCount; ColIndex++)
           {
             cell = sheet.getCellAt(ColIndex, RowIndex);
             if (cell.getTextValue().toLowerCase().contains(Pattern)) {
             System.out.println("Riga: "+RowIndex+" "+ "Colonna: "+ ColIndex+ " Valore: "+ cell.getTextValue()+ " ");
             found=true;
             }
            }
          }
         if (!found)
        	 System.out.println("Il dato non è stato trovato.");
    	}
    } catch (IOException e) {
            e.printStackTrace();
          }


  }

  public static void Intestazione(String vers) {
		System.out.println("JWarezReader "+vers+" by numerone: tutti i diritti riservati\nNasce da una idea di Antonio Adamo di Warez. Via solimena 25\nQuesto programma è in versione 0.1\nE' sotto licenza GPL v3 o, secondo la tua opinione qualsiasi versione successiva");
  }
  
  public void EnumerateFiles() {
	    File directory = new File(path);
		
	    FileFilter odsFileFilter = new FileFilter() {
	        public boolean accept(File directory) {
	            return directory.getName().endsWith(".ods");
	        }
	    };
			
	    File[] files = directory.listFiles(odsFileFilter);
	    if (files==null || files.length==0) {
			  System.out.println("Controllare la Path. Il programma termina");
			  System.exit(0);
	    }
	    FileList = new ArrayList<File>(files.length);
	    for (File file : files) {
	    	FileList.add(file);
	    }
  }
  
  public void PrintFiles() {
	  if (FileList==null || FileList.size()==0) {
		  System.out.println("Nessun file trovato. Il programma termina");
		  System.exit(0);
	  }
	  System.out.println("Verranno caricati i seguenti files");
	  for (File f: FileList) {
		  System.out.println(""+f.getName());
	  }
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