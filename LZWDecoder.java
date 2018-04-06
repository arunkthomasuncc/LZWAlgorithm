/*
* Author: Arun Kunnumpuram Thomas
* Student ID: 801027386
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class LZWDecoder {

	static String inputString=null;
	static int encoderWidth;
	static int MAX_TABLE_SIZE=0;
	static ArrayList<Integer> codes= new ArrayList<Integer>();
	static ArrayList<String> decodedString= new ArrayList<String>();
	static String outputFileName=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args.length ==2)
		{
			String filePath=args[0];
			encoderWidth=Integer.parseInt(args[1]);
			MAX_TABLE_SIZE=(int) Math.pow(2,encoderWidth);
			readInputFile(filePath);
			decode();
			printOutput();
		}
		else
		{
			System.out.println("Please provide file path and no of encode bit as imputs");
		}
	}
	public static void readInputFile(String filePath)
	{
		File infile = new File(filePath);
		outputFileName=infile.getName().replaceFirst("[.][^.]+$", "");
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(infile);
			Reader in;
			in = new InputStreamReader(inputStream, "UTF-16");
			int read;
			System.out.println("Reading the input file !!!!!!!!!!!!!");
			while ((read = in.read()) != -1) 
			{
				//System.out.println(read);
				codes.add(read);

			}
			in.close();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	public static void decode()
	{
		HashMap<Integer,String> dictionary= new HashMap<Integer,String>();
		for(int i=0;i<256;i++)
		{
			dictionary.put(i,""+ (char) i);
		}
		int nextCode=256;
		String str=null;
		StringBuilder newstring= new StringBuilder();
		newstring.setLength(0);
		System.out.println("Decoding started !!!!!!!!!!!!!");
		if(codes.size()>0)
			str=dictionary.get(codes.get(0));
		
		System.out.println("Output :"+str);
		decodedString.add(str);
		for(int i=1;i<codes.size();i++)
		{
			if(!dictionary.containsKey(codes.get(i)))
			{
				newstring.setLength(0);
				newstring.append(str+str.charAt(0));
			}
			else
			{
				newstring.setLength(0);
				newstring.append(dictionary.get(codes.get(i)));
			}
			System.out.println("Output :"+ newstring.toString());
			decodedString.add(newstring.toString());
			if(nextCode<MAX_TABLE_SIZE)
			{
				dictionary.put(nextCode, str+newstring.toString().charAt(0));
				nextCode++;
			}
			str=newstring.toString();
		}


	}
	public static void printOutput()
	{
		try {
			FileWriter fw= new FileWriter(outputFileName+"_decoded.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("Writing output to file !!!!!!!!!!!!!");
			if(decodedString.size()>0)
			{
			for(String x : decodedString)
			{
				bw.write(x);

			}
			bw.close();
			System.out.println("Done !!!!!!!!!!!!!");
			}
			else{
				System.out.println("Nothing to write !!!!!!!!!!!!!");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
