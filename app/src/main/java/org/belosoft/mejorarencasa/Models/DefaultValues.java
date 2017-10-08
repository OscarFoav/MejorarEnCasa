package org.belosoft.mejorarencasa.Models;

import org.belosoft.mejorarencasa.App.MyApplication;

import java.util.Date;

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
    String push_ups;                      // pushups
    @Required
    String repetition_number_push_ups;    //seriesnumberpushups
    @Required
    String level_first_push_ups;          //firstlevelpushups
    @Required
    String level_second_push_ups;         //secondlevelpushups
    @Required
    String abs;                           //abs
    @Required
    String repetition_number_abs;         //seriesnumberabs
    @Required
    String level_first_abs;               //firstlevelabs
    @Required
    String level_second_abs;              //secondlevelabs
    @Required
    String dips;                          //dips
    @Required
    String repetition_number_dips;        //seriesnumberdips
    @Required
    String level_first_dips;              //firstleveldips
    @Required
    String level_second_dips;             //secondleveldips
    @Required
    String squats;                        //squats
    @Required
    String repetition_number_squats;      //seriesnumbersquats
    @Required
    String level_first_squats;            //firstlevelsquats
    @Required
    String level_second_squats;           //secondlevelsquats
    @Required
    String pull_ups;                      //pullups
    @Required
    String repetition_number_pull_ups;    //seriesnumberpullups
    @Required
    String level_first_pull_ups;          //firstlevelpullups
    @Required
    String level_second_pull_ups;         //secondlevelpullups
    @Required
    String calves;                        //calves
    @Required
    String repetition_number_calves;      //seriescalves
    @Required
    String level_first_calves;            //firstlevelcalves
    @Required
    String level_second_calves;           //secondlevelcalves
    @Required
    private Date createAt;

    public DefaultValues() {

    }

    public DefaultValues(
            String push_ups
            , String repetition_number_push_ups
            , String level_first_push_ups
            , String level_second_push_ups
            , String abs
            , String repetition_number_abs
            , String level_first_abs
            , String level_second_abs
            , String dips
            , String repetition_number_dips
            , String level_first_dips
            , String level_second_dips
            , String squats
            , String repetition_number_squats
            , String level_first_squats
            , String level_second_squats
            , String pull_ups
            , String repetition_number_pull_ups
            , String level_first_pull_ups
            , String level_second_pull_ups
            , String calves
            , String repetition_number_calves
            , String level_first_calves
            , String level_second_calves) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.push_ups = push_ups;
        this.repetition_number_push_ups = repetition_number_push_ups;
        this.level_first_push_ups = level_first_push_ups;
        this.level_second_push_ups = level_second_push_ups;
        this.abs = abs;
        this.repetition_number_abs = repetition_number_abs;
        this.level_first_abs = level_first_abs;
        this.level_second_abs = level_second_abs;
        this.dips = dips;
        this.repetition_number_dips = repetition_number_dips;
        this.level_first_dips = level_first_dips;
        this.level_second_dips = level_second_dips;
        this.squats = squats;
        this.repetition_number_squats = repetition_number_squats;
        this.level_first_squats = level_first_squats;
        this.level_second_squats = level_second_squats;
        this.pull_ups = pull_ups;
        this.repetition_number_pull_ups = repetition_number_pull_ups;
        this.level_first_pull_ups = level_first_pull_ups;
        this.level_second_pull_ups = level_second_pull_ups;
        this.calves = calves;
        this.repetition_number_calves = repetition_number_calves;
        this.level_first_calves = level_first_calves;
        this.level_second_calves = level_second_calves;
        this.createAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getPush_ups() {
        return push_ups;
    }

    public void setPush_ups(String push_ups) {
        this.push_ups = push_ups;
    }

    public String getRepetition_number_push_ups() {
        return repetition_number_push_ups;
    }

    public void setRepetition_number_push_ups(String repetition_number_push_ups) {
        this.repetition_number_push_ups = repetition_number_push_ups;
    }

    public String getLevel_first_push_ups() {
        return level_first_push_ups;
    }

    public void setLevel_first_push_ups(String level_first_push_ups) {
        this.level_first_push_ups = level_first_push_ups;
    }

    public String getLevel_second_push_ups() {
        return level_second_push_ups;
    }

    public void setLevel_second_push_ups(String level_second_push_ups) {
        this.level_second_push_ups = level_second_push_ups;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getRepetition_number_abs() {
        return repetition_number_abs;
    }

    public void setRepetition_number_abs(String repetition_number_abs) {
        this.repetition_number_abs = repetition_number_abs;
    }

    public String getLevel_first_abs() {
        return level_first_abs;
    }

    public void setLevel_first_abs(String level_first_abs) {
        this.level_first_abs = level_first_abs;
    }

    public String getLevel_second_abs() {
        return level_second_abs;
    }

    public void setLevel_second_abs(String level_second_abs) {
        this.level_second_abs = level_second_abs;
    }

    public String getDips() {
        return dips;
    }

    public void setDips(String dips) {
        this.dips = dips;
    }

    public String getRepetition_number_dips() {
        return repetition_number_dips;
    }

    public void setRepetition_number_dips(String repetition_number_dips) {
        this.repetition_number_dips = repetition_number_dips;
    }

    public String getLevel_first_dips() {
        return level_first_dips;
    }

    public void setLevel_first_dips(String level_first_dips) {
        this.level_first_dips = level_first_dips;
    }

    public String getLevel_second_dips() {
        return level_second_dips;
    }

    public void setLevel_second_dips(String level_second_dips) {
        this.level_second_dips = level_second_dips;
    }

    public String getSquats() {
        return squats;
    }

    public void setSquats(String squats) {
        this.squats = squats;
    }

    public String getRepetition_number_squats() {
        return repetition_number_squats;
    }

    public void setRepetition_number_squats(String repetition_number_squats) {
        this.repetition_number_squats = repetition_number_squats;
    }

    public String getLevel_first_squats() {
        return level_first_squats;
    }

    public void setLevel_first_squats(String level_first_squats) {
        this.level_first_squats = level_first_squats;
    }

    public String getLevel_second_squats() {
        return level_second_squats;
    }

    public void setLevel_second_squats(String level_second_squats) {
        this.level_second_squats = level_second_squats;
    }

    public String getPull_ups() {
        return pull_ups;
    }

    public void setPull_ups(String pull_ups) {
        this.pull_ups = pull_ups;
    }

    public String getRepetition_number_pull_ups() {
        return repetition_number_pull_ups;
    }

    public void setRepetition_number_pull_ups(String repetition_number_pull_ups) {
        this.repetition_number_pull_ups = repetition_number_pull_ups;
    }

    public String getLevel_first_pull_ups() {
        return level_first_pull_ups;
    }

    public void setLevel_first_pull_ups(String level_first_pull_ups) {
        this.level_first_pull_ups = level_first_pull_ups;
    }

    public String getLevel_second_pull_ups() {
        return level_second_pull_ups;
    }

    public void setLevel_second_pull_ups(String level_second_pull_ups) {
        this.level_second_pull_ups = level_second_pull_ups;
    }

    public String getCalves() {
        return calves;
    }

    public void setCalves(String calves) {
        this.calves = calves;
    }

    public String getRepetition_number_calves() {
        return repetition_number_calves;
    }

    public void setRepetition_number_calves(String repetition_number_calves) {
        this.repetition_number_calves = repetition_number_calves;
    }

    public String getLevel_first_calves() {
        return level_first_calves;
    }

    public void setLevel_first_calves(String level_first_calves) {
        this.level_first_calves = level_first_calves;
    }

    public String getLevel_second_calves() {
        return level_second_calves;
    }

    public void setLevel_second_calves(String level_second_calves) {
        this.level_second_calves = level_second_calves;
    }

    public Date getCreateAt() {
        return createAt;
    }
}
