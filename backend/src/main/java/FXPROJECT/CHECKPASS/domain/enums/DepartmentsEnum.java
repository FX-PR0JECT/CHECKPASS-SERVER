package FXPROJECT.CHECKPASS.domain.enums;

public enum DepartmentsEnum {

    MechanicalEngineering("기계공학과",1l),
    AutomotiveEngineering("자동차공학과",1l),
    AircraftMechanicalDesign("항공기계설계학과",1l),
    ElectricalEngineering("전기공학과",1l),
    ElectricEngineering("전자공학과",1l),
    ComputerEngineering("컴퓨터공학과",1l),
    ComputerSoftware("컴퓨터소프트웨어학과",1l),
    AIRoboticsEngineering("AI로봇공학과",1l),
    BiomedicalConvergence("바이오메디컬융합학과",1l),
    PrecisionMedicineMedicalDevices("정밀의료/의료기기학과",1l),
    TransportationEngineering("건설환경도시교통공학부",2l),
    ChemicalBiologicalEngineering("화공생물공학과",2l),
    AdvancedSemiconductorMaterialsEngineering("반도체신소재공학과",2l),
    NanoChemicalMaterialsEngineering("나노화학소재공학과",2l),
    IndustrialManagementEngineering("산업경영공학과",2l),
    SafetyEngineering("안전공학과",2l),
    ArchitecturalEngineering("건축공학과",2l),
    Architecture("건축공학과 [5년제]",2l),
    IndustrialDesign("산업디자인학과",2l),
    CommunicationDesign("커뮤니케이션디자인학과",2l),
    EnglishLanguageLiterature("영어영문학과",3l),
    ChineseLanguage("중국어학과",3l),
    KoreanLanguageLiterature("한국어문학과",3l),
    PublicAdministration("행정학과",3l),
    AdministrativeInformationConvergence("행정정보융합학과",3l),
    BusinessAdministration("경영학과",3l),
    ConvergenceManagement("융합경영학과",3l),
    InternationalTrade("국제무역학과",3l),
    SocialWelfare("사회복지학과",3l),
    Music("음악학과",3l),
    SportsMedicine("스포츠의학과",3l),
    SportsIndustry("스포츠산업학과",3l),
    AviationService("항공서비스학과",3l),
    AviationOperations("항공운항학과",3l),
    EarlyChildhoodEducation("유야교육학과",3l),
    MediaContents("미디어콘텐츠학과",3l),
    Nursing("간호학과",4l),
    PhysicalTherapy("물리치료학과",4l),
    EmergencyRescue("응급구조학과",4l),
    FoodLifeSciences("식품생명학부",4l),
    EarlyChildhoodSpecialEducation("유아특수교육학과",4l),
    ITApplicationConvergence("IT응용융합학과",4l),
    CosmeticIndustry("화장품산업학과",4l),
    NaturalMaterials("천연물소재학과",4l),
    RailwayManagementLogistics("철도경영물류학과",5l),
    AIDataEngineering("AI데이터공학부",5l),
    RailwayOperationSystemEngineering("철도운전시스템공학과",5l),
    RailroadVehicleSystemsEngineering("철도차량시스템공학과",5l),
    RailwayInfrastructureEngineering("철도인프라공학과",5l),
    RailwayElectricalInformationEngineering("철도전기정보공학과",5l),
    SafetyConvergenceEngineering("안전융합공학과",6l),
    ConstructionDisasterEngineering("건설방재융합공학과",6l),
    SportsWelfare("스포츠복지학과",6l),
    WelfareManagement("복지경영학과",6l),
    SmartRailroadTransportationEngineering("스마트철도교통공학과",6l),
    SecondaryBatteryEngineering("이차전지공학과",6l),
    FacultyOfLiberalArts("교양학부",7l),
    Free("자유전공학부",8l),
    CreativeConvergence("창의융합학부",9l);

    private String department;
    private Long collegeCode;

    DepartmentsEnum(String department, Long collegeCode) {
        this.department = department;
        this.collegeCode = collegeCode;
    }

    public String getDepartment() {
        return department;
    }

    public Long getCollegeCode() {
        return collegeCode;
    }
}
