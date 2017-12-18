# Eksperimen 6

- Eksperimen dengan menggunakan annotatedSentences.txt dari `Eksperimen 5`
- Training model itq-ner-person.bin dengan OpenNLP 1.8.2 TokenNameFinderTrainer
- Testing model pada dokumen id.indonesian.txt menggunakan OpenNLP 1.8.2

## Result
- Dari 1101 Entity Person pada dokumen, ditemukan 395 token
- Kesimpulan: model yang digenerate pada versi 1.6.0 masih lebih baik dari versi 1.8.2

## Next Plan
- Menguji coba generate model untuk case yang lebih umum seperti berita
