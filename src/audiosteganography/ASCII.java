/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audiosteganography;

/**
 * Takes a <code>String</code> and converts it to different formats
 * @author kklin
 */
public class ASCII {
	String phrase;
	public ASCII(String input) {
		phrase=input;
	}

	public int[] getBinaryBitArray() {	
																//binary rep of ascii val in string format converted to integers and stored in binaryInts
		String[] binaryStrings = getBinaryStrings();
		int[] binaryInts = new int[binaryStrings.length*8];
		int currentString = 0;
		for (int i = 0 ; i<binaryInts.length ; ) {
			char[] binary = binaryStrings[currentString].toCharArray();
			currentString++;
			for (int j = 0 ; j<binary.length ; j++) {
				binaryInts[i] = binary[j] - '0';
				i++;
			}
		}
		
		return binaryInts;
	}

	public String[] getBinaryStrings() { //returns an array with 1 character per array element
		
		char[] chars = getChars();
		String[] binaryStrings = new String[chars.length];
		for (int i = 0 ; i<chars.length ; i++) {			//store each char as binary string of ascii val
			int ascii = (int) chars[i];
			binaryStrings[i] = Integer.toBinaryString(ascii);
			//System.out.println(binaryStrings[i]);
		}

		for (int i = 0 ; i<binaryStrings.length ; i++) { //add leading 0's if necessary 
			if (binaryStrings[i].length()<8) {
				for (int j = 0 ; j<=8-binaryStrings[i].length() ; j++) {
					binaryStrings[i] = "0" + binaryStrings[i];
				}
			}
			//System.out.println(binaryStrings[i]);
		}

		for (int i = 0 ; i<binaryStrings.length ; i++) { 	//reverses each string
			char[] binary = binaryStrings[i].toCharArray();
			String reversedBinary = "";
			for (int j = binary.length-1 ; j>=0 ; j--) {
				reversedBinary = reversedBinary + binary[j];
			}
			binaryStrings[i] = reversedBinary;
			//System.out.println(binaryStrings[i]);
		}

		return binaryStrings;
	}

	public char[] getChars() {
		return phrase.toCharArray();
	}
}
