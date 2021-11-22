<%@page import="calender.calender.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@ page import="calender.calender.dto.ArticleCountResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>caledar</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    int year = Integer.parseInt(request.getParameter("year"));
    int month = Integer.parseInt(request.getParameter("month"));
    HashMap<Integer, Long> counts = (HashMap<Integer, Long>) request.getAttribute(
            "counts");
%>
<div class="container">
    <div class="login">
        <sec:authorize access="isAuthenticated()">
            <div class="auth"><sec:authentication property="Principal.user.loginId"/> 님</div>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <input class="signUp" type="button" value="회원가입" onclick="location='signup'">
            <input class="login" type="button" value="로그인" onclick="location='login'">
        </sec:authorize>
        <div class="counts">
            <div>1~9 개: &nbsp&nbsp <img class="count"
                                        src="${pageContext.request.contextPath}/img/yellowCircle.png">
            </div>
            <div>10~19 개: <img class="count"
                               src="${pageContext.request.contextPath}/img/redCircle.png"></div>
            <div>20~ 개: &nbsp&nbsp <img class="count"
                                        src="${pageContext.request.contextPath}/img/purpleCircle.png">
            </div>
        </div>
    </div>
    <table width="800" border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>
                <input type="button" value="이전 달"
                       onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
            </th>
            <th id="title" colspan="5">
                <%=year%>년  <%=month%>월
            </th>
            <th>
                <input type="button" value="다음 달"
                       onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
            </th>
        </tr>
        <tr>
            <td class="sunday">일</td>
            <td class="etcday">월</td>
            <td class="etcday">화</td>
            <td class="etcday">수</td>
            <td class="etcday">목</td>
            <td class="etcday">금</td>
            <td class="saturday">토</td>
        </tr>
        <tr>
            <%
                int first = Calendar.weekDay(year, month, 1);

                int start = 0;
                start = month == 1 ? Calendar.lastDay(year - 1, 12) - first
                        : Calendar.lastDay(year, month - 1) - first;

                for (int i = 1; i <= first; i++) {
                    if (i == 1) {
                        out.println(
                                "<td class = 'redbefore'>" + (month == 1 ? 12 : month - 1) + "/"
                                        + ++start + "</td>");
                    } else {
                        out.println(
                                "<td class = 'before'>" + (month == 1 ? 12 : month - 1) + "/"
                                        + ++start
                                        + "</td>");
                    }
                }

                for (int i = 1; i <= Calendar.lastDay(year, month); i++) {
                    switch (Calendar.weekDay(year, month, i)) {
                        case 0:
                            out.println("<td class ='sun' onclick=\"location='articles?year=" + year
                                    + "&month=" + month + "&day=" + i + "'\">" + i);
                            break;
                        case 6:
                            out.println("<td class ='sat' onclick=\"location='articles?year=" + year
                                    + "&month=" + month + "&day=" + i + "'\">" + i);
                            break;
                        default:
                            out.println("<td class ='etc' onclick=\"location='articles?year=" + year
                                    + "&month=" + month + "&day=" + i + "'\">" + i);
                            break;
                    }

                    if (counts.containsKey(i)) {
                        if (counts.get(i) >= 1 && counts.get(i) <= 9) {
                            out.println("<div class='count' style='background-color: yellow'></div></td>");
                        } else if (counts.get(i) >= 10 && counts.get(i) <= 19) {
                            out.println("<div class='count' style='background-color: red'></div></td>");
                        } else {
                            out.println("<div class='count' style='background-color: purple'></div></td>");
                        }
                    } else {
                        out.println("</td>");
                    }

                    if (Calendar.weekDay(year, month, i) == 6 && i != Calendar.lastDay(year,
                            month)) {
                        out.println("</tr><tr>");
                    }
                }
                if (Calendar.weekDay(year, month, Calendar.lastDay(year, month)) != 6) {
                    for (int i =
                            Calendar.weekDay(year, month, Calendar.lastDay(year, month)) + 1;
                            i < 7; i++) {
                        out.println("<td></td>");
                    }
                }
            %>
        </tr>
    </table>
</div>
</body>
</html>