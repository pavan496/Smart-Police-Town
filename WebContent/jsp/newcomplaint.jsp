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
<link rel="stylesheet" href="../styles/jquery.datetimepicker.css"
	type="text/css"></link>

<script type="text/javascript" src="../scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="../scripts/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="../scripts/additional-methods.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../scripts/jquery.MultiFile.pack.js"></script>
<style type="text/css">
.ui-block-a, .ui-block-b, .ui-block-c {
	padding-right: 10px;
}
</style>
</head>
<body>
	<div data-role="page" id="complaintTypes" data-transition="slide">
		<div data-role="header" data-position="fixed">
			<a href="../"
				class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-home">Home</a>
			<h1>Complaint Type</h1>
			<script type="text/javascript">
				function updateComplaintType(a) {
					$('#complaintType').val(a);
				}
			</script>
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
					onclick="updateComplaintType('<%=type.getComplaintTypeCode()%>')"><%=type.getComplaintTypeName()%></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div data-role="page" id="complain" data-transition="slide">
		<div data-role="header" data-add-back-btn="true" data-position="fixed">
			<h1>Complaint Info</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form id="compForm">
				<input type="hidden" id="complaintType" name="complaintType"></input>
				<input type="hidden" id="reportedLocation" name="reportedLocation"></input>
				<h2>Incident Information</h2>
				<div class="ui-grid-b ui-responsive">
					<div class="ui-block-a">
						<label for="incidentLocation">Incident Location <font
							color="red">*</font></label> <input id="incidentLocation"
							name="incidentLocation" required minlength="2" maxlength="50"></input>
					</div>
					<div class="ui-block-b">
						<label for="incidentDate">Incident Date<font color="red">*</font></label>
						<input id="incidentDate" name="incidentDate" readonly required></input>
					</div>
					<div class="ui-block-c"></div>
				</div>
				<div data-inline="true">
					<label for="summary">Nature of Complaint <font color="red">*</font></label>
					<textarea name="summary" id="summary" required minlength="2"
						rows="5" maxlength="1000"></textarea>
				</div>
				<h2>Reporter Information</h2>
				<div class="ui-grid-b ui-responsive">
					<div class="ui-block-a">
						<label for="reportedBy">Name <font color="red">*</font></label> <input
							type="text" id="reportedBy" name="reportedBy" required
							minlength="2" maxlength="50"></input>
					</div>
					<div class="ui-block-b">
						<label for="contactNo">Contact No <font color="red">*</font></label>
						<input type="text" id="contactNo" name="contactNo" required
							maxlength="10" minlength="5"></input>
					</div>
					<div class="ui-block-c">
						<label for="emailAddress">Email Address</label> <input
							type="email" id="emailAddress" name="emailAddress" minlength="2"
							maxlength="50"></input>
					</div>
				</div>
			</form>

		</div>
		<div data-role="footer">
			<button id="btnSave"
				class="ui-btn-right ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">Save</button>
		</div>
		<script type="text/javascript">
			var logic = function(selDt) {
				var curDt = new Date();
				if (selDt.getYear() == curDt.getYear()
						&& selDt.getMonth() == curDt.getMonth()
						&& selDt.getDate() == curDt.getDate())
					this.setOptions({
						maxTime : '0'
					});
				else
					this.setOptions({
						maxTime : "24:00"
					});
			};
			$("#incidentDate").datetimepicker({
				maxDate : '0',
				onChangeDateTime : logic,
				onShow : logic
			});

			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(success, error);
			}
			//Get the latitude and the longitude;
			function success(position) {
				$("#reportedLocation").val(
						position.coords.latitude + ","
								+ position.coords.longitude);
			}

			function error() {
				console.log("Geocoder failed");
			}

			$("#btnSave").click(
					function() {
						$("#compForm").validate({
							rules : {
								contactNo : {
									number : true
								}
							}
						});
						if ($("#compForm").valid()) {
							$.ajax({
								type : "POST",
								url : "../register-complaint",
								data : $("#compForm").serialize(),
								dataType : "json",
								success : function(d) {
									$("#incId").html(d.incidentid);
									$("#aiIncId").val(d.incidentid);
									$.mobile.pageContainer.pagecontainer(
											"change", "#reported");
								},
								error : function(e) {
									alert("Transaction Failed");
								}

							});
						}
					});
		</script>
	</div>
	<div data-role="page" id="reported" data-transition="slide">
		<div data-role="header" data-position="fixed">
			<a href="../"
				class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-home">Home</a>
			<h1>Complaint Registered</h1>

		</div>
		<div data-role="main" class="ui-content" style="text-align: center">
			<span>Your complaint has been registered. Please note the
				following incident number for further reference.</span>
			<h1 id="incId"></h1>
			<span>Please use the section below to attach any images
				related to the incident</span>
			<form id="frmAttachImages" method="POST"
				enctype="multipart/form-data">
				<input type="hidden" id="aiIncId" name="aiIncId" /><input
					type="file" class="multi"/>
			</form>
		</div>
		<div data-role="footer">
			<button id="btnAttach"
				class="ui-btn-right ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">Attach</button>
		</div>
		<script type="text/javascript">
			$("#btnAttach")
					.click(
							function() {
								$
										.ajax({
											type : "POST",
											url : "../attach-images",
											data : new FormData(
													$("#frmAttachImages")[0]),
											processData : false,
											contentType : false,
											success : function(d) {
												window.location.href = "search.jsp?inc_id="
														+ $("#incId").html()
														+ "&es";
											},
											error : function(e) {
												alert("Attaching image has failed. Please try again later.");
												window.location.href = "search.jsp?inc_id="
														+ $("#incId").html()
														+ "&es";
											}
										});
							})
		</script>
	</div>
</body>
</html>