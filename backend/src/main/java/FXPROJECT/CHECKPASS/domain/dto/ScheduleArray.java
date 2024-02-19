package FXPROJECT.CHECKPASS.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ScheduleArray {

    Map<String,boolean[]> scheduleArray = new HashMap<>();

}
