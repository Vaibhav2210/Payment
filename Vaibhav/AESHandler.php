<?php

include('Crypto.php');
echo "Decryption Here";

$encryptedString = $_POST['encRequest'];
$workingKey = "1D2A907A05656A7E1A570B14C573D192";

//print_r(decrypt($encryptedString,$workingKey));

?>