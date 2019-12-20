$(document).ready(function() {
	
    var myTable= $('#example').DataTable( {
        data: dataSet,
        autoWidth: false,
		  columnDefs: [
		      { width: '25%', targets: 0 }, 
		      { width: '5%', targets: 1 }, 
		      { width: '5%', targets: 2 },  
		      { width: '5%', targets: 3 }, 
		      { width: '10%', targets: 4 },
		      { width: '10%', targets: 5 }, 
		      { width: '10%', targets: 6 }, 
		      { width: '10%', targets: 7 }, 
		      { width: '10%', targets: 8 }, 
		      { width: '10%', targets: 9 }  
		   ],
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
        iDisplayLength: 25,
        fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
        	var finishDate=new Date(aData[7]).getTime();
        	if(finishDate.toString().indexOf("-")>-1){
        		finishDate=-new Date(aData[7]).getTime();
        	}
        	
        	var startDate=new Date(aData[5]).getTime();
        	if(startDate.toString().indexOf("-")>-1){
        		startDate=-new Date(aData[5]).getTime();
        	}
        	           	
        	if ( aData[3] == "100.0" )
            {
                $('td', nRow).css('background-color', '#dff0d8');
                $('td', nRow).css('color', '#3c763d');
                $('td', nRow).css('border-color', '#94a784');
            }
        	else if (finishDate>=(new Date()).getTime() && startDate<=(new Date()).getTime())
            {
            	$('td', nRow).css('background-color', '#d9edf7');//in progress
                $('td', nRow).css('color', '#31708f');
                $('td', nRow).css('border-color', '#499eaf');            
            }
            else if (finishDate<=(new Date()).getTime() && aData[3] != "100.0")
            {
            	$('td', nRow).css('background-color', '#f2dede');//Running late
                $('td', nRow).css('color', '#a94442');
                $('td', nRow).css('border-color', '#e08594');            
            }        	
        }
    } );
    
    var ctrlShowContent = function(event) {
    	var inputText=$('.dataTables_filter input').val();
    	$('#example').DataTable().destroy();
    	
    	var table =$('#example').DataTable( {
    		"ajax": {
    		    "url": "searchServlet?status="+event.target.textContent,
    		    "dataSrc": ""
    		  },
    		  autoWidth: false,
    		  columnDefs: [
    			  { width: '25%', targets: 0 }, 
    		      { width: '5%', targets: 1 }, 
    		      { width: '5%', targets: 2 },  
    		      { width: '5%', targets: 3 }, 
    		      { width: '10%', targets: 4 },
    		      { width: '10%', targets: 5 }, 
    		      { width: '10%', targets: 6 }, 
    		      { width: '10%', targets: 7 }, 
    		      { width: '10%', targets: 8 }, 
    		      { width: '10%', targets: 9 }  
    		   ],
            columns: [
                { title: "Task" },
                { title: "Plan" },
                { title: "Efforts" },
                { title: "Complete %" },
                { title: "Actual Start (Day MM/dd/YY)" },
                { title: "Start (Day MM/dd/YY)" },
                { title: "Baseline Start (Day MM/dd/YY)" },
                { title: "Finish (Day MM/dd/YY)" },
                { title: "Baseline Finish (Day MM/dd/YY)" },
                { title: "Resource Names" }
            ],                        
            iDisplayLength: 25,
            fnRowCallback: function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            	nRow.cells[0].width='25%';
            	nRow.cells[1].width='5%';
            	nRow.cells[2].width='5%';
            	nRow.cells[3].width='5%';
            	nRow.cells[4].width='10%';
            	nRow.cells[5].width='10%';
            	nRow.cells[6].width='10%';
            	nRow.cells[7].width='10%';
            	nRow.cells[8].width='10%';
            	nRow.cells[9].width='10%';
            	nRow.cells[1].align="center";
            	nRow.cells[2].align="center";
            	nRow.cells[3].align="center";
            	nRow.cells[4].align="center";
            	nRow.cells[5].align="center";
            	nRow.cells[6].align="center";
            	nRow.cells[7].align="center";
            	nRow.cells[8].align="center";
            	nRow.cells[9].align="center";
            	var finishDate=aData[7];
            	var startDate=aData[5];
            	
            	if(aData[7]!="NA"){
            		aData[7].split(" ")[1].split("/")[0]
            		aData[7].split(" ")[1].split("/")[1]
            		aData[7].split(" ")[1].split("/")[2]
            		finishDate=new Date("20"+aData[7].split(" ")[1].split("/")[2],aData[7].split(" ")[1].split("/")[0]-1,aData[7].split(" ")[1].split("/")[1]).getTime();
            	}
            	
            	if(aData[5]!="NA"){
            		aData[5].split(" ")[1].split("/")[0]
            		aData[5].split(" ")[1].split("/")[1]
            		aData[5].split(" ")[1].split("/")[2]
            		startDate=new Date("20"+aData[5].split(" ")[1].split("/")[2],aData[5].split(" ")[1].split("/")[0]-1,aData[5].split(" ")[1].split("/")[1]).getTime();
            	}
            	           	
            	           	
            	if ( aData[3] == "100.0" )
                {
                    $('td', nRow).css('background-color', '#dff0d8');
                    $('td', nRow).css('color', '#3c763d');
                    $('td', nRow).css('border-color', '#94a784');
                }
            	else if (finishDate>=(new Date()).getTime() && startDate<=(new Date()).getTime())
                {
                	$('td', nRow).css('background-color', '#d9edf7');//in progress
                    $('td', nRow).css('color', '#31708f');
                    $('td', nRow).css('border-color', '#499eaf');            
                }
                else if (finishDate<=(new Date()).getTime() && aData[3] != "100.0")
                {
                	$('td', nRow).css('background-color', '#f2dede');//Running late
                    $('td', nRow).css('color', '#a94442');
                    $('td', nRow).css('border-color', '#e08594');            
                }            	
            }
        } );    	
    	table.search(inputText).draw();
};


document.querySelector('#mySidenav').addEventListener('click',
		ctrlShowContent);



window.onscroll = function() {myFunction()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
    $('#example thead').addClass("stickyTable");
        
  } else {
    header.classList.remove("sticky");
    $('#example thead').removeClass("stickyTable");
  }
  
  if (document.body.scrollTop > 30 || document.documentElement.scrollTop > 30) {
	    mybutton.style.display = "block";
	  } else {
	    mybutton.style.display = "none";
	  }
}
//Get the button:
mybutton = document.getElementById("myBtn");

	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();

} )

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

function openPage(pageName,elmnt,color,id) {
	  var i, tablinks;
	  tablinks = document.getElementsByClassName("tablink");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].style.backgroundColor = "";
	    tablinks[i].style.color = "";
	  }

	  elmnt.style.backgroundColor = color;
	  elmnt.style.color = "black";
	  document.querySelector(id).click()
	}


