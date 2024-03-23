package FXPROJECT.CHECKPASS.domain.entity.beacon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBeacon is a Querydsl query type for Beacon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeacon extends EntityPathBase<Beacon> {

    private static final long serialVersionUID = -1717304527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBeacon beacon = new QBeacon("beacon");

    public final QBeaconPK beaconPK;

    public QBeacon(String variable) {
        this(Beacon.class, forVariable(variable), INITS);
    }

    public QBeacon(Path<? extends Beacon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBeacon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBeacon(PathMetadata metadata, PathInits inits) {
        this(Beacon.class, metadata, inits);
    }

    public QBeacon(Class<? extends Beacon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beaconPK = inits.isInitialized("beaconPK") ? new QBeaconPK(forProperty("beaconPK")) : null;
    }

}

