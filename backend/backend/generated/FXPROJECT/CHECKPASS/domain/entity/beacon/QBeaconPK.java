package FXPROJECT.CHECKPASS.domain.entity.beacon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeaconPK is a Querydsl query type for BeaconPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBeaconPK extends BeanPath<BeaconPK> {

    private static final long serialVersionUID = -1062206228L;

    public static final QBeaconPK beaconPK = new QBeaconPK("beaconPK");

    public final NumberPath<Integer> major = createNumber("major", Integer.class);

    public final NumberPath<Integer> minor = createNumber("minor", Integer.class);

    public QBeaconPK(String variable) {
        super(BeaconPK.class, forVariable(variable));
    }

    public QBeaconPK(Path<? extends BeaconPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeaconPK(PathMetadata metadata) {
        super(BeaconPK.class, metadata);
    }

}

