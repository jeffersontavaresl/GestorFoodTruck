package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Conexao;

public class PedidoCliente {

    
    private int codPedido;
    private int codProduto;
    private int codMesa;
    private String produto;
    private String observacao;
    private String statusPagto;

    public boolean realizarPedido() {
        String  sql  = "INSERT INTO pedidocliente (codmesa, ";
                sql += " produto, observacao, statuspagto) ";
                sql += "VALUES(?,?,?,?)";
        Connection con = Conexao.conectar();

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt   (1, this.codMesa);
            stm.setString(2, this.produto);
            stm.setString(3, this.observacao);
            stm.setString(4, this.statusPagto);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean alterarPedido() {
        Connection con = Conexao.conectar();
        String  sql  = "UPDATE pedidocliente ";
                sql += " SET codproduto    = ?, ";
                sql += "     produto      = ?, ";
                sql += "     observacao   = ? ";
                sql += " WHERE codmesa  = ? ";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt   (1, this.codProduto);
            stm.setString(2, this.produto);
            stm.setString(3, this.observacao);
            stm.setInt   (4, this.codMesa);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public PedidoCliente consultarPedido(int pCodMesa) {
        Connection con = Conexao.conectar();
        String  sql  = "SELECT codproduto, produto, observacao, ";
                sql += " statuspagto ";
                sql += "FROM pedidocliente ";
                sql += "WHERE codmesa = ? ";
        PedidoCliente pedcliente = null;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodMesa);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pedcliente = new PedidoCliente();
                pedcliente.setCodProduto(rs.getInt("codproduto"));
                pedcliente.setProduto(rs.getString("produto"));
                pedcliente.setObservacao(rs.getString("observacao"));
                pedcliente.setStatusPagto(rs.getString("statuspagto"));

            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return pedcliente;
    }
    
     public List<PedidoCliente> consultaPedido(int pCodMesa) {
        List<PedidoCliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT codpedido, codproduto, produto, observacao, ";
                sql += "statuspagto, codmesa ";
                sql += "FROM pedidocliente ";
                sql += "WHERE codmesa = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodMesa);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PedidoCliente pedcliente = new PedidoCliente();
                pedcliente.setCodPedido(rs.getInt("codpedido"));
                pedcliente.setCodProduto(rs.getInt("codproduto"));
                pedcliente.setProduto(rs.getString("produto"));
                pedcliente.setObservacao(rs.getString("observacao"));
                pedcliente.setStatusPagto(rs.getString("statuspagto"));
                pedcliente.setCodMesa(rs.getInt("codmesa"));
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
        String  sql  = "SELECT codpedido, produto, codmesa";
                sql += "FROM pedidocliente ";
                sql += "ORDER BY codmesa";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                PedidoCliente pedcliente = new PedidoCliente();
                pedcliente.setCodProduto(rs.getInt("codpedido"));
                pedcliente.setProduto(rs.getString("produto"));
                pedcliente.setCodMesa(rs.getInt("codmesa"));
                lista.add(pedcliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

    public boolean cancelarPedido() {
        Connection con = Conexao.conectar();
        String  sql  = "DELETE FROM pedidocliente ";
                sql += " WHERE codmesa = ?";
                sql += " AND codproduto = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.codMesa);
            stm.setInt(2, this.codProduto);
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

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodMesa() {
        return codMesa;
    }

    public void setCodMesa(int codMesa) {
        this.codMesa = codMesa;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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
    

}
