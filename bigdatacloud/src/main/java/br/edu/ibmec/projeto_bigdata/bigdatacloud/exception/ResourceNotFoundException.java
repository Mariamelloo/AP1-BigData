package br.edu.ibmec.projeto_bigdata.bigdatacloud.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}