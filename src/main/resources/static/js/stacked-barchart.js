

function barCallRestBackend(){

//google.charts.load('current', {'packages':['bar']});
//console.log('I got here!!!! ==> barCallRestBackend');
$.ajax({

        type : 'GET',
        headers : {
            Accept : "application/json; charset=utf-8",
            "Content-Type" : "application/json; charset=utf-8"
        },
        url : '/come-in-index',
        success : function(result) {
            console.log('I got here!!!! ==> barCallRestBackend');
            google.charts.load('current', {
                'packages' : ['corechart']
            });
            google.charts.setOnLoadCallback(function() {
                drawChart(result);
                console.log('I got here!!!! ==> setOnLoadCallback');
            });
        }
    });
}

function drawChart(result){
    console.log('I got here!!!! ==> drawChart');
	// chart colors
	//let colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#ff0000'];

	//console.log('Labels:'+result['labels']);
	//console.log('value-1: '+result['line-1']);
	
	/* large line chart */
	let chLine = document.getElementById("chLine");
	//let chLine2 = document.getElementById("chLine2");
	var data = google.visualization.arrayToDataTable(result);

	var options = {
            width: 800,
            height: 600,
            legend: { position: 'top', maxLines: 3 },
            bar: { groupWidth: '75%' },
            isStacked: true
          };

    var chart = new google.visualization.BarChart(document.getElementById('bar_div'));
    chart.draw(data, options);

    console.log('I got here!!!! ==> the end');

}