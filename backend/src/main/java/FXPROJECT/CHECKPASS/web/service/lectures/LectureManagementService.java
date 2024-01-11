package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.repository.JpaLectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class LectureManagementService {

    private final JpaLectureRepository jpaLectureRepository;

    public Lecture registerLecture(Lecture lecture){
        return jpaLectureRepository.save(lecture);
    }

    public Lecture editLectureInfo(Long lectureCode, Lecture param){

        Lecture byLectureId = jpaLectureRepository.findByLectureCode(lectureCode);

        if (byLectureId == null){
            log.info("fail to find by lecture id");
            return null;
        }

        TransferLectureToTarget(param, byLectureId);

        return jpaLectureRepository.save(byLectureId);

    }

    public boolean deleteLecture(Long lectureCode){

        if (!jpaLectureRepository.existsByLectureCode(lectureCode)){
            log.info("400 존재하지 않는 강의!");
            return false;
        }

        jpaLectureRepository.deleteByLectureCode(lectureCode);

        if (jpaLectureRepository.existsByLectureCode(lectureCode)){
            log.info("500 삭제 실패!");
            return false;
        }

        return true;

    };

    public Lecture showLectureInfo(Long lectureCode){

        if (!jpaLectureRepository.existsByLectureCode(lectureCode)){
            log.info("400 존재하지 않는 강의!");
            return null;
        }

        return jpaLectureRepository.findByLectureCode(lectureCode);
    }


    /*public SimpleLectureInfo showLectureSimpleInfo(Long lectureCode){
        if (!jpaLectureRepository.existsByLectureCode(lectureCode)){
            log.info("400 존재하지 않는 강의!");
            return null;
        }

        return jpaLectureRepository.findByLectureId(lectureCode);
    }*/

    private static Lecture TransferLectureToTarget(Lecture param, Lecture byLectureId) {
        return byLectureId.builder()
                .lectureCode(param.getLectureCode())
                .lectureName(param.getLectureName())
                .lectureCount(param.getLectureCount())
                .lectureTimes(param.getLectureTimes())
                .lectureFull(param.getLectureFull())
                .lectureRoom(param.getLectureRoom())
                .lectureGrade(param.getLectureGrade())
                .lectureKind(param.getLectureKind())
                .dayOrNight(param.getDayOrNight())
                .professor(param.getProfessor())
                .lectureGrades(param.getLectureGrades())
                .build();
    }



}
