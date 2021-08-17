package modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Conexao;

public class Administrador extends Insumo{
    
    private int codFornecedor;
    private int codPedFornecedor;
    private int codInsumo;
    private float qtdInsumo;
    private float custoInsumo;
    private String cnpj;
    private String descInsumo;
    private String razaoSocial;
    private String nomeContato;
    
    
    public boolean realizarPedidoItem() { 
        String  sql  = "INSERT INTO pedidofornecedoritem (codfornecedor, ";
                sql += " codinsumo, qtdinsumo) ";
                sql += "VALUES(?,?,?)";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt  (1, this.codFornecedor);
            stm.setInt  (2, this.codInsumo);
            stm.setFloat(3, this.qtdInsumo);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public Administrador consultarPedidoItem(int pCodFornecedor) { 
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codfornecedor, a.cnpj, a.razaosocial, ";
                sql += "a.nomecontato, b.codpedfornecedor, b.qtdinsumo, ";
                sql += "c.codinsumo, c.descinsumo, c.custoinsumo ";
                sql += "FROM fornecedor a, pedidofornecedoritem b, insumo c ";
                sql += "WHERE a.codfornecedor = ? ";
                sql += "AND a.codfornecedor = b.codfornecedor ";
                sql += "AND c.codinsumo = b.codinsumo ";

        Administrador adm = null;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodFornecedor);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                adm = new Administrador();
                adm.setCodFornecedor(rs.getInt("codfornecedor"));
                adm.setCodPedFornecedor(rs.getInt("codpedfornecedor"));
                adm.setCodInsumo(rs.getInt("codinsumo"));
                adm.setQtdInsumo(rs.getFloat("qtdinusmo"));
                adm.setCnpj(rs.getString("cnpj"));
                adm.setDescInsumo(rs.getString("descinsumo"));
                adm.setCustoInsumo(rs.getFloat("custoinsumo"));
                adm.setRazaoSocial(rs.getString("razaosocial"));
                adm.setNomeContato(rs.getString("nomecontato"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return adm;
    }
    
    public List<Administrador> consultaPedidoItem(int pCodPedFornecedor) {
        List<Administrador> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codfornecedor, a.cnpj, a.razaosocial, ";
                sql += "a.nomecontato, b.codpedfornecedor, b.qtdinsumo, ";
                sql += "c.codinsumo, c.descinsumo, c.custoinsumo ";
                sql += "FROM fornecedor a, pedidofornecedoritem b, insumo c ";
                sql += "WHERE a.codfornecedor = ? ";
                sql += "AND a.codfornecedor = b.codfornecedor ";
                sql += "AND c.codinsumo = b.codinsumo ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodPedFornecedor);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Administrador adm = new Administrador();
                adm.setCodFornecedor(rs.getInt("codfornecedor"));
                adm.setCodPedFornecedor(rs.getInt("codpedfornecedor"));
                adm.setCodInsumo(rs.getInt("codinsumo"));
                adm.setQtdInsumo(rs.getFloat("qtdinusmo"));
                adm.setCnpj(rs.getString("cnpj"));
                adm.setDescInsumo(rs.getString("descinsumo"));
                adm.setCustoInsumo(rs.getFloat("custoinsumo"));
                adm.setRazaoSocial(rs.getString("razaosocial"));
                adm.setNomeContato(rs.getString("nomecontato"));
                lista.add(adm);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Administrador> lovPedidosForn() {
        List<Administrador> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "SELECT a.codfornecedor, a.cnpj, a.razaosocial, ";
                sql += "a.nomecontato, b.codpedfornecedor, b.qtdinsumo, ";
                sql += "c.codinsumo, c.descinsumo, c.custoinsumo ";
                sql += "FROM fornecedor a, pedidofornecedoritem b, insumo c ";
                sql += "WHERE a.codfornecedor = ? ";
                sql += "AND a.codfornecedor = b.codfornecedor ";
                sql += "AND c.codinsumo = b.codinsumo ";
                sql += "ORDER BY a.codfornecedor";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                Administrador adm = new Administrador();
                adm.setCodFornecedor(rs.getInt("codfornecedor"));
                adm.setCodPedFornecedor(rs.getInt("codpedfornecedor"));
                adm.setCodInsumo(rs.getInt("codinsumo"));
                adm.setQtdInsumo(rs.getFloat("qtdinusmo"));
                adm.setCnpj(rs.getString("cnpj"));
                adm.setDescInsumo(rs.getString("descinsumo"));
                adm.setCustoInsumo(rs.getFloat("custoinsumo"));
                adm.setRazaoSocial(rs.getString("razaosocial"));
                adm.setNomeContato(rs.getString("nomecontato"));
                lista.add(adm);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean alterarPedidoItem(int pCodPedFornecedor) { 
        Connection con = Conexao.conectar();
        String  sql  = "UPDATE pedidofornecedoritem ";
                sql += " SET codinsumo    = ?, ";
                sql += "     qtdinsumo   = ? ";
                sql += " WHERE codpedfornecedor  = ? ";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt  (1, this.codInsumo);
            stm.setFloat(2, this.qtdInsumo);
            stm.setInt  (3, pCodPedFornecedor);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean excluirPedidoItem(int pCodPedFornecedor) { 
        Connection con = Conexao.conectar();
        String  sql  = "DELETE FROM pedidofornecedor ";
                sql += " WHERE codpedfornecedor = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, pCodPedFornecedor);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
        return true;
    }
    

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public int getCodPedFornecedor() {
        return codPedFornecedor;
    }

    public void setCodPedFornecedor(int codPedFornecedor) {
        this.codPedFornecedor = codPedFornecedor;
    }

    @Override
    public int getCodInsumo() {
        return codInsumo;
    }

    @Override
    public void setCodInsumo(int codInsumo) {
        this.codInsumo = codInsumo;
    }

    public float getQtdInsumo() {
        return qtdInsumo;
    }

    public void setQtdInsumo(float qtdInsumo) {
        this.qtdInsumo = qtdInsumo;
    }

    @Override
    public float getCustoInsumo() {
        return custoInsumo;
    }

    @Override
    public void setCustoInsumo(float custoInsumo) {
        this.custoInsumo = custoInsumo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getDescInsumo() {
        return descInsumo;
    }

    @Override
    public void setDescInsumo(String descInsumo) {
        this.descInsumo = descInsumo;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }
}
