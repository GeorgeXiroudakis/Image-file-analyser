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
	
	public static void main(String[] args) {
		 String path = utils.selectAFolderDialog(JFileChooser.DIRECTORIES_ONLY);
		 File folder = new File(path);
		 System.out.println("\nfollowing files detected:");
		 listFilesForFolder(folder);
		 System.out.println("-----------------------");
		 printNumOfDirAndFil(folder);
		 System.out.println("-----------------------");
		 makeHdml(folder);
		 System.out.println("-----------------------");
		 makeGui(folder);
	 }
 	
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
		System.out.println("There are " + NumOfDir + " Directories.");
		System.out.println("There are " + NumOfFil + " Files.");		
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
		try {
			BufferedWriter HtmlFile = new BufferedWriter(new FileWriter("images.htm"));
			HtmlFile.write(text);
			HtmlFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("HTML made.");
	}
	
	
	public static void makeGui(File folder) {
		for (File fileEntry : folder.listFiles()) {
			 if (fileEntry.isDirectory()) {
				 makeGui(fileEntry);
			}else {
				 images.add(fileEntry);
			}
		 }
		ImagesGui G = new ImagesGui(images);
		System.out.println("Gui created.");
	}

}
