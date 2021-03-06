$(document).ready(function() {
	$('#searchButton').on('click', function() {
		search();
	});
	
	drawChart1();
	drawChart2();
});

function drawChart1() {
	<!-- Bar cahrt -->
	var ctx1 = document.getElementById("myBarChart"); 
	var myBarChart = new Chart(ctx1 , {
	    type: 'bar',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: '푼 문제수',
	            data: dataForBar,
	            borderColor: "rgba(255, 201, 14, 1)",
	            backgroundColor: "rgba(255, 201, 14, 0.5)",
	            fill: false,
	        }]
	    },
	    options: {
	        responsive: true,
	        hover: {
	            mode: 'nearest',
	            intersect: true
	        },
	        scales: {
	            xAxes: [{
	                display: true,
	                scaleLabel: {
	                    display: true,
	                },
	                ticks: {
	                    autoSkip: false,
	                    maxTicksLimit:4
	                }
	            }],
	            yAxes: [{
	                display: true,
	                ticks: {
	                    suggestedMin: 0,
	                    stepSize: 1,
	                },
	                scaleLabel: {
	                    display: true,
	                }
	            }]
	        }
	    }
	});
}

function drawChart2() {
	<!-- Doughnut cahrt -->		
	var ctx = document.getElementById("myDoughnutChart"); 
	var myDoughnutChart = new Chart(ctx, { 
	    type: 'doughnut', 
	    data: {
	    	labels: ['총 문제수','푼 문제수'],
	        datasets: [{
	            data: dataForDoughnut,
	            backgroundColor: ['#e8e8e8','rgba(255, 201, 14, 0.5)'],
	        }]
	    },
	    plugins: [{
	    	beforeDraw: function(chart) {
			    var width = chart.chart.width,
			        height = chart.chart.height,
			        ctx = chart.chart.ctx;

			    ctx.restore();
			    var fontSize = (height / 210).toFixed(2);
			    ctx.font = fontSize + "em sans-serif";
			    ctx.textBaseline = "middle";

			    var text =  uP+"문제"+" / "+gN+"문제",
			        textX = Math.round((width - ctx.measureText(text).width) / 2),
			        textY = height / 1.7;

			    ctx.fillText(text, textX, textY);
			    ctx.save();
			  }
	   }],
	    options: {
	       responsive: false,
	       legend: {
	         display: true
	       },
	       cutoutPercentage: 65
	    },
	 });
}

function printAllContent(id){
	$('#site').html($(id+' .pSite').html());
	$('#problemName').html($(id+' .pTitle').html());
	$('#memo').html($(id+' .pMemo').html());
	$('#regdate').html($(id+' .pRegdate').html());
	$('#difficulty').html($(id+' .pDifficulty').html());
	
	$('#UuserProblemID').html(id.substring(8, id.length));
	$('#Usite').html($(id+' .pSite').html());
	$('#UproblemName').html($(id+' .pTitle').html());
	$('#Umemo').html($(id+' .pMemo').html());
	$('#Uregdate').html($(id+' .pRegdate').html());
	
	var d = jQuery($(id+' .readDifficulty').html()).attr("alt");
	$("#ud"+d).attr('checked', 'checked');
	
	rudModel("#readSolvedProblem", "#updateSolvedProblem", "문제 상세보기", "문제 수정하기", updateAjax, deleteAjax, search);
}

function callModal() {
	selectHtml = $('#selectHtml').html();
	
	createModel("#createProblem", "푼 문제 등록", addajax, search);
}

function addajax(){
	var siteId = [];
	var problem = [];
	var link = [];
	
	$('.sweet-modal-content .problem').each(function(){
		var s_id = 0;
		var l = "";
		var p;
		
		var valueSplit = $(this).val().split(' (');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0].trim();
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1].trim();
			console.log("problem: "+split[split.length-1].trim());

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0].trim();
			len = $(this).val().split(' - ');
			if(len.length != 0)
				l = len[len.length-1].trim();
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
	
   $.ajax({
        url : '../createProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link
        },
        success: function(data){
        	resetContent();
        	console.log("success");
        },
        error:function(request,status,error){
        	resetContent();
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
}

function search(){
	$.ajax({
		url: "./problems/search",
		type: "POST",
		async: false,
		data: {
			searchValue:$('#searchValue').val()
		},
		success: function(data){
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function updateAjax(){
	var difficulty_cnt = document.getElementsByName("difficulty").length;
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("difficulty")[i].checked == true)
			var difficulty = document.getElementsByName("difficulty")[i].value;
	}
	
	$.ajax({
		url: "problems/update",
		type: "POST",
		async: false,
		data: {
			id:$('.sweet-modal-content #UuserProblemID').html(),
			difficulty:difficulty,
			memo: $('.sweet-modal-content #Umemo').val()
		},
		success: function(data){
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function deleteAjax (){
	$.ajax({
		url: "./problems/delete",
		type: "POST",
		async: false,
		data: {
			id:$('#UuserProblemID').html()
		},
		success: function(data){
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
}

var count=0;
function insertProblems(){
	var siteName = $(".sweet-modal-content #siteName option:selected").text();
	var siteId = $('.sweet-modal-content #siteName').val();
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #problems").val();
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #confirmSite').html();
	
	$(".sweet-modal-content #problems").val("");
	if(siteId == 1){
		$.ajax({
        url : '../crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            console.log(data);
            var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	}else{
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><i class="small smaller material-icons" style="color:green;">done</i><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/>';
			count++;
		}
		$('.sweet-modal-content #confirmSite').html(data);
		$('#confirmSite').html(data);
	}
};

function resetContent() {
	$('#createProblem #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	
}