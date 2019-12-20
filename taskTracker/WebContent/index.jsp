<!DOCTYPE html>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<html>
<head>
    <title>SPPA-T3000 Task Tracker</title>
    <link rel = "stylesheet" type = "text/css" href = "https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
     <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/fixedcolumns/3.3.0/js/dataTables.fixedColumns.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="https://intranet.entry.siemens.com/favicon.ico"/>
	<link rel="stylesheet" href="./css/mppstyle.css">
	<script type="text/javascript" charset="utf8" src="./js/mppjavascript.js"></script>
</head>
<body>
	<div class="dashboard-header" id="myHeader">
		<nav class="navbar" style="background: linear-gradient(135.27deg, rgb(203, 203, 243) 2%, rgb(32, 161, 213) 85%, rgb(84, 90, 128) 85%); margin-bottom: 0px; border: inherit;">
			<div style="height: 50px; font-size: 18px; line-height: 20px;"
				style="width: 100%;">
				<a style="float: left" href="#"><img
					src="./images/sie_logo_claim_petrol_rgb.png" alt="Siemens"
					style="width: 150px"></a> <span
					style="font-family: sans-serif; color: white !important; float: right; padding: 20px 30px;">SPPA Task Tracker</span>
			</div>
		</nav>
		<nav class="navbar" style="background: white">
			<div style="width: 50%; margin-left: 25%;">
				<button class="tablink" onclick="openPage('Home', this, '#faebcc','#all')" id="defaultOpen">All Tasks!</button>
				<button class="tablink" onclick="openPage('News', this, '#d6e9c6','#about')" >Completed!</button>
				<button class="tablink" onclick="openPage('Contact', this, '#bce8f1','#blog')">In Progress!</button>
				<button class="tablink" onclick="openPage('About', this, '#ebccd1','#projects')">Running Late!</button>
				<button class="tablink" onclick="openPage('About', this, '#f1f1f1','#contact')">Not Started!</button>
			</div>
		</nav>
	</div>
	
	<div class="content" id="topNavBar">	
		<table id="example" class="display" width="100%" border="0.5"></table>
			<div style="width: 15%; padding: 8px 10px;">
				<div id="mySidenav" class="sidenav" style="display: none;">
					<a href="#" id="all" style="height: none;"><strong>All Tasks!</strong></a>
					<a href="#" id="about" style="height: none;"><strong>Completed!</strong></a>
					<a href="#" id="blog" style="height: none;"><strong>In Progress!</strong></a> 
					<a href="#" id="projects" style="height: none;"><strong>Running Late!</strong></a> 
					<a href="#"	id="contact" style="height: none;"><strong>Not Started!</strong></a>
				</div>
			</div>		
		<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
	</div>
</body>

<style>
#example thead {
    background-color: #31708f; 
    color: #fff; 
    font-family: 'HelveticaNeue-Light', Helvetica, Arial; 
    font-size: 15px;
}

#example td {
font-size: small
}

#example thead, th {text-align: center;}

#example_wrapper {
 font-family: 'HelveticaNeue-Light', Helvetica, Arial; 
/*  padding: 25px; */
}
</style>
<script>
var dataSet = ${person}.Sheet1;
</script>
</html>