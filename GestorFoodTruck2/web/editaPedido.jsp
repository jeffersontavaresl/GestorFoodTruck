<%@page import="java.util.ArrayList"%>
<%@page import="modelos.PedidoCliente"%>
<%@page import="modelos.Cardapio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>E-Truck Management - Editar Pedido</title>
        <!--FAVICON-->
        <link rel="icon"  type="image/gif" href="styles/imagens/hamburger-solid.svg">
        <!-- Bootstrap e Bot�es-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link href="styles/functions.css" rel="stylesheet"/>
        <link href="styles/functions02.css" rel="stylesheet"/>
        <link href="styles/editar.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="bg-padrao border-end" id="sidebar-wrapper">
                <div class="bg-padrao list-group list-group-flush">
                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="functions.jsp"><i class="fas fa-home"></i> In�cio</a>
                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-user"></i> Gar�om</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="realizarPedido.jsp">Realizar Pedido</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultapedido.jsp">Consultar Pedido</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-utensils"></i> Cozinha</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaCozinha.jsp">Consulta Cozinha</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-cash-register"></i> Caixa</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultacaixa.jsp">Finalizar Pedido</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="cadastroMetodoPagamento.jsp">Cadastrar Forma de Pagamento</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaMetodoPagamento.jsp">Consultar Formas de Pagamento</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-clipboard"></i> Card�pio</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="cadastraItem.jsp">Adicionar Item</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaItem.jsp">Consultar Itens</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-table"></i> Mesa</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="cadastraMesa.jsp">Cadastrar Mesa</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaMesas.jsp">Consultar Mesa</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-cart-arrow-down"></i> Insumo</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="cadastroInsumo.jsp">Cadastrar Estoque</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaInsumo.jsp">Consultar Estoque</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="realizarPedFornecedor.jsp">Realizar Pedido Fornecedor</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaPedFornecedor.jsp">Consultar Pedido Fornecedor</a>
                    </div>

                    <a class="dropdown-btn p-3 mt-1 btn-funcoes" href="#!"><i class="fas fa-solid fa-user-tie"></i> Administrador</a>
                    <div class="dropdown-container">
                        <a class="list-group-item p-3 btn-funcoes-drop" href="cadastraFornecedor.jsp">Cadastrar Fornecedor</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaAlertaEstq.jsp">Estoque em Alerta</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaForn.jsp">Consulta Fornecedor</a>
                        <a class="list-group-item p-3 btn-funcoes-drop" href="consultaRelatorio.jsp">Relat�rio de Pagamentos</a>
                    </div>
                    <br><br><br>
                </div>
            </div>
            <div id="page-content-wrapper">
                <!-- Conte�do da P�gina-->
                
                <!-- Verifica se o usu�rio est� logado-->
                <p id="login">
                    <%
                        String login = (String) session.getAttribute("login");
                        if (login == null) {
                            response.sendRedirect("login.jsp");
                        }
                    %>
                </p>
                <body>
                    <div class="container-fluid">
                        <div class="container-fluid border w-50 h-75 mt-5 mb-4" id="tabela">
                            <div class="container-fluid borderless w-50 h-75 mt-3 mb-4">
                                
                                <!--Requerimento do c�digo vindo da consulta para edi��o-->
                                <h1>Editar Pedido</h1>
                                <%
                                    String codpedido = request.getParameter("codPedido");
                                    int codPedido = 0;
                                    PedidoCliente pdcliente = new PedidoCliente();
                                    if (codpedido != null) {
                                        codPedido = Integer.parseInt(codpedido);
                                        pdcliente = pdcliente.consultarPedido(codPedido);
                                    }
                                %>
                            </div>
                            <div class="container-fluid mt-3 mr-4">
                                <form action="recebeEditaPedido.jsp" method="POST">
                                    <%
                                        Cardapio card = new Cardapio();
                                        List<Cardapio> cardapio = card.lovCardapio();
                                    %>
                                    <label>Novo produto</label>
                                    <select name="codProduto" class="form-control" required>
                                        <% for (Cardapio c : cardapio) { %>
                                        <option value ="<%out.write("" + c.getCodProduto());%>">
                                            <% out.write(c.getDescProduto()); %>
                                        </option>
                                        <%}%>
                                    </select>                             
                                    <br>
                                    <label>Observa��o</label>
                                    <input type="text" class="form-control" maxlength="90" name="obsPedido" placeholder="Observa��o" />
                                    <br>

                                    
                                    <input hidden type="text" class="form-control" name="codPedido" readonly placeholder="C�digo do pedido" 
                                           value="<%out.write("" + pdcliente.getCodPedido());%>"/>                                   

                                    <div class="text-center mb-3">
                                        <input type="submit" value="Alterar" class="btn btn-primary"/>
                                        <a href="consultapedido.jsp" class="btn btn-danger">Cancelar</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            </div>
            <!--JS - SIDEBAR-->
            <script src="js/sidebar.js"></script>
            <script src="js/scripts.js"></script>

            <!--FONT AWESOME-->
            <script src="https://kit.fontawesome.com/941d2c80e7.js" crossorigin="anonymous"></script>
    </body>
</html>
