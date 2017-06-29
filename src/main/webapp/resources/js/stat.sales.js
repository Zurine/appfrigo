var table = null;
var colors = ['#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5','#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5','#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5',
	'#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5','#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5','#B2F2B2','#F3F3CA','#BDD8F2','#F3C0F1','#D0A9F5'];
var border =  ['#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6','#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6','#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6',
	'#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6','#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6','#91F391','#F2F2A8','#96C7F1','#EF9EEC','#C490F6'];
var border2=  ['#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5','#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5','#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5',
	'#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5','#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5','#80F280','#F1F191','#86BFF2','#EE8CEB','#BC80F5'];
var type = null;
var myBarChart  = null;

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
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Equipment',
	    dropUp: true
    });
    $('#region').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Region',
	    dropUp: true
    });
    $('#channel').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Channel',
	    dropUp: true
    });
    $('#equipmentType').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Equipment Type',
	    dropUp: true
    });
    $('#product').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Product',
	    dropUp: true
    });
    $('#operator').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Operator',
	    dropUp: true
    });
 
    loadTable();
	 
	$('#fileGenerator').show();
	$('#table_length').hide();
	
	setType();

	
} );

function setType(){
    if(type == null){
    	$('#info-type1').iCheck('check');
    	type = 0;
    }
    $('#info-type1').on('ifChanged', function(event){
    	type = 0;
    	changeData()
	});	
	$('#info-type2').on('ifChanged', function(event){
		type = 1;
		changeData()
	});	
}

function loadTable(){
	$('#table').DataTable( {
   	 aaSorting: [],
   	 bFilter : false,
   	 bInfo : false
   	/* bPaginate: false,
   	 bFilter : false,
   	 bInfo : false*/
   });
//$( ".table-hover tr:even" ).css( "background-color", "#FFE6ED" );
}

function submitData(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'statistic/sales',
	    'type': 'POST',
	    'data': $('#sales').serialize(),
	    'success': function(result){
			 $("#module-statistics" ).html( result );
			 if(myBarChart!=null)
		    	myBarChart.destroy();
			 myBarChart = null;
			 $('#list-statistics').show();
			 $('.se-pre-con').fadeOut('slow');
			 $('#fileGenerator').show();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function getProducts(id){
	$('.se-pre-con').show();
	$('#search-id').val(id);
	
	if($('#plus'+id).hasClass('fa-plus-square')){
		$('#plus'+id).removeClass('fa-plus-square');
		$('#plus'+id).addClass('fa-minus-square');
		
		$.ajax({
		    'url':  path+'statistic/productSales',
		    'type': 'POST',
		    'data': $('#sales').serialize(),
		    'success': function(result){
				 $("#module-products"+id ).html( result );
				 $("#module-products"+id ).show();
				 $('.se-pre-con').fadeOut('slow');
				 
				 $('#module-products'+id).find('table')
				// $('#table-sub'+id)
				 .DataTable( {
			    	 aaSorting: [],
			    	 bPaginate: false,
			    	 bFilter : false,
			    	 bInfo : false
			    });
		    },
		    'error': function(result){
				 $('.se-pre-con').fadeOut('slow');
		    }
		});
	}
	else{
		$('#plus'+id).removeClass('fa-minus-square');
		$('#plus'+id).addClass('fa-plus-square');
		 $("#module-products"+id ).hide();
		 
		 $('.se-pre-con').fadeOut('slow');
	}
	

}

function submitWorkDay(id){
	
	var action = $('#form-path').attr('action') +"?id="+id;
	
	$('#form-path').find('input#startDate').val(	$('#sales').find('input#startDate').val());
	$('#form-path').find('input#endDate').val(	$('#sales').find('input#endDate').val());
	
	$('#form-path').attr('action',action)	
	$('#path').click();
}

function showProducts(id, type){
	
	if(type==8)
	{
		$('#operator').multiselect('select', id);
		submitData();
	}
	else getProducts(id);
}

function showAdvancedSearch(){
	$('.x_panel').show();
	$('#advanced').hide();
}

function getPdf(){
	$.ajax({
	    'url':  path+'statistic/getSalesPDF',
	    'type': 'POST',
	    'data': $('#sales').serialize(),
	    'success': function(result){
	    	$("#file-module" ).html( result );
	    	if($('#url').val()!=''){
	    		window.open($('#url').val(),'_blank');
	    	}

	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function showCharts(){
	if($('#table-data').is(':visible')){
		$('#table-data').hide();
		$('#chart-data').show();
		$('#btn-data').find('span').removeClass('fa-bar-chart');
		$('#btn-data').find('span').addClass('fa-table');
		if(myBarChart == null){
			var ctx = $("#canvas");
			myBarChart = new Chart(ctx, {
			    type: 'bar',
			    data: loadData(),
			    options:{
			    	 legend: {
			             display: false
			         }
			    }
			});
		}
		if ($("input.flat")[0]) {
		    $(document).ready(function () {
		        $('input.flat').iCheck({
		            checkboxClass: 'icheckbox_flat-red',
		            radioClass: 'iradio_flat-red'
		        });
		    });
		}
		setType();

	}
	else{
		 showTable();
	}
}

function showTable(){
	$('#chart-data').hide();
	$('#table-data').show();
	$('#btn-data').find('span').removeClass('fa-table');
	$('#btn-data').find('span').addClass('fa-bar-chart');
}

function changeData(){
	
	if(type!=null && myBarChart!= null){
		myBarChart.data.datasets = [
	        {
	            label: "Sales",
	            backgroundColor: colors,
	            borderColor: border,
	            borderWidth: 2,
	            hoverBackgroundColor: border,
	            hoverBorderColor:border2,
	            data: salesData(),
	        }
	    ];
		myBarChart.update();
	}
}

function salesData(){
	var data = [];
	var sales = JSON.parse($('#sales-data').val());
	if(sales!=null && sales.length>0)
	{
		for(var i=0;i<sales.length;i++)
		{
			if(type == 0)
				data.push(sales[i].total);
			else data.push(sales[i].totalAmount);
		}
	}
	return data;
} 

function loadData(){

	var sales = JSON.parse($('#sales-data').val());
	var data = [];
	var label = [];
	if(sales!=null && sales.length>0)
	{
		for(var i=0;i<sales.length;i++)
		{
			data.push(sales[i].total);
			label.push(sales[i].item.name);
		}
	}
	var result = {
		    labels: label,
		    datasets: [
		        {
		            label: "Sales",
		            backgroundColor: colors,
		            borderColor: border,
		            borderWidth: 2,
		            hoverBackgroundColor: border,
		            hoverBorderColor:border2,
		            data: data,
		        }
		    ]
		};
	
	return result;
}
