package com.esgi.al2.projet.annuel.levelUp.model;

import java.time.LocalDateTime;

public class Response {

    private int idExercice;
    private int idUser;
    private String codeSend;
    private LocalDateTime sendDate;

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCodeSend() {
        return codeSend;
    }

    public void setCodeSend(String codeSend) {
        this.codeSend = codeSend;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}
