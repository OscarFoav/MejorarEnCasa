package org.belosoft.mejorarencasa.Activities;

import android.graphics.Movie;

/**
 * Created by oscar on 17/09/2017.
 */

public class Serie {

    public String name;
    public int boton;

    public Serie(){

    }


    public Serie(String name, int boton) {
        this.name = name;
        this.boton = boton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoton() {
        return boton;
    }

    public void setBoton(int boton) {
        this.boton = boton;
    }

}
