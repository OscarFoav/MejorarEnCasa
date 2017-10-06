package org.belosoft.mejorarencasa.Models;

import org.belosoft.mejorarencasa.App.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class DefaultValues extends RealmObject {

    @PrimaryKey
    private int id;                                             // clave unica
    @Required
    String default_values_column_push_ups;                      // pushups
    @Required
    String default_values_column_repetition_number_push_ups;    //seriesnumberpushups
    @Required
    String default_values_column_level_first_push_ups;          //firstlevelpushups
    @Required
    String default_values_column_level_second_push_ups;         //secondlevelpushups
    @Required
    String default_values_column_abs;                           //abs
    @Required
    String default_values_column_repetition_number_abs;         //seriesnumberabs
    @Required
    String default_values_column_level_first_abs;               //firstlevelabs
    @Required
    String default_values_column_level_second_abs;              //secondlevelabs
    @Required
    String default_values_column_dips;                          //dips
    @Required
    String default_values_column_repetition_number_dips;        //seriesnumberdips
    @Required
    String default_values_column_level_first_dips;              //firstleveldips
    @Required
    String default_values_column_level_second_dips;             //secondleveldips
    @Required
    String default_values_column_squats;                        //squats
    @Required
    String default_values_column_repetition_number_squats;      //seriesnumbersquats
    @Required
    String default_values_column_level_first_squats;            //firstlevelsquats
    @Required
    String default_values_column_level_second_squats;           //secondlevelsquats
    @Required
    String default_values_column_pull_ups;                      //pullups
    @Required
    String default_values_column_repetition_number_pull_ups;    //seriesnumberpullups
    @Required
    String default_values_column_level_first_pull_ups;          //firstlevelpullups
    @Required
    String default_values_column_level_second_pull_ups;         //secondlevelpullups
    @Required
    String default_values_column_calves;                        //calves
    @Required
    String default_values_column_repetition_number_calves;      //seriescalves
    @Required
    String default_values_column_level_first_calves;            //firstlevelcalves
    @Required
    String default_values_column_level_second_calves;           //secondlevelcalves


    public DefaultValues() {

    }

    public DefaultValues(
            String default_values_column_push_ups
            , String default_values_column_repetition_number_push_ups
            , String default_values_column_level_first_push_ups
            , String default_values_column_level_second_push_ups
            , String default_values_column_abs
            , String default_values_column_repetition_number_abs
            , String default_values_column_level_first_abs
            , String default_values_column_level_second_abs
            , String default_values_column_dips
            , String default_values_column_repetition_number_dips
            , String default_values_column_level_first_dips
            , String default_values_column_level_second_dips
            , String default_values_column_squats
            , String default_values_column_repetition_number_squats
            , String default_values_column_level_first_squats
            , String default_values_column_level_second_squats
            , String default_values_column_pull_ups
            , String default_values_column_repetition_number_pull_ups
            , String default_values_column_level_first_pull_ups
            , String default_values_column_level_second_pull_ups
            , String default_values_column_calves
            , String default_values_column_repetition_number_calves
            , String default_values_column_level_first_calves
            , String default_values_column_level_second_calves) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.default_values_column_push_ups = default_values_column_push_ups;
        this.default_values_column_repetition_number_push_ups = default_values_column_repetition_number_push_ups;
        this.default_values_column_level_first_push_ups = default_values_column_level_first_push_ups;
        this.default_values_column_level_second_push_ups = default_values_column_level_second_push_ups;
        this.default_values_column_abs = default_values_column_abs;
        this.default_values_column_repetition_number_abs = default_values_column_repetition_number_abs;
        this.default_values_column_level_first_abs = default_values_column_level_first_abs;
        this.default_values_column_level_second_abs = default_values_column_level_second_abs;
        this.default_values_column_dips = default_values_column_dips;
        this.default_values_column_repetition_number_dips = default_values_column_repetition_number_dips;
        this.default_values_column_level_first_dips = default_values_column_level_first_dips;
        this.default_values_column_level_second_dips = default_values_column_level_second_dips;
        this.default_values_column_squats = default_values_column_squats;
        this.default_values_column_repetition_number_squats = default_values_column_repetition_number_squats;
        this.default_values_column_level_first_squats = default_values_column_level_first_squats;
        this.default_values_column_level_second_squats = default_values_column_level_second_squats;
        this.default_values_column_pull_ups = default_values_column_pull_ups;
        this.default_values_column_repetition_number_pull_ups = default_values_column_repetition_number_pull_ups;
        this.default_values_column_level_first_pull_ups = default_values_column_level_first_pull_ups;
        this.default_values_column_level_second_pull_ups = default_values_column_level_second_pull_ups;
        this.default_values_column_calves = default_values_column_calves;
        this.default_values_column_repetition_number_calves = default_values_column_repetition_number_calves;
        this.default_values_column_level_first_calves = default_values_column_level_first_calves;
        this.default_values_column_level_second_calves = default_values_column_level_second_calves;
    }

    public int getId() {
        return id;
    }

    public String getDefault_values_column_push_ups() {
        return default_values_column_push_ups;
    }

    public void setDefault_values_column_push_ups(String default_values_column_push_ups) {
        this.default_values_column_push_ups = default_values_column_push_ups;
    }

    public String getDefault_values_column_repetition_number_push_ups() {
        return default_values_column_repetition_number_push_ups;
    }

    public void setDefault_values_column_repetition_number_push_ups(String default_values_column_repetition_number_push_ups) {
        this.default_values_column_repetition_number_push_ups = default_values_column_repetition_number_push_ups;
    }

    public String getDefault_values_column_level_first_push_ups() {
        return default_values_column_level_first_push_ups;
    }

    public void setDefault_values_column_level_first_push_ups(String default_values_column_level_first_push_ups) {
        this.default_values_column_level_first_push_ups = default_values_column_level_first_push_ups;
    }

    public String getDefault_values_column_level_second_push_ups() {
        return default_values_column_level_second_push_ups;
    }

    public void setDefault_values_column_level_second_push_ups(String default_values_column_level_second_push_ups) {
        this.default_values_column_level_second_push_ups = default_values_column_level_second_push_ups;
    }

    public String getDefault_values_column_abs() {
        return default_values_column_abs;
    }

    public void setDefault_values_column_abs(String default_values_column_abs) {
        this.default_values_column_abs = default_values_column_abs;
    }

    public String getDefault_values_column_repetition_number_abs() {
        return default_values_column_repetition_number_abs;
    }

    public void setDefault_values_column_repetition_number_abs(String default_values_column_repetition_number_abs) {
        this.default_values_column_repetition_number_abs = default_values_column_repetition_number_abs;
    }

    public String getDefault_values_column_level_first_abs() {
        return default_values_column_level_first_abs;
    }

    public void setDefault_values_column_level_first_abs(String default_values_column_level_first_abs) {
        this.default_values_column_level_first_abs = default_values_column_level_first_abs;
    }

    public String getDefault_values_column_level_second_abs() {
        return default_values_column_level_second_abs;
    }

    public void setDefault_values_column_level_second_abs(String default_values_column_level_second_abs) {
        this.default_values_column_level_second_abs = default_values_column_level_second_abs;
    }

    public String getDefault_values_column_dips() {
        return default_values_column_dips;
    }

    public void setDefault_values_column_dips(String default_values_column_dips) {
        this.default_values_column_dips = default_values_column_dips;
    }

    public String getDefault_values_column_repetition_number_dips() {
        return default_values_column_repetition_number_dips;
    }

    public void setDefault_values_column_repetition_number_dips(String default_values_column_repetition_number_dips) {
        this.default_values_column_repetition_number_dips = default_values_column_repetition_number_dips;
    }

    public String getDefault_values_column_level_first_dips() {
        return default_values_column_level_first_dips;
    }

    public void setDefault_values_column_level_first_dips(String default_values_column_level_first_dips) {
        this.default_values_column_level_first_dips = default_values_column_level_first_dips;
    }

    public String getDefault_values_column_level_second_dips() {
        return default_values_column_level_second_dips;
    }

    public void setDefault_values_column_level_second_dips(String default_values_column_level_second_dips) {
        this.default_values_column_level_second_dips = default_values_column_level_second_dips;
    }

    public String getDefault_values_column_squats() {
        return default_values_column_squats;
    }

    public void setDefault_values_column_squats(String default_values_column_squats) {
        this.default_values_column_squats = default_values_column_squats;
    }

    public String getDefault_values_column_repetition_number_squats() {
        return default_values_column_repetition_number_squats;
    }

    public void setDefault_values_column_repetition_number_squats(String default_values_column_repetition_number_squats) {
        this.default_values_column_repetition_number_squats = default_values_column_repetition_number_squats;
    }

    public String getDefault_values_column_level_first_squats() {
        return default_values_column_level_first_squats;
    }

    public void setDefault_values_column_level_first_squats(String default_values_column_level_first_squats) {
        this.default_values_column_level_first_squats = default_values_column_level_first_squats;
    }

    public String getDefault_values_column_level_second_squats() {
        return default_values_column_level_second_squats;
    }

    public void setDefault_values_column_level_second_squats(String default_values_column_level_second_squats) {
        this.default_values_column_level_second_squats = default_values_column_level_second_squats;
    }

    public String getDefault_values_column_pull_ups() {
        return default_values_column_pull_ups;
    }

    public void setDefault_values_column_pull_ups(String default_values_column_pull_ups) {
        this.default_values_column_pull_ups = default_values_column_pull_ups;
    }

    public String getDefault_values_column_repetition_number_pull_ups() {
        return default_values_column_repetition_number_pull_ups;
    }

    public void setDefault_values_column_repetition_number_pull_ups(String default_values_column_repetition_number_pull_ups) {
        this.default_values_column_repetition_number_pull_ups = default_values_column_repetition_number_pull_ups;
    }

    public String getDefault_values_column_level_first_pull_ups() {
        return default_values_column_level_first_pull_ups;
    }

    public void setDefault_values_column_level_first_pull_ups(String default_values_column_level_first_pull_ups) {
        this.default_values_column_level_first_pull_ups = default_values_column_level_first_pull_ups;
    }

    public String getDefault_values_column_level_second_pull_ups() {
        return default_values_column_level_second_pull_ups;
    }

    public void setDefault_values_column_level_second_pull_ups(String default_values_column_level_second_pull_ups) {
        this.default_values_column_level_second_pull_ups = default_values_column_level_second_pull_ups;
    }

    public String getDefault_values_column_calves() {
        return default_values_column_calves;
    }

    public void setDefault_values_column_calves(String default_values_column_calves) {
        this.default_values_column_calves = default_values_column_calves;
    }

    public String getDefault_values_column_repetition_number_calves() {
        return default_values_column_repetition_number_calves;
    }

    public void setDefault_values_column_repetition_number_calves(String default_values_column_repetition_number_calves) {
        this.default_values_column_repetition_number_calves = default_values_column_repetition_number_calves;
    }

    public String getDefault_values_column_level_first_calves() {
        return default_values_column_level_first_calves;
    }

    public void setDefault_values_column_level_first_calves(String default_values_column_level_first_calves) {
        this.default_values_column_level_first_calves = default_values_column_level_first_calves;
    }

    public String getDefault_values_column_level_second_calves() {
        return default_values_column_level_second_calves;
    }

    public void setDefault_values_column_level_second_calves(String default_values_column_level_second_calves) {
        this.default_values_column_level_second_calves = default_values_column_level_second_calves;
    }
}
