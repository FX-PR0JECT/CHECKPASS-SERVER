package FXPROJECT.CHECKPASS.domain.entity.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = -685192707L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaff staff = new QStaff("staff");

    public final QUsers _super;

    // inherited
    public final QAccount account;

    // inherited
    public final FXPROJECT.CHECKPASS.domain.entity.college.QDepartments departments;

    public final StringPath HIREDATE = createString("HIREDATE");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> joinDate;

    //inherited
    public final NumberPath<Long> userId;

    //inherited
    public final EnumPath<FXPROJECT.CHECKPASS.domain.enums.Job> userJob;

    //inherited
    public final StringPath userName;

    public QStaff(String variable) {
        this(Staff.class, forVariable(variable), INITS);
    }

    public QStaff(Path<? extends Staff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStaff(PathMetadata metadata, PathInits inits) {
        this(Staff.class, metadata, inits);
    }

    public QStaff(Class<? extends Staff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QUsers(type, metadata, inits);
        this.account = _super.account;
        this.departments = _super.departments;
        this.joinDate = _super.joinDate;
        this.userId = _super.userId;
        this.userJob = _super.userJob;
        this.userName = _super.userName;
    }

}

