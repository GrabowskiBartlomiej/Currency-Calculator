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
    <title>Charity Donation</title>

    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header>
<%@include file="../fragments/header.jsp"%>
</header>

<h2>Witaj na przeliczniku walut online</h2>

<p style="text-align: center">Dane z dnia: ${dateOfData}</p>
<table id="customTables" class="center" style="text-align: center; font-size: 16px">
    <tr>
        <th>Kod</th>
        <th>Waluta</th>
        <th>Kurs w stosunku do zł</th>
    </tr>
    <c:forEach items="${allCurrencies}" var="currency">
        <tr>
            <td>${currency.code}</td>
            <td>${currency.currency}</td>
            <td>${currency.rate}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
