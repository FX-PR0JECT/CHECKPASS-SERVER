package FXPROJECT.CHECKPASS.domain.entity.attendance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceTokens is a Querydsl query type for AttendanceTokens
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceTokens extends EntityPathBase<AttendanceTokens> {

    private static final long serialVersionUID = 1193382667L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceTokens attendanceTokens = new QAttendanceTokens("attendanceTokens");

    public final NumberPath<Integer> attendanceCode = createNumber("attendanceCode", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expirationDate = createDateTime("expirationDate", java.time.LocalDateTime.class);

    public final FXPROJECT.CHECKPASS.domain.entity.lectures.QLecture lecture;

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QAttendanceTokens(String variable) {
        this(AttendanceTokens.class, forVariable(variable), INITS);
    }

    public QAttendanceTokens(Path<? extends AttendanceTokens> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceTokens(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceTokens(PathMetadata metadata, PathInits inits) {
        this(AttendanceTokens.class, metadata, inits);
    }

    public QAttendanceTokens(Class<? extends AttendanceTokens> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new FXPROJECT.CHECKPASS.domain.entity.lectures.QLecture(forProperty("lecture"), inits.get("lecture")) : null;
    }

}

