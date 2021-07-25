package com.esgi.al2.projet.annuel.levelUp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DockerBuildException extends RuntimeException {

    public DockerBuildException(String message) {
        super(message);
    }

}
