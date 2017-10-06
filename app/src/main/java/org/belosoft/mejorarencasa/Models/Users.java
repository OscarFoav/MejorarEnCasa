package org.belosoft.mejorarencasa.Models;

import org.belosoft.mejorarencasa.App.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class Users extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    String user;
    @Required
    String user_age;
    @Required
    String user_weight;
    @Required
    String series_name;
    @Required
    String repetition_series_one;
    @Required
    String repetition_series_two;
    @Required
    String repetition_series_three;
    @Required
    String repetition_series_four;
    @Required
    String repetition_series_five;

    public Users() {
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_weight() {
        return user_weight;
    }

    public void setUser_weight(String user_weight) {
        this.user_weight = user_weight;
    }

    public Users(
            int id
            , String user                               // usuario
            , String user_age
            , String user_weight
            , String series_name                        // nombre serie: flexiones, abdominales, ... gemelos
            , String repetition_series_one
            , String repetition_series_two
            , String repetition_series_three
            , String repetition_series_four
            , String repetition_series_five) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.user = user;
        this.user_age = user_age;
        this.user_weight = user_weight;
        this.series_name = series_name;
        this.repetition_series_one = repetition_series_one;
        this.repetition_series_two = repetition_series_two;
        this.repetition_series_three = repetition_series_three;
        this.repetition_series_four = repetition_series_four;
        this.repetition_series_five = repetition_series_five;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getRepetition_series_one() {
        return repetition_series_one;
    }

    public void setRepetition_series_one(String repetition_series_one) {
        this.repetition_series_one = repetition_series_one;
    }

    public String getRepetition_series_two() {
        return repetition_series_two;
    }

    public void setRepetition_series_two(String repetition_series_two) {
        this.repetition_series_two = repetition_series_two;
    }

    public String getRepetition_series_three() {
        return repetition_series_three;
    }

    public void setRepetition_series_three(String repetition_series_three) {
        this.repetition_series_three = repetition_series_three;
    }

    public String getRepetition_series_four() {
        return repetition_series_four;
    }

    public void setRepetition_series_four(String repetition_series_four) {
        this.repetition_series_four = repetition_series_four;
    }

    public String getRepetition_series_five() {
        return repetition_series_five;
    }

    public void setRepetition_series_five(String repetition_series_five) {
        this.repetition_series_five = repetition_series_five;
    }
}
