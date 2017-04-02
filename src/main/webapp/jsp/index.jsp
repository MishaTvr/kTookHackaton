<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing my rest app</title>
</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    var prefix = '/bestteam-1.0-SNAPSHOT/myservice';
    var prefixToLogin = prefix+'/authenticate'

    var RestGet = function() {
        $.ajax({
            type: 'GET',
            url:  prefix + '/' + Date.now(),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Юзер с айди: ' + result.id
                        + ', и именем: ' + result.name + "Pass:"+result.password+ "Card:"+result.card.holder +"Number:"+ result.card.id+ "money:"+ result.card.money );
                //
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    var RestPost = function() {
        $.ajax({
            type: 'POST',
            url:  prefix,
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Булат сосет хуй у айди: ' + result.id
                        + ', имени: ' + result.name);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }


</script>

<body>

<h3>Это простой пример использования REST c помощью Ajax</h3>

<button type="button" onclick="RestGet()">Метод GET</button>
<button type="button" onclick="RestPost()">Метод POST</button>

</body>
</html>