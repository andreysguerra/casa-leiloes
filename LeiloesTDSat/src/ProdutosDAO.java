/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
      String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try (Connection conn = new conectaDAO().connect();
         PreparedStatement prep = conn.prepareStatement(sql)) {

        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
         ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    try (Connection conn = new conectaDAO().connect();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet rs = prep.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO dto = new ProdutosDTO();
            dto.setId(rs.getInt("id"));       // precisa ter campo id no DTO
            dto.setNome(rs.getString("nome"));
            dto.setValor(rs.getInt("valor"));
            dto.setStatus(rs.getString("status"));
            lista.add(dto);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }
    return lista;
    }
    
    
    
        
}

