
function getThisMonth(year, month){
    let result = {
        year:0,
        month:0,
        cntOfWeeks: 0,
        firstDayOfWeek: 0,
        lastDay: 0,
        plans: []
    };

    return $.ajax({
        method: 'POST',
        url: '/',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json")
        },
        complete: function() {},
        error: function() {}
    }).then(function(planParam){
        result = planParam;

        for(var i=0;i<result.cntOfWeeks;i++){
            for(var j=0;j<7;j++){
                var day = 7*i+j-result.firstDayOfWeek+1;
                if(day > 0 && day <= result.lastDay){
                    // <td> 안에 날짜 넣기
                    var currentMonthStr = String(result.year)+String(result.month);
                    if(result.plans[day-1][currentMonthStr] != null && result.plans[day-1][currentMonthStr].size > 0){
                        // 일정 [size] 개
                    }
                }
            }
        }
    });
}

// 달력 원소 클릭

function init(){
    //날짜 오늘 날짜로 설정
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    getThisMonth(year,month);
}

$(document).ready(function(){
    init();
});