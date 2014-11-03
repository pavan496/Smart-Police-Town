<%@page import="com.dh.spt.db.model.ComplaintTypes"%>
<%@page import="java.util.List"%>
<%@page import="com.dh.spt.db.ComplaintTypesUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../styles/jquery.mobile-1.4.5.min.css"
	type="text/css"></link>
<script type="text/javascript" src="../scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.mobile-1.4.5.js"></script>

</head>
<body>
	<div data-role="page" id="complaintTypes">
		<div data-role="header">
			<a href="../"
				class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-home">Home</a>
			<h1>New Complaint</h1>
		</div>
		<div data-role="main" class="ui-content">
			<ul data-role="listview" data-filter="true"
				data-filter-placeholder="Search Complaint Types" data-inset="true">
				<%
					ComplaintTypesUtil ctUtil = new ComplaintTypesUtil();
					List<ComplaintTypes> allComplaintTypes = ctUtil.getAllComplaintTypes();
					
					for(ComplaintTypes type : allComplaintTypes){
				%>
				<li><a href="#complain"
					value="<%=type.getComplaintTypeCode()%>"><%=type.getComplaintTypeName()%></a></li>
				<%
					}
				%>

			</ul>
		</div>
	</div>
	<div data-role="page" id="complain">
		<script type="text/javascript">
			$("#btnSave").click(
					function() {
						$.post("../register-complaint",
								$("#compForm").serialize()).done(function() {
							alert("Hogaya");
						});

					});
		</script>
		<style type="text/css">
.ui-block-a, .ui-block-b, .ui-block-c {
	padding-right: 10px;
}
</style>
		<div data-role="header" data-add-back-btn="true">
			<button id="btnSave"
				class="ui-btn-right ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">Save</button>
			<h1>New Complaint</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form id="compForm">
				<input type="hidden" id="complaintType" name="complaintType"></input>
				<h2>Incident Information</h2>
				<div class="ui-grid-b ui-responsive">
					<div class="ui-block-a">

						<label for="incidentDate">Incident Date:</label> <input
							id="incidentDate" name="incidentDate"></input>
					</div>
					<div class="ui-block-b">
						<label for="incidentTime">Incident Time:</label> <input
							id="incidentTime" name="incidentTime"></input>
					</div>
					<div class="ui-block-c">
						<label for="incidentLocation">Incident Location:</label> <input
							id="incidentLocation" name="incidentLocation"></input>
					</div>
				</div>
				<div data-inline="true">
					<label for="summary">Summary:</label>
					<textarea name="summary" id="summary"></textarea>
				</div>
				<h2>Reporter Information</h2>
				<div class="ui-grid-a ui-responsive">
					<div class="ui-block-a">
						<label for="reportedBy">Name:</label> <input type="text"
							id="reportedBy" name="reportedBy"></input>
					</div>
					<div class="ui-block-b">
						<label for="contactNo">Contact No:</label> <input type="text"
							id="contactNo" name="contactNo"></input>
					</div>
				</div>

			</form>

		</div>
	</div>
</body>
</html>