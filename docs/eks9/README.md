# Eksperimen 9
Eksperimen NER dengan menggunakan OpenNLP API (Sebelumnya selalu menggunakan OpennNLP Tool)

## Deskripsi
- Membuat list untuk semua entities Person, Location dan Thing
- Membuat program untuk generate train data
- Membentuk train data dari `id.indonesian.txt`
- Membuat OpenNLP API untuk Train model
- Membuat OpenNLP API untuk Finder 
- Membuat OpenNLP API untuk Evaluator
- Menjalankan program train model, menghasilkan `itq-ner-all.bin` dan `log/Trainer.txt`
- Menjalankan program finder, menghasilkan `log/Finder.txt`
- Menjalankan program Evaluator, menghasilkan `log/Evaluator.txt`

## Map File
- entities              // Folder berisi list entitas untuk Person, Location dan Thing
-- itq-ner-location.txt // file berisi list entitas Location
-- itq-ner-thing.txt    // file berisi list entitas Thing
-- itq-ner-person.txt   // file berisi list entitas Person

- log             // Folder berisi hasil/catatan dari program yang dijalankan
-- Entities.txt   // berisi list entitas yang valid/benar/seharusnya dari id.indonesian.txt
-- Trainer.txt    // berisi catatan/log saat menjalankan program Trainer model
-- Finder.txt     // berisi catatan/log saat menjalankan program Finder dalam menguji model
-- Evaluator.txt  // berisi catatan saat menjalankan program Evaluator untuk mengevaluasi model

- models            // folder berisi model yang dibutuhkan dalam eksperimen
-- itq-sent.bin     // model untuk sentenceFinder, digunakan di program Finder
-- en-token.bin     // model untuk tokenisasi, digunakan di program Finder
-- itq-ner-all.bin  // output model dari program Trainer Model

- id.indonesian.txt           // Source file utama dalam eksperimen
- itq-ner-1.0-SNAPSHOT-jar-with-dependencies.jar // Package JAR yang berisi program Trainer, Finder dan Evaluator
- itq-train-ner.txt           // output dari itq-train-ner-generator, dan input untuk program Trainer
- itq-train-ner-generator.php // program untuk generate training data dengan input dari id.indonesian.txt dan list entities

## Dokumentasi Program
- Train Data Generator
`php itq-train-ner-generator.php`
- Train Model
`java -cp itq-ner-1.0-SNAPSHOT-jar-with-dependencies.jar com.itq.ner.Trainer`
- Named Entity Extraction
`java -cp itq-ner-1.0-SNAPSHOT-jar-with-dependencies.jar com.itq.ner.Finder`
- Evaluate Model
`java -cp itq-ner-1.0-SNAPSHOT-jar-with-dependencies.jar com.itq.ner.Evaluator`
