package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.NonExistingLecture;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Enrollment;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaEnrollmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final JpaEnrollmentRepository jpaEnrollmentRepository;
    private final JpaLectureRepository jpaLectureRepository;
    private final LectureService lectureService;

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
            return ResultFormUtils.getFailResultForm(REQUEST_COUNT_EXCEEDED.getCode(), REQUEST_COUNT_EXCEEDED.getTitle(), "수강인원이 초과되었습니다.", null);
        }

        Enrollment enrollment = new Enrollment((Students) loggedInUser, target);

        if (jpaEnrollmentRepository.existsById(enrollment.getEnrollmentId())){
            return ResultFormUtils.getFailResultForm(REQUEST_COUNT_EXCEEDED.getCode(), REQUEST_COUNT_EXCEEDED.getTitle(), "이미 수강신청된 강의입니다.", null);
        }

        jpaEnrollmentRepository.save(enrollment);
        target.setLectureCount(target.getLectureCount() + 1);

        return ResultFormUtils.getSuccessResultForm("수강신청이 완료되었습니다.");
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
            return ResultFormUtils.getFailResultForm(BAD_URI_REQUEST.getCode(), BAD_URI_REQUEST.getTitle(), BAD_URI_REQUEST.getDescription(), null);
        }

        jpaEnrollmentRepository.deleteById(enrollmentId);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE.getDescription());
    }

    private Long idGenerator(Long lectureCode, Users loggedInUser){
        return Long.valueOf(loggedInUser.getUserId().toString() + lectureCode.toString());
    }
}