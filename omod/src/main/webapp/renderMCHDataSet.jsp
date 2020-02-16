<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="/WEB-INF/view/module/reporting/include.jsp"%>
<%@ include file="/WEB-INF/view/module/reporting/includeScripts.jsp"%>


<c:set var="__openmrs_hide_report_link" value="true" />
<c:set var="dataSetMaps" value="${__openmrs_report_data.dataSets}" />
<c:set var="mapDataSet" value="${dataSetMaps['defaultDataSet'].data}"/>


<style type="text/css">
    .alt { background-color: #EEE; }
    .hover { background-color: #B0BED9; }
    .althover { background-color: #B0BED9; }
</style>

<script type="text/javascript">
$j(document).ready(function(){
	$j('.dataset tr:even').addClass('alt');
	$j('.dataset tr:even').hover(
			function(){$j(this).addClass('hover')},
			function(){$j(this).removeClass('hover')}
	);
	$j('.dataset tr:odd').hover(
			function(){$j(this).addClass('althover')},
			function(){$j(this).removeClass('althover')}
	);
	$j('#tabs').tabs();
	$j('.ui-tabs-panel').css('padding','0').css('padding-top','.5em').css('overflow', 'auto');
});
</script>


<%--
  This page assumes a ReportData object in the session as the attribute '__openmrs_report_data'
--%>

<style type="text/css">
	#wrapper input, #wrapper select, #wrapper textarea, #wrapper label, #wrapper button, #wrapper span, #wrapper div { font-size: large; }
	form ul { margin:0; padding:0; list-style-type:none; width:100%; }
	form li { display:block; margin:0; padding:6px 5px 9px 9px; clear:both; color:#444; }
	fieldset { padding: 5px; margin:5px; }
	fieldset legend { font-weight: bold; background: #E2E4FF; padding: 6px; border: 1px solid black; }
	label.desc { line-height:150%; margin:0; padding:0 0 3px 0; border:none; color:#222; display:block; font-weight:bold; }
</style>


<openmrs:portlet url="currentReportHeader" moduleId="reporting" parameters="showDiscardButton=true"/>

<div id="page">
	<div id="container">
		<div id="tabs">
			<ul>
					<li><a href="#IMCI">PNC</a></li>
					<li><a href="#GBV">PNC</a></li>
					<li><a href="#ANC">ANC</a></li>
					<li><a href="#INTRAPARTUM">INTRAPARTUM</a></li>
					<li><a href="#PNC">PNC</a></li>

			</ul>



			 <div id="IMCI">
                        IMCI Indicators
             </div>

			 <div id="GBV">
                        GBV Indicators
             </div>


			<div id="ANC">

 <table width="100%">
     <c:forEach var="cohortResults" items="${ancList}" varStatus="loopTimer">
           <tr>
             <c:if test="${!empty cohortResults.dataSet}">
        		 <c:url var="url" value="/module/reporting/dashboard/viewCohortDataSet.form?savedDataSetKey=ancDataSet&savedColumnKey=${cohortResults.selectedColumn.name}"/>
		            <td bgcolor="#E0ECF8">${cohortResults.selectedColumn.name}</td>
		            <td bgcolor="#E0ECF8" class="categories">${cohortResults.selectedColumn.label}</td>
		            <td bgcolor="#E0ECF8"><a style="text-decoration: underline" href="${url}">${fn:length(cohortResults.dataSet.rows)}</a></td>
            </c:if>
</tr>
</c:forEach>


</table>



			</div>
			<div id="INTRAPARTUM">
             <table width="100%">
                 <c:forEach var="cohortResults" items="${IntraList}" varStatus="loopTimer">
                       <tr>
                         <c:if test="${!empty cohortResults.dataSet}">
                    		 <c:url var="url" value="/module/reporting/dashboard/viewCohortDataSet.form?savedDataSetKey=ancDataSet&savedColumnKey=${cohortResults.selectedColumn.name}"/>
            		            <td bgcolor="#E0ECF8">${cohortResults.selectedColumn.name}</td>
            		            <td bgcolor="#E0ECF8" class="categories">${cohortResults.selectedColumn.label}</td>
            		            <td bgcolor="#E0ECF8"><a style="text-decoration: underline" href="${url}">${fn:length(cohortResults.dataSet.rows)}</a></td>
                        </c:if>
            </tr>
            </c:forEach>
            </table>
            </div>
            <div id="PNC">
            PNC Indicators
            </div>
		</div>
	</div>
</div>






<%@ include file="/WEB-INF/template/footer.jsp"%>