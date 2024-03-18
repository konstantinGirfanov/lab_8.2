<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>files</title>
</head>

<body>
<p>Текущее время: <%= new Date() %></p>
<p>Текущая папка: <%= request.getParameter("path") %></p>
<ul>
    <%
        String path = (String)request.getParameter("path");
        String parentPath = new File(path).getParent();
        if(parentPath == null){
            parentPath = "C:/";
        }
    %>
    <li>
        <a href="files?path=<%= parentPath.replace("\\", "/") %>"> На уровень выше </a>
    </li>
    <%
    File[] files = (File[]) request.getAttribute("files");
    for (File file : files) {
        String filePath = file.getAbsolutePath().replace("\\", "/");
        if (file.isDirectory()) {
            %>
                <li>
                    [Папка] <a href="files?path=<%= filePath %>"> <%= file.getName() %> </a>
                </li>
            <%
        }
        else {
            %>
                <li>
                    [Файл] <a href="download?path=<%= filePath %>"> <%= file.getName() %> </a>
                </li>
            <%
        }
    }
    %>
</ul>
</body>
</html>