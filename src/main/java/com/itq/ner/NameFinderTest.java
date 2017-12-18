package com.itq.ner;

import java.io.FileInputStream; 
import java.io.InputStream;  
import java.io.File;  
import java.util.Scanner;  
import java.io.PrintWriter;

import opennlp.tools.namefind.NameFinderME; 
import opennlp.tools.namefind.TokenNameFinderModel; 
import opennlp.tools.tokenize.TokenizerME; 
import opennlp.tools.tokenize.TokenizerModel; 
import opennlp.tools.util.Span;  

public class NameFinderTest { 
   public static void main(String args[]) throws Exception{
    //String modelType = args[0];

      InputStream inputStreamTokenizer = new 
         FileInputStream("../en-token.bin"); 
      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
       
      //String paragraph = "Mike and Smith are classmates"; 
      File paragraphFile = new File("id.indonesian.txt");
      String paragraph = new Scanner(paragraphFile).useDelimiter("\\Z").next();
      
      //String paragraph = "ataukah mereka dengki kepada manusia (Muhammad)"; 

      //Instantiating the TokenizerME class 
      TokenizerME tokenizer = new TokenizerME(tokenModel); 
      String tokens[] = tokenizer.tokenize(paragraph); 
       
      //Loading the NER-person moodel 
      InputStream inputStreamNameFinder = new 
         FileInputStream("itq-ner-person.bin");       
      TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder); 
        
      //Instantiating the NameFinderME class 
      NameFinderME nameFinder = new NameFinderME(model);      
        
      //PrintWriter out = new PrintWriter("ner-person-result.txt");      
      //Finding the names of a person 
      Span nameSpans[] = nameFinder.find(tokens);        
      // Printing the spans of the persons in the sentence
      // and save to file
      for(Span s: nameSpans) {
        //out.println(s);
        System.out.println(s.toString()+"  "+tokens[s.getStart()]);
      }
      //out.close();

   }    
}   