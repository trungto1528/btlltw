package com.btl.ltw.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for all DAO classes
 * Provides common database connection functionality
 */
public abstract class BaseDAO {

    @Autowired
    protected DataSource dataSource;

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
