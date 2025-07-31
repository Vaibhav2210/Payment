<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AES Cryptography</title>
<style>
  body {
  /*background-image: url(https://secure.ccavenue.com/transaction/bg_image.jpg); /*You will specify your image path here.*/
  background-color: black;
  -moz-background-size: cover;
  -webkit-background-size: cover;
  background-size: cover;
  background-position: top center !important;
  background-repeat: no-repeat !important;
  background-attachment: fixed;
  }
</style>
</script>
</head>
<body>
<form method="post" action="Crypto.php">
<center>
<br><br>
<h1><font color="#E6E6E6">Encryption Decryption Tool</font></h1>
<br><br>
<table cellspacing="2" cellpadding="2" border="0">
<tbody><tr>
  <td><font size="4" color="#E6E6E6">Encrypted Request: </font></td>
  <td>
    <textarea rows="5" cols="100" id="encRequest" name="encRequest" ></textarea>
  </td>
</tr>
<tr>
  <td colspan="2" align="center">&nbsp;</td>
</tr>
<tr>
  <td colspan="2" align="center">&nbsp;</td>
</tr>
<tr>
</tr>
<tr>
  <td colspan="2" align="center">&nbsp;</td>
</tr>
<tr>
  <td><input type="reset" id="reset" value="Clear" onclick="document.getElementById('encRequest').value = ''"></td>
  <td><input type="submit" id="submit" value="Show Decryption!" ></td>
</tr>
</tbody></table>
<br><br><br><br><br>
</center>
</form>
</body></html>