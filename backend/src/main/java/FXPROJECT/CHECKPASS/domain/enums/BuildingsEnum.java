package FXPROJECT.CHECKPASS.domain.enums;

public enum BuildingsEnum {

     /* Arena K(체육관), IT관, 건설환경관, 건축관, 경영항공관, 공군학생교육단, 공동실습관, 국제관, 대학본부, 대학원, 미래융합정보관, 백주년관, 보건관, 본관동, 학생회관
        비행훈련원, 산업체, 생명관, 스마트ICT관, 시스템관, 원격강좌, 인문사회관, 전산관, 종합강의동, 중앙도서관, 철도공학관, 청아홀, 테크노관, 학생회관, 현장수업, 화학생명관
     */

    // W(충주): 87, E(충주): 69, U(의왕): 85 J(증평): 74

    Tennis("테니스장", 8701),
    ArenaK("Arena K(체육관)", 8702),
    GraduateSchool("대학원",8704),
    ComprehensiveLecture("종합강의동", 8705),
    ChemicalLife("화학생명관", 8707),
    CollaborativePractice("공동실습관", 8709),
    IT("IT관", 8716),
    FutureConvergenceInformation("미래융합정보관", 8718),
    CentralLibrary("중앙도서관", 8720),
    System("시스템관", 6904),
    Techno("테크노관", 6905),
    StudentHall("학생회관", 6906),
    ManagementAviation("경영항공관", 6907),
    UniversityHeadquarters("대학본부", 6908),
    Architecture("건축관", 6912),
    ConstructionEnvironment("건설환경관", 6913),
    HumanitiesSocial("인문사회관", 6914),
    AirForceStudentEducation("공군학생교육", 6915),
    SmartICT("스마트ICT관", 6917),
    MainBuilding("본관동", 8501),
    DataProcessing("전산관", 8502),
    CentenaryAnniversary("백주년관", 8503),
    RailwayEngineering("철도공학관", 8508),
    CheongA("청아홀", 7401),
    Health("보건관", 7402),
    Life("생명관", 7403),
    International("국제관", 7408),

    RemoteCourse("원격강좌", 1000),
    FieldLesson("현장수업", 1001),
    Industry("산업체", 1002),
    FlightTraining("비행훈련원", 1003);


    private String building;
    private int buildingCode;

    BuildingsEnum(String building, int buildingCode) {
        this.building = building;
        this.buildingCode = buildingCode;
    }

    public String getBuilding() { return building; }

    public int getBuildingCode() { return buildingCode; }
}
