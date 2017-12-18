<?php
unlink("annotatedSentences.txt");

$file = @file_get_contents("knownentities.txt");
$entities = preg_split('/\R/', $file);

$sentences = @fopen("id.indonesian.txt", "r");
if ($sentences) {
    $num = 0;
    while (($sentence = fgets($sentences)) !== false) {
        $num++;
        $annotated = $sentence;
        for ($i=0; $i<count($entities); $i++) {
            $annotated = str_replace($entities[$i], " <START:person> ".$entities[$i]." <END> ", $annotated);
        }
        file_put_contents("annotatedSentences.txt", $annotated, FILE_APPEND);
        echo("$sentence \n");
    }
    if (!feof($sentences)) {
        echo "Error: unexpected fgets() fail\n";
    }
    fclose($sentences);
}