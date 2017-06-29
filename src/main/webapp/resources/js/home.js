
$(document).ready(function () {
	addColor(0);
	
	var d = new Date();
	var month = (parseInt(d.getMonth())+1);
	if(month.toString().length == 1)
		month = '0'+month;
	
	var day = d.getDate();
	
	if(day.toString().length == 1)
		day = '0'+day;
	
	var hour = d.getHours();
	if(hour.toString().length == 1)
		hour = '0'+hour;
	
	var min = d.getMinutes();
	if(min.toString().length == 1)
		min = '0'+min;
	

	$('#dateId').text(d.getFullYear()+"/"+month+"/"+day+" " +hour+":" +min)
});




function addColor(id){
	
	if(id == 0)
	{
		$('.colum_1').css('background-color', '#E5F6E5');		
		$('.colum_1').css('border-color', '#B2F2B2');
		
		$('.colum_2').css('background-color', '#F5F5E0');
		$('.colum_2').css('border-color', '#F3F3CA');
		
		$('.colum_3').css('background-color', '#DDEAF6');
		$('.colum_3').css('border-color', '#BDD8F2');
		
		
		$('.colum_4').css('background-color', '#F7DEF6');
		$('.colum_4').css('border-color', '#F3C0F1');
	}
	else if(id == 1)
	{
		$('.dash_box').css('background-color', '#ffffff');
		$('.colum_1').css('background-color', '#B2F2B2');		
		$('.colum_1').css('border-color', '#B2F2B2');
	}
	else if(id == 2)
	{
		$('.dash_box').css('background-color', '#ffffff');
		$('.colum_2').css('background-color', '#F5F5E0');		
		$('.colum_2').css('border-color', '#F3F3CA');
	}
	else if(id == 3)
	{
		$('.dash_box').css('background-color', '#ffffff');
		$('.colum_3').css('background-color', '#DDEAF6');		
		$('.colum_3').css('border-color', '#BDD8F2');
	}
	else if(id == 4)
	{
		$('.dash_box').css('background-color', '#ffffff');
		$('.colum_4').css('background-color', '#F7DEF6');		
		$('.colum_4').css('border-color', '#F3C0F1');
	}
	
	
	
	var pdfBtn = document.getElementById('pdfButton');

	pdfBtn.addEventListener('click', function(event) {
		makeScreenshotWithString("content", "1");
	});
}



function getRatios(id)
{
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'/rates?equipmentType='+id,
	    'type': 'GET',
//	    'data': $('#sales').serialize(),
	    'success': function(result){
			 $('.right_data').html(result);
			 $('.se-pre-con').fadeOut('slow');
			 addColor(id);
		//	 $('.colum_'+id).css('background-color', 'rgba(233, 182, 182, 0.7)');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}


//
//function loadLabels(labels){
//
//	var label = new Array();
//	if(labels!=null && labels.length>0)
//	{
//		
//		for(var i=0;i<labels.length;i++)
//		{
//			label.push(labels[i].name);
//			//data.push(labels[i].data);
//		}
//	}
//	
//	return label;
//}
//
//function loadData(labels){
//
//	var data = new Array();
//	if(labels!=null && labels.length>0)
//	{
//		
//		for(var i=0;i<labels.length;i++)
//		{
////			label.push(labels[i].name);
//			data.push(labels[i].data);
//		}
//	}
//	
//	return data;
//}
//
//function loadGraphics()
//{
////	var label = new Array();
////	var data = new Array();
//	
////	if(labels!=null && labels.length>0)
////	{
////		
////		for(var i=0;i<labels.length;i++)
////		{
//////			label.push(labels[i].name);
////			data.push(labels[i].data);
////		}
////	}
//	
//	var labels = JSON.parse($('#equipment').val());
//	var label = loadLabels(labels);
//	var data = loadData(labels);
//	
//	var data = {
//		    labels: label,
//		    datasets: [
//		        {
//		            label: "Item/Day",
//		            backgroundColor: "rgba(52,152,219,0.2)",
//		            borderColor: "rgba(52,152,219,1)",
//		            borderWidth: 1,
//		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
//		            hoverBorderColor: "rgba(52,152,219,1)",
//		            data: data,
//		        }
//		    ]
//		};
//	
//	
//
//	var ctx = $("#equipmentChart");
//	var myChart = new Chart(ctx, {
//	    type: 'bar',
//	    data:data,
//	    options: {
//	        scales: {
//	            yAxes: [{
//	                ticks: {
//	                    beginAtZero:true
//	                }
//	            }]
//	        }
//	    }
//	});
//	
//	
////	labels = JSON.parse($('#distributor').val());
////	var label = loadLabels(JSON.parse($('#distributor').val()));
//	
//	labels = JSON.parse($('#distributor').val());
//	label = loadLabels(labels);
//	data = loadData(labels);
//	
//	ctx = $("#distributorChart");
//	
//	data = {
//		    labels: label,
//		    datasets: [
//		        {
//		            data: data,
//		            backgroundColor: [
//		                "#58b2ee",
//		                "#A9D0F5",
//		                "#8181F7",
//		                "#81F7F3",
//		                "#0431B4",
//		                "#CED8F6"
//		                
//		            ],
//		            hoverBackgroundColor: [
//		                "#FF6384",
//		                "#36A2EB",
//		                "#FFCE56",
//		                "#4000FF",
//		                "#A901DB",
//		                "#FAAC58"
//		            ]
//		        }]
//		};
//	
//	// For a pie chart
//	var myPieChart = new Chart(ctx,{
//	    type: 'pie',
//	    data: data//,
////	    options: options
//	});
//	
//	
//	/*var data = {
//		    labels: ["January", "February", "March", "April", "May", "June", "July"],
//		    datasets: [
//		        {
//		            label: "My First dataset",
//		            backgroundColor: "rgba(255,99,132,0.2)",
//		            borderColor: "rgba(255,99,132,1)",
//		            borderWidth: 1,
//		            hoverBackgroundColor: "rgba(255,99,132,0.4)",
//		            hoverBorderColor: "rgba(255,99,132,1)",
//		            data: [65, 59, 80, 81, 56, 55, 40],
//		        }
//		    ]
//		};
//	
//	var myBarChart = new Chart(ctx, {
//	    type: 'bar',
//	    data: data,
//	    options: options
//	});*/
//	
//
//}
function makeScreenshotWithString(stringForContentPDF, pdfON) {

	/*html2canvas(document.getElementById(stringForContentPDF)).then(
			function(canvas) {

				// document.body.appendChild(canvas);

				if (pdfON == "1") {

					var pdfImageURL = canvas.toDataURL("image/jpeg", 1.0);

					var doc = new jsPDF("l", "mm", "a4");

					doc.addImage(pdfImageURL, 'JPEG', 10, 10, 180, 150);

					// doc.output("dataurlnewwindow");

					doc.save('test.pdf');

				} else {

					window.open(pdfImageURL);
				}

			}); */
	
	
	var pdf = new jsPDF('p','pt','a4');

	pdf.addHTML(document.getElementById(stringForContentPDF),function() {
		pdf.save('smartCabinets.pdf');
	});
	
}