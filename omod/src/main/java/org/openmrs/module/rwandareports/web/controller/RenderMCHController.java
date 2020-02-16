/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.rwandareports.web.controller;

import org.openmrs.Cohort;
import org.openmrs.Encounter;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.dataset.DataSet;
import org.openmrs.module.reporting.dataset.DataSetColumn;
import org.openmrs.module.reporting.dataset.MapDataSet;
import org.openmrs.module.reporting.dataset.SimpleDataSet;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SimplePatientDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.service.DataSetDefinitionService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.indicator.dimension.CohortIndicatorAndDimensionResult;
import org.openmrs.module.reporting.report.ReportData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Controller prepares result page for Quarterly HIV Care and ART Reporting
 */
@Controller
public class RenderMCHController {
	
	@RequestMapping("/module/rwandareports/renderMCHDataSet.form")
	public String showReport(Model model, HttpSession session) throws Exception {
			String renderArg = (String) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

			ReportData data = null;
			try {
				data = (ReportData) session
						.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);
				// start
				String savedDataSetKey = "ancDataSet";
				//String savedDataSetEncKey = "encFuture";
				//String savedDataSetNcdKey="defaultDataSetncd";

				if (savedDataSetKey.equals("ancDataSet")) {
					manipulateIMCI(savedDataSetKey, model, session);
					manipulateGBV(savedDataSetKey, model, session);
					manipulateANC(savedDataSetKey, model, session);
					manipulateIntrapartum(savedDataSetKey, model, session);
					manipulatePNC(savedDataSetKey, model, session);

					/*if(savedDataSetEncKey.equals("encFuture")){
						manipulateEncounters(savedDataSetEncKey, model, session);

					}*/

				}

				/*if (savedDataSetNcdKey.equals("defaultDataSetncd")){
					manipulatencdDsd(savedDataSetNcdKey, model, session);

				}*/

				// end of if

			} catch (ClassCastException ex) {
				// pass
			}
			if (data == null)
				return "redirect:../reporting/dashboard/index.form";

			@SuppressWarnings("unused")
			SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
					renderArg);
			// model.addAttribute("columns", dataSet.getMetaData());
			return null;
		}

	public String manipulateIMCI(
			@RequestParam(required = false, value = "savedDataSetKey") String savedDataSetKey,
			Model model, HttpSession session) throws Exception {

		String renderArg = (String) session
				.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

		ReportData data = null;
		try {
			data = (ReportData) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);

			savedDataSetKey=savedDataSetKey;
			List<String> savedColumnKeys = new ArrayList<String>();

			getSavedIMCIKeys(savedColumnKeys);
			List<DQReportModel> imciList = new ArrayList<DQReportModel>();

			for(String savedColumnKey : savedColumnKeys ) {
				DQReportModel dQRObject = new DQReportModel();


				for (Map.Entry<String, DataSet> e : data.getDataSets().entrySet()) {
					if (e.getKey().equals(savedDataSetKey)) {
						MapDataSet mapDataSet = (MapDataSet) e.getValue();
						DataSetColumn dataSetColumn = mapDataSet.getMetaData().getColumn(savedColumnKey);
						dQRObject.setSelectedColumn(dataSetColumn);

						Object result = mapDataSet.getData(dataSetColumn);
						Cohort selectedCohort = null;
						if (result instanceof CohortIndicatorAndDimensionResult) {
							CohortIndicatorAndDimensionResult cidr =(CohortIndicatorAndDimensionResult) mapDataSet.getData(dataSetColumn);
							selectedCohort = cidr.getCohortIndicatorAndDimensionCohort();

						}

						// Evaluate the default patient dataset definition
						DataSetDefinition dsd = null; if (dsd == null) {
							SimplePatientDataSetDefinition d = new SimplePatientDataSetDefinition();
							d.addPatientProperty("patientId"); List<PatientIdentifierType>
									types = ReportingConstants.GLOBAL_PROPERTY_PREFERRED_IDENTIFIER_TYPES();
							if (!types.isEmpty()) {
								d.setIdentifierTypes(types);
							}

							d.addPatientProperty("givenName");
							d.addPatientProperty("familyName");
							d.addPatientProperty("age");
							d.addPatientProperty("gender");

							dsd = d;


						}

						EvaluationContext evalContext = new EvaluationContext();
						evalContext.setBaseCohort(selectedCohort);

						DataSet patientDataSet;
						try {
							patientDataSet =Context.getService( DataSetDefinitionService.class).evaluate(dsd, evalContext);
							dQRObject.setDataSet(patientDataSet);
							dQRObject.setDataSetDefinition(dsd);
						}
						catch (EvaluationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

				// Add all dataset definition to the request (allow user to choose)
				dQRObject.setDataSetDefinitions(Context.getService(
						DataSetDefinitionService.class).getAllDefinitions( false));
				imciList.add(dQRObject);
			}

			model.addAttribute("imciList", imciList);
		}
		catch (ClassCastException ex) {
			// pass
		}
		if (data == null)
			return "redirect:../reporting/dashboard/index.form";

		@SuppressWarnings("unused")
		SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
				renderArg);
		// model.addAttribute("columns", dataSet.getMetaData());
		return null;
	}


	public String manipulateGBV(
			@RequestParam(required = false, value = "savedDataSetKey") String savedDataSetKey,
			Model model, HttpSession session) throws Exception {

		String renderArg = (String) session
				.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

		ReportData data = null;
		try {
			data = (ReportData) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);

			savedDataSetKey=savedDataSetKey;
			List<String> savedColumnKeys = new ArrayList<String>();

			getSavedGBVKeys(savedColumnKeys);
			List<DQReportModel> gbvList = new ArrayList<DQReportModel>();

			for(String savedColumnKey : savedColumnKeys ) {
				DQReportModel dQRObject = new DQReportModel();


				for (Map.Entry<String, DataSet> e : data.getDataSets().entrySet()) {
					if (e.getKey().equals(savedDataSetKey)) {
						MapDataSet mapDataSet = (MapDataSet) e.getValue();
						DataSetColumn dataSetColumn = mapDataSet.getMetaData().getColumn(savedColumnKey);
						dQRObject.setSelectedColumn(dataSetColumn);

						Object result = mapDataSet.getData(dataSetColumn);
						Cohort selectedCohort = null;
						if (result instanceof CohortIndicatorAndDimensionResult) {
							CohortIndicatorAndDimensionResult cidr =(CohortIndicatorAndDimensionResult) mapDataSet.getData(dataSetColumn);
							selectedCohort = cidr.getCohortIndicatorAndDimensionCohort();

						}

						// Evaluate the default patient dataset definition
						DataSetDefinition dsd = null; if (dsd == null) {
							SimplePatientDataSetDefinition d = new SimplePatientDataSetDefinition();
							d.addPatientProperty("patientId"); List<PatientIdentifierType>
									types = ReportingConstants.GLOBAL_PROPERTY_PREFERRED_IDENTIFIER_TYPES();
							if (!types.isEmpty()) {
								d.setIdentifierTypes(types);
							}

							d.addPatientProperty("givenName");
							d.addPatientProperty("familyName");
							d.addPatientProperty("age");
							d.addPatientProperty("gender");

							dsd = d;


						}

						EvaluationContext evalContext = new EvaluationContext();
						evalContext.setBaseCohort(selectedCohort);

						DataSet patientDataSet;
						try {
							patientDataSet =Context.getService( DataSetDefinitionService.class).evaluate(dsd, evalContext);
							dQRObject.setDataSet(patientDataSet);
							dQRObject.setDataSetDefinition(dsd);
						}
						catch (EvaluationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

				// Add all dataset definition to the request (allow user to choose)
				dQRObject.setDataSetDefinitions(Context.getService(
						DataSetDefinitionService.class).getAllDefinitions( false));
				gbvList.add(dQRObject);
			}

			model.addAttribute("ancList", gbvList);
		}
		catch (ClassCastException ex) {
			// pass
		}
		if (data == null)
			return "redirect:../reporting/dashboard/index.form";

		@SuppressWarnings("unused")
		SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
				renderArg);
		// model.addAttribute("columns", dataSet.getMetaData());
		return null;
	}


	public String manipulateANC(
				@RequestParam(required = false, value = "savedDataSetKey") String savedDataSetKey,
				Model model, HttpSession session) throws Exception {

			String renderArg = (String) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

			ReportData data = null;
			try {
				data = (ReportData) session
						.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);

				savedDataSetKey=savedDataSetKey;
				List<String> savedColumnKeys = new ArrayList<String>();

				getSavedANCKeys(savedColumnKeys);
				List<DQReportModel> ancList = new ArrayList<DQReportModel>();

				for(String savedColumnKey : savedColumnKeys ) {
					DQReportModel dQRObject = new DQReportModel();


					for (Map.Entry<String, DataSet> e : data.getDataSets().entrySet()) {
						if (e.getKey().equals(savedDataSetKey)) {
							MapDataSet mapDataSet = (MapDataSet) e.getValue();
							DataSetColumn dataSetColumn = mapDataSet.getMetaData().getColumn(savedColumnKey);
							dQRObject.setSelectedColumn(dataSetColumn);

							Object result = mapDataSet.getData(dataSetColumn);
							Cohort selectedCohort = null;
							if (result instanceof CohortIndicatorAndDimensionResult) {
								CohortIndicatorAndDimensionResult cidr =(CohortIndicatorAndDimensionResult) mapDataSet.getData(dataSetColumn);
								selectedCohort = cidr.getCohortIndicatorAndDimensionCohort();

							}

							// Evaluate the default patient dataset definition
							DataSetDefinition dsd = null; if (dsd == null) {
								SimplePatientDataSetDefinition d = new SimplePatientDataSetDefinition();
								d.addPatientProperty("patientId"); List<PatientIdentifierType>
										types = ReportingConstants.GLOBAL_PROPERTY_PREFERRED_IDENTIFIER_TYPES();
								if (!types.isEmpty()) {
									d.setIdentifierTypes(types);
								}

									d.addPatientProperty("givenName");
									d.addPatientProperty("familyName");
									d.addPatientProperty("age");
									d.addPatientProperty("gender");

									dsd = d;


							}

							EvaluationContext evalContext = new EvaluationContext();
							evalContext.setBaseCohort(selectedCohort);

							DataSet patientDataSet;
							try {
								patientDataSet =Context.getService( DataSetDefinitionService.class).evaluate(dsd, evalContext);
								dQRObject.setDataSet(patientDataSet);
								dQRObject.setDataSetDefinition(dsd);
							}
							catch (EvaluationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}

					// Add all dataset definition to the request (allow user to choose)
					dQRObject.setDataSetDefinitions(Context.getService(
							DataSetDefinitionService.class).getAllDefinitions( false));
					ancList.add(dQRObject);
				}

				model.addAttribute("ancList", ancList);
			}
			catch (ClassCastException ex) {
				// pass
			}
			if (data == null)
				return "redirect:../reporting/dashboard/index.form";

			@SuppressWarnings("unused")
			SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
					renderArg);
			// model.addAttribute("columns", dataSet.getMetaData());
			return null;
		}



	public String manipulateIntrapartum(
			@RequestParam(required = false, value = "savedDataSetKey") String savedDataSetKey,
			Model model, HttpSession session) throws Exception {

		String renderArg = (String) session
				.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

		ReportData data = null;
		try {
			data = (ReportData) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);

			savedDataSetKey=savedDataSetKey;
			List<String> savedColumnKeys = new ArrayList<String>();

			getSavedKeysIntrapartum(savedColumnKeys);
			List<DQReportModel> IntraList = new ArrayList<DQReportModel>();

			for(String savedColumnKey : savedColumnKeys ) {
				DQReportModel dQRObject = new DQReportModel();


				for (Map.Entry<String, DataSet> e : data.getDataSets().entrySet()) {
					if (e.getKey().equals(savedDataSetKey)) {
						MapDataSet mapDataSet = (MapDataSet) e.getValue();
						DataSetColumn dataSetColumn = mapDataSet.getMetaData().getColumn(savedColumnKey);
						dQRObject.setSelectedColumn(dataSetColumn);

						Object result = mapDataSet.getData(dataSetColumn);
						Cohort selectedCohort = null;
						if (result instanceof CohortIndicatorAndDimensionResult) {
							CohortIndicatorAndDimensionResult cidr =(CohortIndicatorAndDimensionResult) mapDataSet.getData(dataSetColumn);
							selectedCohort = cidr.getCohortIndicatorAndDimensionCohort();

						}

						// Evaluate the default patient dataset definition
						DataSetDefinition dsd = null; if (dsd == null) {
							SimplePatientDataSetDefinition d = new SimplePatientDataSetDefinition();
							d.addPatientProperty("patientId"); List<PatientIdentifierType>
									types = ReportingConstants.GLOBAL_PROPERTY_PREFERRED_IDENTIFIER_TYPES();
							if (!types.isEmpty()) {
								d.setIdentifierTypes(types);
							}

							d.addPatientProperty("givenName");
							d.addPatientProperty("familyName");
							d.addPatientProperty("age");
							d.addPatientProperty("gender");

							dsd = d;


						}
						System.out.println("2. In Rendering Controlerrrrrrrrrrrrrrrrrrrrrrrrr");

						EvaluationContext evalContext = new EvaluationContext();
						evalContext.setBaseCohort(selectedCohort);

						DataSet patientDataSet;
						try {
							patientDataSet =Context.getService( DataSetDefinitionService.class).evaluate(dsd, evalContext);
							dQRObject.setDataSet(patientDataSet);
							dQRObject.setDataSetDefinition(dsd);
						}
						catch (EvaluationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

				// Add all dataset definition to the request (allow user to choose)
				dQRObject.setDataSetDefinitions(Context.getService(
						DataSetDefinitionService.class).getAllDefinitions( false));
				IntraList.add(dQRObject);
			}

			model.addAttribute("IntraList", IntraList);
		}
		catch (ClassCastException ex) {
			// pass
		}
		if (data == null)
			return "redirect:../reporting/dashboard/index.form";

		@SuppressWarnings("unused")
		SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
				renderArg);
		// model.addAttribute("columns", dataSet.getMetaData());
		return null;
	}


	public String manipulatePNC(
			@RequestParam(required = false, value = "savedDataSetKey") String savedDataSetKey,
			Model model, HttpSession session) throws Exception {

		String renderArg = (String) session
				.getAttribute(ReportingConstants.OPENMRS_REPORT_ARGUMENT);

		ReportData data = null;
		try {
			data = (ReportData) session
					.getAttribute(ReportingConstants.OPENMRS_REPORT_DATA);

			savedDataSetKey=savedDataSetKey;
			List<String> savedColumnKeys = new ArrayList<String>();

			getSavedPNCKeys(savedColumnKeys);
			List<DQReportModel> pncList = new ArrayList<DQReportModel>();

			for(String savedColumnKey : savedColumnKeys ) {
				DQReportModel dQRObject = new DQReportModel();


				for (Map.Entry<String, DataSet> e : data.getDataSets().entrySet()) {
					if (e.getKey().equals(savedDataSetKey)) {
						MapDataSet mapDataSet = (MapDataSet) e.getValue();
						DataSetColumn dataSetColumn = mapDataSet.getMetaData().getColumn(savedColumnKey);
						dQRObject.setSelectedColumn(dataSetColumn);

						Object result = mapDataSet.getData(dataSetColumn);
						Cohort selectedCohort = null;
						if (result instanceof CohortIndicatorAndDimensionResult) {
							CohortIndicatorAndDimensionResult cidr =(CohortIndicatorAndDimensionResult) mapDataSet.getData(dataSetColumn);
							selectedCohort = cidr.getCohortIndicatorAndDimensionCohort();

						}

						// Evaluate the default patient dataset definition
						DataSetDefinition dsd = null; if (dsd == null) {
							SimplePatientDataSetDefinition d = new SimplePatientDataSetDefinition();
							d.addPatientProperty("patientId"); List<PatientIdentifierType>
									types = ReportingConstants.GLOBAL_PROPERTY_PREFERRED_IDENTIFIER_TYPES();
							if (!types.isEmpty()) {
								d.setIdentifierTypes(types);
							}

							d.addPatientProperty("givenName");
							d.addPatientProperty("familyName");
							d.addPatientProperty("age");
							d.addPatientProperty("gender");

							dsd = d;


						}

						EvaluationContext evalContext = new EvaluationContext();
						evalContext.setBaseCohort(selectedCohort);

						DataSet patientDataSet;
						try {
							patientDataSet =Context.getService( DataSetDefinitionService.class).evaluate(dsd, evalContext);
							dQRObject.setDataSet(patientDataSet);
							dQRObject.setDataSetDefinition(dsd);
						}
						catch (EvaluationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

				// Add all dataset definition to the request (allow user to choose)
				dQRObject.setDataSetDefinitions(Context.getService(
						DataSetDefinitionService.class).getAllDefinitions( false));
				pncList.add(dQRObject);
			}

			model.addAttribute("ancList", pncList);
		}
		catch (ClassCastException ex) {
			// pass
		}
		if (data == null)
			return "redirect:../reporting/dashboard/index.form";

		@SuppressWarnings("unused")
		SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().get(
				renderArg);
		// model.addAttribute("columns", dataSet.getMetaData());
		return null;
	}


	private void getSavedIMCIKeys(List<String> savedColumnKeys) {
		savedColumnKeys.add("ANC1");
		savedColumnKeys.add("ANC2");
		savedColumnKeys.add("ANC3");
	}


	private void getSavedGBVKeys(List<String> savedColumnKeys) {
		savedColumnKeys.add("ANC1");
		savedColumnKeys.add("ANC2");
		savedColumnKeys.add("ANC3");
	}

	private void getSavedANCKeys(List<String> savedColumnKeys) {
			savedColumnKeys.add("ANC1");
			savedColumnKeys.add("ANC2");
			savedColumnKeys.add("ANC3");
		}
	private void getSavedKeysIntrapartum(List<String> savedColumnKeys) {
		savedColumnKeys.add("ANC1");
		savedColumnKeys.add("ANC2");
	}

	private void getSavedPNCKeys(List<String> savedColumnKeys) {
		savedColumnKeys.add("ANC1");
		savedColumnKeys.add("ANC2");
		savedColumnKeys.add("ANC3");
	}

		private static Comparator<Encounter> COMPARATOR = new Comparator<Encounter>() {

			public int compare(Encounter enc1, Encounter enc2) {

				return enc2.getEncounterDatetime().compareTo(
						enc1.getEncounterDatetime());
			}

		};



	}