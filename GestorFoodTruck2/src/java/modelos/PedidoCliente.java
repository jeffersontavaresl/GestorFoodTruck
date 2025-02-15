package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Conexao;

public class PedidoCliente extends Cardapio{
    
    private int codPedido;
    private int codProduto;
    private int codMesa;
    private String observacao;
    private String statusPagto;
    private String statusPedido;
    private String mesa;

    public boolean realizarPedido() {
        String  sql  = "INSERT INTO pedidocliente (codmesa, ";
                sql += " codproduto, observacao, statuspagto, statuspedido) ";
                sql += "VALUES(?,?,?,?,?)";
        Connection con = Conexao.conectar();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt   (1, this.codMesa);
            stm.setInt   (2, this.codProduto);
            stm.setString(3, this.observacao);
            stm.setString(4, this.statusPagto);
            stm.setString(5, this.statusPedido);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean alterarPedido(int pCodPedido) {
        Connection con = Conexao.conectar();
        String  sql  = "UPDATE pedidocliente ";
                sql += " SET codproduto    = ?, ";
                sql += "     observacao   = ? ";
                sql += " WHERE codpedido  = ? ";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt   (1, this.codProduto);
            stm.setString(2, this.observacao);
            stm.setInt   (3, pCodPedido);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public PedidoCliente consultarPedido(int pCodMesa) {
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codpedido, a.codproduto, c.descproduto, ";
                sql += "a.observacao, a.statuspagto, a.statuspedido ";
                sql += "FROM pedidocliente a, cardapio c, mesa b ";
                sql += "WHERE a.codpedido = ? ";
                sql += "AND a.codproduto = c.codproduto ";
                sql += "AND a.codmesa = b.codmesa ";
        PedidoCliente pedcliente = null;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodMesa);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pedcliente = new PedidoCliente();
                pedcliente.setCodPedido(rs.getInt("codpedido"));
                pedcliente.setCodProduto(rs.getInt("codproduto"));
                pedcliente.setDescProduto(rs.getString("descproduto"));
                pedcliente.setObservacao(rs.getString("observacao"));
                pedcliente.setStatusPagto(rs.getString("statuspagto"));
                pedcliente.setStatusPedido(rs.getString("statuspedido"));
                pedcliente.setCodMesa(rs.getInt("codmesa"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return pedcliente;
    }
    
     public List<PedidoCliente> consultaPedido(String pStatusPedido) {
        List<PedidoCliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codpedido, a.codproduto, c.descproduto, a.codmesa, ";
                sql += "a.observacao, a.statuspagto, a.statuspedido, c.preco, b.mesa ";
                sql += "FROM pedidocliente a, cardapio c, mesa b ";
                sql += " WHERE a.statuspedido = ? ";
                sql += "AND a.codproduto = c.codproduto ";
                sql += "AND a.codmesa = b.codmesa ";
                sql += "AND a.codproduto = c.codproduto ";
                sql += "ORDER BY b.codmesa";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, pStatusPedido);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PedidoCliente pedcliente = new PedidoCliente();
                pedcliente.setCodPedido(rs.getInt("codpedido"));
                pedcliente.setMesa(rs.getString("mesa"));
                pedcliente.setCodProduto(rs.getInt("codproduto"));
                pedcliente.setDescProduto(rs.getString("descproduto"));
                pedcliente.setCodMesa(rs.getInt("codmesa"));
                pedcliente.setObservacao(rs.getString("observacao"));
                pedcliente.setStatusPagto(rs.getString("statuspagto"));
                pedcliente.setStatusPedido(rs.getString("statuspedido"));
                pedcliente.setPreco(rs.getFloat("preco"));
                lista.add(pedcliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<PedidoCliente> lovPedidos() {
        List<PedidoCliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codpedido, a.codproduto, c.descproduto, a.codmesa, ";
                sql += "a.observacao, a.statuspagto, a.statuspedido, c.preco, b.mesa ";
                sql += "FROM pedidocliente a, cardapio c, mesa b ";
                sql += "WHERE a.codproduto = c.codproduto ";
                sql += "AND a.codmesa = b.codmesa ";
                sql += "AND a.codproduto = c.codproduto ";
                sql += "ORDER BY b.codmesa";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                PedidoCliente pedcliente = new PedidoCliente();
                pedcliente.setCodPedido(rs.getInt("codpedido"));
                pedcliente.setMesa(rs.getString("mesa"));
                pedcliente.setCodProduto(rs.getInt("codproduto"));
                pedcliente.setDescProduto(rs.getString("descproduto"));
                pedcliente.setCodMesa(rs.getInt("codmesa"));
                pedcliente.setObservacao(rs.getString("observacao"));
                pedcliente.setStatusPagto(rs.getString("statuspagto"));
                pedcliente.setStatusPedido(rs.getString("statuspedido"));
                pedcliente.setPreco(rs.getFloat("preco"));
                lista.add(pedcliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<PedidoCliente> lovMesa() {
        List<PedidoCliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT codmesa, mesa ";
                sql += "FROM mesa ";
                sql += "ORDER BY codmesa";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                PedidoCliente pedcliente = new PedidoCliente();
                pedcliente.setCodMesa(rs.getInt("codmesa"));
                pedcliente.setMesa(rs.getString("mesa"));
                lista.add(pedcliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

    public boolean cancelarPedido(int pCodPedido) {
        Connection con = Conexao.conectar();
        String  sql  = "DELETE FROM pedidocliente ";
                sql += " WHERE codpedido = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodPedido);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    /* ÁREA DE GETTERS E SETTERS */ 

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodMesa() {
        return codMesa;
    }

    public void setCodMesa(int codMesa) {
        this.codMesa = codMesa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatusPagto() {
        return statusPagto;
    }

    public void setStatusPagto(String statusPagto) {
        this.statusPagto = statusPagto;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    @Override
    public int getCodProduto() {
        return codProduto;
    }

    @Override
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
}
