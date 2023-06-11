<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab7_20206442_20201497.Models.Beans.Seleccion" %>
<%@ page import="com.example.lab7_20206442_20201497.Models.Beans.Estadio" %><%--
  Created by IntelliJ IDEA.
  User: jossr
  Date: 9/06/2023
  Time: 02:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Estadio> estadios = (ArrayList<Estadio>) request.getAttribute("estadios");
%>


<html class="h-100" lang="en">

<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/css/bootstrap.min.css">
    <style>
        .custom-select {
            appearance: none;
            padding: 0.5rem 1.75rem 0.5rem 0.75rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    <meta name="description" content="A well made and handcrafted Bootstrap 5 template">
    <link rel="apple-touch-icon" sizes="180x180" href="Recursos/img/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="Recursos/img/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="Recursos/img/favicon-16x16.png">
    <link rel="icon" type="image/png" sizes="96x96" href="Recursos/img/favicon.png">
    <meta name="author" content="Holger Koenemann">
    <meta name="generator" content="Eleventy v2.0.0">
    <meta name="HandheldFriendly" content="true">
    <title>Agregar selección</title>
    <link rel="stylesheet" href="Recursos/css/theme.min.css">


</head>

<body class="d-flex h-100 w-100 bg-black text-white" data-bs-spy="scroll" data-bs-target="#navScroll">

<div class="h-100 container-fluid">
    <div class="h-100 row d-flex align-items-stretch">

        <div class="col-lg-10 col-md-7 col-lg-6 col-xl-5 d-flex align-items-start flex-column px-vw-5">

            <header class="mb-auto py-vh-1 col-12">
                <a class="navbar-brand pe-md-4 fs-4 col-12 col-md-auto text-center" href="index.jsp">
                </a>

            </header>
            <main class="mb-auto col-12">
                <h1>Agregar una nueva Selección</h1>

                <form method="POST" action="<%=request.getContextPath()%>/listaSelecciones">
                    <div class="mb-3">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="nombre">
                    </div>
                    <div class="mb-3">
                        <label for="tecnico">Técnico</label>
                        <input type="text" class="form-control" name="tecnico" id="tecnico">
                    </div>
                    <div class="mb-3">
                        <label for="id_estadio" class="form-label">Estadio</label>
                        <div class="container">
                            <select class="custom-select" aria-label=".form-select-lg example" name ="id_estadio">
                                <% for (Estadio e : estadios){ %>
                                <option  value="<%=e.getIdEstadio()%>">
                                    <%=e.getNombre()%>
                                </option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-white btn-xl mb-4">Agregar</button>
                </form>

            </main>
        </div>

        <div class="col-12 col-md-5 col-lg-6 col-xl-7 gradient"></div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/js/bootstrap.bundle.min.js"></script>

</div></body>
</html>

