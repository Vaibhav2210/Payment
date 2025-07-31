<!DOCTYPE html>
<html>
<head>
	<title>Transaction Details</title>
	<style type="text/css">
		table {
			border-collapse: collapse;
			width: 100%;
		}

		th, td {
			text-align: center;
			padding: 8px;
			border-bottom: 1px solid #ddd;
		}

		tr:hover {
			background-color: #f5f5f5;
		}

		th {
			background-color: #4CAF50;
			color: white;
		}
		h1 {
			color: black;
			text-align: center;
		}
		body {
			color: black;
		}
	</style>
</head>
<body>
	<h1>Bank Transaction Details</h1>
	<table width="200" border="1">
		<thead>
			<tr>
				<th>Sr No.</th>
				<th>ID</th>
				<th>peerBankCode</th>
				<th>peerBankBranch</th>
				<th>peerBankName</th>
				<th>transactionid</th>
				<th>bank_verify</th>
				<th>UVR</th>
			</tr>
		</thead>
		<tbody>
			<?php
			$curl = curl_init();

			curl_setopt_array($curl, array(
  CURLOPT_URL => 'http://localhost:8082/bank/newbank',
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => '',
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => 'GET',
  CURLOPT_HTTPHEADER => array(
    'Content-Type: application/json'
  ),
));
			$response = curl_exec($curl);

			curl_close($curl);

			$Decode = json_decode($response);

			foreach($Decode as $key => $value) {
			  $index = $key + 1;
			  echo "<tr>";
			  echo "<td>".$index."</td>";
			  foreach($value as $newkey => $newvalue){
			    echo "<td>".$newvalue."</td>";
			  }
			  echo "</tr>";
			}
			?>
		</tbody>
	</table>
</body>
</html>