<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.PedidoCliente"%>
<%@page import="modelos.Cardapio"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realizar Pedido</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/realizar.css">
    </head>

    <body>
        <div class="container-fluid border w-50 h-75 mt-3 mb-4" id="head">
            <div class="container-fluid d-flex justify-content-center mt-4">
                <h1>Realizar Pedido</h1><br>
                
                <%
                    PedidoCliente pcli = new PedidoCliente();
                    List<PedidoCliente> mesas = pcli.lovMesa();

                    Cardapio card = new Cardapio();
                    List<Cardapio> produtos = card.lovCardapio();

                %>
            </div>
            <div class="container-fluid mt-2 mr-4">
                <form action="recebeRealizarPedido.jsp" method="POST">
                    <p class="font-weight-bold">
                    <%
                        if (request.getParameter("pmensagem") != null)
                            out.write(request.getParameter("pmensagem"));
                    %>
                    </p>
                    <label>Código da mesa</label><br>
                    <select name="codMesa" class="form-control">
                        <% for (PedidoCliente m : mesas) {%>
                        <option value="<%out.write("" + m.getCodMesa());%>">
                            <% out.write(m.getMesa());%>
                        </option>
                        <%}%>
                    </select><br>

                    <label>Produto</label><br>
                    <select name="codProduto" class="form-control">
                        <% for (Cardapio p : produtos) {%>
                        <option value="<%out.write("" + p.getCodProduto());%>">
                            <% out.write(p.getDescProduto());%>
                        </option>
                        <%}%>

                    </select><br>

                    <label>Observação</label><br>
                    <input type="text" name="observacao" class="form-control" placeholder="Insira as observações"><br>

                    <label>Status do Pagamento</label><br>
                    <select name="statusPagto" class="form-control">
                        <option value="pendente">Pendente</option>
                    </select><br>

                    <label>Status do Pedido</label><br>
                    <select name="statusPedido" class="form-control">
                        <option value="andamento">Andamento</option>
                    </select><br>
                    <div class="text-center mb-3">
                        <input type="submit" value="Confirmar" class="btn btn-success"/>
                        <input type="reset" value="Cancelar" class="btn btn-danger"/>
                    </div>
                </form>
            </div>
        </div>
    </body>

</html>
