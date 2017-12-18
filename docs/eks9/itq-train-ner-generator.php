<?php
unlink("itq-train-ner.txt");
unlink("log/exist_entities.txt");

$sentences = @fopen("id.indonesian.txt", "r");
$log = @fopen("log/exist_entities.txt");
if ($sentences) {

  $num_sentence = 0;
  $num_person = 0;
  $location_file = @file_get_contents("entities/itq-ner-location.txt");
  $thing_file = @file_get_contents("entities/itq-ner-thing.txt");
  $person_file = @file_get_contents("entities/itq-ner-person.txt");
  $locations = preg_split('/\R/', $location_file);
  $things = preg_split('/\R/', $thing_file);
  $persons = preg_split('/\R/', $person_file);
  while (($sentence = fgets($sentences)) !== false) {
    $log_text = "";
    $num_sentence++;
    $annotated = $sentence;

    // find location entities
    $found = 0;
    for ($i=0; $i<count($locations); $i++) {
      $annotated = str_replace(" ".$locations[$i]." ", " <START:location> ".$locations[$i]." <END> ", $annotated, $count);
      $found += $count;
      if ($count > 0) echo($log_text .= "#$num_sentence location ".$locations[$i]."\n");
    }
    $num_location += $found;

    // find thing entities
    $found = 0;
    for ($i=0; $i<count($things); $i++) {
      $annotated = str_replace(" ".$things[$i]." ", " <START:thing> ".$things[$i]." <END> ", $annotated, $count);
      $found += $count;
      if ($count > 0) echo($log_text .= "#$num_sentence thing ".$things[$i]."\n");
    }
    $num_thing += $found;

    // find person entities
    $found = 0;
    for ($i=0; $i<count($persons); $i++) {
      $annotated = str_replace(" ".$persons[$i]." ", " <START:person> ".$persons[$i]." <END> ", $annotated, $count);
      $found += $count;
      if ($count > 0) echo($log_text .= "#$num_sentence person ".$persons[$i]."\n");
    }
    $num_person += $found;

    file_put_contents("itq-train-ner.txt", $annotated, FILE_APPEND);
    file_put_contents("log/exist_entities.txt", $log_text, FILE_APPEND);
  }

  if (!feof($sentences)) {
    echo "Error: unexpected fgets() fail\n";
  }

  $result = "\nTotal Location Entities Found: $num_location / $num_sentence \n";
  $result .= "Total Thing Entities Found: $num_thing / $num_sentence \n";
  $result .= "Total Person Entities Found: $num_person / $num_sentence \n";
  file_put_contents("log/exist_entities.txt", $result, FILE_APPEND);
  fclose($sentences);
  echo($result);
}
