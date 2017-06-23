<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN">
<!---gooはEUCキャラクタを使っています--->
<html>

<head>
<title>SAMPLE</title>
<meta http-equiv="Content-Type" content="text/html;charset=EUC-JP">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script language="JavaScript" type="text/javascript">

//＜クリックした時に実行される関数＞
//*** 英和辞書
function func1(form){
document.form1.MT.value =form.TXT.value;
document.form1.submit();
}
//*** サーチ
function func2(form){
document.form2.MT.value = form.TXT.value;
document.form2.submit();
}

</script>
</head>

<body>
<!---実際に画面表示されるフォーム--->
<form>
<input type="text" name="TXT" value="Congratulation" size="40"><br>
<input type="button" onClick="func1(this.form)" value="Eng-JP">
<input type="button" onClick="func2(this.form)" value="goo">
</form>

<!-- 辞書用の仮想フォーム -->
<form name="form1" method="GET" action="http://dictionary.goo.ne.jp/cgi-bin/dict_search.">
<input type="hidden" name="MT">
<input type="hidden" name="sw" value="0">
</form>

<!--- サーチ用の仮想フォーム --->
<form name="form2" method="GET" action="http://search.goo.ne.jp/web.jsp">
<input type="hidden" name="MT">
</form>

</body>
</html>