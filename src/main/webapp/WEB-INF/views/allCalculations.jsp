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


    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<header>
<%@include file="../fragments/header.jsp"%>
</header>

<h2>Witaj na przeliczniku walut online</h2>

<table id="customTables" class="center" style="text-align: center; font-size: 16px">
    <tr>
        <th>Kwota</th>
        <th>Z Waluty</th>
        <th>Do Waluty</th>
        <th>Kurs</th>
        <th>Kwota Po Przeliczeniu</th>
        <th>Kurs Z Dnia</th>
    </tr>
    <c:forEach items="${allCalculations}" var="calculation">
        <tr>
            <td>${calculation.moneyInput}</td>
            <td>${calculation.currencyCodeFrom}</td>
            <td>${calculation.currencyCodeTo}</td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "4" value = "${calculation.rate}" /></td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${calculation.moneyOutput}" /></td>
            <td>${calculation.rateFrom}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
