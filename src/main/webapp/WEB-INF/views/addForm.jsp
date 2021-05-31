<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Currency Converter</title>

    <base href="/">
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header>
<%@include file="../fragments/header.jsp"%>
</header>

<h2>Wykonaj nowe obliczenie</h2>


<section class="login-page">
    <form method="post">
        <div class="form-group">
            <input type="number" name="moneyInput" min="0" step="0.01"><p style="font-size: 17px">kwota</p>
        </div>
        <div class="form-group">
            <select name="from">
                <c:forEach items="${allCurrencies}" var="currency">
                    <option value="${currency.code}">${currency.code}</option>
                </c:forEach>
            </select><p style="font-size: 17px">z waluty</p>
        </div>
        <div class="form-group">
            <select name="to">
                <c:forEach items="${allCurrencies}" var="currency">
                    <option value="${currency.code}">${currency.code}</option>
                </c:forEach>
            </select><p style="font-size: 17px">na walutę</p>
        </div>
        <button type="submit" class="btn">Oblicz</button>
    </form>
</section>
</body>
</html>
