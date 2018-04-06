/*
 * Author: Arun Kunnumpuram Thomas
 * Student ID: 801027386 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class LZWEncoder {

	static String inputString=null;
	static int encoderWidth;
	static int MAX_TABLE_SIZE=0;
	static ArrayList<Integer> outputCode= new ArrayList<Integer>();
	static ArrayList<Character> inputchars= new ArrayList<Character>();
	static String outputFileName=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length ==2)
		{
			String filePath=args[0];
			encoderWidth=Integer.parseInt(args[1]);
			MAX_TABLE_SIZE=(int) Math.pow(2,encoderWidth);
			readInputFile(filePath);
			System.out.println("Bit length :"+encoderWidth);
			System.out.println("Max table size :"+MAX_TABLE_SIZE);
			if(inputchars.size()>0)
			{
				encode();
				writeToFile();
			}
			else
			{
				System.out.println("It seems file is empty. Nothing to compress");
			}

		}
		else
		{
			System.out.println("Please provide file path and no of encode bit as imputs");
		}

	}


	public static void readInputFile(String filePath)
	{
		StringBuilder sb = new StringBuilder();
		try {
			File file= new File(filePath);
			outputFileName=file.getName().replaceFirst("[.][^.]+$", "");
			//System.out.println("Output file name"+outputFileName);
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			int r;
			while ((r = br.read()) != -1) {
				char ch = (char) r;
				inputchars.add(ch);
			}
			br.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Please pass correct path of input file");
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void encode()
	{
		HashMap<String,Integer> dictionary= new HashMap<String,Integer>();
		for(int i=0;i<256;i++)
		{
			dictionary.put(""+ (char) i,i);
		}
		int nextCode=256;
		StringBuilder str= new StringBuilder();
		String symbol=null;
		str.setLength(0);
        System.out.println("Encoding started !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		for(int i = 0; i < inputchars.size(); i++) {

			symbol=""+inputchars.get(i);
			if(dictionary.containsKey(str.toString()+symbol))
			{

				str=str.append(symbol);
			}
			else
			{
				System.out.println("Output code"+dictionary.get(str.toString()));
				outputCode.add(dictionary.get(str.toString()));
				if(nextCode<MAX_TABLE_SIZE)
				{
					dictionary.put(str.toString()+symbol, nextCode);
					nextCode++;
					str.setLength(0);
					str.append(symbol);

				}
				else
				{
					System.out.println("Dictionary is full, cannot insert more");
					return;
				}

			}
		}
		System.out.println("Output code"+dictionary.get(str.toString()));
		outputCode.add(dictionary.get(str.toString()));
	}

	public static void writeToFile()
	{

		if(outputCode.size()>0)
		{

			System.out.println("Start writing output codes to file!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			try {

				OutputStream outputStream  = new FileOutputStream(outputFileName+".lzw");
				Writer outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16BE");

				for(int i=0;i<outputCode.size();i++)
				{
					int code=outputCode.get(i);
					outputStreamWriter.write(code);
				}
				outputStreamWriter.close();
				System.out.println("Done !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		else
		{
			System.out.println("Nothing to write, it seems file is empty");
		}
	}
}
