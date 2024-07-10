package BaseDeDados;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moc3jvl
 */
public class BancoDeDados {
    public Connection conexao = null;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String nomeDoBanco = "projeto_java";
    private final String local = "jdbc:mysql://localhost:3306/"+nomeDoBanco;
    private final String login = "root";
    private final String senha = "zyhua1U#oyox";
    
    public boolean getConnection(){
        try{
            Class.forName(driver);
            conexao=DriverManager.getConnection(local,login,senha);
            return true;
        } catch(ClassNotFoundException erro){
            System.out.println("Drive não encontrado"+erro.toString());
            return false;
        } catch(SQLException erro){
            System.out.println("Falha na conexão"+erro.toString());
            return false;
        }
    }
}
