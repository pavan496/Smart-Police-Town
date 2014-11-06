<%@page import="com.dh.spt.db.model.Complaints"%>
<%@page import="java.util.List"%>
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
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<a href="../"
				class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-home">Home</a>
			<h1>Complaints</h1>
		</div>
		<div data-role="main" class="ui-content">
			<%
				ComplaintsUtil util = new ComplaintsUtil();

				List<Complaints> complaints = util.getLatestComplaints();

				if (complaints.isEmpty()) {
			%>
			<span>There are no complaints logged yet.</span>
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
					</tr>
				</thead>
				<tbody>
					<%
						for (Complaints complaint : complaints) {
					%>
					<tr>
						<th><a rel="external"
							href="search.jsp?inc_id=<%=complaint.getIncidentId()%>"><%=complaint.getIncidentId()%></a></th>
						<td><%=complaint.getComplaintTypes()
							.getComplaintTypeName()%></td>
						<td><%=complaint.getIncidentLocataion()%></td>
						<td><%=complaint.getIncidentTime()%></td>
						<td><%=complaint.getReportedTime()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				}
			%>

		</div>
	</div>
</body>
</html>