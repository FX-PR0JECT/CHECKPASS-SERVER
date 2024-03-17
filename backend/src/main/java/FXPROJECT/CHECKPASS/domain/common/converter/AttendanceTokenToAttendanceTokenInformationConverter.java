package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.AttendanceTokenInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttendanceTokenToAttendanceTokenInformationConverter implements Converter<AttendanceTokens, AttendanceTokenInformation> {

    @Override
    public AttendanceTokenInformation convert(AttendanceTokens attendanceToken) {

        AttendanceTokenInformation attendanceTokenInformation = new AttendanceTokenInformation().builder()
                .attendanceCode(attendanceToken.getAttendanceCode())
                .startDate(attendanceToken.getStartDate())
                .build();

        return attendanceTokenInformation;
    }
}