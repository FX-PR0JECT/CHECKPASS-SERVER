package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage;
import FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode;
import FXPROJECT.CHECKPASS.domain.common.constant.State;
import FXPROJECT.CHECKPASS.domain.common.exception.NoSearchResultsFound;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.form.requestForm.letures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private final JpaLectureRepository jpaLectureRepository;

    /**
     * 강의 등록
     * @param lecture 등록할 Lecture 객체
     * @return 등록된 Lecture 반환
     */
    @Transactional
    public ResultForm registerLecture(Lecture lecture){
        Lecture registeredLecture = null;
        if(!existsLecture(lecture.getLectureCode())){
            registeredLecture = jpaLectureRepository.save(lecture);
        }
        if(registeredLecture != null){
            return new ResultForm().builder()
                    .state(State.SUCCESS)
                    .code(ErrorCode.OK.getCode())
                    .title(ErrorCode.OK.getTitle())
                    .resultSet(CommonMessage.COMPLETE_REGISTER.getDescription())
                    .build();
        }
        return new ResultForm().builder()
                .state(State.FAIL)
                .code(ErrorCode.EXISTING_LECTURE.getCode())
                .title(ErrorCode.EXISTING_LECTURE.getTitle())
                .resultSet(CommonMessage.FAIL_REGISTER.getDescription())
                .build();
    }

    /**
     * 강의 정보 수정
     * @param lectureCode 강의 코드
     * @param param 강의 정보 수정 Parameter
     * @return 수정된 Lecture 반환
     */
    @Transactional
    public Lecture editLectureInfo(Long lectureCode, LectureUpdateForm param){

        if(!existsLecture(lectureCode)){
            return null;
        }

        Lecture target = jpaLectureRepository.findByLectureCode(lectureCode);

        Lecture editLecture = updateLecture(target, param);
        jpaLectureRepository.save(editLecture);

        return editLecture;
    }

    /**
     * 강의 코드로 강의 정보 보기
     * @param lectureCode 강의코드
     * @return 강의코드로 찾은 Lecture 반환
     */
    public Lecture showLectureInfo(Long lectureCode){
        return jpaLectureRepository.findByLectureCode(lectureCode);
    }

    /**
     * 강의 코드로 강의 삭제
     * @param lectureCode 강의 코드
     * @return 삭제 완료 후 ResultForm 반환
     */
    public ResultForm deleteLecture(Long lectureCode){

        if (!existsLecture(lectureCode)){
            throw new NoSearchResultsFound();
        }
        jpaLectureRepository.deleteLectureByLectureCode(lectureCode);

        return new ResultForm().builder()
                .state(State.SUCCESS)
                .code(ErrorCode.OK.getCode())
                .title(ErrorCode.OK.getTitle())
                .resultSet(CommonMessage.COMPLETE_DELETE.getDescription())
                .build();

    }


    /**
     * 강의 코드를 이용하여 등록된 강의인지 확인
     * @param lectureCode
     * @return true: 존재함, false: 존재하지 않음
     */
    public Boolean existsLecture(Long lectureCode) {
        return jpaLectureRepository.existsByLectureCode(lectureCode);
    }

    /**
     * 등록된 lecture를 param의 정보로 update
     * @param target 수정할 강의
     * @param param 수정할 정보
     * @return 수정된 강의
     */
    public Lecture updateLecture(Lecture target, LectureUpdateForm param){
        target.setLectureCode(param.getLectureCode());
        target.setProfessor(param.getProfessor());
        target.setLectureName(param.getLectureName());
        target.setLectureTimes(param.getLectureTimes());
        target.setLectureRoom(param.getLectureRoom());
        target.setLectureGrade(param.getLectureGrade());
        target.setLectureKind(param.getLectureKind());
        target.setLectureGrades(param.getLectureGrades());
        target.setLectureFull(param.getLectureFull());
        target.setDayOrNight(param.getDayOrNight());

        return target;
    }


}
