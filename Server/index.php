<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
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
ul {
	list-style: none;
}
li {
	margin: 3px;
}
button.facultate {
	background: #47A3FF;
	color: #fff;
	padding: 3px;
}
</style>
</head>
<body>

<?php

require 'clase/connection.php';

function testTable($orare, $i) {

	if($orare[$i] == "")
		return FALSE;
	else
		return TRUE;

}

$con = new Connection();

echo "
<div id=\"faclutati\" style=\"float:left; margin:10px; padding: 5px 20px 20px 20px; border: 2px solid black;\">
<a href=\"?\"><h2>Facultăți</h2></a>";

$toate = $con->getToateFacultatile();

echo "<ul>";

while($row=mysql_fetch_array($toate))
echo "<li><a href=\"?id_fclt=".$row['ID']."\"><button class=\"facultate\">". $row['Nume'] ."</button></a></li>";

echo "</ul>";


echo "</div>";

if(isset($_GET['id_fclt'])){

echo "<div id=\"sectii\" style=\"float:left; margin:10px; padding: 5px 20px 20px 20px; border: 2px solid black;\">";

	$toate = $con->getSectii($_GET['id_fclt']);
	$facultate = mysql_fetch_array($con->getFacultate($_GET['id_fclt']));

echo "<h2>".$facultate['Nume']."</h2>";
echo "<form action=\"orareSectie.php\" method=\"GET\">";
echo "
<table border=1>
	<tr>
		<td class=\"title\">Nume Secție</td>
		<td class=\"title\">Forma de învățământ</td>
		<td class=\"title\">Număr de ani</td>
		<td class=\"title\">Orare</td>
	<tr>
";
while($row=mysql_fetch_array($toate)){
echo "
<tr>
<td>" . $row['Nume'] . "</td>
<td>". $row['Forma_de_invatamant'] ."</td>
<td>". $row['Numar_ani'] ."</td>
<td><a href=?id_fclt=". $_GET['id_fclt']. "&id_sectie=" .$row['ID']."><button><img src=\"calendar-icon.png\" width=\"20\" /></button></a></td>
</tr>
";
}
echo "
</table>
</form>";

echo "</div>";

}



if(isset($_GET['id_sectie'])){
echo "<div id=\"orar\" style=\"clear:both; margin:10px; padding: 5px 20px 20px 20px; border: 2px solid black;\">";

$toate = $con->getSectii($_GET['id_fclt']);
$facultate = mysql_fetch_array($con->getFacultate($_GET['id_fclt']));
$sectie = $con->getSectie($_GET['id_sectie']);

echo "<h2>Orar la ".$facultate['Nume']." - Secția ".$sectie['Nume']."</h2>";


$orare = $con->getOrare($_GET['id_sectie']);



for($i = 1; $i <= $orare['numar_semestre']; $i++) {

	if(testTable($orare, $i))
		$modifica_orar = "Modifică orar";
	else
		$modifica_orar = "Adaugă orar";

	echo "<p><h3 style=\"display:inline;\">Semestrul $i</h3> <a href=\"modifica_orar.php?id_fclt=".$_GET['id_fclt']."&id_sectie=". $sectie['ID'] ."&semestru=$i\">$modifica_orar</a></p>";

	if(!testTable($orare, $i))
		echo "<p>Orarul nu există.</p>";
	else {
		echo "
		<table border=\"1\">
		<tr>
			<td class=\"title\">Ora</td>
			<td class=\"title\">Luni</td>
			<td class=\"title\">Marți</td>
			<td class=\"title\">Miercuri</td>
			<td class=\"title\">Joi</td>
			<td class=\"title\">Vineri</td>
			<td class=\"title\">Sâmbătă</td>
			<td class=\"title\">Duminică</td>
		</tr>
		";

		while($row = mysql_fetch_array($orare[$i])){
				echo "
					<tr>
						<td class=\"title\">". $row['ora'] ."</td>
						<td>". $row['luni'] ."</td>
						<td>". $row['marti'] ."</td>
						<td>". $row['miercuri'] ."</td>
						<td>". $row['joi'] ."</td>
						<td>". $row['vineri'] ."</td>
						<td>". $row['sambata'] ."</td>
						<td>". $row['duminica'] ."</td>
					</tr>
				";

		}
		echo "</table>";
	}
}

echo "</div>";
}




?>


</body>
</html>
