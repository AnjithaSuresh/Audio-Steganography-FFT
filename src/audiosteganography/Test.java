/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audiosteganography;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author kklin
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	
    	Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do? (enter the number)");
        int command = 0;
       
        do
        {
                System.out.println("1 - Encode a text file into an audio file");
                System.out.println("2 - Decode an audio file ");
                System.out.print("> ");
                command = scan.nextInt();
                System.out.println();
       
                if (command == 1)
                        encode();
                if (command == 2)
                        decode();
               
        } while (command != 1 && command != 2);
               
        System.out.println("The End.");
        scan.close();
        
       
    }
    
    public static void encode() {
    	 Scanner scan= new Scanner(System.in);          
         System.out.print("Enter text file name that you want to encode (must be a plain text file)\n> ");
         String textFileName = scan.next(); // The text file to read in
         System.out.println();
         if (!textFileName.endsWith(".txt")) // Check if the filename ends with .txt and add the extension if it doesn't
                 textFileName += ".txt";        

         File file = new File(textFileName);
         String message="";
         try {
                 // The \\Z delimiter in combination with .next() will read input until there isn't any left
         		char[] msgarr= new Scanner(file).useDelimiter("\\Z").next().toCharArray();
 				message= new String(msgarr);
 				//System.out.println("The message is : "+ message);
                // message = charArrayToByteArray( new Scanner(file).useDelimiter("\\Z").next().toCharArray() );
         } catch (FileNotFoundException e) {
                 e.printStackTrace();
         }
         
         System.out.println("Enter the audio file name that you want the text encoded into (must be a .wav file)");
         String filePath = scan.next();
         System.out.println();
         
         
         String outPath = filePath.substring(0, filePath.length() - 4) + "-Encoded.wav";
         Encoder encoder = new Encoder(new File(filePath));
         encoder.encodeMessage(message, outPath);
         System.out.println("Successfully encoded the message into " + outPath);
         
    }
    
    public static void decode() throws IOException {
    	 Scanner scan = new Scanner(System.in);
         
         System.out.print("Enter audio file name that you want to decode (must be a wav file)\n> ");
         String audioToDecode = scan.nextLine();
         if (!audioToDecode.endsWith(".wav")) // Check if the filename ends with .wav and add the extension if it doesn't
                 audioToDecode += ".wav";
         
         System.out.println("Beginning decode");
         Decoder decoder = new Decoder(new File(audioToDecode));
         
         //System.out.println(decoder.decodeMessage());
         String message=decoder.decodeMessage();
         //System.out.println("The msg is "+ message);
         System.out.print("Enter the name for a text file to write the data to\n> ");
         String outputName = scan.nextLine();
         if (!outputName.endsWith(".txt"))
                 outputName += ".txt";
        
         // write message to file
         BufferedWriter writer = null;
         writer = new BufferedWriter(new FileWriter(outputName));
         writer.write(message);
         writer.close();
         scan.close();        
    	
    }
}
