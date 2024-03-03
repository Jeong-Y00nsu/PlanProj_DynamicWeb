<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- TUI Calender -->
    <link rel="stylesheet" type="text/css" href="/WEB-INF/lib/@toast-ui/calendar/dist/toastui-calendar.css" />
    <!-- If you use the default popups, use this. -->
    <link rel="stylesheet" type="text/css" href="/WEB-INF/lib/@toast-ui/calendar/dist/tui-date-picker.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/calendar/tui-calendar/dist/tui-time-picker.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/calendar/tui-calendar/dist/tui-time-picker.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/calendar/tui-calendar/examples/css/icons.css" />
    <!-- TUI JS -->
    <script src="/resources/js/calendar/tui-calendar/dist/tui-code-snippet.js"></script>
    <script src="/resources/js/calendar/tui-calendar/dist/tui-dom.js"></script>
    <script src="/resources/js/calendar/tui-calendar/dist/tui-time-picker.min.js"></script>
    <script src="/resources/js/calendar/tui-calendar/dist/tui-date-picker.min.js"></script>
    <script src="/resources/js/calendar/tui-calendar/dist/tui-calendar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
</head>
<body>
    <div id="menu">
      <span class="dropdown" style="display:none;">
          <span id="dropdownMenu-calendarType" class="btn btn-default btn-sm dropdown-toggle" type="label" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="true" disabled>
              <i id="calendarTypeIcon" class="calendar-icon ic_view_month" style="margin-right: 4px;"></i>
              <span id="calendarTypeName">Monthly</span>&nbsp;
              <i class="calendar-icon tui-full-calendar-dropdown-arrow"></i>
          </span>
          <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu-calendarType"></ul>
      </span>
        <span id="renderRange" class="render-range"></span>
        <span id="menu-navi">
            <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Today</button>
            <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
                <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
            </button>
            <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
                <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
            </button>
        </span>
    </div>
    <div id="calendar"></div>
    <script type="text/javascript">
        var MONTHLY_CUSTOM_THEME = {
            // month header 'dayname'
            'month.dayname.height': '42px',
            'month.dayname.borderLeft': 'none',
            'month.dayname.paddingLeft': '8px',
            'month.dayname.paddingRight': '0',
            'month.dayname.fontSize': '16px',
            'month.dayname.backgroundColor': 'inherit',
            'month.dayname.fontWeight': 'normal',
            'month.dayname.textAlign': 'left',
            'month.dayname.borderBottom': '1px solid #e5e5e5',

            // month day grid cell 'day'
            'month.holidayExceptThisMonth.color': '#f3acac',
            'month.dayExceptThisMonth.color': '#bbb',
            'month.weekend.backgroundColor': '#ffba6b2b',
            'month.day.fontSize': '16px',

            // month schedule style
            'month.schedule.borderRadius': '5px',
            'month.schedule.height': '18px',
            'month.schedule.marginTop': '2px',
            'month.schedule.marginLeft': '10px',
            'month.schedule.marginRight': '10px',

            // month more view
            'month.moreView.boxShadow': 'none',
            'month.moreView.paddingBottom': '0',
            'month.moreView.border': '1px solid #9a935a',
            'month.moreView.backgroundColor': '#f9f3c6',
            'month.moreViewTitle.height': '28px',
            'month.moreViewTitle.marginBottom': '0',
            'month.moreViewTitle.backgroundColor': '#f4f4f4',
            'month.moreViewTitle.borderBottom': '1px solid #ddd',
            'month.moreViewTitle.padding': '0 10px',
            'month.moreViewList.padding': '10px'
        };

        cal = new Calendar('#calendar', {
            defaultView: 'month',
            useCreationPopup: false,
            useDetailPopup: false,
            // calendars: CalendarList,
            theme: MONTHLY_CUSTOM_THEME,
            timezones: {
                timezoneOffset: 540,
                // displayLabel: 'GMT+09:00',
                tooltip: 'Seoul'
            },
            calendars: [
                {
                    id: '1',
                    name: '개인',
                    color: '#ffffff',
                    bgColor: '#9e5fff',
                    dragBgColor: '#9e5fff',
                    borderColor: '#9e5fff'
                },
                {
                    id: '2',
                    name: '회사',
                    color: '#00a9ff',
                    bgColor: '#00a9ff',
                    dragBgColor: '#00a9ff',
                    borderColor: '#00a9ff'
                },
                {
                    id: '3',
                    name: '휴일',
                    color: '#ff5583',
                    bgColor: '#ff5583',
                    dragBgColor: '#ff5583',
                    borderColor: '#ff5583'
                }
            ]

        });

        // Ajax 데이터 호출, 콜백함수에 로직 추가
        function callScheduleAjax(data){
            createList(data);
        };
        // 라이브러리 스케줄 Set 함수
        function setSchedules() {
            cal.clear();
            //generateSchedule(cal.getViewName(), cal.getDateRangeStart(), cal.getDateRangeEnd());

            cal.createSchedules(ScheduleList);
        }

        function createList(data){
            var calendar = cal || findCalendar('1');
            var schedule = new Array;
            var newtemp = new ScheduleInfo();
            var temp = {
                id : undefined,
                calendarId : undefined,
                title : undefined,
                isAllDay : undefined,
                end : undefined,
                start : undefined,
                category : undefined,
                dueDateClass : undefined,
                color : undefined,
                bgColor : undefined,
                dragBgColor : undefined,
                borderColor : undefined,
                UserCode: undefined,
                UserName: undefined
            }

            if(data != null){
                for(var i in data){
                    temp.id = String(chance.guid());
                    temp.calendarId = data[i].Seq;
                    temp.UserCode = data[i].UserCode;
                    temp.UserName = data[i].UserName;
                    temp.title = data[i].title;
                    temp.isAllDay = 'allday';
                    temp.start = data[i].start;
                    temp.end = data[i].end;
                    temp.category = 'allday';
                    temp.dueDateClass = '';
                    temp.color = "#ffffff";
                    temp.bgColor = "#726ff5";
                    temp.dragBgColor = "#726ff5";
                    temp.borderColor = "#726ff5";

                    schedule.push(temp);

                    temp = {
                        id : undefined,
                        calendarId : undefined,
                        title : undefined,
                        isAllDay : undefined,
                        end : undefined,
                        start : undefined,
                        category : undefined,
                        dueDateClass : undefined,
                        color : undefined,
                        bgColor : undefined,
                        dragBgColor : undefined,
                        borderColor : undefined,
                        UserCode: undefined,
                        UserName: undefined
                    }
                }
            }
            ScheduleList = schedule;
            console.log(ScheduleList);
            cal.createSchedules(schedule);
        }
    </script>
</body>
</html>
