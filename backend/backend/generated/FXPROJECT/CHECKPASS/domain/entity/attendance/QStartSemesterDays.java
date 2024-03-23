package FXPROJECT.CHECKPASS.domain.entity.attendance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStartSemesterDays is a Querydsl query type for StartSemesterDays
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStartSemesterDays extends EntityPathBase<StartSemesterDays> {

    private static final long serialVersionUID = -328203511L;

    public static final QStartSemesterDays startSemesterDays = new QStartSemesterDays("startSemesterDays");

    public final NumberPath<Integer> semester = createNumber("semester", Integer.class);

    public final StringPath startSemesterDay = createString("startSemesterDay");

    public QStartSemesterDays(String variable) {
        super(StartSemesterDays.class, forVariable(variable));
    }

    public QStartSemesterDays(Path<? extends StartSemesterDays> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStartSemesterDays(PathMetadata metadata) {
        super(StartSemesterDays.class, metadata);
    }

}

