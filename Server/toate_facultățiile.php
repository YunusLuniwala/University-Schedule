<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<?php

require 'clase/facultate.php';

$facultati = new Facultati();

$toate = $facultati->getToateFacultatile();

echo "
<table border=1>
	<tr>
		<td>ID Facultate</td>
		<td>Nume Facultate</td>
	<tr>
";
while($row=mysql_fetch_array($toate)){
echo "
<tr>
<td>" . $row['ID'] . "</td>
<td><a href=\"sectii_facultate.php?id=".$row['ID']."\">" . $row['Nume'] . "</a></td>
</tr>
";
}
echo "
</table>
";

?>


</body>
</html>