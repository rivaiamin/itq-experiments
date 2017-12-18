package com.itq.ner;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

import opennlp.tools.util.eval.FMeasure;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderCrossValidator;
import opennlp.tools.namefind.TokenNameFinderEvaluator;
import opennlp.tools.namefind.TokenNameFinderModel;

public class Evaluator {
  public static void main (String args[]) throws Exception {
    InputStream inputStreamNameFinder = new FileInputStream("models/itq-ner-all.bin");
    TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

    // reading training data
    InputStreamFactory sampleDataIn = null;
    try {
      sampleDataIn = new MarkableFileInputStreamFactory(new File("itq-train-ner.txt"));
    } catch (FileNotFoundException e2) {
      e2.printStackTrace();
    }

    ObjectStream<NameSample> sampleStream = new NameSampleDataStream(new PlainTextByLineStream(sampleDataIn, StandardCharsets.UTF_8));
    TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));
    evaluator.evaluate(sampleStream);

    FMeasure result = evaluator.getFMeasure();

    System.out.println(result.toString());

  }
}

