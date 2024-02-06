package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.NonExistingLecture;
import FXPROJECT.CHECKPASS.domain.common.exception.NumberOfStudentsExceeded;
import FXPROJECT.CHECKPASS.domain.common.exception.RegisteredForLecture;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaEnrollmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final JpaEnrollmentRepository jpaEnrollmentRepository;
    private final JpaLectureRepository jpaLectureRepository;
    private final LectureService lectureService;
    private final QueryRepository queryRepository;

    /**
     * 수강신청
     * @param lectureCode 강의코드
     * @param loggedInUser 로그인유저
     * @return 성공 : 신청완료 ResultForm  실패 : 신청실패 ResultForm
     */
    @Transactional
    public ResultForm enrollment(Long lectureCode, Users loggedInUser){

        if (!lectureService.existsLecture(lectureCode)){
            throw new NonExistingLecture();
        }

        Lecture target = jpaLectureRepository.findLectureByLectureCode(lectureCode);

        if (target.getLectureFull() == target.getLectureCount()){
            throw new NumberOfStudentsExceeded();
        }

        Enrollment enrollment = new Enrollment((Students) loggedInUser, target);

        if (jpaEnrollmentRepository.existsById(enrollment.getEnrollmentId())){
            throw new RegisteredForLecture();
        }

        jpaEnrollmentRepository.save(enrollment);
        target.setLectureCount(target.getLectureCount() + 1);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_ENROLLMENT.getDescription());
    }


    /**
     * 수강신청 취소
     * @param lectureCode 강의 코드
     * @param loggedInUser 로그인유저
     * @return 성공 : 삭제 완료 ResultForm  실패 : 잘못된 요청 ResultForm
     */
    @Transactional
    public ResultForm cancelEnrollment(Long lectureCode, Users loggedInUser){

        Long enrollmentId = idGenerator(lectureCode, loggedInUser);

        if (!jpaEnrollmentRepository.existsById(enrollmentId)){
            return ResultFormUtils.getFailResultForm(BAD_URI_REQUEST);
        }

        jpaEnrollmentRepository.deleteById(enrollmentId);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE.getDescription());
    }


    /**
     * 수강신청 목록 조회
     * @param loggedInUser 로그인 유저
     * @return 로그인 유저의 수강신청한 강의 목록
     */
    public List<LectureInformation> getEnrollmentList(Users loggedInUser){

        List<Lecture> enrollmentList =  queryRepository.getEnrollmentList(loggedInUser.getUserId());

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : enrollmentList) {
            LectureInformation lectureInformation = new LectureInformation().builder()
                    .lectureCode(lecture.getLectureCode())
                    .lectureName(lecture.getLectureName())
                    .lectureGrade(lecture.getLectureGrade())
                    .lectureKind(lecture.getLectureKind())
                    .lectureGrades(lecture.getLectureGrades())
                    .professorName(lecture.getProfessor().getUserName())
                    .lectureRoom(lecture.getLectureRoom())
                    .lectureTimes(lecture.getLectureTimeCode())
                    .lectureFull(lecture.getLectureFull())
                    .lectureCount(lecture.getLectureCount())
                    .dayOrNight(lecture.getDayOrNight())
                    .departments(lecture.getDepartments().getDepartment())
                    .build();

            lectureInformationList.add(lectureInformation);
        }
        return lectureInformationList;
    }


    private Long idGenerator(Long lectureCode, Users loggedInUser){
        return Long.valueOf(loggedInUser.getUserId().toString() + lectureCode.toString());
    }
}