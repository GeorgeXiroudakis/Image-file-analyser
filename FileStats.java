package csd.uoc.gr.A24;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import csd.uoc.gr.A24.Gui.ImagesGui;
import csd.uoc.gr.A24.Utilities.utils;


public class FileStats {
	static int NumOfDir = 0;
	static int NumOfFil = 0;
	static String text = "<html><body>\n";
	static List<File> images = new ArrayList<>();
 	
	public static void listFilesForFolder(File folder) {
		 for (File fileEntry : folder.listFiles()) {
			 if (fileEntry.isDirectory()) {
				 listFilesForFolder(fileEntry);
			}else {
				 System.out.println(fileEntry.getAbsolutePath());
			}
		 }
	}
	
	public static void printNumOfDirAndFil(File folder) {
		for (File fileEntry : folder.listFiles()) {
			 if (fileEntry.isDirectory()) {
				 NumOfDir += 1;
				 printNumOfDirAndFil(fileEntry);
			}else {
				 NumOfFil += 1;
			}
		 }
		//check if we are at the last iteration
		if( folder.listFiles().length == NumOfDir ){
			System.out.println("There are " + NumOfDir + " Directories.");
			System.out.println("There are " + NumOfFil + " Files.");		
		}
	}
	
	public static void makeHdml(File folder){
		
		for (File fileEntry : folder.listFiles()) {
			 if (fileEntry.isDirectory()) {
				 makeHdml(fileEntry);
			}else {
				String extension = "";
				int indexOfDot = fileEntry.getName().lastIndexOf('.');
				if(indexOfDot >= 0)extension = fileEntry.getName().substring(indexOfDot+1);
				if(extension.equals("png") || extension.equals("jpg") || extension.equals("tif") || extension.equals("tiff") || extension.equals("raw")) {
					text += "<img src=\"" + fileEntry.toURI() + "\" " + "alt=\"aa\" " + "height=\"100\">\n";
				}
			}
		}
		//check if we are at the last iteration
		if( folder.listFiles().length == NumOfDir ) {
			try {
				BufferedWriter HtmlFile = new BufferedWriter(new FileWriter("images.htm"));
				HtmlFile.write(text);
				HtmlFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Folder made.");
		}
	}
	
	
	public static void makeGui(File folder) {
		for (File fileEntry : folder.listFiles()) {
			 if (fileEntry.isDirectory()) {
				 makeGui(fileEntry);
			}else {
				 images.add(fileEntry);
			}
		 }
		if( folder.listFiles().length == NumOfDir ) {
			ImagesGui G = new ImagesGui(images);
			System.out.println("Gui created.");
		}
	}

	
	 public static void main(String[] args) {
		 String path = utils.selectAFolderDialog(JFileChooser.DIRECTORIES_ONLY);
		 File folder = new File(path);
		 listFilesForFolder(folder);
		 System.out.println("-----------------------");
		 printNumOfDirAndFil(folder);
		 System.out.println("-----------------------");
		 makeHdml(folder);
		 System.out.println("-----------------------");
		 makeGui(folder);
	 }

}
