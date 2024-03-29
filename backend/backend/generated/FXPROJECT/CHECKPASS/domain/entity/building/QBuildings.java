package FXPROJECT.CHECKPASS.domain.entity.building;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBuildings is a Querydsl query type for Buildings
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuildings extends EntityPathBase<Buildings> {

    private static final long serialVersionUID = 904866146L;

    public static final QBuildings buildings = new QBuildings("buildings");

    public final NumberPath<Integer> buildingCode = createNumber("buildingCode", Integer.class);

    public final StringPath buildingName = createString("buildingName");

    public QBuildings(String variable) {
        super(Buildings.class, forVariable(variable));
    }

    public QBuildings(Path<? extends Buildings> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuildings(PathMetadata metadata) {
        super(Buildings.class, metadata);
    }

}

