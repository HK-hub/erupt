package com.erupt.model;

import com.erupt.annotation.EruptField;
import com.erupt.annotation.sub_field.EditType;
import com.erupt.annotation.util.ConfigUtil;
import com.erupt.util.EruptAnnoConver;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by liyuepeng on 10/10/18.
 */
public class EruptFieldModel implements Serializable {

    private transient EruptField eruptField;

    private transient Field field;

    private JsonObject eruptFieldJson;

    private String fieldName;

    private String fieldReturnName;

    public EruptFieldModel(EruptField eruptField, Field field) {
        this.eruptField = eruptField;
        this.field = field;
        this.eruptFieldJson = new JsonParser().parse(ConfigUtil.annoStrToJsonStr(eruptField.toString())).getAsJsonObject();
        this.fieldName = field.getName();
        this.fieldReturnName = field.getType().getSimpleName();
        //如果是Tab类型视图，数据必须为一对多关系管理，需要用泛型集合来存放，固！取出泛型的名称重新赋值到fieldReturnName中
        if (eruptField.edit().type() == EditType.TAB) {
            Type gType = field.getGenericType();
            if (gType instanceof ParameterizedType) {
                Type[] typeArguments = ((ParameterizedType) gType).getActualTypeArguments();
                System.out.println(typeArguments[0].getTypeName());
                String[] gArray = typeArguments[0].getTypeName().split("\\.");
                this.fieldReturnName = gArray[gArray.length - 1];
            }
        }
        EruptAnnoConver.validateEruptFieldInfo(this);
    }

    public void setEruptField(EruptField eruptField) {
        this.eruptField = eruptField;
    }

    public EruptField getEruptField() {
        return eruptField;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public JsonObject getEruptFieldJson() {
        return eruptFieldJson;
    }

    public void setEruptFieldJson(JsonObject eruptFieldJson) {
        this.eruptFieldJson = eruptFieldJson;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldReturnName() {
        return fieldReturnName;
    }

    public void setFieldReturnName(String fieldReturnName) {
        this.fieldReturnName = fieldReturnName;
    }
}