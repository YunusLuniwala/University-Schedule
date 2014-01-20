 <!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<?php


require 'clase/connection.php';

$con = new Connection();

$sem=$_POST['semestru'];
$sect=$_POST['sectie'];



$tableName="orar_sectie".$sect."_sem".$sem;


$i=1;
$k=6;
$l=7;
while($i<14)
{
$k=$k+1;
$l=$l+1;
$ora=$k." - ".$l;

$u=array("lu".$i, "ma".$i, "mi".$i, "jo".$i, "vi".$i, "sa".$i, "du".$i);
$zile=array($_POST[$u[0]],$_POST[$u[1]],$_POST[$u[2]],$_POST[$u[3]], $_POST[$u[4]], $_POST[$u[5]], $_POST[$u[6]]);

$con->insertIntoDatabase($ora, $zile, $tableName);
$i=$i+1;
}
echo "Date introduse cu succes!";
echo "<br>";
echo "<a href=\"index.php\">Inapoi</a>";





?>
</body>
</html>