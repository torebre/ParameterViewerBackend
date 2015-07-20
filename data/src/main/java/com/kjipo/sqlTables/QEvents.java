package com.kjipo.sqlTables;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QEvents is a Querydsl query type for QEvents
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QEvents extends com.mysema.query.sql.RelationalPathBase<QEvents> {

    private static final long serialVersionUID = 331791869;

    public static final QEvents events = new QEvents("EVENTS");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> logid = createNumber("logid", Integer.class);

    public final NumberPath<Integer> start = createNumber("start", Integer.class);

    public final NumberPath<Integer> stop = createNumber("stop", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QEvents> sysPk10095 = createPrimaryKey(id);

    public QEvents(String variable) {
        super(QEvents.class, forVariable(variable), "PUBLIC", "EVENTS");
        addMetadata();
    }

    public QEvents(String variable, String schema, String table) {
        super(QEvents.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEvents(Path<? extends QEvents> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "EVENTS");
        addMetadata();
    }

    public QEvents(PathMetadata<?> metadata) {
        super(QEvents.class, metadata, "PUBLIC", "EVENTS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(logid, ColumnMetadata.named("LOGID").withIndex(2).ofType(Types.INTEGER).withSize(32));
        addMetadata(start, ColumnMetadata.named("START").withIndex(3).ofType(Types.INTEGER).withSize(32));
        addMetadata(stop, ColumnMetadata.named("STOP").withIndex(4).ofType(Types.INTEGER).withSize(32));
    }

}

