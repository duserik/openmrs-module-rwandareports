package org.openmrs.module.rwandareports.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.module.rwandareports.renderer.MCHCustomWebRenderer;
import org.openmrs.module.rwandareports.util.Cohorts;
import org.openmrs.module.rwandareports.util.Indicators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SetupMCHIndicatorReport {
	
	protected final Log log = LogFactory.getLog(getClass());

	// properties
	private Program anc;

	private Concept bpCategory= Context.getConceptService().getConceptByUuid("e7fc9067-ccf2-4fc4-b482-51c8c36c8553");
	private Concept preEclampsia= Context.getConceptService().getConceptByUuid("61320ab5-3aef-4e43-98a1-9a925fd8dc14");
	private Concept severePreEclampsia= Context.getConceptService().getConceptByUuid("316ef3e4-c2a6-4bdc-8438-9b4a6a2a7898");
	private Concept eclampsia= Context.getConceptService().getConceptByUuid("c312855e-542a-4817-8cd3-78adbe0f52e6");

	private Form ancEnrolmentForm=Context.getFormService().getForm(217);
	private Concept durationOfPregnancy= Context.getConceptService().getConcept(105483);

	private List<Concept> eclapsiaList=new ArrayList<Concept>();




	public void setup() throws Exception {

		setUpProperties();

		createReportDefinition();

	}

	public void delete() {

		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("MCHWebRenderer".equals(rd.getName()) || "MCH_Excel".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		
		Helper.purgeReportDefinition("MCH Report");
	}

	// DQ Report by Site
	public ReportDefinition createReportDefinition() throws IOException {

		PeriodIndicatorReportDefinition rd = new PeriodIndicatorReportDefinition();
		//rd.removeParameter(ReportingConstants.START_DATE_PARAMETER);
		//rd.removeParameter(ReportingConstants.END_DATE_PARAMETER);
		//rd.removeParameter(ReportingConstants.LOCATION_PARAMETER);
		rd.addParameter(new Parameter("location", "Location", Location.class));
		rd.addParameter(new Parameter("startDate", "StartDate", Date.class));
		rd.addParameter(new Parameter("endDate", "EndDate", Date.class));

		rd.setName("MCH Report");







		rd.setupDataSetDefinition();

		rd.setBaseCohortDefinition(Cohorts
				.createParameterizedLocationAndProgramCohort("At Location",anc),
				ParameterizableUtil
						.createParameterMappings("location=${location}"));

		rd.addDataSetDefinition(createIndicatorsForReports(), ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}"));

		Helper.saveReportDefinition(rd);

		ReportDesign monthlyDesign = Helper.createRowPerPatientXlsOverviewReportDesign(rd,"MCH_Excel.xls", "MCH_Excel", null);
		Properties monthlyProps = new Properties();
		monthlyProps.put("repeatingSections", "sheet:1,dataset:ancDataSet");
		monthlyProps.put("sortWeight","5000");
		monthlyDesign.setProperties(monthlyProps);
		Helper.saveReportDesign(monthlyDesign);

		createCustomWebRenderer(rd, "MCHWebRenderer");


		return rd;
	}







	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CohortIndicatorDataSetDefinition createIndicatorsForReports() {
		CohortIndicatorDataSetDefinition ancDataSetDefinition=new CohortIndicatorDataSetDefinition();
		ancDataSetDefinition.setName("ancDataSet");
		ancDataSetDefinition.addParameter(new Parameter("startDate", "StartDate", Date.class));
		ancDataSetDefinition.addParameter(new Parameter("endDate", "EndDate", Date.class));
		ancDataSetDefinition.addParameter(new Parameter("location", "Location", Location.class));



/*

		// ======================================================================================
		// 1. The proportion of all women with severe pre-eclampsia or eclampsia in the health facility who received the timely dose of Magnesium sulphate before transfer to hospital
		// ======================================================================================

		//Numerator
		SqlCohortDefinition patientWithPreEclampsiaOrEclampsia=Cohorts.getPatientsWithCodedObservationsBetweenStartDateAndEndDate("patientWithPreEclampsiaOrEclampsia",bpCategory,eclapsiaList);
		patientWithPreEclampsiaOrEclampsia.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientWithPreEclampsiaOrEclampsia.addParameter(new Parameter("endDate", "EndDate", Date.class));

		//Denominator
		SqlCohortDefinition patientWithBPCategory=Cohorts.getPatientsWithObsByStartDateEndDate("patientWithBPCategory",bpCategory);
		patientWithBPCategory.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientWithBPCategory.addParameter(new Parameter("endDate", "EndDate", Date.class));


		CohortIndicator patientWithBPCategoryAndPreEclampsiaOrEclampsiaIndicator = Indicators
				.newFractionIndicator("patientWithBPCategoryAndPreEclampsiaOrEclampsiaIndicator", patientWithPreEclampsiaOrEclampsia,
						ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"),
						patientWithBPCategory,
						ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));


		ancDataSetDefinition.addColumn("ANC1","The proportion of all women with severe pre-eclampsia or eclampsia in the health facility who received the timely dose of Magnesium sulphate before transfer to hospital",
				new Mapped(patientWithBPCategoryAndPreEclampsiaOrEclampsiaIndicator,ParameterizableUtil
						.createParameterMappings("startDate=${startDate},endDate=${endDate}")),"");
*/



/*		SqlCohortDefinition patientsInANC = new SqlCohortDefinition(
				"select distinct patient_id from patient_program pp,program p where pp.program_id=p.program_id and p.name='"
						+ anc.getName()
						+ "' ");*/
		//=============================================================================================================
		//ANC 1

		SqlCohortDefinition patientsEnrolInANC = new SqlCohortDefinition(
				"select patient_id from encounter where form_id="+ancEnrolmentForm.getFormId()+" and encounter_datetime>= :startDate and encounter_datetime<= :endDate and voided=0");
		patientsEnrolInANC.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientsEnrolInANC.addParameter(new Parameter("endDate", "EndDate", Date.class));

		CohortIndicator patientsInANCIndicator = Indicators
				.newCountIndicator(
						"ANC1: ANC New Registrations/ CPN Nouvelles inscrites",
						patientsEnrolInANC, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

		ancDataSetDefinition.addColumn("ANC1","ANC New Registrations/ CPN Nouvelles inscrites",
			 new Mapped(patientsInANCIndicator,ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")),"");

		//ANC 2

		AgeCohortDefinition under20= Cohorts.createUnderAgeCohort("",20, DurationUnit.YEARS);

		CompositionCohortDefinition patientsInANCIndicatorUnder20=new CompositionCohortDefinition();
		patientsInANCIndicatorUnder20.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientsInANCIndicatorUnder20.addParameter(new Parameter("endDate", "EndDate", Date.class));
		patientsInANCIndicatorUnder20.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
		patientsInANCIndicatorUnder20.getSearches().put(
				"1",
				new Mapped<CohortDefinition>(patientsEnrolInANC, ParameterizableUtil
						.createParameterMappings("startDate=${startDate},endDate=${endDate}")));
		patientsInANCIndicatorUnder20.getSearches().put(
				"2",
				new Mapped<CohortDefinition>(under20, ParameterizableUtil
						.createParameterMappings("effectiveDate=${effectiveDate}")));
		patientsInANCIndicatorUnder20.setCompositionString("1 and 2");

		CohortIndicator patientsInANCIndicatorUnder20Indicator = Indicators
				.newCountIndicator(
						"ANC new registrations pregnancy under 20 years/ CPN Grossesses chez les femmes de moins de 20 ans",
						patientsInANCIndicatorUnder20, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},effectiveDate=${endDate}"));

		ancDataSetDefinition.addColumn("ANC2","ANC new registrations pregnancy under 20 years/ CPN Grossesses chez les femmes de moins de 20 ans",
				new Mapped(patientsInANCIndicatorUnder20Indicator,ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")),"");



//ANC 3

		/*
		// It take long time
		SqlCohortDefinition patientsEnrolInANCWithlessThan12Weeks = new SqlCohortDefinition(
				"select patient_id from obs o,(select * from (select * from encounter where form_id="+ancEnrolmentForm.getFormId()+" and encounter_datetime>= :startDate and encounter_datetime<= :endDate and voided=0 order by encounter_datetime) orderd_enc group by orderd_enc.patient_id) encou_g where o.encounter_id=encou_g.encounter_id and o.concept_id=105483 and o.voided=0 and value_numeric < 12");
		patientsEnrolInANCWithlessThan12Weeks.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientsEnrolInANCWithlessThan12Weeks.addParameter(new Parameter("endDate", "EndDate", Date.class));
*/

		SqlCohortDefinition patientsEnrolInANCWithlessThan12Weeks = new SqlCohortDefinition(
				"select e.patient_id from encounter e,obs o where e.encounter_id=o.encounter_id and e.form_id="+ancEnrolmentForm.getFormId()+" and e.encounter_datetime>= :startDate and e.encounter_datetime<= :endDate and e.voided=0 and o.voided=0 and o.concept_id= "+durationOfPregnancy.getConceptId()+" and o.value_numeric<12");
		patientsEnrolInANCWithlessThan12Weeks.addParameter(new Parameter("startDate", "StartDate", Date.class));
		patientsEnrolInANCWithlessThan12Weeks.addParameter(new Parameter("endDate", "EndDate", Date.class));





		CohortIndicator patientsEnrolInANCWithlessThan12WeeksIndicator = Indicators
				.newCountIndicator(
						"ANC First standard visit (Within 12 Weeks)/ CPN Première visite standard (endeans 12 semaine()",
						patientsEnrolInANCWithlessThan12Weeks, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));

		ancDataSetDefinition.addColumn("ANC3","ANC First standard visit (Within 12 Weeks)/ CPN Première visite standard (endeans 12 semaine()",
				new Mapped(patientsEnrolInANCWithlessThan12WeeksIndicator,ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")),"");




		return ancDataSetDefinition;

	}


	private void setUpProperties() {

	   //anc = gp.getProgram(GlobalPropertiesManagement.ANC_PROGRAM);
       anc = Context.getProgramWorkflowService().getProgram(25);

		eclapsiaList.add(severePreEclampsia);
		eclapsiaList.add(eclampsia);

    }

	private ReportDesign createCustomWebRenderer(ReportDefinition rd,
			String name) throws IOException {
		final ReportDesign design = new ReportDesign();
		design.setName(name);
		design.setReportDefinition(rd);
		design.setRendererType(MCHCustomWebRenderer.class);
		ReportService rs = Context.getService(ReportService.class);
		return rs.saveReportDesign(design);
	}



}
