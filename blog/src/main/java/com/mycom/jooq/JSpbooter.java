/*
 * This file is generated by jOOQ.
 */
package com.mycom.jooq;


import com.mycom.jooq.tables.JBaseEntity;
import com.mycom.jooq.tables.JBoard;
import com.mycom.jooq.tables.JBook;
import com.mycom.jooq.tables.JBookStore;
import com.mycom.jooq.tables.JChatMessageRoom;
import com.mycom.jooq.tables.JChatRoom;
import com.mycom.jooq.tables.JFriend;
import com.mycom.jooq.tables.JLocation;
import com.mycom.jooq.tables.JReply;
import com.mycom.jooq.tables.JUser1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class JSpbooter extends SchemaImpl {

    private static final long serialVersionUID = -156406165;

    /**
     * The reference instance of <code>SPBOOTER</code>
     */
    public static final JSpbooter SPBOOTER = new JSpbooter();

    /**
     * The table <code>SPBOOTER.BASE_ENTITY</code>.
     */
    public final JBaseEntity BASE_ENTITY = com.mycom.jooq.tables.JBaseEntity.BASE_ENTITY;

    /**
     * The table <code>SPBOOTER.BOARD</code>.
     */
    public final JBoard BOARD = com.mycom.jooq.tables.JBoard.BOARD;

    /**
     * The table <code>SPBOOTER.BOOK</code>.
     */
    public final JBook BOOK = com.mycom.jooq.tables.JBook.BOOK;

    /**
     * The table <code>SPBOOTER.BOOK_STORE</code>.
     */
    public final JBookStore BOOK_STORE = com.mycom.jooq.tables.JBookStore.BOOK_STORE;

    /**
     * The table <code>SPBOOTER.CHAT_MESSAGE_ROOM</code>.
     */
    public final JChatMessageRoom CHAT_MESSAGE_ROOM = com.mycom.jooq.tables.JChatMessageRoom.CHAT_MESSAGE_ROOM;

    /**
     * The table <code>SPBOOTER.CHAT_ROOM</code>.
     */
    public final JChatRoom CHAT_ROOM = com.mycom.jooq.tables.JChatRoom.CHAT_ROOM;

    /**
     * The table <code>SPBOOTER.FRIEND</code>.
     */
    public final JFriend FRIEND = com.mycom.jooq.tables.JFriend.FRIEND;

    /**
     * The table <code>SPBOOTER.LOCATION</code>.
     */
    public final JLocation LOCATION = com.mycom.jooq.tables.JLocation.LOCATION;

    /**
     * The table <code>SPBOOTER.REPLY</code>.
     */
    public final JReply REPLY = com.mycom.jooq.tables.JReply.REPLY;

    /**
     * The table <code>SPBOOTER.USER1</code>.
     */
    public final JUser1 USER1 = com.mycom.jooq.tables.JUser1.USER1;

    /**
     * No further instances allowed
     */
    private JSpbooter() {
        super("SPBOOTER", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            JBaseEntity.BASE_ENTITY,
            JBoard.BOARD,
            JBook.BOOK,
            JBookStore.BOOK_STORE,
            JChatMessageRoom.CHAT_MESSAGE_ROOM,
            JChatRoom.CHAT_ROOM,
            JFriend.FRIEND,
            JLocation.LOCATION,
            JReply.REPLY,
            JUser1.USER1);
    }
}
