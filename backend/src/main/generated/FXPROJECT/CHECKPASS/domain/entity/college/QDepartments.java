package FXPROJECT.CHECKPASS.domain.entity.college;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDepartments is a Querydsl query type for DepartmentsEnum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartments extends EntityPathBase<Departments> {

    private static final long serialVersionUID = -1889334003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartments departments = new QDepartments("departments");

    public final QColleges colleges;

    public final StringPath department = createString("department");

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public QDepartments(String variable) {
        this(Departments.class, forVariable(variable), INITS);
    }

    public QDepartments(Path<? extends Departments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartments(PathMetadata metadata, PathInits inits) {
        this(Departments.class, metadata, inits);
    }

    public QDepartments(Class<? extends Departments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.colleges = inits.isInitialized("colleges") ? new QColleges(forProperty("colleges")) : null;
    }

}

