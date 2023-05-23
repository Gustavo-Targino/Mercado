/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Produto;

/**
 *
 * @author Gustavo
 */
public class JDBCProduto {
    
    Connection conexao;
    
    public JDBCProduto(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void inserirProduto(Produto p) {
        
        String sql = "insert into produto(nome, valor, quantidade, genero) values(?, ?, ?, ?)";
        PreparedStatement ps;
        
        try {
            
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setInt(3, p.getQuantidade());
            ps.setString(4, p.getGenero());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso" , "Operação", JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void atualizarProduto(int id, String nome, int valor, int quantidade, String genero) {
        
        String sql = String.format("update produto set nome = '%s', valor = %d, quantidade = %d, genero = '%s' WHERE id = %d", nome, valor, quantidade, genero, id);
        
        PreparedStatement ps;
        
         try {
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso" , "Operação", JOptionPane.INFORMATION_MESSAGE);
           
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
     
     public Produto listarProdutoEspecifico(String id) {
        
         String nome="", genero="";
         int valor=0, quantidade=0;
     
        String sql = "select * from produto where id="+id;
        
        try {
            
            Statement declaracao = conexao.createStatement();
            ResultSet resposta = declaracao.executeQuery(sql);
            
            while(resposta.next()) {
               
                nome = resposta.getString("nome");
                valor = resposta.getInt("valor");
                quantidade = resposta.getInt("quantidade");
                genero = resposta.getString("genero");
                
          
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        Produto p = new Produto(nome, valor, quantidade, genero);
        return p;
    }

    public ArrayList<Produto> listarProdutos() {
        
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        
        String sql = "select * from produto";
        
        try {
            
            Statement declaracao = conexao.createStatement();
            ResultSet resposta = declaracao.executeQuery(sql);
            
            while(resposta.next()) {
                
                int id = resposta.getInt("id");
                String nome = resposta.getString("nome");
                int valor = resposta.getInt("valor");
                int quantidade = resposta.getInt("quantidade");
                String genero = resposta.getString("genero");
                
                Produto p = new Produto(id, nome, valor, quantidade, genero);
                produtos.add(p);
                
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        
        return produtos;
    }
    
    public void apagarProduto(String id) {
        String sql = "delete from produto where id="+id;
        
        PreparedStatement ps;
        
        try {
            
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto apagado com sucesso" , "Operação", JOptionPane.INFORMATION_MESSAGE);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void apagarTudo() {
        
        String sql = "delete from produto";
        
        PreparedStatement ps;
        
        try {
            
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
            
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
}
