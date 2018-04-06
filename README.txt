Student Details
----------------------------------
ARUN KUNNUMPURAM THOMAS
STUDENT ID :801027386

Programming Language details
-----------------------------------
Programming Language: JAVA
Compiler Version : JAVA 8

Instruction to run program
-----------------------------------
LZWEncoder
------------
1. Go to project folder from command prompt
2. Execute javac LZWEncoder.java
3. Execute java LZWEncoder <inputfilename with extension><bit length>
   ex: java LZWEncoder input1.txt 12
       This will create an output file input1.lzw in the same folder
LZWDecoder
------------
1. Go to project folder from command prompt
2. Execute javac LZWDecoder.java
3. Execute java LZWDecoder <outputfile from LZWEncoder><bit length>
   ex: java LZWEncoder input1.lzw 12
       This will create an output file input1_decoded.txt in the same folder
	   

Program Description and data structure details
-----------------------------------------------
LZWEncoder.java
----------------
It has mainly three operations

1. Reading the input file
2. Encoding
3. Writing to output file	 

In this program, I am reading the file character by character and passing it to the encoder method. Encoder method is the implementation of LZW compression algorithm. I am using HashMap to represent the
dictionary, so that dictionary search will be fast. Iam writing the encoded codes from encoder to outputfile using UTF-16BE encoding. Program will create a .lzw file with name same as input file in the project directory.

LZWDecoder.java
----------------
It has mainly three operations
1. Reading the input LZW file
2. Decoding
3. Writing the decoded text to output file	 

In this program, I am reading the LZW file from LZWEncoder using UTF-16BE emcoding and passing the set of integer codes to the decoder method. Decoder method is the implementation of LZW decompression algorithm.
I am using HashMap to represent the dictionary, so that dictionary search will be fast. The program will write the decoded text from decoder to a decoded.txt file. Program will create a file with 
name <inputfilename>_decoded.txt file in the project folder.

  
	

