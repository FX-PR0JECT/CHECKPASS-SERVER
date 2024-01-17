package FXPROJECT.CHECKPASS.domain.entity.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudents is a Querydsl query type for Students
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudents extends EntityPathBase<Students> {

    private static final long serialVersionUID = 1974439259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudents students = new QStudents("students");

    public final QUsers _super;

    // inherited
    public final QAccount account;

    public final StringPath dayOrNight = createString("dayOrNight");

    // inherited
    public final FXPROJECT.CHECKPASS.domain.entity.college.QDepartments departments;

    public final StringPath studentGrade = createString("studentGrade");

    public final StringPath studentSemester = createString("studentSemester");

    //inherited
    public final NumberPath<Long> userId;

    //inherited
    public final EnumPath<FXPROJECT.CHECKPASS.domain.enums.Job> userJob;

    //inherited
    public final StringPath userName;

    public QStudents(String variable) {
        this(Students.class, forVariable(variable), INITS);
    }

    public QStudents(Path<? extends Students> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudents(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudents(PathMetadata metadata, PathInits inits) {
        this(Students.class, metadata, inits);
    }

    public QStudents(Class<? extends Students> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QUsers(type, metadata, inits);
        this.account = _super.account;
        this.departments = _super.departments;
        this.userId = _super.userId;
        this.userJob = _super.userJob;
        this.userName = _super.userName;
    }

}

