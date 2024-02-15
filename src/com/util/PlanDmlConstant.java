package com.util;

public class PlanDmlConstant {

    public static final String SELECT_PLAN_BY_DAY = "SELECT * FROM Plan WHERE startDt <= STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\") AND endDt >= STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\")";

    public static final String INSERT_PLAN = "INSERT INTO Plan (id, title, text, startDt, endDt) VALUES (\"%s\",\"%s\",\"%s\",STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\"),STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\"))";

    public static final String DELETE_PLAN_BY_ID = "DELETE FROM Plan WHERE id=\"%s\"";

    public static final String UPDATE_PLAN_BY_ID = "UPDATE Plan SET title = \"%s\", text = \"%s\", startDt= STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\"), endDt= STR_TO_DATE(\"%s\",\"%Y%m%d%H%i%s\") WHERE id = \"%s\"";
}
