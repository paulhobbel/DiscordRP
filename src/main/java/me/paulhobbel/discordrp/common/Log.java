package me.paulhobbel.discordrp.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private static Logger logger = LogManager.getLogger("DiscordRP");

    private Log() {}

    public static void info(String message, Object... params) {
        logger.info(message, params);
    }

    public static void warn(String message, Object... params) {
        logger.warn(message, params);
    }

    public static void debug(String message, Object... params) {
        logger.debug(message, params);
    }

    public static void error(String message, Object... params) {
        logger.error(message, params);
    }

    public static void error(String message, Throwable e) {
        logger.error(message, e);
    }
}
