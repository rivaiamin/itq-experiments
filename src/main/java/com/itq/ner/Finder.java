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

    // refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
    InputStream is = new FileInputStream("models/itq-sent.bin");
    SentenceModel sentenceModel = new SentenceModel(is);
    //Loading the tokenizer model
    InputStream inputStreamTokenizer = new FileInputStream("models/en-token.bin");
    TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);
    //Loading the NER-person model
    InputStream inputStreamNameFinder = new FileInputStream("models/itq-ner-all.bin");
    TokenNameFinderModel nerModel = new TokenNameFinderModel(inputStreamNameFinder);


    // feed the model to SentenceDetectorME class
    SentenceDetectorME sdetector = new SentenceDetectorME(sentenceModel);
    //Instantiating the TokenizerME class
    TokenizerME tokenizer = new TokenizerME(tokenModel);
    //Instantiating the NameFinderME class
    NameFinderME nameFinder = new NameFinderME(nerModel);

    //Tokenizing the sentence in to a string array
    String documents = Finder.readFile("id.indonesian.txt");

    // detect sentences in the paragraph
    String sentences[] = sdetector.sentDetect(documents);

    // print the sentences detected, to console
    Integer count = 0;
    for(int i=0;i<sentences.length;i++){
      //System.out.println(sentences[i]);

      String tokens[] = tokenizer.tokenize(sentences[i]);
      //Finding the names in the sentence
      Span nameSpans[] = nameFinder.find(tokens);

      //Printing the names and their spans in a sentence
      for(Span s: nameSpans) {
        count++;
        System.out.println(s.toString()+"  "+tokens[s.getStart()]);
      }

    }
    System.out.println("Total Entities : " + count);
    is.close();

   }
}
