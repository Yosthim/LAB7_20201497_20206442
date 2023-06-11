<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab7_20206442_20201497.Models.Beans.Jugador" %>
<%--
  Created by IntelliJ IDEA.
  User: jossr
  Date: 9/06/2023
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    ArrayList<Jugador> lista = (ArrayList<Jugador>) request.getAttribute("lista");
%>

<html class="h-100" lang="en">

<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        .table-container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            margin-top: 50px;
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
    <title>Clasificatorias</title>
    <link rel="stylesheet" href="Recursos/css/theme.min.css">
    <style>

        /* inter-300 - latin */
        @font-face {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 300;
            font-display: swap;
            src: local(''),
            url('Recursos/fonts/inter-v12-latin-300.woff2') format('woff2'), /* Chrome 26+, Opera 23+, Firefox 39+ */
            url('Recursos/fonts/inter-v12-latin-300.woff') format('woff'); /* Chrome 6+, Firefox 3.6+, IE 9+, Safari 5.1+ */
        }

        /* inter-400 - latin */
        @font-face {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-display: swap;
            src: local(''),
            url('Recursos/fonts/inter-v12-latin-regular.woff2') format('woff2'), /* Chrome 26+, Opera 23+, Firefox 39+ */
            url('Recursos/fonts/inter-v12-latin-regular.woff') format('woff'); /* Chrome 6+, Firefox 3.6+, IE 9+, Safari 5.1+ */
        }

        @font-face {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 500;
            font-display: swap;
            src: local(''),
            url('Recursos/fonts/inter-v12-latin-500.woff2') format('woff2'), /* Chrome 26+, Opera 23+, Firefox 39+ */
            url('Recursos/fonts/inter-v12-latin-500.woff') format('woff'); /* Chrome 6+, Firefox 3.6+, IE 9+, Safari 5.1+ */
        }
        @font-face {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 700;
            font-display: swap;
            src: local(''),
            url('Recursos/fonts/inter-v12-latin-700.woff2') format('woff2'), /* Chrome 26+, Opera 23+, Firefox 39+ */
            url('Recursos/fonts/inter-v12-latin-700.woff') format('woff'); /* Chrome 6+, Firefox 3.6+, IE 9+, Safari 5.1+ */
        }

    </style>


</head>

<body class="bg-black text-white mt-0" data-bs-spy="scroll" data-bs-target="#navScroll">

<jsp:include page="Recursos/nav.jsp"></jsp:include>

<main>
        <div class="w-100 overflow-hidden position-relative bg-black text-white" data-aos="fade">
            <div class="position-absolute w-50 h-50 bg-black opacity-75 top-0 start-0"></div>
            <div class="container py-vh-1 position-relative mt-5 px-vw-1 text-center">
                <div class="row d-flex align-items-center justify-content-center py-vh-5">
                    <div class="col-12 col-xl-20">
                        <h1 class="display-huge mt-3 mb-3 lh-1">Lista de Jugadores</h1>
                    </div>
                    <div class="col-12 text-center">

                    </div>
                </div>
            </div>
            <div class="container-fluid px-vw-5 position-relative" data-aos="fade">
                <div class="position-absolute w-100 h-50 bg-black top-0 start-0"></div>
                <div class="position-relative py-vh-5 bg-cover bg-center rounded-5" style="background-image: url(Recursos/img/webp/abstract12.webp)">
                    <div class="container bg-black px-vw-5 py-vh-3 rounded-5 shadow">
                        <div class="row d-flex align-items-center">

                        <table class="table table-striped table-dark">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Edad</th>
                                <th scope="col">Possición</th>
                                <th scope="col">Club</th>
                                <th scope="col">Selección</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Jugador jugador : lista){

                            %>

                            <tr>
                                <td><%=jugador.getIdJugador()%></td>
                                <td><%=jugador.getNombre()%></td>
                                <td><%=jugador.getEdad()%></td>
                                <td><%=jugador.getPosicion()%></td>
                                <td><%=jugador.getClub()%></td>
                                <td><%=jugador.getSeleccion().getNombre()%></td>
                            </tr>
                            <%
                                }
                            %>

                            </tbody>
                        </table>

                    </div>
                </div>

            </div>
            <div class="col-12 text-center mx-8">
                <a href="<%=request.getContextPath()%>/JugadorServlet?action=crear" class="btn btn-xl btn-light">Agregar
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                    </svg>
                </a>
            </div>
        </div>


    </div>

</main>



<div class="container text-center small py-vh-2 border-top border-dark table-container ">Made by
</div>






<script src="Recursos/js/bootstrap.bundle.min.js"></script>
<script src="Recursos/js/aos.js"></script>
<script>
    AOS.init({
        duration: 800, // values from 0 to 3000, with step 50ms
    });
</script>
<script>
    let scrollpos = window.scrollY
    const header = document.querySelector(".navbar")
    const header_height = header.offsetHeight

    const add_class_on_scroll = () => header.classList.add("scrolled", "shadow-sm")
    const remove_class_on_scroll = () => header.classList.remove("scrolled", "shadow-sm")

    window.addEventListener('scroll', function() {
        scrollpos = window.scrollY;

        if (scrollpos >= header_height) { add_class_on_scroll() }
        else { remove_class_on_scroll() }

        console.log(scrollpos)
    })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>