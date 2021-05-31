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

<h2>Co chcesz zrobić?</h2>

<table class="center" style="text-align: center; font-size: 16px">
    <tr>
        <td><a href="/calculator/new-calculation" class="btn btn--without-border">Nowe przeliczenie</a></td>
        <td><a href="/calculator/all-calculations" class="btn btn--without-border">Twoje przeliczenia</a></td>
        <td><a href="#" class="btn btn--without-border">Stare kursy</a></td>
        <td><a href="#" class="btn btn--without-borde">button</a></td>
    </tr>
</table>

</body>
</html>
