<%@page import="com.dh.spt.db.model.Complaints"%>
<%@page import="com.dh.spt.db.ComplaintsUtil"%>
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
<script type="text/javascript" src="../scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="../scripts/additional-methods.min.js"></script>
<%
	String incidentId = request.getParameter("inc_id");
	if (incidentId == null)
		incidentId = "";
	Complaints complaint = null;
	if (!"".equalsIgnoreCase(incidentId)) {
		ComplaintsUtil util = new ComplaintsUtil();
		complaint = util.getComplaintByComplaintId(incidentId);
	}

	if (complaint != null && complaint.getReportedLocation() != null
			&& !complaint.getReportedLocation().equals("")) {
%>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
	function initialize() {
		var myLatlng = new google.maps.LatLng(
<%=complaint.getReportedLocation()%>
	);
		var mapOptions = {
			zoom : 16,
			center : myLatlng
		}
		var map = new google.maps.Map(document.getElementById("map_canvas"),
				mapOptions);

		// To add the marker to the map, use the 'map' property
		var marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			title : "Location"
		});
	}
</script>
<%
	}
%>
</head>
<body onload="initialize()">
	<div data-role="page" id="search" data-transition="slide">
		<div data-role="header" data-position="fixed">
			<a href="../"
				class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-home">Home</a>
			<h1>Complaint Detail</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form id="complaintSearch" method="get" data-ajax="false">
				<input type="search" id="inc_id" name="inc_id"
					value="<%=incidentId%>" placeholder="Incident Id">
			</form>
			<br />
			<%
				if (!"".equalsIgnoreCase(incidentId)) {
					if (complaint == null) {
			%><span>Incident Id does not exist. </span>
			<%
				} else {
			%>
			<table data-role="table" data-mode="reflow" class="ui-responsive table-stroke">
				<thead>
					<tr>
						<th data-priority="1">Incident Id</th>
						<th data-priority="2">Complaint Type</th>
						<th data-priority="3">Location</th>
						<th data-priority="4">Incident Date &amp; Time</th>
						<th data-priority="5">Reported Date &amp; Time</th>
						<th data-priority="6">Reported By</th>
						<th data-priority="6">Contact No</th>
						<th data-priority="6">Email Address</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=complaint.getIncidentId()%></td>
						<td><%=complaint.getComplaintTypes()
							.getComplaintTypeName()%></td>
						<td><%=complaint.getIncidentLocataion()%></td>
						<td><%=complaint.getIncidentTime()%></td>
						<td><%=complaint.getReportedTime()%></td>
						<td><%=complaint.getReporterName()%></td>
						<td><%=complaint.getReporterContactNo()%></td>
						<td><%=complaint.getReporterEmailAddress()%></td>
					</tr>
				</tbody>
			</table>
			<br />
			<h3>Summary</h3>
			<span><%=complaint.getSummary() == null ? "" : complaint
							.getSummary().replaceAll("(\r\n|\n)", "<br />")%></span>
			<%
				if (complaint.getReportedLocation() != null
								&& !complaint.getReportedLocation().equals("")) {
			%>
			<br />
			<h3>Reported Location</h3>
			<div id="map_canvas" style="width: 100%; height: 300px"></div>
			<%
				}
					}
				}
			%>

		</div>
	</div>
</body>
</html>