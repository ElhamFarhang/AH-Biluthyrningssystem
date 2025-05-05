package com.example.ahbiluthyrningssystem.managers;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Principal;

public class LoggerManager {
    private String loggedInUser;
    private final Logger FUNC_LOG;
    private LoggerManager() {
        FUNC_LOG = LogManager.getLogger("functionality");
    }

    private static LoggerManager instance;

    public static LoggerManager getInstance() {
        if (instance == null) {
            instance = new LoggerManager();
        }
        return instance;
    }

    public static void setInstance(LoggerManager instance) {
        LoggerManager.instance = instance;
    }

    public void logInfo(String message) {
        FUNC_LOG.info("{} by user {}", message, loggedInUser);
    }
    public void logWarn(String message) {
        FUNC_LOG.warn(message);
    }
    public void logError(String message) {
        FUNC_LOG.error(message);
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
