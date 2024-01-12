package FXPROJECT.CHECKPASS.domain.repository;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.enums.LectureKind;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.ProfessorSearchCondition;
import FXPROJECT.CHECKPASS.web.common.searchCondition.users.StudentSearchCondition;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSearchResultsFound;
import FXPROJECT.CHECKPASS.domain.entity.users.*;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static FXPROJECT.CHECKPASS.domain.entity.lectures.QLecture.*;
import static FXPROJECT.CHECKPASS.domain.entity.users.QProfessor.*;
import static FXPROJECT.CHECKPASS.domain.entity.users.QStaff.*;
import static FXPROJECT.CHECKPASS.domain.entity.users.QStudents.*;


@Slf4j
@Repository
public class QueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public QueryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<Professor> getProfessorList(ProfessorSearchCondition condition, Pageable pageable) {

        String college = condition.getCollege();
        String department = condition.getDepartment();
        log.info("method :{}" ,department);

        List<Professor> result = query
                .select(professor)
                .from(professor)
                .where(equalProfessorCollege(college),equalProfessorDepartment(department))
                .orderBy(
                        professor.userId.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (result.size() == 0){
            throw new NoSearchResultsFound();
        }

        return result;
    }


    public List<Staff> getStaffList(ProfessorSearchCondition condition, Pageable pageable) {

        String college = condition.getCollege();
        String department = condition.getDepartment();
        log.info("method :{}" ,department);

        List<Staff> result = query
                .select(staff)
                .from(staff)
                .where(equalStaffCollege(college),equalStaffDepartment(department))
                .orderBy(
                        staff.userId.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (result.size() == 0){
            throw new NoSearchResultsFound();
        }

        return result;
    }

    public List<Students> getStudentList(StudentSearchCondition condition,Pageable pageable) {

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier();

        String userId = condition.getUserId().substring(0,2);
        String grade = condition.getGrade();
        String dayOrNight = condition.getDayOrNight();
        String semester = condition.getSemester();
        String college = condition.getCollege();
        String department = condition.getDepartment();

        log.info("semester : {}" , semester);

        List<Students> result = query
                .select(students)
                .from(students)
                .where(equalStudentCollege(college),equalStudentDepartment(department),
                        likeUserId(userId),eqUserGrade(grade),eqDayOrNight(dayOrNight),eqSemester(semester))
                .orderBy(
                        orderSpecifiers
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (result.size() == 0){
            throw new NoSearchResultsFound();
        }

        return result;
    }

    public List<Lecture> getLectureList(LectureSearchCondition condition){

        String grade = condition.getGrade();
        LectureKind kind = condition.getKind();
        String grades = condition.getGrades();
        String dayOrNight = condition.getDayOrNight();
        Long lectureCode = condition.getLectureCode();
        String lectureName = condition.getLectureName();

        log.info("Kind : {}", kind);

        List<Lecture> result = query
                .select(lecture)
                .from(lecture)
                .where(eqLectureGrade(grade), equalKind(kind), equalGrades(grades),
                       eqLectureDayOrNight(dayOrNight), eqLectureCode(lectureCode), eqLectureName(lectureName))
                .fetch();

        if (result.isEmpty()){
            throw new NoSearchResultsFound();
        }
        return result;
    }

    private BooleanExpression eqLectureGrade(String grade) {
        if (StringUtils.hasText(grade)){
            return lecture.lectureGrade.eq(grade);
        }
        return null;
    }

    private BooleanExpression equalKind(LectureKind kind) {
        if (kind != null){
            return lecture.lectureKind.eq(kind);
        }
        return null;
    }

    private BooleanExpression equalGrades(String grades){
        if (StringUtils.hasText(grades)) {
            return lecture.lectureGrades.eq(grades);
        }
        return null;
    }

    private BooleanExpression eqLectureDayOrNight(String dayOrNight){
        if (StringUtils.hasText(dayOrNight)){
            return lecture.dayOrNight.eq(dayOrNight);
        }
        return null;
    }

    private BooleanExpression eqLectureCode(Long lectureCode){
        if (lectureCode != null && lectureCode > 0){
            return lecture.lectureCode.eq(lectureCode);
        }
        return null;
    }

    private BooleanExpression eqLectureName(String lectureName){
        if (StringUtils.hasText(lectureName)){
            return lecture.lectureName.eq(lectureName);
        }
        return null;
    }

    private BooleanExpression equalProfessorCollege(String userCollege){
        if (StringUtils.hasText(userCollege)){
            return professor.userCollege.eq(userCollege);
        }
        return null;
    }

    private BooleanExpression equalProfessorDepartment(String department){
        if (StringUtils.hasText(department)){
            return professor.userDepartment.eq(department);
        }
        return null;
    }

    private BooleanExpression equalStaffCollege(String userCollege){
        if (StringUtils.hasText(userCollege)){
            return staff.userCollege.eq(userCollege);
        }
        return null;
    }

    private BooleanExpression equalStaffDepartment(String department){
        if (StringUtils.hasText(department)){
            return staff.userDepartment.eq(department);
        }
        return null;
    }

    private BooleanExpression equalStudentCollege(String userCollege){
        if (StringUtils.hasText(userCollege)){
            return students.userCollege.eq(userCollege);
        }
        return null;
    }

    private BooleanExpression equalStudentDepartment(String department){
        if (StringUtils.hasText(department)){
            return students.userDepartment.eq(department);
        }
        return null;
    }

    private BooleanExpression likeUserId(String userId){
        if (StringUtils.hasText(userId)){
            return students.userId.like(userId + "%");
        }
        return null;
    }

    private BooleanExpression eqUserGrade(String grade){
        if (StringUtils.hasText(grade)){
            return students.studentGrade.eq(grade);
        }
        return null;
    }

    private BooleanExpression eqDayOrNight(String dayOrNight){
        if (StringUtils.hasText(dayOrNight)){
            return students.dayOrNight.eq(dayOrNight);
        }
        return null;
    }

    private BooleanExpression eqSemester(String semester){
        if (StringUtils.hasText(semester)){
            return students.studentSemester.eq(semester);
        }
        return null;
    }


    private OrderSpecifier[] createOrderSpecifier() {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        orderSpecifiers.add(new OrderSpecifier(Order.ASC, students.userId));
        orderSpecifiers.add(new OrderSpecifier(Order.ASC, students.userName));
        orderSpecifiers.add(new OrderSpecifier(Order.DESC, students.studentGrade));
        orderSpecifiers.add(new OrderSpecifier(Order.ASC, students.studentSemester));
        orderSpecifiers.add(new OrderSpecifier(Order.ASC, students.dayOrNight));

        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }
}