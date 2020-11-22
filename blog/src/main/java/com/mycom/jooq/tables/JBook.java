/*
 * This file is generated by jOOQ.
 */
package com.mycom.jooq.tables;


import com.mycom.jooq.JSpbooter;
import com.mycom.jooq.Keys;
import com.mycom.jooq.tables.records.JBookRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JBook extends TableImpl<JBookRecord> {

    private static final long serialVersionUID = -263809234;

    /**
     * The reference instance of <code>SPBOOTER.BOOK</code>
     */
    public static final JBook BOOK = new JBook();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JBookRecord> getRecordType() {
        return JBookRecord.class;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public final TableField<JBookRecord, Object> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.OTHER.defaultValue(org.jooq.impl.DSL.field("", org.jooq.impl.SQLDataType.OTHER)), this, "");

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public final TableField<JBookRecord, Object> NAME = createField(DSL.name("NAME"), org.jooq.impl.SQLDataType.OTHER.defaultValue(org.jooq.impl.DSL.field("", org.jooq.impl.SQLDataType.OTHER)), this, "");

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public final TableField<JBookRecord, Object> STORE_ID = createField(DSL.name("STORE_ID"), org.jooq.impl.SQLDataType.OTHER.defaultValue(org.jooq.impl.DSL.field("", org.jooq.impl.SQLDataType.OTHER)), this, "");

    /**
     * Create a <code>SPBOOTER.BOOK</code> table reference
     */
    public JBook() {
        this(DSL.name("BOOK"), null);
    }

    /**
     * Create an aliased <code>SPBOOTER.BOOK</code> table reference
     */
    public JBook(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>SPBOOTER.BOOK</code> table reference
     */
    public JBook(Name alias) {
        this(alias, BOOK);
    }

    private JBook(Name alias, Table<JBookRecord> aliased) {
        this(alias, aliased, null);
    }

    private JBook(Name alias, Table<JBookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> JBook(Table<O> child, ForeignKey<O, JBookRecord> key) {
        super(child, key, BOOK);
    }

    @Override
    public Schema getSchema() {
        return JSpbooter.SPBOOTER;
    }

    @Override
    public UniqueKey<JBookRecord> getPrimaryKey() {
        return Keys.PK_BOOK;
    }

    @Override
    public List<UniqueKey<JBookRecord>> getKeys() {
        return Arrays.<UniqueKey<JBookRecord>>asList(Keys.PK_BOOK);
    }

    @Override
    public JBook as(String alias) {
        return new JBook(DSL.name(alias), this);
    }

    @Override
    public JBook as(Name alias) {
        return new JBook(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JBook rename(String name) {
        return new JBook(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JBook rename(Name name) {
        return new JBook(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Object, Object, Object> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}