 <!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<?php
if(!isset($_GET['id_sectie']))
	if(!isset($_GET['semestru']))
		echo "<meta http-equiv=\"REFRESH\" content=\"0;url=index.php\">";
?>
<style>
td {
	padding: 2px;
	text-align: center;
}
td.title {
	background: #47A3FF;
	color: #FFF;
	text-weight: bold;
	padding: 3px;
}
button {
	text-align: center;
	vertical-align: center;
}
</style>
</head>
<body>

<?php

if(isset($_GET['id_sectie']) && isset($_GET['semestru'])) {

require 'clase/connection.php';

$con = new Connection();

$con->create_if_not_exists($_GET['id_sectie'], $_GET['semestru']);

$facultate = mysql_fetch_array($con->getFacultate($_GET['id_fclt']));
$sectie = $con->getSectie($_GET['id_sectie']);

echo "<a href=\"index.php\"><button><img src=\"back.png\" width=\"30\"/> Înapoi</button></a>";

echo "<h2>Orar la ".$facultate['Nume']." - Secția ".$sectie['Nume']."</h2>";

// ===========================================

$luni = $con->getOrarLuni($_GET['id_sectie'], $_GET['semestru']); // Select luni FROM orar
$marti = $con->getOrarMarti($_GET['id_sectie'], $_GET['semestru']);
$miercuri = $con->getOrarMiercuri($_GET['id_sectie'], $_GET['semestru']);
$joi = $con->getOrarJoi($_GET['id_sectie'], $_GET['semestru']);
$vineri = $con->getOrarVineri($_GET['id_sectie'], $_GET['semestru']);
$sambata = $con->getOrarSambata($_GET['id_sectie'], $_GET['semestru']);
$duminica = $con->getOrarDuminica($_GET['id_sectie'], $_GET['semestru']);

$ora = 7;

$lu = "";

for($i = 0; $i < 13; $i++){

$l = mysql_fetch_array($luni);
	if(isset($l['luni']))
		$lu .= array( $ora." - ".$ora+1 => $l['luni']);
	else
		$lu .= array( $ora." - ".$ora+1 => "a");

$m1 = mysql_fetch_array($marti);
	if(isset($m1['marti']))
		$ma = array( $ora." - ".$ora+1 => $m1['marti']);
	else
		$ma = array( $ora." - ".$ora+1 => "");

$m2 = mysql_fetch_array($miercuri);
	if(isset($m2['miercuri']))
		$mi = array( $ora." - ".$ora+1 => $m2['miercuri']);
	else
		$mi = array( $ora." - ".$ora+1 => "");

$j = mysql_fetch_array($joi);
	if(isset($j['joi']))
		$jo = array( $ora." - ".$ora+1 => $j['joi']);
	else
		$jo = array( $ora." - ".$ora+1 => "");

$v = mysql_fetch_array($vineri);
	if(isset($v['vineri']))
		$vi = array( $ora." - ".$ora+1 => $v['vineri']);
	else
		$vi = array( $ora." - ".$ora+1 => "");

$s = mysql_fetch_array($sambata);
	if(isset($s['sambata']))
		$sa = array( $ora." - ".$ora+1 => $s['sambata']);
	else
		$sa = array( $ora." - ".$ora+1 => "");

$d = mysql_fetch_array($duminica);
	if(isset($d['duminica']))
		$du = array( $ora." - ".$ora+1 => $d['duminica']);
	else
		$du = array( $ora." - ".$ora+1 => "");

	$ora++;
}



// ===========================================



print_r($lu);
echo "<p>--------------------------------------------------------</p>";
print_r($ma);
echo "<p>--------------------------------------------------------</p>";
print_r($mi);
echo "<p>--------------------------------------------------------</p>";
print_r($jo);
echo "<p>--------------------------------------------------------</p>";
print_r($vi);
echo "<p>--------------------------------------------------------</p>";
print_r($sa);
echo "<p>--------------------------------------------------------</p>";
print_r($du);

}

?>



</body>
</html>