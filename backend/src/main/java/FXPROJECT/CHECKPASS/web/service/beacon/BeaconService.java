package FXPROJECT.CHECKPASS.web.service.beacon;

import FXPROJECT.CHECKPASS.domain.common.exception.ExistingBeacon;
import FXPROJECT.CHECKPASS.domain.common.exception.NonExistentBeacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.enums.BuildingsEnum;
import FXPROJECT.CHECKPASS.domain.repository.beacon.JpaBeaconRepository;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.building.JpaBuildingRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.beacon.register.BeaconRegisterForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.BeaconInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeaconService {

    private final JpaBeaconRepository jpaBeaconRepository;
    private final JpaBuildingRepository jpaBuildingRepository;
    private final JpaLectureRepository jpaLectureRepository;
    private final QueryRepository queryRepository;

    /**
     * 비콘 등록
     * @param form 비콘 등록 Form
     * @return 등록된 비콘
     */
    @Transactional
    public Beacon registerBeacon(BeaconRegisterForm form){
        int major = form.getMajor();
        int minor = form.getMinor();
        BeaconPK beaconPK = new BeaconPK(major, minor);

        if (existsBeacon(beaconPK)) {
            throw new ExistingBeacon();
        }

        Beacon beacon = new Beacon(beaconPK);

        return jpaBeaconRepository.save(beacon);
    }

    /**
     * 비콘 조회
     * @param major 비콘 major
     * @param minor 비콘 minor
     * @return 조회된 비콘
     */
    public BeaconInformation getBeaconInformation(int major, int minor){
        BeaconPK beaconPK = new BeaconPK(major, minor);

        if (!existsBeacon(beaconPK)) {
            throw new NonExistentBeacon();
        }

        BeaconInformation beaconInformation = new BeaconInformation().builder()
                .major(major)
                .minor(minor)
                .buildingName(getBuilding(major))
                .build();

        return beaconInformation;
    }

    public Beacon getBeacon(int major, int minor){
        BeaconPK beaconPK = new BeaconPK(major, minor);

        if (!existsBeacon(beaconPK)) {
            throw new NonExistentBeacon();
        }

        return jpaBeaconRepository.findByBeaconPK(beaconPK);
    }

    /**
     * 비콘 목록 조회
     * @return DB에 저장되어 있는 비콘 List
     */
    public List<BeaconInformation> getBeaconList(){
        List<Beacon> beaconList = queryRepository.getBeaconList();
        List<BeaconInformation> beaconInformationList = new ArrayList<>();
        for (Beacon beacon : beaconList) {
            BeaconPK beaconPK = beacon.getBeaconPK();
            int major = beaconPK.getMajor();
            int minor = beaconPK.getMinor();
            String buildingName = getBuilding(major);

            BeaconInformation beaconInformation = new BeaconInformation().builder()
                    .major(major)
                    .minor(minor)
                    .buildingName(buildingName)
                    .build();

            beaconInformationList.add(beaconInformation);
        }
        return beaconInformationList;
    }

    /**
     * 비콘 삭제
     * @param major 비콘 major
     * @param minor 비콘 minor
     * @return 성공 : Beacon 삭제 성공 ResultForm, 실패 : 해당 Beacon이 DB에 없음
     */
    @Transactional
    public ResultForm deleteBeacon(int major, int minor){
        BeaconPK beaconPK = new BeaconPK(major, minor);
        Beacon beacon = getBeacon(major, minor);

        if (!existsBeacon(beaconPK)){
            throw new NonExistentBeacon();
        }

        List<Lecture> lectureList = jpaLectureRepository.findAllByBeacon(beacon);
        for (Lecture lecture : lectureList) {
            lecture.setBeacon(null);
        }

        jpaBeaconRepository.deleteById(beaconPK);
        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE.getDescription());
    }

    /**
     * 비콘 DB에 저장되어 있는지 확인
     * @param beaconPK 비콘의 복합 PK(major + minor)
     * @return true: 존재함, false: 존재하지 않음
     */
    public boolean existsBeacon(BeaconPK beaconPK) {
        if (!jpaBeaconRepository.existsById(beaconPK)){
            return false;
        }

        return true;
    }

    public String getLectureRoom(BeaconPK beaconPK) {
        int major = beaconPK.getMajor();
        int minor = beaconPK.getMinor();
        String buildingName = getBuilding(major);
        String lectureRoom = buildingName + " (" + minor + ")";

        return lectureRoom;
    }

    public String getBuilding(int major) {
        return jpaBuildingRepository.findBuildingNameByBuildingCode(major);
    }
}