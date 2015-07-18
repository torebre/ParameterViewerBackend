package com.kjipo.sqlTables;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QParameters is a Querydsl query type for QParameters
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParameters extends com.mysema.query.sql.RelationalPathBase<QParameters> {

    private static final long serialVersionUID = 366316750;

    public static final QParameters parameters = new QParameters("PARAMETERS");

    public final StringPath name = createString("name");

    public QParameters(String variable) {
        super(QParameters.class, forVariable(variable), "PUBLIC", "PARAMETERS");
        addMetadata();
    }

    public QParameters(String variable, String schema, String table) {
        super(QParameters.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QParameters(Path<? extends QParameters> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PARAMETERS");
        addMetadata();
    }

    public QParameters(PathMetadata<?> metadata) {
        super(QParameters.class, metadata, "PUBLIC", "PARAMETERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(1).ofType(Types.VARCHAR).withSize(50));
    }

}

