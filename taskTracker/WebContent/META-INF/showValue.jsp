<!DOCTYPE html>
<html>
<head>
    <title>Convert JSON Data to HTML Table</title>
    <link rel = "stylesheet" type = "text/css" href = "https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
     <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>

</head>
<body>
<div class="h1Heading">M.S.P.</div>

<table id="example" class="display" width="100%" border="0.5"></table>
	<div style="width: 15%;padding: 8px 10px;">
		<div class="alert alert-success">
			<strong>Completed!</strong>.
		</div>
		<div class="alert alert-info">
			<strong>On Going!</strong>.
		</div>
		<div class="alert alert-danger">
			<strong>Should be completed!</strong>.
		</div>
	</div>
</body>


<script>

var dataSet = ${person}.Sheet1;

$(document).ready(function() {
    $('#example').DataTable( {
        data: dataSet,
        columns: [
            { title: "Task" },
            { title: "Plan" },
            { title: "Efforts" },
            { title: "Complete %" },
            { title: "Actual Start" },
            { title: "Start" },
            { title: "Baseline Start" },
            { title: "Finish" },
            { title: "Baseline Finish" },
            { title: "Resource Names" }
        ],
        fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            if ( aData[3] == "100.0" )
            {
                $('td', nRow).css('background-color', '#dff0d8');
                $('td', nRow).css('color', '#3c763d');
                $('td', nRow).css('border-color', '#94a784');
            }
            else if (Date.parse(aData[7])<=Date.parse(new Date()) )
            {
            	$('td', nRow).css('background-color', '#f2dede');
                $('td', nRow).css('color', '#a94442');
                $('td', nRow).css('border-color', '#e08594');            
            }
            else if (Date.parse(aData[7])>=Date.parse(new Date()) && Date.parse(aData[5])<=Date.parse(new Date()))
            {
            	$('td', nRow).css('background-color', '#d9edf7');
                $('td', nRow).css('color', '#31708f');
                $('td', nRow).css('border-color', '#499eaf');            
            }
        }
    } );
} )
</script>

<style>
#example thead {
    background-color: #568996; 
    color: #fff; 
    font-family: 'HelveticaNeue-Light', Helvetica, Arial; 
    font-size: 11px;
}

#example td{
font-size: small
}

#example_wrapper{
 font-family: 'HelveticaNeue-Light', Helvetica, Arial; 
 padding: 25px;
}

.h1Heading, h1 {
    font-size: 24px;
    text-align: center;
    line-height: 24px;
    margin: 25px 0 0px;
    font-weight: 100;
    font-family: Heebo,sans-serif;}
</style>
<body>
</body>
</html>