package com.example.carpulin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    @SerializedName("valido")
    private boolean valido;

    public boolean getValido() {
        return valido;
    }
}
