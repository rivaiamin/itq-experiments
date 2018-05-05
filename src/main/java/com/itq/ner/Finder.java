package com.itq.ner;

import java.io.FileInputStream;
import java.io.InputStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.util.Span;
//import opennlp.tools.namefind.TokenNameFinder;

public class Finder {
  public static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      return sb.toString();
    } finally {
      br.close();
    }
  }

  public static void main(String args[]) throws Exception{
    String doc = args[0];

    // refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
    String sentmodel = args[1];
    InputStream is = new FileInputStream(sentmodel);
    SentenceModel sentenceModel = new SentenceModel(is);
    //Loading the tokenizer model
    String tokenmodel = args[2];
    InputStream inputStreamTokenizer = new FileInputStream(tokenmodel);
    TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);
    //Loading the NER-person model
    String nermodel = args[3];
    InputStream inputStreamNameFinder = new FileInputStream(nermodel);
    TokenNameFinderModel nerModel = new TokenNameFinderModel(inputStreamNameFinder);

    // feed the model to SentenceDetectorME class
    SentenceDetectorME sdetector = new SentenceDetectorME(sentenceModel);
    //Instantiating the TokenizerME class
    TokenizerME tokenizer = new TokenizerME(tokenModel);
    //Instantiating the NameFinderME class
    NameFinderME nameFinder = new NameFinderME(nerModel);

    //Tokenizing the sentence in to a string array
    String documents = Finder.readFile(doc);

    // detect sentences in the paragraph
    String sentences[] = sdetector.sentDetect(documents);

    // print the sentences detected, to console
    Integer count = 0;
    Integer total_token = 0;
    for(int i=0;i<sentences.length;i++){

      String tokens[] = tokenizer.tokenize(sentences[i]);
      total_token += tokens.length;

      //Span nameSpans[] = tNameFinder.find(tokens);

      //Finding the names in the sentence
      Span nameSpans[] = nameFinder.find(tokens);
      //Printing the names and their spans in a sentence
      for(Span s: nameSpans) {
        count++;
        System.out.println(s.toString()+" "+tokens[s.getStart()]);
      }
    }

    System.out.println("Total Sentences: " + sentences.length);
    System.out.println("Total Token: " + total_token);
    System.out.println("Total Entities : " + count);
    is.close();

   }
}
