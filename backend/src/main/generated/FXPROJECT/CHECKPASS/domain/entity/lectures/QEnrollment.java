package FXPROJECT.CHECKPASS.domain.entity.lectures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnrollment is a Querydsl query type for Enrollment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnrollment extends EntityPathBase<Enrollment> {

    private static final long serialVersionUID = 1955936160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnrollment enrollment = new QEnrollment("enrollment");

    public final NumberPath<Long> enrollmentId = createNumber("enrollmentId", Long.class);

    public final QLecture lecture;

    public final FXPROJECT.CHECKPASS.domain.entity.users.QStudents student;

    public final StringPath yearSemester = createString("yearSemester");

    public QEnrollment(String variable) {
        this(Enrollment.class, forVariable(variable), INITS);
    }

    public QEnrollment(Path<? extends Enrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnrollment(PathMetadata metadata, PathInits inits) {
        this(Enrollment.class, metadata, inits);
    }

    public QEnrollment(Class<? extends Enrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.student = inits.isInitialized("student") ? new FXPROJECT.CHECKPASS.domain.entity.users.QStudents(forProperty("student")) : null;
    }

}

