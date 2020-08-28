

function pieCallRestBackend(){

//google.charts.load('current', {'packages':['bar']});
//console.log('I got here!!!! ==> barCallRestBackend');
$.ajax({

        type : 'GET',
        headers : {
            Accept : "application/json; charset=utf-8",
            "Content-Type" : "application/json; charset=utf-8"
        },
        url : '/workers-in-department',
        success : function(result) {
            console.log('I got here!!!! ==> pieCallRestBackend');
            google.charts.load('current', {
                'packages' : ['corechart']
            });
            google.charts.setOnLoadCallback(function() {
                drawPieChart(result);
                console.log('I got here!!!! ==> pieSetOnLoadCallback');
            });
        }
    });
}

function drawPieChart(result){
    console.log('I got here!!!! ==> drawPieChart');

	var data = google.visualization.arrayToDataTable(result);

            var options = {
               width: 800,
               height: 600,
              title: 'Workers in Departments',
              is3D: true
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_div'));

            chart.draw(data, options);


    console.log('I got here!!!! ==> Pie the end');

}