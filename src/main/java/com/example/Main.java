package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java";
        String usuario = "root";
        String contraseña = "root";
        
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            
            if (conexion != null) {
                System.out.println("¡Conexión exitosa!");

                // Crear un Statement para ejecutar la consulta
                Statement statement = conexion.createStatement();
                
                // Definir la consulta SQL
                String consulta = "SELECT * FROM empleados"; 
                
                // Ejecutar la consulta
                ResultSet resultado = statement.executeQuery(consulta);
                
                // Procesar los resultados
                while (resultado.next()) {
                    // Asumiendo que la tabla tiene columnas 'id' y 'nombre'
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    
                    // Mostrar los resultados
                    System.out.println("ID: " + id + ", Nombre: " + nombre);
                }
                
                // Cerrar el ResultSet y el Statement
                resultado.close();
                statement.close();
            } else {
                System.out.println("Fallo al conectar.");
            }
            
            // Cerrar la conexión
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + e.getMessage());
        }
    }
}
