package org.application.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class MySQLConnection {

    private final DataSource dataSource;

    public MySQLConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void checkConnection(){
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}
