package org.openmrs.module.rwandareports.reporting;

import org.openmrs.Concept;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.*;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.module.rwandareports.dataset.EncounterIndicatorDataSetDefinition;
import org.openmrs.module.rwandareports.dataset.LocationHierachyIndicatorDataSetDefinition;
import org.openmrs.module.rwandareports.util.Cohorts;
import org.openmrs.module.rwandareports.util.GlobalPropertiesManagement;
import org.openmrs.module.rwandareports.util.Indicators;
import org.openmrs.module.rwandareports.widget.AllLocation;
import org.openmrs.module.rwandareports.widget.LocationHierarchy;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SetupHMISCancerScreeningDashboardreport {
    GlobalPropertiesManagement gp = new GlobalPropertiesManagement();

    private static class IndicatorSpec {
        private final String key;
        private final String label;
        private final Mapped<CohortIndicator> mappedIndicator;

        private IndicatorSpec(String key, String label, Mapped<CohortIndicator> mappedIndicator) {
            this.key = key;
            this.label = label;
            this.mappedIndicator = mappedIndicator;
        }
    }

    // properties

    private Form oncologyBreastScreeningExamination;

    private Form oncologyCervicalScreeningExamination;

    private Form mUzimaBreastScreening;

    private Form mUzimaCervicalScreening;


    private Form oncologyScreeningLabResultsForm;
    private Form muzimaOncologyScreeningLabResults;

    private List<Form> oncologyScreeningLabResultsForms =new ArrayList<Form>();

    private  List<Form> screeningExaminationForms =new ArrayList<Form>();

    private  List<Form> screeningCervicalForms = new ArrayList<Form>();

    private  List<String> parameterNames=new ArrayList<String>();

    private Concept screeningType;

    private Concept HPV;

    private Concept testResult;
    private Concept HPVpositive;
    private Concept HPVNegative;

    private  List<Concept> testResults =new ArrayList<Concept>();

    private  List<Concept> positiveTestResults =new ArrayList<Concept>();

    private Form mUzimaCervicalCancerScreeningFollowup;

    private Form OncologyCervicalScreeningFollowUp;

    private  List<Form> cervicalCancerScreeningFollowupAndExaminationForms=new ArrayList<Form>();;

    private Concept typeOfVIAPerformed;
    private Concept VIATriage;
    private  List<Concept> VIATriageInList=new ArrayList<Concept>();

    private Concept VIAResults;
    private Concept VIAAndEligibleForThermalAblation ;
    private Concept VIAAndEligibleForLEEP;

    private  List<Concept> VIAAndEligibleResults=new ArrayList<Concept>();

    private Form mUzimaBreastCancerScreening;
    private List<Form> breastCancerScreeningForms = new ArrayList<Form>();

    private Concept reasonForBreastExam;
    private Concept breastSymptoms;
    private Concept screening;
    private Concept breastExamination;
    private Concept ABNORMAL;

    private Concept nextStep;
    private Concept referredTo;
    private Concept furtherManagement;
    private Concept medicalImaging;
    private Concept BIOPSY;
    private Concept OTHERNONCODED;

    private Concept breastUltrasound;
    private Concept YES;
    private Concept solidMass;
    private Concept intermediate;
    private Concept highSuspiciousForMalignancy;
    private Concept proceduresDone;
    private Concept PAPANICOLAOUSMEAR;
    private Concept NORMAL;

    private Form muzimaOncologyScreeningDiagnosis;
    private Form oncologyScreeningDiagnosis;
    private List<Form> diagnosisScreeningForms = new ArrayList<Form>();

    private Concept CONFIRMEDDIAGNOSIS;
    private Concept confirmedCancerDiagnosis;
    private Concept cervicalCancer;
    private Concept breastCancer;
    private Form muzimaOncologyScreeningLabRequest;
    private Form OncologyScreeningLabRequest;


    private Form oncologyBreastCancerScreeningTransferIn;
    private List<Form> oncologyBreastCancerScreeningTransferInList = new ArrayList<Form>();


    private Concept VIANegative;
    private  List<Concept> VIANegativeInList=new ArrayList<Concept>();;

    private Concept VIAScreen;
    private  List<Concept> VIAScreenInList=new ArrayList<Concept>();

    private Concept typeOfTreatmentPerformed;
    private Concept thermalAblation;
    private Concept cryotherapy;
    private  List<Concept> thermalAblationAndCryotherapyList =new ArrayList<Concept>();

    private Concept reasonsForReferral;
    private Concept loopElectrosurgicalExcisionProcedure;
    private Concept suspecancerDiagnosis;
    private  List<Concept> loopElectrosurgicalExcisionProcedureInList=new ArrayList<Concept>();

    private  List<Concept> LEEPAndColposcopy=new ArrayList<Concept>();



    private Concept suspectedCancer;
    private  List<Concept> suspectedCancerInList=new ArrayList<Concept>();

    private Concept biopsyperformed;
    private Concept yes;
    private  List<Concept> yesInList=new ArrayList<Concept>();
    private  List<Concept> breastExaminationAnswers = new ArrayList<Concept>();
    private List<Form> breastcancerScreeningAndFollowupForms = new ArrayList<Form>();
    private Form muzimaBreastCancerFollowup;
    private Concept radiologicDiagnosis;
    private Concept breastMass;
    private Concept auxillaryMass;
    private Concept biradsFinalAssessmentCategory;
    private Concept breastMassUltrasoundResults;
    private Concept complexcystic;
    private Concept axillaryMassUltrasoundResults;
    private Concept abnormalPalpableLymphNode;
    private Concept abnormalNonPalpableLymphNode;

    /* private Form oncologyCervicalCancerScreeningTransferIn;
     private  List<Form> oncologyCervicalCancerScreeningTransferInList=new ArrayList<Form>();
 */
    private Concept hivStatus;

    private Concept positive;
    private Concept visualInspectionWithAceticAcid;

    private Concept reasonsForReferralIn;
    private Concept breastDiagnosis;

    private Concept DrugPrescribed;

    private Concept HPVPositive16;
    private Concept HPVPositive18;
    private Concept HPVPositiveType;

    private Concept typeOfTransformationZone;
    private Concept transformationZoneType3;
    private Concept followupReason;
    private Concept postTreatmentFollowup;
    private Concept hpvFollowupAt2Years;
    private Concept axillaMass;
    private Concept focalBreastPain;
    private Concept bloodyNippleDischarge;
    private Concept negative;
    private Concept unknown;

    public void setup() throws Exception {

        setUpProperties();

        Properties properties = new Properties();
        properties.setProperty("hierarchyFields", "countyDistrict:District");

        // Quarterly Report Definition: Start

        ReportDefinition monthlyRd = new ReportDefinition();
        monthlyRd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        monthlyRd.addParameter(new Parameter("endDate", "End Date", Date.class));


        monthlyRd.addParameter(new Parameter("location", "Location", AllLocation.class, properties));

        monthlyRd.setName("ONC - dashboard Cancer Screening Monthly Indicator Report");

        monthlyRd.addDataSetDefinition(createMonthlyLocationDataSet(),
                ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}"));

        // Monthly Report Definition: End

        EncounterCohortDefinition ScreeningExaminationEncounter= Cohorts.createEncounterBasedOnForms("ScreeningExaminationEncounter",parameterNames, screeningExaminationForms);

        monthlyRd.setBaseCohortDefinition(ScreeningExaminationEncounter,
                ParameterizableUtil.createParameterMappings("onOrBefore=${endDate},onOrAfter=${startDate}"));

        Helper.saveReportDefinition(monthlyRd);

        ReportDesign mothlyDesign = Helper.createRowPerPatientXlsOverviewReportDesign(monthlyRd,
                "ONC_dashboard_Cancer_Screening_Monthly.xls", "dashboard Cancer Screening Monthly Indicator Report (Excel)", null);
        Properties monthlyProps = new Properties();
        monthlyProps.put("repeatingSections", "sheet:1,dataset:Encounter dashboard Cancer Screening Data Set");
        monthlyProps.put("sortWeight","5000");
        mothlyDesign.setProperties(monthlyProps);
        if (mothlyDesign.getCreator() == null) {
            mothlyDesign.setCreator(Context.getAuthenticatedUser());
        }
        if (mothlyDesign.getDateCreated() == null) {
            mothlyDesign.setDateCreated(new Date());
        }
        Helper.saveReportDesign(mothlyDesign);

        PeriodIndicatorReportDefinition pid = new PeriodIndicatorReportDefinition();
        pid.setName("ONC - DHIS2 Cancer Screening Monthly Indicator Report");
        pid.addParameter(new Parameter("startDate", "Start Date", Date.class));
        pid.addParameter(new Parameter("endDate", "End Date", Date.class));
        pid.addParameter(new Parameter("location", "Location", Location.class));
        pid.setupDataSetDefinition();
        CohortIndicatorDataSetDefinition pidDsd = pid.getIndicatorDataSetDefinition();
        for (IndicatorSpec spec : buildIndicatorSpecs().values()) {
            pidDsd.addColumn(spec.key, spec.label, spec.mappedIndicator, "");
        }
        Helper.saveReportDefinition(pid);
    }

    public void delete() {
        ReportService rs = Context.getService(ReportService.class);
        for (ReportDesign rd : rs.getAllReportDesigns(false)) {
            if ("dashboard Cancer Screening Monthly Indicator Report (Excel)".equals(rd.getName())) {
                rs.purgeReportDesign(rd);
            }
        }
        Helper.purgeReportDefinition("ONC - dashboard Cancer Screening Monthly Indicator Report");
        Helper.purgeReportDefinition("ONC - DHIS2 Cancer Screening Monthly Indicator Report");

    }



    //Create Monthly Encounter Data set

    public LocationHierachyIndicatorDataSetDefinition createMonthlyLocationDataSet() {

        LocationHierachyIndicatorDataSetDefinition ldsd = new LocationHierachyIndicatorDataSetDefinition(
                createEncounterMonthlyBaseDataSet());
        ldsd.addBaseDefinition(createMonthlyBaseDataSet());
        ldsd.setName("Encounter dashboard Cancer Screening Data Set");
        ldsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        ldsd.addParameter(new Parameter("endDate", "End Date", Date.class));
        ldsd.addParameter(new Parameter("location", "District", LocationHierarchy.class));

        return ldsd;
    }

    private EncounterIndicatorDataSetDefinition createEncounterMonthlyBaseDataSet() {

        EncounterIndicatorDataSetDefinition eidsd = new EncounterIndicatorDataSetDefinition();
        eidsd.setName("eidsd");
        eidsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        eidsd.addParameter(new Parameter("endDate", "End Date", Date.class));
        createMonthlyIndicators(eidsd);
        return eidsd;
    }

    private void createMonthlyIndicators(EncounterIndicatorDataSetDefinition dsd) {

    }

    // create monthly cohort Data set

    private CohortIndicatorDataSetDefinition createMonthlyBaseDataSet() {
        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.setName("Monthly Cohort Data Set");
        dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
        dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        createMonthlyIndicators(dsd);
        return dsd;
    }

    private void createMonthlyIndicators(CohortIndicatorDataSetDefinition dsd) {
        Map<String, IndicatorSpec> indicatorSpecs = buildIndicatorSpecs();
        for (IndicatorSpec spec : indicatorSpecs.values()) {
            dsd.addColumn(spec.key, spec.label, spec.mappedIndicator, "");
        }
    }

    private Map<String, IndicatorSpec> buildIndicatorSpecs() {
        Map<String, IndicatorSpec> indicatorSpecs = new LinkedHashMap<String, IndicatorSpec>();

        SqlCohortDefinition screenedForCervicalCancerWithHPV=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForCervicalCancerWithHPV",screeningCervicalForms,screeningType,HPV);

        GenderCohortDefinition female=Cohorts.createFemaleCohortDefinition("female");

        SqlCohortDefinition hivPositivePatient=Cohorts.getPatientsWithCodedObsEver("hivPositivePatient",hivStatus,positive);
        SqlCohortDefinition hivNegativePatient=Cohorts.getPatientsWithCodedObsEver("hivNegativePatient",hivStatus,negative);
        SqlCohortDefinition hivUnknownPatient=Cohorts.getPatientsWithCodedObsEver("hivUnknownPatient",hivStatus,unknown);
//        SqlCohortDefinition hivNegativePatient=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("hivNegativePatient",screeningCervicalForms,hivStatus,negative);
//        SqlCohortDefinition hivUnknownPatient=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("hivUnknownPatient",screeningCervicalForms,hivStatus,unknown);

        SqlCohortDefinition VIAPositiveLEEPResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIAPositiveLEEPResults",screeningCervicalForms,VIAResults,VIAAndEligibleForLEEP);
        SqlCohortDefinition VIACancerSuspectedResults = Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIACancerSuspectedResults",screeningCervicalForms,VIAResults,suspecancerDiagnosis);
        SqlCohortDefinition VIAAndEligibleForThermalAblationResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIAAndEligibleForThermalAblationResults",screeningCervicalForms,VIAResults,VIAAndEligibleForThermalAblation);
        SqlCohortDefinition VIANegativeResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIANegativeResults",screeningCervicalForms,VIAResults,VIANegative);
        SqlCohortDefinition biopsyPerformsResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("biopsyPerformsResults",screeningCervicalForms,biopsyperformed,yes);

        SqlCohortDefinition LEEPAsReasonForReferral=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("LEEPAsReasonForReferral",screeningCervicalForms,reasonsForReferral,loopElectrosurgicalExcisionProcedure);
        SqlCohortDefinition suspectedCancerAsReasonReferred=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("suspectedCancerAsReasonReferred",screeningCervicalForms,reasonsForReferral,suspecancerDiagnosis);





// ==================== D1 ====================================================
        CompositionCohortDefinition femaleVIAPositiveReferredForLEEP=new CompositionCohortDefinition();
        femaleVIAPositiveReferredForLEEP.setName("femaleVIAPositiveReferredForLEEP");
        femaleVIAPositiveReferredForLEEP.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleVIAPositiveReferredForLEEP.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleVIAPositiveReferredForLEEP.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsReasonForReferral, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        femaleVIAPositiveReferredForLEEP.setCompositionString("1 and 2");

        CohortIndicator femaleVIAPositiveReferredForLEEPIndicator = Indicators.newCountIndicator("femaleVIAPositiveReferredForLEEPIndicator",
                femaleVIAPositiveReferredForLEEP, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D1", "Number of screened positive women referred for LEEP & Colposcopy to other levels", new Mapped(
                femaleVIAPositiveReferredForLEEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D1HIVpos ====================================================
        CompositionCohortDefinition HIVposfemaleVIAPositiveReferredForLEEP=new CompositionCohortDefinition();
        HIVposfemaleVIAPositiveReferredForLEEP.setName("HIVposfemaleVIAPositiveReferredForLEEP");
        HIVposfemaleVIAPositiveReferredForLEEP.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleVIAPositiveReferredForLEEP.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleVIAPositiveReferredForLEEP.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsReasonForReferral, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleVIAPositiveReferredForLEEP.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
//        HIVposfemaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        HIVposfemaleVIAPositiveReferredForLEEP.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVposfemaleVIAPositiveReferredForLEEPIndicator = Indicators.newCountIndicator("HIVposfemaleVIAPositiveReferredForLEEPIndicator",
                HIVposfemaleVIAPositiveReferredForLEEP, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D1HIVpos", "Number of screened HIV positive and positive women referred for LEEP & Colposcopy to other levels", new Mapped(
                HIVposfemaleVIAPositiveReferredForLEEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D1HIVneg ====================================================
        CompositionCohortDefinition HIVnegfemaleVIAPositiveReferredForLEEP=new CompositionCohortDefinition();
        HIVnegfemaleVIAPositiveReferredForLEEP.setName("HIVnegfemaleVIAPositiveReferredForLEEP");
        HIVnegfemaleVIAPositiveReferredForLEEP.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleVIAPositiveReferredForLEEP.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleVIAPositiveReferredForLEEP.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsReasonForReferral, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleVIAPositiveReferredForLEEP.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleVIAPositiveReferredForLEEP.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
//        HIVnegfemaleVIAPositiveReferredForLEEP.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        HIVnegfemaleVIAPositiveReferredForLEEP.setCompositionString("1 and 2 and (3 or 4)");

        CohortIndicator HIVnegfemaleVIAPositiveReferredForLEEPIndicator = Indicators.newCountIndicator("HIVnegfemaleVIAPositiveReferredForLEEPIndicator",
                HIVnegfemaleVIAPositiveReferredForLEEP, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D1HIVneg", "Number of screened HIV negative and positive women referred for LEEP & Colposcopy to other levels", new Mapped(
                HIVnegfemaleVIAPositiveReferredForLEEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


// ==================== D2 ====================================================
        CompositionCohortDefinition femaleSuspectedWithCervicalCancer=new CompositionCohortDefinition();
        femaleSuspectedWithCervicalCancer.setName("femaleSuspectedWithCervicalCancer");
        femaleSuspectedWithCervicalCancer.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleSuspectedWithCervicalCancer.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleSuspectedWithCervicalCancer.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleSuspectedWithCervicalCancer.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleSuspectedWithCervicalCancer.getSearches().put("3",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleSuspectedWithCervicalCancer.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        femaleSuspectedWithCervicalCancer.setCompositionString("1 and NOT 2 and NOT 3");

        CohortIndicator femaleSuspectedWithCervicalCancerIndicator = Indicators.newCountIndicator("femaleSuspectedWithCervicalCancerIndicator",
                femaleSuspectedWithCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D2", "Number of women suspected with cervical cancer", new Mapped(
                femaleSuspectedWithCervicalCancerIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D2HIVpos ====================================================
        CompositionCohortDefinition HIVposfemaleSuspectedWithCervicalCancer=new CompositionCohortDefinition();
        HIVposfemaleSuspectedWithCervicalCancer.setName("HIVposfemaleSuspectedWithCervicalCancer");
        HIVposfemaleSuspectedWithCervicalCancer.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleSuspectedWithCervicalCancer.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleSuspectedWithCervicalCancer.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleSuspectedWithCervicalCancer.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleSuspectedWithCervicalCancer.getSearches().put("3",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleSuspectedWithCervicalCancer.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
//        HIVposfemaleSuspectedWithCervicalCancer.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        HIVposfemaleSuspectedWithCervicalCancer.setCompositionString("1 and NOT 2 and NOT 3 and 4");

        CohortIndicator HIVposfemaleSuspectedWithCervicalCancerIndicator = Indicators.newCountIndicator("HIVposfemaleSuspectedWithCervicalCancerIndicator",
                HIVposfemaleSuspectedWithCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D2HIVpos", "Number of women HIV positive and suspected with cervical cancer", new Mapped(
                HIVposfemaleSuspectedWithCervicalCancerIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D2HIVneg ====================================================
        CompositionCohortDefinition HIVnegfemaleSuspectedWithCervicalCancer=new CompositionCohortDefinition();
        HIVnegfemaleSuspectedWithCervicalCancer.setName("HIVnegfemaleSuspectedWithCervicalCancer");
        HIVnegfemaleSuspectedWithCervicalCancer.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleSuspectedWithCervicalCancer.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleSuspectedWithCervicalCancer.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleSuspectedWithCervicalCancer.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleSuspectedWithCervicalCancer.getSearches().put("3",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleSuspectedWithCervicalCancer.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleSuspectedWithCervicalCancer.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegfemaleSuspectedWithCervicalCancer.setCompositionString("1 and NOT 2 and NOT 3 and (4 OR 5)");

        CohortIndicator HIVnegfemaleSuspectedWithCervicalCancerIndicator = Indicators.newCountIndicator("HIVnegfemaleSuspectedWithCervicalCancerIndicator",
                HIVnegfemaleSuspectedWithCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D2HIVneg", "Number of women HIV negative and suspected with cervical cancer", new Mapped(
                HIVnegfemaleSuspectedWithCervicalCancerIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



// ==================== D3 ====================================================
        CompositionCohortDefinition femaleCancerSuspectBiopsyDone=new CompositionCohortDefinition();
        femaleCancerSuspectBiopsyDone.setName("femaleCancerSuspectBiopsyDone");
        femaleCancerSuspectBiopsyDone.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleCancerSuspectBiopsyDone.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleCancerSuspectBiopsyDone.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleCancerSuspectBiopsyDone.getSearches().put("2",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleCancerSuspectBiopsyDone.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        femaleCancerSuspectBiopsyDone.setCompositionString("1 and 2");

        CohortIndicator femaleCancerSuspectBiopsyDoneIndicator = Indicators.newCountIndicator("femaleCancerSuspectBiopsyDoneIndicator",
                femaleCancerSuspectBiopsyDone, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D3", "Number of women suspected with cervical cancer who underwent biopsy", new Mapped(
                femaleCancerSuspectBiopsyDoneIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D3HIVpos ====================================================
        CompositionCohortDefinition HIVposfemaleCancerSuspectBiopsyDone=new CompositionCohortDefinition();
        HIVposfemaleCancerSuspectBiopsyDone.setName("HIVposfemaleCancerSuspectBiopsyDone");
        HIVposfemaleCancerSuspectBiopsyDone.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleCancerSuspectBiopsyDone.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleCancerSuspectBiopsyDone.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleCancerSuspectBiopsyDone.getSearches().put("2",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleCancerSuspectBiopsyDone.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposfemaleCancerSuspectBiopsyDone.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVposfemaleCancerSuspectBiopsyDoneIndicator = Indicators.newCountIndicator("HIVposfemaleCancerSuspectBiopsyDoneIndicator",
                HIVposfemaleCancerSuspectBiopsyDone, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D3HIVpos", "Number of women HIV positive and suspected with cervical cancer who underwent biopsy", new Mapped(
                HIVposfemaleCancerSuspectBiopsyDoneIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D3HIVneg ====================================================
        CompositionCohortDefinition HIVnegfemaleCancerSuspectBiopsyDone=new CompositionCohortDefinition();
        HIVnegfemaleCancerSuspectBiopsyDone.setName("HIVnegfemaleCancerSuspectBiopsyDone");
        HIVnegfemaleCancerSuspectBiopsyDone.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleCancerSuspectBiopsyDone.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleCancerSuspectBiopsyDone.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleCancerSuspectBiopsyDone.getSearches().put("2",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleCancerSuspectBiopsyDone.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleCancerSuspectBiopsyDone.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegfemaleCancerSuspectBiopsyDone.setCompositionString("1 and 2 and (3 or 4)");

        CohortIndicator HIVnegfemaleCancerSuspectBiopsyDoneIndicator = Indicators.newCountIndicator("HIVnegfemaleCancerSuspectBiopsyDoneIndicator",
                HIVnegfemaleCancerSuspectBiopsyDone, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D3HIVneg", "Number of women HIV negative and suspected with cervical cancer who underwent biopsy", new Mapped(
                HIVnegfemaleCancerSuspectBiopsyDoneIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D4 ====================================================
        CompositionCohortDefinition femaleVIACancerSuspectReferred=new CompositionCohortDefinition();
        femaleVIACancerSuspectReferred.setName("femaleVIACancerSuspectReferred");
        femaleVIACancerSuspectReferred.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleVIACancerSuspectReferred.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleVIACancerSuspectReferred.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleVIACancerSuspectReferred.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleVIACancerSuspectReferred.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        femaleVIACancerSuspectReferred.setCompositionString("1 and 2");

        CohortIndicator femaleVIACancerSuspectReferredIndicator = Indicators.newCountIndicator("femaleVIACancerSuspectReferredIndicator",
                femaleVIACancerSuspectReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D4", "Number of women with Suspected cervical cancer refered to other level", new Mapped(
                femaleVIACancerSuspectReferredIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D4HIVpos ====================================================
        CompositionCohortDefinition HIVposfemaleVIACancerSuspectReferred=new CompositionCohortDefinition();
        HIVposfemaleVIACancerSuspectReferred.setName("HIVposfemaleVIACancerSuspectReferred");
        HIVposfemaleVIACancerSuspectReferred.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleVIACancerSuspectReferred.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleVIACancerSuspectReferred.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleVIACancerSuspectReferred.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleVIACancerSuspectReferred.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposfemaleVIACancerSuspectReferred.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVposfemaleVIACancerSuspectReferredIndicator = Indicators.newCountIndicator("HIVposfemaleVIACancerSuspectReferredIndicator",
                HIVposfemaleVIACancerSuspectReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D4HIVpos", "Number of women HIV positive and with Suspected cervical cancer refered to other level", new Mapped(
                HIVposfemaleVIACancerSuspectReferredIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D4HIVneg ====================================================
        CompositionCohortDefinition HIVnegfemaleVIACancerSuspectReferred=new CompositionCohortDefinition();
        HIVnegfemaleVIACancerSuspectReferred.setName("HIVnegfemaleVIACancerSuspectReferred");
        HIVnegfemaleVIACancerSuspectReferred.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleVIACancerSuspectReferred.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleVIACancerSuspectReferred.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleVIACancerSuspectReferred.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleVIACancerSuspectReferred.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleVIACancerSuspectReferred.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegfemaleVIACancerSuspectReferred.setCompositionString("1 and 2 and (3 OR 4)");

        CohortIndicator HIVnegfemaleVIACancerSuspectReferredIndicator = Indicators.newCountIndicator("HIVnegfemaleVIACancerSuspectReferredIndicator",
                HIVnegfemaleVIACancerSuspectReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D4HIVneg", "Number of women HIV negative and with Suspected cervical cancer refered to other level", new Mapped(
                HIVnegfemaleVIACancerSuspectReferredIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


// ==================== D5 ====================================================

        SqlCohortDefinition HPVPositiveResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("HPVPositiveResults",screeningCervicalForms,testResult,HPVPositiveType);


        CompositionCohortDefinition HPVPositiveResultsComposition=new CompositionCohortDefinition();
        HPVPositiveResultsComposition.setName("HPVPositiveResultsComposition");
        HPVPositiveResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HPVPositiveResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HPVPositiveResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HPVPositiveResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        HPVPositiveResultsComposition.setCompositionString("1");

        CohortIndicator HPVPositiveResultsCompositionIndicator = Indicators.newCountIndicator("HPVPositiveResultsCompositionIndicator",
                HPVPositiveResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D5", "Number of women tested HPV positive", new Mapped(
                HPVPositiveResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


// ==================== D5HIVpos ====================================================



        CompositionCohortDefinition HIVposHPVPositiveResultsComposition=new CompositionCohortDefinition();
        HIVposHPVPositiveResultsComposition.setName("HIVposHPVPositiveResultsComposition");
        HIVposHPVPositiveResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposHPVPositiveResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposHPVPositiveResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposHPVPositiveResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposHPVPositiveResultsComposition.setCompositionString("1 and 2");

        CohortIndicator HIVposHPVPositiveResultsCompositionIndicator = Indicators.newCountIndicator("HIVposHPVPositiveResultsCompositionIndicator",
                HIVposHPVPositiveResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D5HIVpos", "Number of women HIV positive and tested HPV positive", new Mapped(
                HIVposHPVPositiveResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


// ==================== D5HIVneg ====================================================



        CompositionCohortDefinition HIVnegHPVPositiveResultsComposition=new CompositionCohortDefinition();
        HIVnegHPVPositiveResultsComposition.setName("HIVnegHPVPositiveResultsComposition");
        HIVnegHPVPositiveResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegHPVPositiveResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegHPVPositiveResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHPVPositiveResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegHPVPositiveResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegHPVPositiveResultsComposition.setCompositionString("1 and (2 OR 3)");

        CohortIndicator HIVnegHPVPositiveResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegHPVPositiveResultsCompositionIndicator",
                HIVnegHPVPositiveResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D5HIVneg", "Number of women HIV negative and tested HPV positive", new Mapped(
                HIVnegHPVPositiveResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



// ==================== D6 ====================================================

        SqlCohortDefinition HPVNegativeResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("HPVNegativeResults",screeningCervicalForms,testResult,HPVNegative);


        CompositionCohortDefinition HPVResultsComposition=new CompositionCohortDefinition();
        HPVResultsComposition.setName("HPVResultsComposition");
        HPVResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HPVResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HPVResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVNegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HPVResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HPVResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        HPVResultsComposition.setCompositionString("1 OR 2");

        CohortIndicator HPVResultsCompositionIndicator = Indicators.newCountIndicator("HPVResultsCompositionIndicator",
                HPVResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D6", "Number of women who received HPV test results", new Mapped(
                HPVResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D6HIVpos ====================================================



        CompositionCohortDefinition HIVposHPVResultsComposition=new CompositionCohortDefinition();
        HIVposHPVResultsComposition.setName("HIVposHPVResultsComposition");
        HIVposHPVResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposHPVResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposHPVResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVNegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposHPVResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposHPVResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposHPVResultsComposition.setCompositionString("(1 OR 2) and 3 ");

        CohortIndicator HIVposHPVResultsCompositionIndicator = Indicators.newCountIndicator("HIVposHPVResultsCompositionIndicator",
                HIVposHPVResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D6HIVpos", "Number of women HIV positive and who received HPV test results", new Mapped(
                HIVposHPVResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
// ==================== D6HIVneg ====================================================



        CompositionCohortDefinition HIVnegHPVResultsComposition=new CompositionCohortDefinition();
        HIVnegHPVResultsComposition.setName("HIVnegHPVResultsComposition");
        HIVnegHPVResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegHPVResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegHPVResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(HPVNegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHPVResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHPVResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegHPVResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegHPVResultsComposition.setCompositionString("(1 OR 2) and (3 OR 4)");

        CohortIndicator HIVnegHPVResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegHPVResultsCompositionIndicator",
                HIVnegHPVResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D6HIVneg", "Number of women HIV negative and who received HPV test results", new Mapped(
                HIVnegHPVResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D7 ====================================================

        SqlCohortDefinition screeningThroughVIA=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screeningThroughVIA",screeningCervicalForms,screeningType,visualInspectionWithAceticAcid);
        SqlCohortDefinition VIAScreenPerformed =Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIAScreen",screeningCervicalForms,typeOfVIAPerformed, VIAScreen);



        CompositionCohortDefinition VIAScreenedPositiveComposition=new CompositionCohortDefinition();
        VIAScreenedPositiveComposition.setName("VIAScreenedPositiveComposition");
        VIAScreenedPositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        VIAScreenedPositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        VIAScreenedPositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        VIAScreenedPositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        VIAScreenedPositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        VIAScreenedPositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        VIAScreenedPositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        VIAScreenedPositiveComposition.setCompositionString("(1 OR 2) AND (3 OR 4)");

        CohortIndicator VIAScreenedPositiveCompositionIndicator = Indicators.newCountIndicator("VIAScreenedPositiveCompositionIndicator",
                VIAScreenedPositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D7", "Number of women tested VIA Screen positive", new Mapped(
                VIAScreenedPositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D7HIVpos ====================================================


        CompositionCohortDefinition HIVposVIAScreenedPositiveComposition=new CompositionCohortDefinition();
        HIVposVIAScreenedPositiveComposition.setName("HIVposVIAScreenedPositiveComposition");
        HIVposVIAScreenedPositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposVIAScreenedPositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposVIAScreenedPositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedPositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedPositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedPositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedPositiveComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposVIAScreenedPositiveComposition.setCompositionString("(1 OR 2) AND (3 OR 4) AND 5");

        CohortIndicator HIVposVIAScreenedPositiveCompositionIndicator = Indicators.newCountIndicator("HIVposVIAScreenedPositiveCompositionIndicator",
                HIVposVIAScreenedPositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D7HIVpos", "Number of women HIV positive and tested VIA Screen positive", new Mapped(
                HIVposVIAScreenedPositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D7HIVneg ====================================================


        CompositionCohortDefinition HIVnegVIAScreenedPositiveComposition=new CompositionCohortDefinition();
        HIVnegVIAScreenedPositiveComposition.setName("HIVnegVIAScreenedPositiveComposition");
        HIVnegVIAScreenedPositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegVIAScreenedPositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegVIAScreenedPositiveComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegVIAScreenedPositiveComposition.setCompositionString("(1 OR 2) AND (3 OR 4) AND (5 OR 6)");

        CohortIndicator HIVnegVIAScreenedPositiveCompositionIndicator = Indicators.newCountIndicator("HIVnegVIAScreenedPositiveCompositionIndicator",
                HIVnegVIAScreenedPositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D7HIVneg", "Number of women HIV negative and tested VIA Screen positive", new Mapped(
                HIVnegVIAScreenedPositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



// ==================== D8 ====================================================



        CompositionCohortDefinition VIAScreenedComposition=new CompositionCohortDefinition();
        VIAScreenedComposition.setName("VIAScreenedComposition");
        VIAScreenedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        VIAScreenedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        VIAScreenedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        VIAScreenedComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        VIAScreenedComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        VIAScreenedComposition.setCompositionString("1 OR 2");

        CohortIndicator VIAScreenedCompositionIndicator = Indicators.newCountIndicator("VIAScreenedCompositionIndicator",
                VIAScreenedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D8", "Number of women who received VIA Screen", new Mapped(
                VIAScreenedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D8HIVpos ====================================================



        CompositionCohortDefinition HIVposVIAScreenedComposition=new CompositionCohortDefinition();
        HIVposVIAScreenedComposition.setName("HIVposVIAScreenedComposition");
        HIVposVIAScreenedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposVIAScreenedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposVIAScreenedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIAScreenedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposVIAScreenedComposition.setCompositionString("(1 OR 2) AND 3");

        CohortIndicator HIVposVIAScreenedCompositionIndicator = Indicators.newCountIndicator("HIVposVIAScreenedCompositionIndicator",
                HIVposVIAScreenedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D8HIVpos", "Number of women HIV positive and who received VIA Screen", new Mapped(
                HIVposVIAScreenedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D8HIVneg ====================================================



        CompositionCohortDefinition HIVnegVIAScreenedComposition=new CompositionCohortDefinition();
        HIVnegVIAScreenedComposition.setName("HIVnegVIAScreenedComposition");
        HIVnegVIAScreenedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegVIAScreenedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegVIAScreenedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedComposition.getSearches().put("2",new Mapped<CohortDefinition>(screeningThroughVIA, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIAScreenedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegVIAScreenedComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegVIAScreenedComposition.setCompositionString("(1 OR 2) AND (3 OR 4)");

        CohortIndicator HIVnegVIAScreenedCompositionIndicator = Indicators.newCountIndicator("HIVnegVIAScreenedCompositionIndicator",
                HIVnegVIAScreenedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D8HIVneg", "Number of women HIV negative and who received VIA Screen", new Mapped(
                HIVnegVIAScreenedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



// ==================== D9 ====================================================

        SqlCohortDefinition screeningThroughHPV=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screeningThroughHPV",screeningCervicalForms,screeningType,HPV);



        CompositionCohortDefinition screeningThroughHPVComposition=new CompositionCohortDefinition();
        screeningThroughHPVComposition.setName("screeningThroughHPVComposition");
        screeningThroughHPVComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        screeningThroughHPVComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        screeningThroughHPVComposition.getSearches().put("1",new Mapped<CohortDefinition>(screeningThroughHPV, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        screeningThroughHPVComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        screeningThroughHPVComposition.setCompositionString("1");

        CohortIndicator screeningThroughHPVCompositionIndicator = Indicators.newCountIndicator("screeningThroughHPVCompositionIndicator",
                screeningThroughHPVComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D9", "Number of women screened with HPV", new Mapped(
                screeningThroughHPVCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D9HIVpos ====================================================



        CompositionCohortDefinition HIVposscreeningThroughHPVComposition=new CompositionCohortDefinition();
        HIVposscreeningThroughHPVComposition.setName("HIVposscreeningThroughHPVComposition");
        HIVposscreeningThroughHPVComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposscreeningThroughHPVComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposscreeningThroughHPVComposition.getSearches().put("1",new Mapped<CohortDefinition>(screeningThroughHPV, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposscreeningThroughHPVComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposscreeningThroughHPVComposition.setCompositionString("1 AND 2");

        CohortIndicator HIVposscreeningThroughHPVCompositionIndicator = Indicators.newCountIndicator("HIVposscreeningThroughHPVCompositionIndicator",
                HIVposscreeningThroughHPVComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D9HIVpos", "Number of women HIV positive and screened with HPV", new Mapped(
                HIVposscreeningThroughHPVCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

// ==================== D9HIVneg ====================================================


        CompositionCohortDefinition HIVnegscreeningThroughHPVComposition=new CompositionCohortDefinition();
        HIVnegscreeningThroughHPVComposition.setName("HIVnegscreeningThroughHPVComposition");
        HIVnegscreeningThroughHPVComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegscreeningThroughHPVComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegscreeningThroughHPVComposition.getSearches().put("1",new Mapped<CohortDefinition>(screeningThroughHPV, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegscreeningThroughHPVComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegscreeningThroughHPVComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegscreeningThroughHPVComposition.setCompositionString("1 AND (2 OR 3)");

        CohortIndicator HIVnegscreeningThroughHPVCompositionIndicator = Indicators.newCountIndicator("HIVnegscreeningThroughHPVCompositionIndicator",
                HIVnegscreeningThroughHPVComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D9HIVneg", "Number of women HIV negative and screened with HPV", new Mapped(
                HIVnegscreeningThroughHPVCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D10 ====================================================

        SqlCohortDefinition suspectedCancerReasonForReferralIn=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("suspectedCancerReasonForReferralIn",screeningCervicalForms,reasonsForReferralIn,suspecancerDiagnosis);



        CompositionCohortDefinition CervicalCancerSuspicionAttendedComposition=new CompositionCohortDefinition();
        CervicalCancerSuspicionAttendedComposition.setName("CervicalCancerSuspicionAttendedComposition");
        CervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        CervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        CervicalCancerSuspicionAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        CervicalCancerSuspicionAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        CervicalCancerSuspicionAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        CervicalCancerSuspicionAttendedComposition.setCompositionString("1 AND 2");

        CohortIndicator CervicalCancerSuspicionAttendedCompositionIndicator = Indicators.newCountIndicator("CervicalCancerSuspicionAttendedCompositionIndicator",
                CervicalCancerSuspicionAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D10", "Number of women for cervical cancer suspicion attended referral", new Mapped(
                CervicalCancerSuspicionAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D10HIVpos ====================================================


        CompositionCohortDefinition HIVposCervicalCancerSuspicionAttendedComposition=new CompositionCohortDefinition();
        HIVposCervicalCancerSuspicionAttendedComposition.setName("HIVposCervicalCancerSuspicionAttendedComposition");
        HIVposCervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposCervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposCervicalCancerSuspicionAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposCervicalCancerSuspicionAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposCervicalCancerSuspicionAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposCervicalCancerSuspicionAttendedComposition.setCompositionString("1 AND 2 and 3");

        CohortIndicator HIVposCervicalCancerSuspicionAttendedCompositionIndicator = Indicators.newCountIndicator("HIVposCervicalCancerSuspicionAttendedCompositionIndicator",
                HIVposCervicalCancerSuspicionAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D10HIVpos", "Number of women HIV positive and for cervical cancer suspicion attended referral", new Mapped(
                HIVposCervicalCancerSuspicionAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D10HIVneg ====================================================


        CompositionCohortDefinition HIVnegCervicalCancerSuspicionAttendedComposition=new CompositionCohortDefinition();
        HIVnegCervicalCancerSuspicionAttendedComposition.setName("HIVnegCervicalCancerSuspicionAttendedComposition");
        HIVnegCervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegCervicalCancerSuspicionAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegCervicalCancerSuspicionAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegCervicalCancerSuspicionAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(suspectedCancerAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegCervicalCancerSuspicionAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegCervicalCancerSuspicionAttendedComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegCervicalCancerSuspicionAttendedComposition.setCompositionString("1 AND 2 AND (3 OR 4)");

        CohortIndicator HIVnegCervicalCancerSuspicionAttendedCompositionIndicator = Indicators.newCountIndicator("HIVnegCervicalCancerSuspicionAttendedCompositionIndicator",
                HIVnegCervicalCancerSuspicionAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D10HIVneg", "Number of women HIV negative and for cervical cancer suspicion attended referral", new Mapped(
                HIVnegCervicalCancerSuspicionAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D11 ====================================================

        SqlCohortDefinition FurtherManagementReasonForReferralIn=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("FurtherManagementReasonForReferralIn",breastCancerScreeningForms,reasonsForReferralIn,furtherManagement);
        SqlCohortDefinition FurtherManagementAsReasonReferred=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("FurtherManagementAsReasonReferred",screeningCervicalForms,reasonsForReferral,furtherManagement);
        SqlCohortDefinition screenedForBreastCancerWithAbnormalBreastFindings=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForBreastCancerWithAbnormalBreastFindings",breastCancerScreeningForms,breastExamination,ABNORMAL);



        CompositionCohortDefinition breastReferredAndAttendedComposition=new CompositionCohortDefinition();
        breastReferredAndAttendedComposition.setName("breastReferredAndAttendedComposition");
        breastReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        breastReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        breastReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastReferredAndAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastReferredAndAttendedComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        breastReferredAndAttendedComposition.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator breastReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("breastReferredAndAttendedCompositionIndicator",
                breastReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D11", "Number of women with abnormal breast findings referred who attended referral visit", new Mapped(
                breastReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

                // ==================== D11HIVpos ====================================================



        CompositionCohortDefinition HIVposbreastReferredAndAttendedComposition=new CompositionCohortDefinition();
        HIVposbreastReferredAndAttendedComposition.setName("HIVposbreastReferredAndAttendedComposition");
        HIVposbreastReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposbreastReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposbreastReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastReferredAndAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastReferredAndAttendedComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        HIVposbreastReferredAndAttendedComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposbreastReferredAndAttendedComposition.setCompositionString("1 AND 2 AND 3 AND 4 AND 5");

        CohortIndicator HIVposbreastReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("HIVposbreastReferredAndAttendedCompositionIndicator",
                HIVposbreastReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D11HIVpos", "Number of women HIV positive and with abnormal breast findings referred who attended referral visit", new Mapped(
                HIVposbreastReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

                // ==================== D11HIVneg ====================================================



        CompositionCohortDefinition HIVnegbreastReferredAndAttendedComposition=new CompositionCohortDefinition();
        HIVnegbreastReferredAndAttendedComposition.setName("breastReferredAndAttendedComposition");
        HIVnegbreastReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegbreastReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegbreastReferredAndAttendedComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegbreastReferredAndAttendedComposition.setCompositionString("1 AND 2 AND 3 AND 4 AND (5 OR 6)");

        CohortIndicator HIVnegbreastReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("HIVnegbreastReferredAndAttendedCompositionIndicator",
                HIVnegbreastReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D11HIVneg", "Number of women HIV negative and with abnormal breast findings referred who attended referral visit", new Mapped(
                HIVnegbreastReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D12 ====================================================

        SqlCohortDefinition LEEPReasonForReferralIn=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("LEEPReasonForReferralIn",screeningCervicalForms,reasonsForReferralIn,loopElectrosurgicalExcisionProcedure);



        CompositionCohortDefinition LEEPReferredAndAttendedComposition=new CompositionCohortDefinition();
        LEEPReferredAndAttendedComposition.setName("LEEPReferredAndAttendedComposition");
        LEEPReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        LEEPReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        LEEPReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        LEEPReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        LEEPReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        LEEPReferredAndAttendedComposition.setCompositionString("1 AND 2");

        CohortIndicator LEEPReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("LEEPReferredAndAttendedCompositionIndicator",
                LEEPReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D12", "Number of women referred for LEEP &Colposcopy attended referral", new Mapped(
                LEEPReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

               // ==================== D12HIVpos ====================================================


        CompositionCohortDefinition HIVposLEEPReferredAndAttendedComposition=new CompositionCohortDefinition();
        HIVposLEEPReferredAndAttendedComposition.setName("HIVposLEEPReferredAndAttendedComposition");
        HIVposLEEPReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposLEEPReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposLEEPReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposLEEPReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposLEEPReferredAndAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposLEEPReferredAndAttendedComposition.setCompositionString("1 AND 2 AND 3");

        CohortIndicator HIVposLEEPReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("HIVposLEEPReferredAndAttendedCompositionIndicator",
                HIVposLEEPReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D12HIVpos", "Number of women HIV positive and referred for LEEP &Colposcopy attended referral", new Mapped(
                HIVposLEEPReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

               // ==================== D12HIVneg ====================================================



        CompositionCohortDefinition HIVnegLEEPReferredAndAttendedComposition=new CompositionCohortDefinition();
        HIVnegLEEPReferredAndAttendedComposition.setName("HIVnegLEEPReferredAndAttendedComposition");
        HIVnegLEEPReferredAndAttendedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegLEEPReferredAndAttendedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegLEEPReferredAndAttendedComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegLEEPReferredAndAttendedComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPReasonForReferralIn, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegLEEPReferredAndAttendedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegLEEPReferredAndAttendedComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegLEEPReferredAndAttendedComposition.setCompositionString("1 AND 2 AND (3 OR 4)");

        CohortIndicator HIVnegLEEPReferredAndAttendedCompositionIndicator = Indicators.newCountIndicator("HIVnegLEEPReferredAndAttendedCompositionIndicator",
                HIVnegLEEPReferredAndAttendedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D12HIVneg", "Number of women HIV negative and referred for LEEP &Colposcopy attended referral", new Mapped(
                HIVnegLEEPReferredAndAttendedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D13 ====================================================

        SqlCohortDefinition confirmedBreastCancer=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("confirmedBreastCancer",screeningExaminationForms,confirmedCancerDiagnosis,breastCancer);
        SqlCohortDefinition numberOfPatientsWithBiradsSixorabove =Cohorts.getPatientsWithObservationInFormBetweenStartAndEndDateAndObsValueGreaterThanOrEqualTo("numberOfPatientsWithBiradsSixorabove",breastCancerScreeningForms,biradsFinalAssessmentCategory,6);



        CompositionCohortDefinition BiopsyConfirmingCancerComposition=new CompositionCohortDefinition();
        BiopsyConfirmingCancerComposition.setName("BiopsyConfirmingCancerComposition");
        BiopsyConfirmingCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        BiopsyConfirmingCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        BiopsyConfirmingCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedBreastCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        BiopsyConfirmingCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsSixorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        BiopsyConfirmingCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(female, null));
        BiopsyConfirmingCancerComposition.setCompositionString("1 AND 2");

        CohortIndicator BiopsyConfirmingCancerCompositionIndicator = Indicators.newCountIndicator("BiopsyConfirmingCancerCompositionIndicator",
                BiopsyConfirmingCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D13", "Number of breast biopsies confirming cancer", new Mapped(
                BiopsyConfirmingCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

         // ==================== D13HIVpos ====================================================



        CompositionCohortDefinition HIVposBiopsyConfirmingCancerComposition=new CompositionCohortDefinition();
        HIVposBiopsyConfirmingCancerComposition.setName("HIVposBiopsyConfirmingCancerComposition");
        HIVposBiopsyConfirmingCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposBiopsyConfirmingCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposBiopsyConfirmingCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedBreastCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposBiopsyConfirmingCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsSixorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposBiopsyConfirmingCancerComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposBiopsyConfirmingCancerComposition.setCompositionString("1 AND 2 AND 3");

        CohortIndicator HIVposBiopsyConfirmingCancerCompositionIndicator = Indicators.newCountIndicator("HIVposBiopsyConfirmingCancerCompositionIndicator",
                HIVposBiopsyConfirmingCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D13HIVpos", "Number of HIV positive and breast biopsies confirming cancer", new Mapped(
                HIVposBiopsyConfirmingCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

         // ==================== D13HIVneg ====================================================


        CompositionCohortDefinition HIVnegBiopsyConfirmingCancerComposition=new CompositionCohortDefinition();
        HIVnegBiopsyConfirmingCancerComposition.setName("HIVnegBiopsyConfirmingCancerComposition");
        HIVnegBiopsyConfirmingCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegBiopsyConfirmingCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegBiopsyConfirmingCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedBreastCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegBiopsyConfirmingCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsSixorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegBiopsyConfirmingCancerComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegBiopsyConfirmingCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegBiopsyConfirmingCancerComposition.setCompositionString("1 AND 2 AND (3 OR 4)");

        CohortIndicator HIVnegBiopsyConfirmingCancerCompositionIndicator = Indicators.newCountIndicator("HIVnegBiopsyConfirmingCancerCompositionIndicator",
                HIVnegBiopsyConfirmingCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D13HIVneg", "Number of HIV negative and breast biopsies confirming cancer", new Mapped(
                HIVnegBiopsyConfirmingCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));





        // ==================== D14 ====================================================




        CompositionCohortDefinition femaleAbnomalBreastReferredComposition=new CompositionCohortDefinition();
        femaleAbnomalBreastReferredComposition.setName("femaleAbnomalBreastReferredComposition");
        femaleAbnomalBreastReferredComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleAbnomalBreastReferredComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleAbnomalBreastReferredComposition.getSearches().put("1",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleAbnomalBreastReferredComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleAbnomalBreastReferredComposition.getSearches().put("3",new Mapped<CohortDefinition>(female, null));
        femaleAbnomalBreastReferredComposition.setCompositionString("1 AND 2 AND 3");

        CohortIndicator femaleAbnomalBreastReferredCompositionIndicator = Indicators.newCountIndicator("femaleAbnomalBreastReferredCompositionIndicator",
                femaleAbnomalBreastReferredComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D14", "Number of women with abnormal breast exams reffered", new Mapped(
                femaleAbnomalBreastReferredCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D14HIVpos ====================================================




        CompositionCohortDefinition HIVposfemaleAbnomalBreastReferredComposition=new CompositionCohortDefinition();
        HIVposfemaleAbnomalBreastReferredComposition.setName("HIVposfemaleAbnomalBreastReferredComposition");
        HIVposfemaleAbnomalBreastReferredComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleAbnomalBreastReferredComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleAbnomalBreastReferredComposition.getSearches().put("1",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleAbnomalBreastReferredComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleAbnomalBreastReferredComposition.getSearches().put("3",new Mapped<CohortDefinition>(female, null));
        HIVposfemaleAbnomalBreastReferredComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposfemaleAbnomalBreastReferredComposition.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator HIVposfemaleAbnomalBreastReferredCompositionIndicator = Indicators.newCountIndicator("HIVposfemaleAbnomalBreastReferredCompositionIndicator",
                HIVposfemaleAbnomalBreastReferredComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D14HIVpos", "Number of women HIV positive and with abnormal breast exams reffered", new Mapped(
                HIVposfemaleAbnomalBreastReferredCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D14HIVneg ====================================================




        CompositionCohortDefinition HIVnegfemaleAbnomalBreastReferredComposition=new CompositionCohortDefinition();
        HIVnegfemaleAbnomalBreastReferredComposition.setName("HIVnegfemaleAbnomalBreastReferredComposition");
        HIVnegfemaleAbnomalBreastReferredComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleAbnomalBreastReferredComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleAbnomalBreastReferredComposition.getSearches().put("1",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleAbnomalBreastReferredComposition.getSearches().put("2",new Mapped<CohortDefinition>(FurtherManagementAsReasonReferred, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleAbnomalBreastReferredComposition.getSearches().put("3",new Mapped<CohortDefinition>(female, null));
        HIVnegfemaleAbnomalBreastReferredComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleAbnomalBreastReferredComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegfemaleAbnomalBreastReferredComposition.setCompositionString("1 AND 2 AND 3 AND (4 OR 5)");

        CohortIndicator HIVnegfemaleAbnomalBreastReferredCompositionIndicator = Indicators.newCountIndicator("HIVnegfemaleAbnomalBreastReferredCompositionIndicator",
                HIVnegfemaleAbnomalBreastReferredComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D14HIVneg", "Number of women HIV negative and with abnormal breast exams reffered", new Mapped(
                HIVnegfemaleAbnomalBreastReferredCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




    // ==================== D15 ====================================================

        SqlCohortDefinition BreastMassFindings=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForBreastCancerWithAbnormalBreastFindings",breastCancerScreeningForms,breastDiagnosis,breastMass);

        SqlCohortDefinition axillaMassFindings=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForBreastCancerWithaxillaMassFindings",breastCancerScreeningForms,breastDiagnosis,axillaMass);
        SqlCohortDefinition focalBreastPainFindings=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForBreastCancerWithfocalBreastPainFindings",breastCancerScreeningForms,breastDiagnosis,focalBreastPain);
        SqlCohortDefinition bloodyNippleDischargeFindings=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("screenedForBreastCancerWithbloodyNippleDischargeFindings",breastCancerScreeningForms,breastDiagnosis,bloodyNippleDischarge);

        SqlCohortDefinition numberofPeopleReceivingDiagnosticWithBreastUltrasound=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("numberofPeopleReceivingDiagnosticWithBreastUltrasound",breastCancerScreeningForms,breastUltrasound,yes);


        CompositionCohortDefinition abnormalBreastGotdiagnosticUltrasoundComposition=new CompositionCohortDefinition();
        abnormalBreastGotdiagnosticUltrasoundComposition.setName("abnormalBreastGotdiagnosticUltrasoundComposition");
        abnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        abnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(numberofPeopleReceivingDiagnosticWithBreastUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        abnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        abnormalBreastGotdiagnosticUltrasoundComposition.setCompositionString("1 AND (2 OR 3 OR 4 OR 5) AND 6");

        CohortIndicator abnormalBreastGotdiagnosticUltrasoundCompositionIndicator = Indicators.newCountIndicator("abnormalBreastGotdiagnosticUltrasoundCompositionIndicator",
                abnormalBreastGotdiagnosticUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D15", "Number of abnormal breast conditions recieved diagnostic ultrasound", new Mapped(
                abnormalBreastGotdiagnosticUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

            // ==================== D15HIVpos ====================================================



        CompositionCohortDefinition HIVposabnormalBreastGotdiagnosticUltrasoundComposition=new CompositionCohortDefinition();
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.setName("HIVposabnormalBreastGotdiagnosticUltrasoundComposition");
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(numberofPeopleReceivingDiagnosticWithBreastUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("7",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposabnormalBreastGotdiagnosticUltrasoundComposition.setCompositionString("1 AND (2 OR 3 OR 4 OR 5) AND 6 AND 7");

        CohortIndicator HIVposabnormalBreastGotdiagnosticUltrasoundCompositionIndicator = Indicators.newCountIndicator("HIVposabnormalBreastGotdiagnosticUltrasoundCompositionIndicator",
                HIVposabnormalBreastGotdiagnosticUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D15HIVpos", "Number of HIV positive and abnormal breast conditions recieved diagnostic ultrasound", new Mapped(
                HIVposabnormalBreastGotdiagnosticUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

            // ==================== D15HIVneg ====================================================


        CompositionCohortDefinition HIVnegabnormalBreastGotdiagnosticUltrasoundComposition=new CompositionCohortDefinition();
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.setName("HIVnegabnormalBreastGotdiagnosticUltrasoundComposition");
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(numberofPeopleReceivingDiagnosticWithBreastUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(screenedForBreastCancerWithAbnormalBreastFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("7",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.getSearches().put("8",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegabnormalBreastGotdiagnosticUltrasoundComposition.setCompositionString("1 AND (2 OR 3 OR 4 OR 5) AND 6 AND (7 OR 8)");

        CohortIndicator HIVnegabnormalBreastGotdiagnosticUltrasoundCompositionIndicator = Indicators.newCountIndicator("HIVnegabnormalBreastGotdiagnosticUltrasoundCompositionIndicator",
                HIVnegabnormalBreastGotdiagnosticUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D15HIVneg", "Number of HIV negative and abnormal breast conditions recieved diagnostic ultrasound", new Mapped(
                HIVnegabnormalBreastGotdiagnosticUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D16 ====================================================


        CompositionCohortDefinition breastMassComposition=new CompositionCohortDefinition();
        breastMassComposition.setName("breastMassComposition");
        breastMassComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        breastMassComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        breastMassComposition.getSearches().put("1",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastMassComposition.getSearches().put("2",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastMassComposition.getSearches().put("3",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastMassComposition.getSearches().put("4",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        breastMassComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        breastMassComposition.setCompositionString("1 OR 2 OR 3 OR 4");

        CohortIndicator breastMassCompositionIndicator = Indicators.newCountIndicator("breastMassCompositionIndicator",
                breastMassComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D16", "Number of abnormal breast indications for Ultrasound", new Mapped(
                breastMassCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D16HIVpos ====================================================


        CompositionCohortDefinition HIVposbreastMassComposition=new CompositionCohortDefinition();
        HIVposbreastMassComposition.setName("HIVposbreastMassComposition");
        HIVposbreastMassComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposbreastMassComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposbreastMassComposition.getSearches().put("1",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastMassComposition.getSearches().put("2",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastMassComposition.getSearches().put("3",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastMassComposition.getSearches().put("4",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastMassComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposbreastMassComposition.setCompositionString("(1 OR 2 OR 3 OR 4) and 5 ");

        CohortIndicator HIVposbreastMassCompositionIndicator = Indicators.newCountIndicator("HIVposbreastMassCompositionIndicator",
                HIVposbreastMassComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D16HIVpos", "Number of HIV positive and abnormal breast indications for Ultrasound", new Mapped(
                HIVposbreastMassCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D16HIVneg ====================================================


        CompositionCohortDefinition HIVnegHIVnegbreastMassComposition=new CompositionCohortDefinition();
        HIVnegHIVnegbreastMassComposition.setName("HIVnegHIVnegbreastMassComposition");
        HIVnegHIVnegbreastMassComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegHIVnegbreastMassComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegHIVnegbreastMassComposition.getSearches().put("1",new Mapped<CohortDefinition>(BreastMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHIVnegbreastMassComposition.getSearches().put("2",new Mapped<CohortDefinition>(axillaMassFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHIVnegbreastMassComposition.getSearches().put("3",new Mapped<CohortDefinition>(focalBreastPainFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHIVnegbreastMassComposition.getSearches().put("4",new Mapped<CohortDefinition>(bloodyNippleDischargeFindings, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegHIVnegbreastMassComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegHIVnegbreastMassComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegHIVnegbreastMassComposition.setCompositionString("(1 OR 2 OR 3 OR 4) AND (5 OR 6)");

        CohortIndicator HIVnegHIVnegbreastMassCompositionIndicator = Indicators.newCountIndicator("HIVnegHIVnegbreastMassCompositionIndicator",
                HIVnegHIVnegbreastMassComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D16HIVneg", "Number of HIV negative and abnormal breast indications for Ultrasound", new Mapped(
                HIVnegHIVnegbreastMassCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D17 ====================================================

        SqlCohortDefinition complexBreastMassUltrasoundResults=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("complexBreastMassUltrasoundResults",breastCancerScreeningForms,breastMassUltrasoundResults,complexcystic);
        SqlCohortDefinition numberOfScreenedPeopleWithIntermediateSolidMass=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("numberOfScreenedPeopleWithIntermediateSolidMass",breastCancerScreeningForms,solidMass,intermediate);
        SqlCohortDefinition numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass",breastCancerScreeningForms,solidMass,highSuspiciousForMalignancy);

        SqlCohortDefinition abnormalPalpableLymphNodeUltrasound=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("abnormalPalpableLymphNodeUltrasound",breastCancerScreeningForms,axillaryMassUltrasoundResults,abnormalPalpableLymphNode);
        SqlCohortDefinition abnormalNonPalpableLymphNodeUltrasound=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("abnormalNonPalpableLymphNodeUltrasound",breastCancerScreeningForms,axillaryMassUltrasoundResults,abnormalNonPalpableLymphNode);
        SqlCohortDefinition numberOfPatientsWithBiradsFourorabove =Cohorts.getPatientsWithObservationInFormBetweenStartAndEndDateAndObsValueGreaterThanOrEqualTo("numberOfPatientsWithBiradsFourorabove",breastCancerScreeningForms,biradsFinalAssessmentCategory,4);
        SqlCohortDefinition numberOfPatientWithBreastBiopsyPerformed=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("numberOfWomenWithBreastBiopsyPerformed",breastCancerScreeningForms,proceduresDone,BIOPSY);





        CompositionCohortDefinition breastBiopsiesPerformedComposition=new CompositionCohortDefinition();
        breastBiopsiesPerformedComposition.setName("breastBiopsiesPerformedComposition");
        breastBiopsiesPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        breastBiopsiesPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        breastBiopsiesPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        breastBiopsiesPerformedComposition.getSearches().put("7",new Mapped<CohortDefinition>(numberOfPatientWithBreastBiopsyPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        breastBiopsiesPerformedComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        breastBiopsiesPerformedComposition.setCompositionString("(1 OR 2 OR 3 OR 4 OR 5 OR 6) AND 7");

        CohortIndicator breastBiopsiesPerformedCompositionIndicator = Indicators.newCountIndicator("breastBiopsiesPerformedCompositionIndicator",
                breastBiopsiesPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D17", "Number of breast biopsies performed", new Mapped(
                breastBiopsiesPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D17HIVpos ====================================================


        CompositionCohortDefinition HIVposbreastBiopsiesPerformedComposition=new CompositionCohortDefinition();
        HIVposbreastBiopsiesPerformedComposition.setName("HIVposbreastBiopsiesPerformedComposition");
        HIVposbreastBiopsiesPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposbreastBiopsiesPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("7",new Mapped<CohortDefinition>(numberOfPatientWithBreastBiopsyPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposbreastBiopsiesPerformedComposition.getSearches().put("8",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposbreastBiopsiesPerformedComposition.setCompositionString("(1 OR 2 OR 3 OR 4 OR 5 OR 6) AND 7 AND 8");

        CohortIndicator HIVposbreastBiopsiesPerformedCompositionIndicator = Indicators.newCountIndicator("HIVposbreastBiopsiesPerformedCompositionIndicator",
                HIVposbreastBiopsiesPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D17HIVpos", "Number of HIV positive and breast biopsies performed", new Mapped(
                HIVposbreastBiopsiesPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D17HIVneg ====================================================


        CompositionCohortDefinition HIVnegbreastBiopsiesPerformedComposition=new CompositionCohortDefinition();
        HIVnegbreastBiopsiesPerformedComposition.setName("HIVnegbreastBiopsiesPerformedComposition");
        HIVnegbreastBiopsiesPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegbreastBiopsiesPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("7",new Mapped<CohortDefinition>(numberOfPatientWithBreastBiopsyPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("8",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegbreastBiopsiesPerformedComposition.getSearches().put("9",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegbreastBiopsiesPerformedComposition.setCompositionString("(1 OR 2 OR 3 OR 4 OR 5 OR 6) AND 7 AND (8 OR 9)");

        CohortIndicator HIVnegbreastBiopsiesPerformedCompositionIndicator = Indicators.newCountIndicator("HIVnegbreastBiopsiesPerformedCompositionIndicator",
                HIVnegbreastBiopsiesPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D17HIVneg", "Number of HIV negative and breast biopsies performed", new Mapped(
                HIVnegbreastBiopsiesPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));







        // ==================== D18 ====================================================



        CompositionCohortDefinition suspectedBreastMalignancyUltrasoundComposition=new CompositionCohortDefinition();
        suspectedBreastMalignancyUltrasoundComposition.setName("suspectedBreastMalignancyUltrasoundComposition");
        suspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        suspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        suspectedBreastMalignancyUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        suspectedBreastMalignancyUltrasoundComposition.setCompositionString("1 OR 2 OR 3 OR 4 OR 5 OR 6");

        CohortIndicator suspectedBreastMalignancyUltrasoundCompositionIndicator = Indicators.newCountIndicator("suspectedBreastMalignancyUltrasoundCompositionIndicator",
                suspectedBreastMalignancyUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D18", "Number of suspected breast malignancy under Ultrasound", new Mapped(
                suspectedBreastMalignancyUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D18HIVpos ====================================================



        CompositionCohortDefinition HIVpossuspectedBreastMalignancyUltrasoundComposition=new CompositionCohortDefinition();
        HIVpossuspectedBreastMalignancyUltrasoundComposition.setName("HIVpossuspectedBreastMalignancyUltrasoundComposition");
        HIVpossuspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.getSearches().put("7",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpossuspectedBreastMalignancyUltrasoundComposition.setCompositionString("(1 OR 2 OR 3 OR 4 OR 5 OR 6) AND 7");

        CohortIndicator HIVpossuspectedBreastMalignancyUltrasoundCompositionIndicator = Indicators.newCountIndicator("HIVpossuspectedBreastMalignancyUltrasoundCompositionIndicator",
                HIVpossuspectedBreastMalignancyUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D18HIVpos", "Number of HIV positive and suspected breast malignancy under Ultrasound", new Mapped(
                HIVpossuspectedBreastMalignancyUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D18HIVneg ====================================================



        CompositionCohortDefinition HIVnegsuspectedBreastMalignancyUltrasoundComposition=new CompositionCohortDefinition();
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.setName("HIVnegsuspectedBreastMalignancyUltrasoundComposition");
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("1",new Mapped<CohortDefinition>(complexBreastMassUltrasoundResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("2",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithIntermediateSolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("3",new Mapped<CohortDefinition>(numberOfScreenedPeopleWithHighSuspiciousForMalignancySolidMass, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("4",new Mapped<CohortDefinition>(abnormalPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("5",new Mapped<CohortDefinition>(abnormalNonPalpableLymphNodeUltrasound, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("6",new Mapped<CohortDefinition>(numberOfPatientsWithBiradsFourorabove, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("7",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.getSearches().put("8",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegsuspectedBreastMalignancyUltrasoundComposition.setCompositionString("(1 OR 2 OR 3 OR 4 OR 5 OR 6) AND (7 OR 8)");

        CohortIndicator HIVnegsuspectedBreastMalignancyUltrasoundCompositionIndicator = Indicators.newCountIndicator("HIVnegsuspectedBreastMalignancyUltrasoundCompositionIndicator",
                HIVnegsuspectedBreastMalignancyUltrasoundComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D18HIVneg", "Number of HIV negative and suspected breast malignancy under Ultrasound", new Mapped(
                HIVnegsuspectedBreastMalignancyUltrasoundCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));







        // ==================== D19 ====================================================

        SqlCohortDefinition confirmedCervicalCancer=Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("confirmedCervicalCancer",screeningExaminationForms,confirmedCancerDiagnosis,cervicalCancer);



        CompositionCohortDefinition femaleBiopsiesWithSuspectedCervicalCancerComposition=new CompositionCohortDefinition();
        femaleBiopsiesWithSuspectedCervicalCancerComposition.setName("femaleBiopsiesWithSuspectedCervicalCancerComposition");
        femaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        femaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("3",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("5",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        femaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        femaleBiopsiesWithSuspectedCervicalCancerComposition.setCompositionString("1 AND 2 and 3");

        CohortIndicator femaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator = Indicators.newCountIndicator("femaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator",
                femaleBiopsiesWithSuspectedCervicalCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D19", "Number of women suspected with cervical cancer whose biopsy confirmed cervical cancer", new Mapped(
                femaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



    // ==================== D19HIVpos ====================================================



        CompositionCohortDefinition HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition=new CompositionCohortDefinition();
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.setName("HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition");
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("3",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("5",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator HIVposfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator = Indicators.newCountIndicator("HIVposfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator",
                HIVposfemaleBiopsiesWithSuspectedCervicalCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D19HIVpos", "Number of women HIV positive and suspected with cervical cancer whose biopsy confirmed cervical cancer", new Mapped(
                HIVposfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D19HIVneg ====================================================



        CompositionCohortDefinition HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition=new CompositionCohortDefinition();
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.setName("HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition");
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIACancerSuspectedResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("3",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("5",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition.setCompositionString("1 AND 2 AND 3 AND (4 OR 5)");

        CohortIndicator HIVnegfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator = Indicators.newCountIndicator("HIVnegfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator",
                HIVnegfemaleBiopsiesWithSuspectedCervicalCancerComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D19HIVneg", "Number of women HIV negative and suspected with cervical cancer whose biopsy confirmed cervical cancer", new Mapped(
                HIVnegfemaleBiopsiesWithSuspectedCervicalCancerCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D20 ====================================================
        SqlCohortDefinition VIATriagePerformed =Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("VIATriage",screeningCervicalForms,typeOfVIAPerformed, VIATriage);



        CompositionCohortDefinition testedViaTriagePositiveComposition=new CompositionCohortDefinition();
        testedViaTriagePositiveComposition.setName("testedViaTriagePositiveComposition");
        testedViaTriagePositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriagePositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriagePositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriagePositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriagePositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriagePositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriagePositiveComposition.setCompositionString("1 AND (2 OR 3) ");

        CohortIndicator testedViaTriagePositiveCompositionIndicator = Indicators.newCountIndicator("testedViaTriagePositiveCompositionIndicator",
                testedViaTriagePositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D20", "Number of women tested VIA triage positive", new Mapped(
                testedViaTriagePositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

            // ==================== D20HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriagePositiveComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriagePositiveComposition.setName("HIVpostestedViaTriagePositiveComposition");
        HIVpostestedViaTriagePositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriagePositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriagePositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriagePositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriagePositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriagePositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriagePositiveComposition.setCompositionString("1 AND (2 OR 3) AND 4 ");

        CohortIndicator HIVpostestedViaTriagePositiveCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriagePositiveCompositionIndicator",
                HIVpostestedViaTriagePositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D20HIVpos", "Number of women HIV positive and tested VIA triage positive", new Mapped(
                HIVpostestedViaTriagePositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

            // ==================== D20HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriagePositiveComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriagePositiveComposition.setName("HIVnegtestedViaTriagePositiveComposition");
        HIVnegtestedViaTriagePositiveComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriagePositiveComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriagePositiveComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriagePositiveComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriagePositiveComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriagePositiveComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriagePositiveComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriagePositiveComposition.setCompositionString("1 AND (2 OR 3) AND (4 OR 5) ");

        CohortIndicator HIVnegtestedViaTriagePositiveCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriagePositiveCompositionIndicator",
                HIVnegtestedViaTriagePositiveComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D20HIVneg", "Number of women HIV negative and tested VIA triage positive", new Mapped(
                HIVnegtestedViaTriagePositiveCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));





        // ==================== D21 ====================================================

        CompositionCohortDefinition testedViaTriageComposition=new CompositionCohortDefinition();
        testedViaTriageComposition.setName("testedViaTriageComposition");
        testedViaTriageComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageComposition.getSearches().put("2",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageComposition.setCompositionString("1 and 2");

        CohortIndicator testedViaTriageCompositionIndicator = Indicators.newCountIndicator("testedViaTriageCompositionIndicator",
                testedViaTriageComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D21", "Number of HPV-positive women who received VIA triage", new Mapped(
                testedViaTriageCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D21HIVpos ====================================================

        CompositionCohortDefinition HIVpostestedViaTriageComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageComposition.setName("HIVpostestedViaTriageComposition");
        HIVpostestedViaTriageComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaTriageComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaTriageComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageComposition.setCompositionString("1 AND 2");

        CohortIndicator HIVpostestedViaTriageCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageCompositionIndicator",
                HIVpostestedViaTriageComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D21HIVpos", "Number of HIV positive and HPV-positive women who received VIA triage", new Mapped(
                HIVpostestedViaTriageCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D21HIVneg ====================================================

        CompositionCohortDefinition HIVnegtestedViaTriageComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageComposition.setName("HIVnegtestedViaTriageComposition");
        HIVnegtestedViaTriageComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaTriageComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaTriageComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageComposition.setCompositionString("1 AND (2 OR 3)");

        CohortIndicator HIVnegtestedViaTriageCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageCompositionIndicator",
                HIVnegtestedViaTriageComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D21HIVneg", "Number of HIV negative and HPV-positive women who received VIA triage", new Mapped(
                HIVnegtestedViaTriageCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));





        // ==================== D22 ====================================================

        SqlCohortDefinition ThermalAblationAsTypeOfTreatment =Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("ThermalAblationAsTypeOfTreatment",screeningCervicalForms,typeOfTreatmentPerformed, thermalAblation);


        CompositionCohortDefinition testedViaTriageTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        testedViaTriageTreatedWithThermoablationComposition.setName("testedViaTriageTreatedWithThermoablationComposition");
        testedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator testedViaTriageTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("testedViaTriageTreatedWithThermoablationCompositionIndicator",
                testedViaTriageTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D22", "Number of HPV+ & VIA Triage+ women treated with thermoablation", new Mapped(
                testedViaTriageTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D22HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageTreatedWithThermoablationComposition.setName("HIVpostestedViaTriageTreatedWithThermoablationComposition");
        HIVpostestedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3 and 4 and 5");

        CohortIndicator HIVpostestedViaTriageTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageTreatedWithThermoablationCompositionIndicator",
                HIVpostestedViaTriageTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D22HIVpos", "Number of HIV positive and HPV+ & VIA Triage+ women treated with thermoablation", new Mapped(
                HIVpostestedViaTriageTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D22HIVneg ====================================================


        CompositionCohortDefinition HIVnegtestedViaTriageTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.setName("HIVnegtestedViaTriageTreatedWithThermoablationComposition");
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3 and 4 and (5 or 6)");

        CohortIndicator HIVnegtestedViaTriageTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageTreatedWithThermoablationCompositionIndicator",
                HIVnegtestedViaTriageTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D22HIVneg", "Number of HIV negative and HPV+ & VIA Triage+ women treated with thermoablation", new Mapped(
                HIVnegtestedViaTriageTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D23 ====================================================



        CompositionCohortDefinition testedViaTriageThermalAblationAsResultsComposition=new CompositionCohortDefinition();
        testedViaTriageThermalAblationAsResultsComposition.setName("testedViaTriageThermalAblationAsResultsComposition");
        testedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageThermalAblationAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageThermalAblationAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageThermalAblationAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageThermalAblationAsResultsComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator testedViaTriageThermalAblationAsResultsCompositionIndicator = Indicators.newCountIndicator("testedViaTriageThermalAblationAsResultsCompositionIndicator",
                testedViaTriageThermalAblationAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D23", "Number of HPV+ & VIA Triage+ women eligible for Thermal ablation", new Mapped(
                testedViaTriageThermalAblationAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D23HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageThermalAblationAsResultsComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageThermalAblationAsResultsComposition.setName("HIVpostestedViaTriageThermalAblationAsResultsComposition");
        HIVpostestedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageThermalAblationAsResultsComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVpostestedViaTriageThermalAblationAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageThermalAblationAsResultsCompositionIndicator",
                HIVpostestedViaTriageThermalAblationAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D23HIVpos", "Number of HIV positive and HPV+ & VIA Triage+ women eligible for Thermal ablation", new Mapped(
                HIVpostestedViaTriageThermalAblationAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D23HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageThermalAblationAsResultsComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.setName("testedViaTriageThermalAblationAsResultsComposition");
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageThermalAblationAsResultsComposition.setCompositionString("1 and 2 and 3 and (4 or 5)");

        CohortIndicator HIVnegtestedViaTriageThermalAblationAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageThermalAblationAsResultsCompositionIndicator",
                HIVnegtestedViaTriageThermalAblationAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D23HIVneg", "Number of HIV negative and HPV+ & VIA Triage+ women eligible for Thermal ablation", new Mapped(
                HIVnegtestedViaTriageThermalAblationAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D24 ====================================================



        CompositionCohortDefinition testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setName("testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition");
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator testedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("testedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator",
                testedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D24", "Number of HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                testedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D24HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setName("HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition");
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setCompositionString("1 and 2 and 3 and 4 and 5");

        CohortIndicator HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator",
                HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D24HIVpos", "Number of HIV positive and HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                HIVpostestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D24HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setName("HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition");
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition.setCompositionString("1 and 2 and 3 and 4 and (5 or 6)");

        CohortIndicator HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator",
                HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D24HIVneg", "Number of HIV negative and HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                HIVnegtestedViaTriageNegativeAsResultsAndThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D25 ====================================================



        CompositionCohortDefinition testedViaTriageNegativeAsResultsComposition=new CompositionCohortDefinition();
        testedViaTriageNegativeAsResultsComposition.setName("testedViaTriageNegativeAsResultsComposition");
        testedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageNegativeAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageNegativeAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageNegativeAsResultsComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator testedViaTriageNegativeAsResultsCompositionCompositionIndicator = Indicators.newCountIndicator("testedViaTriageNegativeAsResultsCompositionCompositionIndicator",
                testedViaTriageNegativeAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D25", "Number of HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                testedViaTriageNegativeAsResultsCompositionCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D25HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageNegativeAsResultsComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageNegativeAsResultsComposition.setName("HIVpostestedViaTriageNegativeAsResultsComposition");
        HIVpostestedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageNegativeAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageNegativeAsResultsComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVpostestedViaTriageNegativeAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageNegativeAsResultsCompositionIndicator",
                HIVpostestedViaTriageNegativeAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D25HIVpos", "Number of HIV positive and HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                HIVpostestedViaTriageNegativeAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D25HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageNegativeAsResultsComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageNegativeAsResultsComposition.setName("HIVnegtestedViaTriageNegativeAsResultsComposition");
        HIVnegtestedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageNegativeAsResultsComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageNegativeAsResultsComposition.setCompositionString("1 and 2 and 3 and (4 OR 5)");

        CohortIndicator HIVnegtestedViaTriageNegativeAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageNegativeAsResultsCompositionIndicator",
                HIVnegtestedViaTriageNegativeAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D25HIVneg", "Number of HIV negative and HPV+ & VIA Triage- women treated with thermoablation", new Mapped(
                HIVnegtestedViaTriageNegativeAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D26 ====================================================



        CompositionCohortDefinition testedViaScreenTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        testedViaScreenTreatedWithThermoablationComposition.setName("testedViaScreenTreatedWithThermoablationComposition");
        testedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaScreenTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaScreenTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator testedViaScreenTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("testedViaScreenTreatedWithThermoablationCompositionIndicator",
                testedViaScreenTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D26", "Number of VIA screen positive women treated with thermoablation", new Mapped(
                testedViaScreenTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D26HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaScreenTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        HIVpostestedViaScreenTreatedWithThermoablationComposition.setName("HIVpostestedViaScreenTreatedWithThermoablationComposition");
        HIVpostestedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaScreenTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVpostestedViaScreenTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaScreenTreatedWithThermoablationCompositionIndicator",
                HIVpostestedViaScreenTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D26HIVpos", "Number of HIV positive and VIA screen positive women treated with thermoablation", new Mapped(
                HIVpostestedViaScreenTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D26HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaScreenTreatedWithThermoablationComposition=new CompositionCohortDefinition();
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.setName("HIVnegtestedViaScreenTreatedWithThermoablationComposition");
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaScreenTreatedWithThermoablationComposition.setCompositionString("1 and 2 and 3 and (4 or 5)");

        CohortIndicator HIVnegtestedViaScreenTreatedWithThermoablationCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaScreenTreatedWithThermoablationCompositionIndicator",
                HIVnegtestedViaScreenTreatedWithThermoablationComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D26HIVneg", "Number of HIV negative and VIA screen positive women treated with thermoablation", new Mapped(
                HIVnegtestedViaScreenTreatedWithThermoablationCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D27 ====================================================



        CompositionCohortDefinition testedViaScreenWithThermoablationResultsComposition=new CompositionCohortDefinition();
        testedViaScreenWithThermoablationResultsComposition.setName("testedViaScreenWithThermoablationResultsComposition");
        testedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaScreenWithThermoablationResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenWithThermoablationResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenWithThermoablationResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaScreenWithThermoablationResultsComposition.setCompositionString("1 and 2");

        CohortIndicator testedViaScreenWithThermoablationResultsCompositionIndicator = Indicators.newCountIndicator("testedViaScreenWithThermoablationResultsCompositionIndicator",
                testedViaScreenWithThermoablationResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D27", "Number of VIA Screen positive eligible for Thermal ablation", new Mapped(
                testedViaScreenWithThermoablationResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D27HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaScreenWithThermoablationResultsComposition=new CompositionCohortDefinition();
        HIVpostestedViaScreenWithThermoablationResultsComposition.setName("HIVpostestedViaScreenWithThermoablationResultsComposition");
        HIVpostestedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaScreenWithThermoablationResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenWithThermoablationResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaScreenWithThermoablationResultsComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVpostestedViaScreenWithThermoablationResultsCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaScreenWithThermoablationResultsCompositionIndicator",
                HIVpostestedViaScreenWithThermoablationResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D27HIVpos", "Number of HIV positive and VIA Screen positive eligible for Thermal ablation", new Mapped(
                HIVpostestedViaScreenWithThermoablationResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D27HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaScreenWithThermoablationResultsComposition=new CompositionCohortDefinition();
        HIVnegtestedViaScreenWithThermoablationResultsComposition.setName("HIVnegtestedViaScreenWithThermoablationResultsComposition");
        HIVnegtestedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAAndEligibleForThermalAblationResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaScreenWithThermoablationResultsComposition.setCompositionString("1 and 2 and (3 or 4)");

        CohortIndicator HIVnegtestedViaScreenWithThermoablationResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaScreenWithThermoablationResultsCompositionIndicator",
                HIVnegtestedViaScreenWithThermoablationResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D27HIVneg", "Number of HIV negative and VIA Screen positive eligible for Thermal ablation", new Mapped(
                HIVnegtestedViaScreenWithThermoablationResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D28 ====================================================

        SqlCohortDefinition LEEPAsTypeOfTreatment =Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("LEEPAsTypeOfTreatment",screeningCervicalForms,typeOfTreatmentPerformed, loopElectrosurgicalExcisionProcedure);


        CompositionCohortDefinition testedViaTriageTreatedWithLEEPComposition=new CompositionCohortDefinition();
        testedViaTriageTreatedWithLEEPComposition.setName("testedViaTriageTreatedWithLEEPComposition");
        testedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator testedViaTriageTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("testedViaTriageTreatedWithLEEPCompositionIndicator",
                testedViaTriageTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D28", "Number of HPV+ & VIA Triage+ women treated with LEEP", new Mapped(
                testedViaTriageTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D28HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageTreatedWithLEEPComposition.setName("HIVpostestedViaTriageTreatedWithLEEPComposition");
        HIVpostestedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageTreatedWithLEEPComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3 and 4 and 5");

        CohortIndicator HIVpostestedViaTriageTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageTreatedWithLEEPCompositionIndicator",
                HIVpostestedViaTriageTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D28HIVpos", "Number of HIV positive and HPV+ & VIA Triage+ women treated with LEEP", new Mapped(
                HIVpostestedViaTriageTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D28HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageTreatedWithLEEPComposition.setName("HIVnegtestedViaTriageTreatedWithLEEPComposition");
        HIVnegtestedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3 and 4 and (5 or 6)");

        CohortIndicator HIVnegtestedViaTriageTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageTreatedWithLEEPCompositionIndicator",
                HIVnegtestedViaTriageTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D28HIVneg", "Number of HIV negative and HPV+ & VIA Triage+ women treated with LEEP", new Mapped(
                HIVnegtestedViaTriageTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D29 ====================================================



        CompositionCohortDefinition testedViaTriageLEEPAsResultsComposition=new CompositionCohortDefinition();
        testedViaTriageLEEPAsResultsComposition.setName("testedViaTriageLEEPAsResultsComposition");
        testedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageLEEPAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageLEEPAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageLEEPAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageLEEPAsResultsComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator testedViaTriageLEEPAsResultsCompositionIndicator = Indicators.newCountIndicator("testedViaTriageLEEPAsResultsCompositionIndicator",
                testedViaTriageLEEPAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D29", "Number of HPV+ & VIA triage + women eligible for LEEP", new Mapped(
                testedViaTriageLEEPAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D29HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageLEEPAsResultsComposition=new CompositionCohortDefinition();
        HIVpostestedViaTriageLEEPAsResultsComposition.setName("HIVpostestedViaTriageLEEPAsResultsComposition");
        HIVpostestedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageLEEPAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageLEEPAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageLEEPAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageLEEPAsResultsComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVpostestedViaTriageLEEPAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageLEEPAsResultsCompositionIndicator",
                HIVpostestedViaTriageLEEPAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D29HIVpos", "Number of HIV positive and HPV+ & VIA triage + women eligible for LEEP", new Mapped(
                HIVpostestedViaTriageLEEPAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D29HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageLEEPAsResultsComposition=new CompositionCohortDefinition();
        HIVnegtestedViaTriageLEEPAsResultsComposition.setName("HIVnegtestedViaTriageLEEPAsResultsComposition");
        HIVnegtestedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageLEEPAsResultsComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageLEEPAsResultsComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageLEEPAsResultsComposition.setCompositionString("1 and 2 and 3 and (4 or 5)");

        CohortIndicator HIVnegtestedViaTriageLEEPAsResultsCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageLEEPAsResultsCompositionIndicator",
                HIVnegtestedViaTriageLEEPAsResultsComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D29HIVneg", "Number of HIV negative and HPV+ & VIA triage + women eligible for LEEP", new Mapped(
                HIVnegtestedViaTriageLEEPAsResultsCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D30 ====================================================



        CompositionCohortDefinition testedViaScreenTreatedWithLEEPComposition=new CompositionCohortDefinition();
        testedViaScreenTreatedWithLEEPComposition.setName("testedViaScreenTreatedWithLEEPComposition");
        testedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaScreenTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaScreenTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator testedViaScreenTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("testedViaScreenTreatedWithLEEPCompositionIndicator",
                testedViaScreenTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D30", "Number of VIA screen positive women treated with LEEP", new Mapped(
                testedViaScreenTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D30HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaScreenTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVpostestedViaScreenTreatedWithLEEPComposition.setName("HIVpostestedViaScreenTreatedWithLEEPComposition");
        HIVpostestedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaScreenTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaScreenTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVpostestedViaScreenTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaScreenTreatedWithLEEPCompositionIndicator",
                HIVpostestedViaScreenTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D30HIVpos", "Number of HIV positive and VIA screen positive women treated with LEEP", new Mapped(
                HIVpostestedViaScreenTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

    // ==================== D30HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaScreenTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVnegtestedViaScreenTreatedWithLEEPComposition.setName("HIVnegtestedViaScreenTreatedWithLEEPComposition");
        HIVnegtestedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaScreenTreatedWithLEEPComposition.setCompositionString("1 and 2 and 3 and (4 or 5)");

        CohortIndicator HIVnegtestedViaScreenTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaScreenTreatedWithLEEPCompositionIndicator",
                HIVnegtestedViaScreenTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D30HIVneg", "Number of HIV negative and VIA screen positive women treated with LEEP", new Mapped(
                HIVnegtestedViaScreenTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D31 ====================================================



        CompositionCohortDefinition testedViaScreenLEEPEligibleComposition=new CompositionCohortDefinition();
        testedViaScreenLEEPEligibleComposition.setName("testedViaScreenLEEPEligibleComposition");
        testedViaScreenLEEPEligibleComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaScreenLEEPEligibleComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaScreenLEEPEligibleComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaScreenLEEPEligibleComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaScreenLEEPEligibleComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaScreenLEEPEligibleComposition.setCompositionString("1 and 2");

        CohortIndicator testedViaScreenLEEPEligibleCompositionIndicator = Indicators.newCountIndicator("testedViaScreenLEEPEligibleCompositionIndicator",
                testedViaScreenLEEPEligibleComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D31", "Number of VIA Screen positive eligible for LEEP", new Mapped(
                testedViaScreenLEEPEligibleCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D31HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaScreenLEEPEligibleComposition=new CompositionCohortDefinition();
        HIVpostestedViaScreenLEEPEligibleComposition.setName("HIVpostestedViaScreenLEEPEligibleComposition");
        HIVpostestedViaScreenLEEPEligibleComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaScreenLEEPEligibleComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaScreenLEEPEligibleComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenLEEPEligibleComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVpostestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaScreenLEEPEligibleComposition.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVpostestedViaScreenLEEPEligibleCompositionIndicator = Indicators.newCountIndicator("HIVpostestedViaScreenLEEPEligibleCompositionIndicator",
                HIVpostestedViaScreenLEEPEligibleComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D31HIVpos", "Number of HIV positive and VIA Screen positive eligible for LEEP", new Mapped(
                HIVpostestedViaScreenLEEPEligibleCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D31HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaScreenLEEPEligibleComposition=new CompositionCohortDefinition();
        HIVnegtestedViaScreenLEEPEligibleComposition.setName("HIVnegtestedViaScreenLEEPEligibleComposition");
        HIVnegtestedViaScreenLEEPEligibleComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaScreenLEEPEligibleComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIAScreenPerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(VIAPositiveLEEPResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaScreenLEEPEligibleComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaScreenLEEPEligibleComposition.setCompositionString("1 and 2 and (3 or 4)");

        CohortIndicator HIVnegtestedViaScreenLEEPEligibleCompositionIndicator = Indicators.newCountIndicator("HIVnegtestedViaScreenLEEPEligibleCompositionIndicator",
                HIVnegtestedViaScreenLEEPEligibleComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D31HIVneg", "Number of HIV negative and VIA Screen positive eligible for LEEP", new Mapped(
                HIVnegtestedViaScreenLEEPEligibleCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D32 ====================================================



        CompositionCohortDefinition ConfirmedCervicalCancerByBiopsyComposition=new CompositionCohortDefinition();
        ConfirmedCervicalCancerByBiopsyComposition.setName("ConfirmedCervicalCancerByBiopsyComposition");
        ConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        ConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        ConfirmedCervicalCancerByBiopsyComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        ConfirmedCervicalCancerByBiopsyComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        ConfirmedCervicalCancerByBiopsyComposition.setCompositionString("1");

        CohortIndicator ConfirmedCervicalCancerByBiopsyCompositionIndicator = Indicators.newCountIndicator("ConfirmedCervicalCancerByBiopsyCompositionIndicator",
                ConfirmedCervicalCancerByBiopsyComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D32", "Number of all cervical biopsies(LEEP/Suspected cancer) confirming cancer", new Mapped(
                ConfirmedCervicalCancerByBiopsyCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D32HIVpos ====================================================



        CompositionCohortDefinition HIVposConfirmedCervicalCancerByBiopsyComposition=new CompositionCohortDefinition();
        HIVposConfirmedCervicalCancerByBiopsyComposition.setName("HIVposConfirmedCervicalCancerByBiopsyComposition");
        HIVposConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposConfirmedCervicalCancerByBiopsyComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposConfirmedCervicalCancerByBiopsyComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposConfirmedCervicalCancerByBiopsyComposition.setCompositionString("1 and 2");

        CohortIndicator HIVposConfirmedCervicalCancerByBiopsyCompositionIndicator = Indicators.newCountIndicator("HIVposConfirmedCervicalCancerByBiopsyCompositionIndicator",
                HIVposConfirmedCervicalCancerByBiopsyComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D32HIVpos", "Number of HIV positive and all cervical biopsies(LEEP/Suspected cancer) confirming cancer", new Mapped(
                HIVposConfirmedCervicalCancerByBiopsyCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D32HIVneg ====================================================



        CompositionCohortDefinition HIVnegConfirmedCervicalCancerByBiopsyComposition=new CompositionCohortDefinition();
        HIVnegConfirmedCervicalCancerByBiopsyComposition.setName("ConfirmedCervicalCancerByBiopsyComposition");
        HIVnegConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegConfirmedCervicalCancerByBiopsyComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegConfirmedCervicalCancerByBiopsyComposition.getSearches().put("1",new Mapped<CohortDefinition>(confirmedCervicalCancer, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegConfirmedCervicalCancerByBiopsyComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegConfirmedCervicalCancerByBiopsyComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegConfirmedCervicalCancerByBiopsyComposition.setCompositionString("1 and (2 or 3)");

        CohortIndicator HIVnegConfirmedCervicalCancerByBiopsyCompositionIndicator = Indicators.newCountIndicator("HIVnegConfirmedCervicalCancerByBiopsyCompositionIndicator",
                HIVnegConfirmedCervicalCancerByBiopsyComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D32HIVneg", "Number of HIV negative and all cervical biopsies(LEEP/Suspected cancer) confirming cancer", new Mapped(
                HIVnegConfirmedCervicalCancerByBiopsyCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D33 ====================================================



        CompositionCohortDefinition BiopsyPerformedComposition=new CompositionCohortDefinition();
        BiopsyPerformedComposition.setName("BiopsyPerformedComposition");
        BiopsyPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        BiopsyPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        BiopsyPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        BiopsyPerformedComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        BiopsyPerformedComposition.setCompositionString("1");

        CohortIndicator BiopsyPerformedCompositionIndicator = Indicators.newCountIndicator("BiopsyPerformedCompositionIndicator",
                BiopsyPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D33", "Number of all cervical biopsies(LEEP/Suspected cancer) performed", new Mapped(
                BiopsyPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D33HIVpos ====================================================



        CompositionCohortDefinition HIVposBiopsyPerformedComposition=new CompositionCohortDefinition();
        HIVposBiopsyPerformedComposition.setName("HIVposBiopsyPerformedComposition");
        HIVposBiopsyPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposBiopsyPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposBiopsyPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposBiopsyPerformedComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposBiopsyPerformedComposition.setCompositionString("1 and 2");

        CohortIndicator HIVposBiopsyPerformedCompositionIndicator = Indicators.newCountIndicator("HIVposBiopsyPerformedCompositionIndicator",
                HIVposBiopsyPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D33HIVpos", "Number of HIV positive and all cervical biopsies(LEEP/Suspected cancer) performed", new Mapped(
                HIVposBiopsyPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D33HIVneg ====================================================



        CompositionCohortDefinition HIVnegBiopsyPerformedComposition=new CompositionCohortDefinition();
        HIVnegBiopsyPerformedComposition.setName("HIVnegBiopsyPerformedComposition");
        HIVnegBiopsyPerformedComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegBiopsyPerformedComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegBiopsyPerformedComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegBiopsyPerformedComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegBiopsyPerformedComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegBiopsyPerformedComposition.setCompositionString("1 and (2 or 3)");

        CohortIndicator HIVnegBiopsyPerformedCompositionIndicator = Indicators.newCountIndicator("HIVnegBiopsyPerformedCompositionIndicator",
                HIVnegBiopsyPerformedComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D33HIVneg", "Number of HIV negative and all cervical biopsies(LEEP/Suspected cancer) performed", new Mapped(
                HIVnegBiopsyPerformedCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D34====================================================



        CompositionCohortDefinition BiopsyPerformedTreatedWithLEEPComposition=new CompositionCohortDefinition();
        BiopsyPerformedTreatedWithLEEPComposition.setName("BiopsyPerformedTreatedWithLEEPComposition");
        BiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        BiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        BiopsyPerformedTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        BiopsyPerformedTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        BiopsyPerformedTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        BiopsyPerformedTreatedWithLEEPComposition.setCompositionString("1 AND 2 ");

        CohortIndicator BiopsyPerformedTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("BiopsyPerformedTreatedWithLEEPCompositionIndicator",
                BiopsyPerformedTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D34", "Number of Biopsies for women treated with LEEP", new Mapped(
                BiopsyPerformedTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D34HIVpos====================================================



        CompositionCohortDefinition HIVposBiopsyPerformedTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVposBiopsyPerformedTreatedWithLEEPComposition.setName("HIVposBiopsyPerformedTreatedWithLEEPComposition");
        HIVposBiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposBiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposBiopsyPerformedTreatedWithLEEPComposition.setCompositionString("1 AND 2 and 3 ");

        CohortIndicator HIVposBiopsyPerformedTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVposBiopsyPerformedTreatedWithLEEPCompositionIndicator",
                HIVposBiopsyPerformedTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D34HIVpos", "Number of HIV positive and Biopsies for women treated with LEEP", new Mapped(
                HIVposBiopsyPerformedTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D34HIVneg====================================================



        CompositionCohortDefinition HIVnegBiopsyPerformedTreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.setName("HIVnegBiopsyPerformedTreatedWithLEEPComposition");
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(biopsyPerformsResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegBiopsyPerformedTreatedWithLEEPComposition.setCompositionString("1 AND 2 and (3 or 4)");

        CohortIndicator HIVnegBiopsyPerformedTreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVnegBiopsyPerformedTreatedWithLEEPCompositionIndicator",
                HIVnegBiopsyPerformedTreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D34HIVneg", "Number of HIV negative and Biopsies for women treated with LEEP", new Mapped(
                HIVnegBiopsyPerformedTreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D35 ====================================================



        CompositionCohortDefinition treatedWithLEEPComposition=new CompositionCohortDefinition();
        treatedWithLEEPComposition.setName("treatedWithLEEPComposition");
        treatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        treatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        treatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        treatedWithLEEPComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        treatedWithLEEPComposition.setCompositionString("1");

        CohortIndicator treatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("treatedWithLEEPCompositionIndicator",
                treatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D35", "Number of women Treated with LEEP", new Mapped(
                treatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D35HIVpos ====================================================



        CompositionCohortDefinition HIVpostreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVpostreatedWithLEEPComposition.setName("HIVpostreatedWithLEEPComposition");
        HIVpostreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostreatedWithLEEPComposition.setCompositionString("1  and 2");

        CohortIndicator HIVpostreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVpostreatedWithLEEPCompositionIndicator",
                HIVpostreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D35HIVpos", "Number of women HIV positive and Treated with LEEP", new Mapped(
                HIVpostreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D35HIVneg ====================================================



        CompositionCohortDefinition HIVnegtreatedWithLEEPComposition=new CompositionCohortDefinition();
        HIVnegtreatedWithLEEPComposition.setName("HIVnegtreatedWithLEEPComposition");
        HIVnegtreatedWithLEEPComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtreatedWithLEEPComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtreatedWithLEEPComposition.getSearches().put("1",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtreatedWithLEEPComposition.getSearches().put("2",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtreatedWithLEEPComposition.getSearches().put("3",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtreatedWithLEEPComposition.setCompositionString("1 and (2 or 3)");

        CohortIndicator HIVnegtreatedWithLEEPCompositionIndicator = Indicators.newCountIndicator("HIVnegtreatedWithLEEPCompositionIndicator",
                HIVnegtreatedWithLEEPComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D35HIVneg", "Number of women HIV negative and Treated with LEEP", new Mapped(
                HIVnegtreatedWithLEEPCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));




        // ==================== D36====================================================

        SqlCohortDefinition postTreatmentFollowupAsFollowupReason =Cohorts.getPatientsWithObservationInEncounterBetweenStartAndEndDate("postTreatmentFollowupAsFollowupReason",screeningExaminationForms,followupReason,postTreatmentFollowup);


        CompositionCohortDefinition postTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        postTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("postTreatedafterLEEPOrThermalAblationTreatmentComposition");
        postTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        postTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        postTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        postTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        postTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        postTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        postTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString("1");

        CohortIndicator postTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("postTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                postTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D36", "Number of women treated in the previous year for precancerous lesions who returned for a post treatment follow-up screening test at 1 year", new Mapped(
                postTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D36HIVpos====================================================



        CompositionCohortDefinition HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition");
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString("3 AND 4 ");

        CohortIndicator HIVpospostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVpospostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                HIVpospostTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D36HIVpos", "Number of women HIV positive and treated in the previous year for precancerous lesions who returned for a post treatment follow-up screening test at 1 year", new Mapped(
                HIVpospostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D36HIVneg====================================================



        CompositionCohortDefinition HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("postTreatedafterLEEPOrThermalAblationTreatmentComposition");
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString("3 AND (4 OR 5)");

        CohortIndicator HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D36HIVneg", "Number of women HIV positive and treated in the previous year for precancerous lesions who returned for a post treatment follow-up screening test at 1 year", new Mapped(
                HIVnegpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));



        // ==================== D37====================================================



        CompositionCohortDefinition treatmentInLastYearComposition=new CompositionCohortDefinition();
        treatmentInLastYearComposition.setName("treatmentInLastYearComposition");
        treatmentInLastYearComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        treatmentInLastYearComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        treatmentInLastYearComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
        treatmentInLastYearComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
//        treatmentInLastYearComposition.getSearches().put("1",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        treatmentInLastYearComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        treatmentInLastYearComposition.setCompositionString("1 or 2");

        CohortIndicator treatmentInLastYearCompositionIndicator = Indicators.newCountIndicator("treatmentInLastYearCompositionIndicator",
                treatmentInLastYearComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D37", "Number of women treated in the previous year for precancerous lesions", new Mapped(
                treatmentInLastYearCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D37HIVpos====================================================



        CompositionCohortDefinition HIVposTreatedLastYearComposition=new CompositionCohortDefinition();
        HIVposTreatedLastYearComposition.setName("HIVposTreatedLastYearComposition");
        HIVposTreatedLastYearComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposTreatedLastYearComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVposTreatedLastYearComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
        HIVposTreatedLastYearComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
//        HIVposTreatedLastYearComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposTreatedLastYearComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposTreatedLastYearComposition.setCompositionString("(1 or 2) AND 4 ");

        CohortIndicator HIVposTreatedLastYearCompositionIndicator = Indicators.newCountIndicator("HIVposTreatedLastYearCompositionIndicator",
                HIVposTreatedLastYearComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D37HIVpos", "Number of women HIV positive and treated in the previous year for precancerous lesions", new Mapped(
                HIVposTreatedLastYearCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));


        // ==================== D37HIVneg====================================================



        CompositionCohortDefinition HIVnegTreatedLastYearComposition=new CompositionCohortDefinition();
        HIVnegTreatedLastYearComposition.setName("HIVnegTreatedLastYearComposition");
        HIVnegTreatedLastYearComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegTreatedLastYearComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegTreatedLastYearComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
        HIVnegTreatedLastYearComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+12m},endDate=${endDate+24m}")));
//        HIVnegTreatedLastYearComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegTreatedLastYearComposition.getSearches().put("4",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegTreatedLastYearComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegTreatedLastYearComposition.setCompositionString("(1 or 2) AND (4 OR 5)");

        CohortIndicator HIVnegTreatedLastYearCompositionIndicator = Indicators.newCountIndicator("HIVnegTreatedLastYearCompositionIndicator",
                HIVnegTreatedLastYearComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D37HIVneg", "Number of women HIV negative and treated in the previous year for precancerous lesions", new Mapped(
                HIVnegTreatedLastYearCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));






        // ==================== D38====================================================

        CompositionCohortDefinition VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition");
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString(" 3 AND 4");

        CohortIndicator VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D38", "Number of women who received a negative screening test result at their post-treatment follow-up at 1 year", new Mapped(
                VIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D38HIVpos====================================================

        CompositionCohortDefinition HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition");
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString("3 AND 4 AND 5");

        CohortIndicator HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


        addIndicatorSpec(indicatorSpecs, "D38HIVpos", "Number of women who a HIV positive and received a negative screening test result at their post-treatment follow-up at 1 year", new Mapped(
                HIVposVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D37HIVneg====================================================

        CompositionCohortDefinition HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition=new CompositionCohortDefinition();
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setName("HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition");
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
//        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(ThermalAblationAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
//        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate+24m},endDate=${endDate+12m}")));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(postTreatmentFollowupAsFollowupReason, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition.setCompositionString("3 AND 4 AND (5 OR 6)");

        CohortIndicator HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator = Indicators.newCountIndicator("HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator",
                HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D38HIVneg", "Number of women who a HIV negative and received a negative screening test result at their post-treatment follow-up at 1 year", new Mapped(
                HIVnegVIANegAtpostTreatedafterLEEPOrThermalAblationTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));





        // ==================== D39 ====================================================



        CompositionCohortDefinition testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition=new CompositionCohortDefinition();
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.setName("testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition");
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.addParameter(new Parameter("startDate", "startDate", Date.class));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.addParameter(new Parameter("endDate", "endDate", Date.class));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
//        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.getSearches().put("4",new Mapped<CohortDefinition>(female, null));
        testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator testedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator = Indicators.newCountIndicator("testedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator",
                testedViaTriageNegativeAsResutsAndLEEPTreatmentComposition, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D39", "Number of HPV+ & VIA Triage- women treated with LEEP", new Mapped(
                testedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D39HIVpos ====================================================



        CompositionCohortDefinition HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator=new CompositionCohortDefinition();
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.setName("HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator");
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("5",new Mapped<CohortDefinition>(hivPositivePatient, null));
        HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.setCompositionString("1 and 2 and 3 and 4 and 5");

        CohortIndicator HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator = Indicators.newCountIndicator("HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator",
                HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D39HIVpos", "Number of HIV positive and HPV+ & VIA Triage- women treated with LEEP", new Mapped(
                HIVpostestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        // ==================== D39HIVneg ====================================================



        CompositionCohortDefinition HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator=new CompositionCohortDefinition();
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.setName("HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator");
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.addParameter(new Parameter("startDate", "startDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.addParameter(new Parameter("endDate", "endDate", Date.class));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("1",new Mapped<CohortDefinition>(VIATriagePerformed, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("2",new Mapped<CohortDefinition>(VIANegativeResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("3",new Mapped<CohortDefinition>(LEEPAsTypeOfTreatment, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("4",new Mapped<CohortDefinition>(HPVPositiveResults, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("5",new Mapped<CohortDefinition>(hivNegativePatient, null));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.getSearches().put("6",new Mapped<CohortDefinition>(hivUnknownPatient, null));
        HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator.setCompositionString("1 and 2 and 3 and 4 and (5 or 6)");

        CohortIndicator HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator = Indicators.newCountIndicator("HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator",
                HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

        addIndicatorSpec(indicatorSpecs, "D39HIVneg", "Number of HIV negative and HPV+ & VIA Triage- women treated with LEEP", new Mapped(
                HIVnegtestedViaTriageNegativeAsResutsAndLEEPTreatmentCompositionIndicatorIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")));

        return indicatorSpecs;
    }

    private void addIndicatorSpec(Map<String, IndicatorSpec> indicatorSpecs, String key, String label,
                                  Mapped<CohortIndicator> mappedIndicator) {
        indicatorSpecs.put(key, new IndicatorSpec(key, label, mappedIndicator));
    }

    private void setUpProperties() {

        VIAResults = Context.getConceptService().getConceptByUuid("a37a937a-a2a6-4c22-975f-986fb3599ea3");
        VIAAndEligibleForLEEP = Context.getConceptService().getConceptByUuid("402f3951-420e-4c09-9a9a-955bc0cff140");
        reasonsForReferral= Context.getConceptService().getConceptByUuid("1aa373f4-4db5-4b01-bce0-c10a636bb931");
        loopElectrosurgicalExcisionProcedure =  Context.getConceptService().getConceptByUuid("55040927-bae5-410c-80da-c79f7574167f");
        suspecancerDiagnosis =  Context.getConceptService().getConceptByUuid("476123d7-54f1-4278-92f1-2de4f19ae951");
        VIAAndEligibleForThermalAblation = Context.getConceptService().getConceptByUuid("3fe69559-cc82-48cb-926e-5d925aca088b");
        VIANegative = Context.getConceptService().getConceptByUuid("a7b08a37-0380-49dd-8f12-c2c2c76c8b13");
        biopsyperformed = Context.getConceptService().getConceptByUuid("5563e827-9a1e-40eb-b05b-81d55981ce6d");
        yes = Context.getConceptService().getConceptByUuid("3cd6f600-26fe-102b-80cb-0017a47871b2");
        testResult=Context.getConceptService().getConceptByUuid("bfb3eb1e-db98-4846-9915-0168511c6298");
        HPVPositiveType = Context.getConceptService().getConceptByUuid("1b4a5f67-6106-4a4d-a389-2f430be543e4");
        HPVNegative =Context.getConceptService().getConceptByUuid("64c23192-54e4-4750-9155-2ed0b736a0db");

        testResults.add(HPVpositive);
        testResults.add(HPVNegative);

        screeningType=Context.getConceptService().getConceptByUuid("7e4e6554-d6c5-4ca3-b371-49806a754992");
        visualInspectionWithAceticAcid=Context.getConceptService().getConceptByUuid("6178405e-c115-4e78-b9e0-fb0811a2bb2d");
        typeOfVIAPerformed=Context.getConceptService().getConceptByUuid("820b0e37-5d3e-46c6-9462-a8e7adaff954");
        VIAScreen = Context.getConceptService().getConceptByUuid("690d8a13-a1ac-4fd7-97a4-f3964b97f049");
        HPV=Context.getConceptService().getConceptByUuid("f7c2d59d-2043-42ce-b04d-08564d54b0c7");
        reasonsForReferralIn= Context.getConceptService().getConceptByUuid("cc227602-a240-43fb-926d-b6ad3f42edab");
        furtherManagement = Context.getConceptService().getConceptByUuid("de3a4342-b07e-48f8-ab80-202aab697756");
        confirmedCancerDiagnosis = Context.getConceptService().getConceptByUuid("3dc2eb50-6981-43d3-b907-e3dd8b5ed620");
        breastCancer = Context.getConceptService().getConceptByUuid("e1bd83f4-e9fa-4564-b8aa-74a9b199aca8");
        biradsFinalAssessmentCategory = Context.getConceptService().getConceptByUuid("1ba801e8-969b-418d-8ad1-7262580ee0c0");
        breastUltrasound = Context.getConceptService().getConceptByUuid("58eacedf-cb05-4a8f-a98d-cc5717348d74");
        breastDiagnosis = Context.getConceptService().getConceptByUuid("1ed543c7-36ff-4444-bc99-0f01eede9937");
        breastMass = Context.getConceptService().getConceptByUuid("09e3246a-5968-4ab4-960a-6b324517dc64");

        breastMassUltrasoundResults = Context.getConceptService().getConceptByUuid("f43869bd-4dbf-453d-bd05-7b693b316e69");
        complexcystic = Context.getConceptService().getConceptByUuid("7a637747-d98b-4d30-9d19-b998e26c70a1");
        axillaryMassUltrasoundResults= Context.getConceptService().getConceptByUuid("e63fef93-e3f7-478f-8c08-f922786ab592");
        abnormalPalpableLymphNode= Context.getConceptService().getConceptByUuid("1e3af50c-a676-4e11-baec-1028a6b91a2d");
        abnormalNonPalpableLymphNode= Context.getConceptService().getConceptByUuid("396e21d0-7f45-4353-aa05-8522eb1a33fb");
        proceduresDone = Context.getConceptService().getConceptByUuid("e44f17a3-28bd-4fe1-a4e1-1bb94779a1fd");
        BIOPSY = Context.getConceptService().getConceptByUuid("db64df50-1db1-4f80-abe5-b0307d7d4f9e");
        VIATriage=Context.getConceptService().getConceptByUuid("69a0ca97-2fee-4c4a-9d84-4f2c25f70c93");
        typeOfTreatmentPerformed =  Context.getConceptService().getConceptByUuid("f7719cd4-e591-4a39-8bfe-9dd93d3cab89");
        thermalAblation =  Context.getConceptService().getConceptByUuid("ef4d5bd3-2c4e-4ac2-9b91-9d4ba12b44f8");
        cervicalCancer = Context.getConceptService().getConceptByUuid("36052b70-ba49-466f-a4eb-bc99581be7a2");
        followupReason = Context.getConceptService().getConceptByUuid("2e788aae-d93e-4d7c-bd3b-e1141f1915c7");
        postTreatmentFollowup = Context.getConceptService().getConceptByUuid("c96d377e-133d-4c4e-b1d7-f03a8621d5f8");
        axillaMass = Context.getConceptService().getConceptByUuid("fae86544-9032-48e7-accb-44719fad3b7a");
        focalBreastPain = Context.getConceptService().getConceptByUuid("8b2ac6f1-1ec1-41b8-9b4c-57fdcf7363fb");
        bloodyNippleDischarge = Context.getConceptService().getConceptByUuid("327660ea-12b8-4489-978e-365b8f9f9063");
        positive = Context.getConceptService().getConceptByUuid("3cd3a7a2-26fe-102b-80cb-0017a47871b2");
        negative = Context.getConceptService().getConceptByUuid("3cd28732-26fe-102b-80cb-0017a47871b2");
        unknown = Context.getConceptService().getConceptByUuid("3cd6fac4-26fe-102b-80cb-0017a47871b2");




        mUzimaBreastCancerScreening = Context.getFormService().getForm("mUzima Breast cancer screening");
        muzimaBreastCancerFollowup = Context.getFormService().getForm("mUzima Breast cancer screening followup");
        oncologyBreastScreeningExamination=gp.getForm(GlobalPropertiesManagement.ONCOLOGY_BREAST_SCREENING_EXAMINATION);
        mUzimaCervicalScreening=Context.getFormService().getForm("mUzima Cervical cancer screening");
        mUzimaCervicalCancerScreeningFollowup=Context.getFormService().getForm("mUzima Cervical cancer screening follow up");
        OncologyCervicalScreeningFollowUp=Context.getFormService().getFormByUuid("9de98350-bc86-4012-a559-fcce13fc10c5");
        oncologyCervicalScreeningExamination=gp.getForm(GlobalPropertiesManagement.ONCOLOGY_CERVICAL_SCREENING_EXAMINATION);
        oncologyScreeningLabResultsForm=Context.getFormService().getFormByUuid("d7e4f3e6-2462-427d-83df-97d8488a53aa");
        muzimaOncologyScreeningLabResults=Context.getFormService().getFormByUuid("3a0e1a09-c88a-4412-99c6-cdbd7add50fd");
        muzimaOncologyScreeningLabRequest=Context.getFormService().getFormByUuid("f2a044dd-2dd9-41f8-bfac-ea6c54d044bc");
        OncologyScreeningLabRequest=Context.getFormService().getFormByUuid("39c9442c-aa69-472b-bf03-da28483c1598");
        muzimaOncologyScreeningDiagnosis = Context.getFormService().getForm("muzima oncology screening Diagnosis");
        oncologyScreeningDiagnosis = Context.getFormService().getForm("Oncology Screening Diagnosis");


        breastCancerScreeningForms.add(mUzimaBreastCancerScreening);
        breastCancerScreeningForms.add(oncologyBreastScreeningExamination);
        breastCancerScreeningForms.add(muzimaBreastCancerFollowup);
        breastCancerScreeningForms.add(muzimaOncologyScreeningDiagnosis);
        breastCancerScreeningForms.add(oncologyScreeningDiagnosis);

        screeningCervicalForms.add(oncologyCervicalScreeningExamination);
        screeningCervicalForms.add(mUzimaCervicalScreening);
        screeningCervicalForms.add(mUzimaCervicalCancerScreeningFollowup);
        screeningCervicalForms.add(OncologyCervicalScreeningFollowUp);
        screeningCervicalForms.add(oncologyScreeningLabResultsForm);
        screeningCervicalForms.add(muzimaOncologyScreeningLabResults);
        screeningCervicalForms.add(muzimaOncologyScreeningLabRequest);
        screeningCervicalForms.add(OncologyScreeningLabRequest);
        screeningCervicalForms.add(muzimaOncologyScreeningDiagnosis);
        screeningCervicalForms.add(oncologyScreeningDiagnosis);



        screeningExaminationForms.add(oncologyBreastScreeningExamination);
        screeningExaminationForms.add(oncologyCervicalScreeningExamination);
        screeningExaminationForms.add(mUzimaCervicalScreening);
        screeningExaminationForms.add(mUzimaCervicalCancerScreeningFollowup);
        screeningExaminationForms.add(OncologyCervicalScreeningFollowUp);
        screeningExaminationForms.add(mUzimaBreastCancerScreening);
        screeningExaminationForms.add(muzimaBreastCancerFollowup);
        screeningExaminationForms.add(oncologyScreeningLabResultsForm);
        screeningExaminationForms.add(muzimaOncologyScreeningLabResults);
        screeningExaminationForms.add(muzimaOncologyScreeningDiagnosis);
        screeningExaminationForms.add(oncologyScreeningDiagnosis);




        // *********************************************************************************

//        mUzimaBreastScreening=Context.getFormService().getForm("mUzima Breast cancer screening");

        parameterNames.add("onOrBefore");
        parameterNames.add("onOrAfter");

        //mUzimaCervicalCancerScreeningFollowup=Context.getFormService().getFormByUuid("94470633-8a84-4430-9910-10dcd628a0a2");









        oncologyScreeningLabResultsForm=Context.getFormService().getFormByUuid("d7e4f3e6-2462-427d-83df-97d8488a53aa");
        muzimaOncologyScreeningLabResults=Context.getFormService().getFormByUuid("3a0e1a09-c88a-4412-99c6-cdbd7add50fd");

        oncologyScreeningLabResultsForms.add(oncologyScreeningLabResultsForm);
        oncologyScreeningLabResultsForms.add(muzimaOncologyScreeningLabResults);
       /* oncologyScreeningLabResultsForms.add(mUzimaCervicalCancerScreeningFollowup);
        oncologyScreeningLabResultsForms.add(OncologyCervicalScreeningFollowUp);
        oncologyScreeningLabResultsForms.add(oncologyCervicalScreeningExamination);
        oncologyScreeningLabResultsForms.add(mUzimaCervicalScreening);*/
        screeningExaminationForms.add(oncologyScreeningLabResultsForm);
        screeningExaminationForms.add(muzimaOncologyScreeningLabResults);


        HPVpositive=Context.getConceptService().getConceptByUuid("1b4a5f67-6106-4a4d-a389-2f430be543e4");
        HPVPositive16 = Context.getConceptService().getConceptByUuid("059fddd3-711f-47ab-818f-087984aeecc3");
        HPVPositive18 = Context.getConceptService().getConceptByUuid("b672c3ff-96c9-41cd-9ae6-aa0811ce347f");

        typeOfTransformationZone = Context.getConceptService().getConceptByUuid("af1421bc-333e-449b-bab5-99a54a38490a");
        transformationZoneType3 = Context.getConceptService().getConceptByUuid("cc6497b1-8f5b-48be-ac95-d5e142e6e663");
        followupReason = Context.getConceptService().getConceptByUuid("2e788aae-d93e-4d7c-bd3b-e1141f1915c7");
        postTreatmentFollowup = Context.getConceptService().getConceptByUuid("c96d377e-133d-4c4e-b1d7-f03a8621d5f8");
        hpvFollowupAt2Years = Context.getConceptService().getConceptByUuid("e43a0fc4-b995-44f7-96cb-5af34b600f50");





        positiveTestResults.add(HPVpositive);


        cervicalCancerScreeningFollowupAndExaminationForms.add(mUzimaCervicalCancerScreeningFollowup);
        cervicalCancerScreeningFollowupAndExaminationForms.add(OncologyCervicalScreeningFollowUp);
        cervicalCancerScreeningFollowupAndExaminationForms.add(oncologyCervicalScreeningExamination);
        cervicalCancerScreeningFollowupAndExaminationForms.add(mUzimaCervicalScreening);
        cervicalCancerScreeningFollowupAndExaminationForms.add(oncologyScreeningLabResultsForm);
        cervicalCancerScreeningFollowupAndExaminationForms.add(muzimaOncologyScreeningLabResults);

        VIATriageInList.add(VIATriage);


        VIAAndEligibleResults.add(VIAAndEligibleForThermalAblation);
        VIAAndEligibleResults.add(VIAAndEligibleForLEEP);

        VIANegativeInList.add(VIANegative);

        VIAScreenInList.add(VIAScreen);



        cryotherapy = Context.getConceptService().getConceptByUuid("e48bc8db-8f6e-4556-9083-0a000a136e95");
        thermalAblationAndCryotherapyList.add(thermalAblation);
        thermalAblationAndCryotherapyList.add(cryotherapy);

        loopElectrosurgicalExcisionProcedureInList.add(loopElectrosurgicalExcisionProcedure);

        LEEPAndColposcopy.add(loopElectrosurgicalExcisionProcedure); // Colposcopy not available in Reason fo referral


        suspectedCancerInList.add(suspectedCancer);


        yesInList.add(yes);
/*

        oncologyCervicalCancerScreeningTransferIn=Context.getFormService().getFormByUuid("f939311f-53ac-4587-9a9a-48d41ea1b38b");
        oncologyCervicalCancerScreeningTransferInList.add(oncologyCervicalCancerScreeningTransferIn);
*/









        reasonForBreastExam = Context.getConceptService().getConceptByUuid("0483b8fa-b6d2-4551-ab4c-b141399897d7");
        breastSymptoms = Context.getConceptService().getConceptByUuid("b761e2c9-3d07-4a03-b274-c38282cc723c");
        screening = Context.getConceptService().getConceptByUuid("d9e56001-1c4e-479b-95f1-878e18d7ece8");
        breastExamination = Context.getConceptService().getConceptByUuid("f9576ba2-e96f-4b48-a178-42e72e6382ca");
        ABNORMAL = Context.getConceptService().getConceptByUuid("3cd75230-26fe-102b-80cb-0017a47871b2");
        nextStep = Context.getConceptService().getConceptByUuid("69b9671b-d8b1-461b-bb7d-adb151775a57");
        referredTo = Context.getConceptService().getConceptByUuid("25782f2c-074f-4834-b7d2-4668cd645a57");
        medicalImaging = Context.getConceptService().getConceptByUuid("7f779262-de04-425b-97f7-9e5cc834eb55");
        OTHERNONCODED = Context.getConceptService().getConceptByUuid("3cee7fb4-26fe-102b-80cb-0017a47871b2");
        YES = Context.getConceptService().getConceptByUuid("3cd6f600-26fe-102b-80cb-0017a47871b2");
        solidMass = Context.getConceptService().getConceptByUuid("9ca1695a-f070-462e-81ce-d9778ee749b6");
        intermediate = Context.getConceptService().getConceptByUuid("636fc0a4-b82e-4a18-a97a-2cd43af8b852");
        highSuspiciousForMalignancy = Context.getConceptService().getConceptByUuid("f133b708-d7cf-47b3-81aa-17e618d47b2d");
        PAPANICOLAOUSMEAR = Context.getConceptService().getConceptByUuid("3cd4de2e-26fe-102b-80cb-0017a47871b2");
        NORMAL = Context.getConceptService().getConceptByUuid("3cd750a0-26fe-102b-80cb-0017a47871b2");
        breastExaminationAnswers.add(ABNORMAL);
        breastExaminationAnswers.add(NORMAL);



        diagnosisScreeningForms.add(muzimaOncologyScreeningDiagnosis);
        diagnosisScreeningForms.add(oncologyScreeningDiagnosis);
        CONFIRMEDDIAGNOSIS = Context.getConceptService().getConceptByUuid("3d762b82-f951-4d13-b147-6aaba63b25d1");
        radiologicDiagnosis = Context.getConceptService().getConceptByUuid("16318cc8-cdd9-41be-94d2-b31c67bc6b8f");
        auxillaryMass = Context.getConceptService().getConceptByUuid("3ce9672c-26fe-102b-80cb-0017a47871b2");
      /*  oncologyBreastCancerScreeningTransferIn = Context.getFormService().getForm("Oncology Breast Cancer Screening transfer in");
        oncologyBreastCancerScreeningTransferInList.add(oncologyBreastCancerScreeningTransferIn);
*/
        hivStatus=Context.getConceptService().getConceptByUuid("aec6ad18-f4dd-4cfa-b68d-3d7bb6ea908e");

        DrugPrescribed = Context.getConceptService().getConceptByUuid("c28bc221-065e-4716-a9b0-b959239bc102");
    }
}
