package com.asierso.vortexengine.exceptions;

/**
 * Runtime exception that handles when is created a flag without a correct key name
 *
 * @author Asierso
 */
public class InvalidFlagKeyException extends RuntimeException{
    public InvalidFlagKeyException(String key){
        super("Invalid flag key '" + key + "'");
    }
}
