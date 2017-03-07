package com.example;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static final int VERSION = 1;
    public static final String GREEN_DAO_CODE_PATH = "../loter/app/src/main/java";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(VERSION, "com.android.loter.db");

        addAttendace(schema);


        File f = new File(GREEN_DAO_CODE_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        new DaoGenerator().generateAll(schema, f.getAbsolutePath());
    }

    /**
     * 考勤表
     *
     * @param schema
     */
    private static void addAttendace(Schema schema) {
        Entity note = schema.addEntity("Attendace");
        note.addIdProperty().autoincrement();
        note.addStringProperty("time").notNull();
        note.addIntProperty("type").notNull();
        note.addIntProperty("projectid").notNull();
        note.addIntProperty("imageid");
        note.addStringProperty("imagebase64");
        note.addStringProperty("lot");
        note.addStringProperty("lat");
        note.addStringProperty("address");
        note.addIntProperty("projectlocationid").notNull();
        note.addStringProperty("networkTime");
    }



}
