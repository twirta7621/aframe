import java.io.IOException;
import java.util.Map;
import java.sql.*;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

//javac -cp sqlite-jdbc-3.23.1.jar; aframeServer.java
//path C:\Program Files\Java\jdk1.8.0_131\bin
//path C:\Program Files\Java\jdk-12.0.1\bin
public class aframeServer{
    public static void main(String[] args)throws IOException{
        
        int port = 8600;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Database db = new Database("jdbc:sqlite:textures.db");

        String query = "SELECT * FROM textures";//  SQL statement
        String result = db.selectData(query);// runs statement

        
        server.createContext("/json", new RouteHandler(result));

        String code = Input.readFile("aframe.html");

        server.createContext("/code", new RouteHandler(code));
        
        System.out.println("Server is listening on port " + port);

        server.start(); 
    }
}
