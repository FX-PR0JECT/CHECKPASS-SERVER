package FXPROJECT.CHECKPASS.domain.entity.attendance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceId is a Querydsl query type for AttendanceId
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceId extends EntityPathBase<AttendanceId> {

    private static final long serialVersionUID = 1931180812L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceId attendanceId = new QAttendanceId("attendanceId");

    public final QAttendance attendance;

    public final FXPROJECT.CHECKPASS.domain.entity.lectures.QLecture lecture;

    public final FXPROJECT.CHECKPASS.domain.entity.users.QUsers users;

    public final NumberPath<Long> week = createNumber("week", Long.class);

    public QAttendanceId(String variable) {
        this(AttendanceId.class, forVariable(variable), INITS);
    }

    public QAttendanceId(Path<? extends AttendanceId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceId(PathMetadata metadata, PathInits inits) {
        this(AttendanceId.class, metadata, inits);
    }

    public QAttendanceId(Class<? extends AttendanceId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendance = inits.isInitialized("attendance") ? new QAttendance(forProperty("attendance")) : null;
        this.lecture = inits.isInitialized("lecture") ? new FXPROJECT.CHECKPASS.domain.entity.lectures.QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.users = inits.isInitialized("users") ? new FXPROJECT.CHECKPASS.domain.entity.users.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

