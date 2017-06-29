var myChart = null;
var background = new Array();
var hover = new Array();

$(document).ready(function () {

	
	background.push("#FF6384");
	background.push("#36A2EB");
	background.push("#FFCE56");
	
	hover.push("#FF6384");
	hover.push("#36A2EB");
	hover.push("#FFCE56");
	
	loadGraphicsVoucher();
	
});

function barPerVoucher()
{
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    //options: options
	});
}


function loadLabels(labels){

	var label = new Array();
	if(labels!=null && labels.length>0)
	{
		
		for(var i=0;i<labels.length;i++)
		{
			label.push(labels[i].name);
			//data.push(labels[i].data);
		}
	}
	
	return label;
}

function loadData(labels){

	var data = new Array();
	if(labels!=null && labels.length>0)
	{
		
		for(var i=0;i<labels.length;i++)
		{
//			label.push(labels[i].name);
			data.push(labels[i].data);
		}
	}
	
	return data;
}


function changeActive(counter)
{
	$('.dash_count').removeClass('selected_count');
	$(counter).find('.dash_count').addClass('selected_count');
}

function showVoucher(counter)
{
	$('#voucherRow').show();
	$('#promoRow').hide();
	$('#userRow').hide();
	$('#answerRow').hide();
	loadGraphicsVoucher();
	changeActive(counter);
}


function loadGraphicsVoucher()
{
	
	var labels = JSON.parse($('#voucherById').val());
	var label = loadLabels(labels);
	var data = loadData(labels);
	
	var data = {
		    labels: label,
		    datasets: [
		        {
		            label: "Exchanged",
		            backgroundColor: "rgba(52,152,219,0.2)",
		            borderColor: "rgba(52,152,219,1)",
		            borderWidth: 1,
		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
		            hoverBorderColor: "rgba(52,152,219,1)",
		            data: data,
		        }
		    ]
		};
	
	var ctx = $("#chart_v1");
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	labels = JSON.parse($('#voucherByRetail').val());
	label = loadLabels(labels);
	data = loadData(labels);
	
	data = {
		    labels: label,
		    datasets: [
		        {
		            label: "Exchanged",
		            backgroundColor: "rgba(52,152,219,0.2)",
		            borderColor: "rgba(52,152,219,1)",
		            borderWidth: 1,
		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
		            hoverBorderColor: "rgba(52,152,219,1)",
		            data: data,
		        }
		    ]
		};
	
	ctx = $("#chart_v2");
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	labels = JSON.parse($('#voucherByRange').val());
	label = loadLabels(labels);
	data = loadData(labels);
	
	data = {
		    labels: label,
		    datasets: [
		        {
		            label: "Exchanged",
		            backgroundColor: "rgba(52,152,219,0.2)",
		            borderColor: "rgba(52,152,219,1)",
		            borderWidth: 1,
		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
		            hoverBorderColor: "rgba(52,152,219,1)",
		            data: data,
		        }
		    ]
		};
	
	ctx = $("#chart_v3");
	var myLineChart = new Chart(ctx, {
	    type: 'line',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#voucherByGender').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_v4");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#voucherByStudy').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_v5");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#voucherByBuyer').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_v6");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});


}


function showUser(counter)
{
	$('#voucherRow').hide();
	$('#promoRow').hide();
	$('#answerRow').hide();
	$('#userRow').show();
	loadGraphicsUser();

	changeActive(counter);
}


function loadGraphicsUser()
{
	var labels = JSON.parse($('#userByGender').val());
	var label = loadLabels(labels);
	var data = loadData(labels);
	
	var data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	var ctx = $("#chart_u1");
	var myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#userByStudy').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_u2");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
}


function showPromotion(counter)
{
	$('#voucherRow').hide();
	$('#userRow').hide();
	$('#promoRow').show();
	loadGraphicsPromotion();
	changeActive(counter);
}

function loadGraphicsPromotion()
{
	
	var labels = JSON.parse($('#promotionById').val());
	var label = loadLabels(labels);
	var data = loadData(labels);
	
	var data = {
		    labels: label,
		    datasets: [
		        {
		            label: "Exchanged",
		            backgroundColor: "rgba(52,152,219,0.2)",
		            borderColor: "rgba(52,152,219,1)",
		            borderWidth: 1,
		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
		            hoverBorderColor: "rgba(52,152,219,1)",
		            data: data,
		        }
		    ]
		};
	
	var ctx = $("#chart_p1");
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	labels = JSON.parse($('#promotionByType').val());
	label = loadLabels(labels);
	data = loadData(labels);
	
	data = {
		    labels: label,
		    datasets: [
		        {
		            label: "Exchanged",
		            backgroundColor: "rgba(52,152,219,0.2)",
		            borderColor: "rgba(52,152,219,1)",
		            borderWidth: 1,
		            hoverBackgroundColor: "rgba(52,152,219,0.4)",
		            hoverBorderColor: "rgba(52,152,219,1)",
		            data: data,
		        }
		    ]
		};
	
	ctx = $("#chart_p2");
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	labels = JSON.parse($('#promotionByGender').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_p3");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#promotionByStudy').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_p4");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#promotionByBuyer').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_p5");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});

}

function showAnswer(counter)
{
	$('#voucherRow').hide();
	$('#promoRow').hide();
	$('#userRow').hide();
	$('#answerRow').show();
	loadGraphicsAnswer();
	changeActive(counter);
}

function loadGraphicsAnswer()
{
	
	var labels = JSON.parse($('#answer1').val());
	var label = loadLabels(labels);
	var data = loadData(labels);
	
	var data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	var ctx = $("#chart_a1");
	var myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
	
	labels = JSON.parse($('#answer2').val());
	label = loadLabels(labels);
	data = loadData(labels);

	data = {
		    labels: label,
		    datasets: [
		        {
		            data: data,
		            backgroundColor:background,
		            hoverBackgroundColor:hover
		        }]
		};
	
	ctx = $("#chart_a2");
	myPieChart = new Chart(ctx,{
	    type: 'pie',
	    data: data,
	//    options: options
	});
}