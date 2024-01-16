package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultSetUtils;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.State.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private final JpaLectureRepository jpaLectureRepository;

    /**
     * 강의 삭제
     * @param lectureCode 강의 코드
     * @return 삭제 성공 시: 성공 ResultForm, 삭제 실패 시: 실패 ResultForm
     */
    @Transactional
    public ResultForm deleteLecture(Long lectureCode){
        if(!existsLecture(lectureCode)){
            return ResultSetUtils.getResultForm(FAIL, NON_EXISTING_LECTURE.getCode(), NON_EXISTING_LECTURE.getTitle(), FAIL_DELETE.getDescription(), null);
        }
        jpaLectureRepository.deleteLectureByLectureCode(lectureCode);

        return ResultSetUtils.getResultForm(SUCCESS, OK.getCode(), OK.getTitle(), COMPLETE_DELETE.getDescription(), null);
    }

    /**
     * 강의 코드를 이용하여 등록된 강의인지 확인
     * @param lectureCode 강의 코드
     * @return true: 존재함, false: 존재하지 않음
     */
    public Boolean existsLecture(Long lectureCode) {
        return jpaLectureRepository.existsByLectureCode(lectureCode);
    }
}