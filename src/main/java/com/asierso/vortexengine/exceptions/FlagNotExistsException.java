package com.asierso.vortexengine.exceptions;

/**
 * Runtime exception that handles when trying to access to a flag that doesn't exist
 *
 * @author Asierso
 */
public class FlagNotExistsException extends RuntimeException{
    public FlagNotExistsException(String key){
        super("Flag '" + key + "'not exists");
    }
}
