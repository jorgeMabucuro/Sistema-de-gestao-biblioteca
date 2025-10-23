/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getsaobiblioteca.conexao;

import java.sql.*;
/**
 *
 * @author Jorge Mabucuro
 */
public class ModuloConexao {
    public static Connection conectar(){
        Connection conexao = null;
        String Driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bibliotecaJava";
        String user = "root";
        String password = "";
        try {
            Class.forName(Driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
//            System.out.println("Falha na conexão "+ e);
            return null;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        }
    }

    

    
}
