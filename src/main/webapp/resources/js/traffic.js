var myChart = null;
$(function () {
    
    

});

$(document).ready(function() {
    
	$('#startDate').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    
    $('#endDate').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    
	
    $('#equipment').multiselect({
    	nonSelectedText : 'Select Equipment'
    });
    
    $('#region').multiselect({
    	nonSelectedText : 'Select Region'
    });
    
    $('#channel').multiselect({
    	nonSelectedText : 'Select Channel'
    });
    
    $('#equipmentType').multiselect({
    	nonSelectedText : 'Select Equipment Type'
    });
} );

function submitData(){
	$('.se-pre-con').show();
	$.ajax({
		'url':  path+'statistic/traffic',
	    'type': 'POST',
	    'data': $('#traffic').serialize(),
	    'success': function(result){
			 $("#module-statistics").html(result);
			 $('.se-pre-con').fadeOut('slow');
			 $('#table').DataTable( {
			    	 "aaSorting": []
			 } );
			 if($('#table tr').get(1).innerText == 'No data available in table')
				 	$('#fileGenerator').hide();
				 else $('#fileGenerator').show();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function loadGraphics()
{
	var d = $('#graphicData').val();
	var l = $('#label').val();
	if(myChart!=null)
		myChart.destroy();
	
	if(l!=null && l!='' && d!=null && d !='')
	{
		var labels = JSON.parse(l);
		if(labels.length == 0 || labels.length == 1)
		{
			$('.error-row').children().remove()
			$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>There is not enough data to show graphics</p></div></div>');
		}
		else
		{
			
			$('#graphics-panel').show();
			var r = JSON.parse(d);
			var fillColorArray = ["rgba(169,226,243,0.3)","rgba(129,129,247,0.3)","rgba(88,172,250,0.3)","rgba(206,216,246,0.3)","rgba(88,88,250,0.3)","rgba(0,128,255,0.3)","rgba(88,211,247,0.3)"
			                      ,"rgba(129,247,243,0.3)","rgba(206,206,246,0.3)","rgba(28,70,198,0.3)","rgba(71,92,154,0.3)","rgba(2,200,193,0.3)","rgba(120,153,250,0.3)","rgba(63,255,205,0.3)"];
			
			var strokeColorArray = ["rgba(169,226,243,0.8)","rgba(129,129,247,0.8)","rgba(88,172,250,0.8)","rgba(206,216,246,0.8)","rgba(88,88,250,0.8)","rgba(0,128,255,0.8)","rgba(88,211,247,0.8)"
			                      ,"rgba(129,247,243,0.8)","rgba(206,206,246,0.8)","rgba(28,70,198,0.8)","rgba(71,92,154,0.8)","rgba(2,200,193,0.8)","rgba(120,153,250,0.8)","rgba(63,255,205,0.8)"];
			
			var datasetValue = [];
			var color = '';
			for (var j = 0; j < r.length; j++) { 
				color = Math.floor(Math.random()*fillColorArray.length);
				datasetValue[j] = 
			    {
					label: r[j].businessName,
					fillColor:  fillColorArray[color], 
		            strokeColor: strokeColorArray[color],
		            pointColor: strokeColorArray[color],
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(151,187,205,1)",
				    title :r[j].businessName,
				    data : r[j].items,

			    }
			}

		    var lineChartData = {
		            labels: labels,
		            datasets: datasetValue
		    }
		    
		    myChart = new Chart(document.getElementById("canvas000").getContext("2d")).Line(lineChartData, {
		        responsive: true,
		        tooltipFillColor: "rgba(51, 51, 51, 0.55)",
		        multiTooltipTemplate: "<%= value %> - <%= datasetLabel  %>",
		    });
		    document.getElementById('js-legend').innerHTML = myChart.generateLegend();
		  
		    $('#table-panel').hide();
		}
	}
	else
	{
		$('.error-row').children().remove();
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>There is not enough data to show graphics</p></div></div>');
	}
}

function backTable()
{
	$('#graphics-panel').hide();
	$('#table-panel').show();
}