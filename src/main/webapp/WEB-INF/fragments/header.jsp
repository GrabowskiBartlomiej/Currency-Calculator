<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <nav class="container container--70">

            <c:choose>
                <c:when test="${user==null}">
                    <ul class="nav--actions">
                        <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                        <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
                    </ul>

                    <ul>
                        <li><a href="/calculator" class="btn btn--without-border active">Do kalkulatora</a></li>
                        <li><a href="/about" class="btn btn--without-border">O co chodzi?</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li><a href="/calculator" class="btn btn--without-border active">Do kalkulatora</a></li>
                        <li><a href="/about" class="btn btn--without-border">O co chodzi?</a></li>
                        <li><form action="/logout" method="post"><button class="btn btn--without-border" type="submit">Wyloguj</button></form></li>
                    </ul>
                </c:otherwise>
            </c:choose>
    </nav>

