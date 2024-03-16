<%@ page import="java.util.Date;" %>
<%@ page import="java.io.File" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список файлов</title>
</head>
<body>
<p>Текущее время: <%= new Date() %></p>
<hr>
<h3>Текущая директория: <%= req.getAttribute("path") %></h3>
<ul>
    <%
    String path = (String)req.getAttribute("path");
    String parentPath = new File(path).getParent();
    }

    %>
    <li>
        <a href="files?path=<%= URLEncoder.encode(parentPath, "UTF-8") %>"> На уровень выше </a>
    </li>
    <%
    File[] files = (File[]) req.getAttribute("files");
    for (File file : files) {
        String filePath = URLEncoder.encode(file.getAbsolutePath(), "UTF-8");
        if (file.isDirectory()) {
            %>
            <li>
                [Dir] <a href="files?path=<%= filePath %>"> <%= file.getName() %> </a>
            </li>
            <%
        }
        else {
            %>
            <li>
                [File] <a href="download?path=<%= filePath %>"><%= file.getName() %></a>
            </li>
            <%
        }
    }
    %>
</ul>
</body>
</html>