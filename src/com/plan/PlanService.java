package com.plan;

import com.util.PlanDmlConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlanService {

    private PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Map<LocalDate,List<Plan>>> getMonthPlan(String year, String month){
        LocalDate pivot = LocalDate.parse(year+month+"01",DateTimeFormatter.ofPattern("yyyyMMdd").withLocale(Locale.KOREA));
        List<Map<LocalDate, List<Plan>>> result = new ArrayList<>();

        for(int i =1;i<=pivot.lengthOfMonth();i++){
            Plan plan = new Plan();
            Map<LocalDate, List<Plan>> day = new HashMap<>();
            LocalDate tmpDate = pivot.withDayOfMonth(i);

            List<Plan> plans = (List<Plan>)(Object)(planRepository.selectByElement(PlanDmlConstant.SELECT_PLAN_BY_DAY,tmpDate.toString()));
            day.put(tmpDate,plans);

            result.add(day);
        }
        return result;
    }


}
