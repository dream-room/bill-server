package com.dream.room.bill.common;

import org.hibernate.dialect.MySQL57Dialect;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/5/11.
 */
public class MySQLDialectWithoutFK extends MySQL57Dialect {

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return "";
    }

}
