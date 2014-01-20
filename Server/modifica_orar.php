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
	border-bottom: 1px dashed #000;
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
textarea.ta_ziua {
	width: 150px;
	height: 40px;
	border: 1px solid #cccccc;
	padding: 2px;
	font-family: Tahoma, sans-serif;
	text-align: center;
	vertical-align: center;
	margin: 0px;
}
td.td_title {
	border: 2px solid #000;
}
.h_title {
	margin: 5px;
}
.h_ora {
	margin: 15px;
}
</style>

</head>
<body>

<?php


if(isset($_GET['id_sectie']) && isset($_GET['semestru'])) {

require 'clase/connection.php';

$con = new Connection();

$facultate = mysql_fetch_array($con->getFacultate($_GET['id_fclt']));
$sectie = $con->getSectie($_GET['id_sectie']);


echo "<a href=\"index.php\"><button><img src=\"back.png\" width=\"30\"/> Înapoi</button></a>";

echo "<h2>Orar la ".$facultate['Nume']." - Secția ".$sectie['Nume']."</h2>";

// ===== Verificări dacă există tabelul și dacă este gol
$con->create_if_not_exists($_GET['id_sectie'], $_GET['semestru']);


// ===== Creare tabel cu orar
echo <<<EOT

 <form action="salvare.php" method="post">


<table>
<tr>
<td class="td_title"><h2 class="h_title">Ora</h2></td>
<td class="td_title"><h2 class="h_title">Luni</h2></td>
<td class="td_title"><h2 class="h_title">Marți</h2></td>
<td class="td_title"><h2 class="h_title">Miercuri</h2></td>
<td class="td_title"><h2 class="h_title">Joi</h2></td>
<td class="td_title"><h2 class="h_title">Vineri</h2></td>
<td class="td_title"><h2 class="h_title">Sâmbătă</h2></td>
<td class="td_title"><h2 class="h_title">Duminică</h2></td>
</tr>
EOT;

$result = $con->getOrar($_GET['id_sectie'], $_GET['semestru']);
$i=0;

while($row = mysql_fetch_array($result)){

$i=$i+1;
$ora = $row['ora'];
$luni = $row['luni'];
$marti = $row['marti'];
$miercuri = $row['miercuri'];
$joi = $row['joi'];
$vineri = $row['vineri'];
$sambata = $row['sambata'];
$duminica = $row['duminica'];


echo "<tr>";
echo "<td><h4 name=\"h".$i."\" class=\"h_ora\">".$ora."</h4></td>";
echo "<td><textarea name=\"lu".$i."\" class=\"ta_luni ta_ziua\">$luni</textarea></td>";
echo "<td><textarea name=\"ma".$i."\" class=\"ta_marti ta_ziua\">$marti</textarea></td>";
echo "<td><textarea name=\"mi".$i."\" class=\"ta_miercuri ta_ziua\">$miercuri</textarea></td>";
echo "<td><textarea name=\"jo".$i."\" class=\"ta_joi ta_ziua\">$joi</textarea></td>";
echo "<td><textarea name=\"vi".$i."\" class=\"ta_vineri ta_ziua\">$vineri</textarea></td>";
echo "<td><textarea name=\"sa".$i."\" class=\"ta_sambata ta_ziua\">$sambata</textarea></td>";
echo "<td><textarea name=\"du".$i."\" class=\"ta_duminica ta_ziua\">$duminica</textarea></td>";
echo "</tr>";


}


echo "</table>";
echo "<input type=\"submit\" name=\"submit\" value=\"Submit\">";
echo "<input type=\"hidden\" name=\"sectie\" value=".$_GET['id_sectie'].">";
echo "<input type=\"hidden\" name=\"semestru\" value=".$_GET['semestru'].">";

echo "</form>";




}
?>



</body>
</html>