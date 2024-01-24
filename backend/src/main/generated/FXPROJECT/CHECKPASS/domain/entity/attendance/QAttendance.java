package FXPROJECT.CHECKPASS.domain.entity.attendance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendance is a Querydsl query type for Attendance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendance extends EntityPathBase<Attendance> {

    private static final long serialVersionUID = -2076200431L;

    public static final QAttendance attendance1 = new QAttendance("attendance1");

    public final BooleanPath attend = createBoolean("attend");

    public final NumberPath<Long> attendance = createNumber("attendance", Long.class);

    public final DateTimePath<java.time.LocalDateTime> attendTime = createDateTime("attendTime", java.time.LocalDateTime.class);

    public final BooleanPath canceled = createBoolean("canceled");

    public final StringPath makeupClass = createString("makeupClass");

    public QAttendance(String variable) {
        super(Attendance.class, forVariable(variable));
    }

    public QAttendance(Path<? extends Attendance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendance(PathMetadata metadata) {
        super(Attendance.class, metadata);
    }

}

