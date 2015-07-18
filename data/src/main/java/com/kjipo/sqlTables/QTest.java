package com.kjipo.sqlTables;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTest is a Querydsl query type for QTest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTest extends com.mysema.query.sql.RelationalPathBase<QTest> {

    private static final long serialVersionUID = -200340874;

    public static final QTest test = new QTest("TEST");

    public final NumberPath<Integer> idtime = createNumber("idtime", Integer.class);

    public final NumberPath<Double> value1 = createNumber("value1", Double.class);

    public final com.mysema.query.sql.PrimaryKey<QTest> sysPk10092 = createPrimaryKey(idtime);

    public QTest(String variable) {
        super(QTest.class, forVariable(variable), "PUBLIC", "TEST");
        addMetadata();
    }

    public QTest(String variable, String schema, String table) {
        super(QTest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTest(Path<? extends QTest> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TEST");
        addMetadata();
    }

    public QTest(PathMetadata<?> metadata) {
        super(QTest.class, metadata, "PUBLIC", "TEST");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(idtime, ColumnMetadata.named("IDTIME").withIndex(1).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(value1, ColumnMetadata.named("VALUE1").withIndex(2).ofType(Types.DOUBLE).withSize(64));
    }

}

