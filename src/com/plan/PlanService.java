package com.plan;

import com.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlanService {

    private PlanRepository planRepository;
    static final Logger logger = LoggerFactory.getLogger(PlanService.class);

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

    public Response regPlan(Plan plan){
        Response result = validationRegParam(plan);
        if(result.getResult()==Result.INVALID_PARAM) return result;
        planRepository.insertElement(PlanDmlConstant.INSERT_PLAN,plan);
        return result;
    }

    private Response validationRegParam(Plan plan){
        //제목
        String[] chars = {"/","=","!","'"};
        if(plan.getTitle()==null ){
            return new Response(Result.INVALID_PARAM,"제목을 입력해주세요.");
        }
        if(plan.getTitle().getBytes().length<=0 || plan.getTitle().getBytes().length>100){
            return new Response(Result.INVALID_PARAM, "제목 byte길이는 0보다 크고 100 이하여야 합니다.");
        }
        if(Validation.isContainChars(plan.getTitle(),chars)){
            return new Response(Result.INVALID_PARAM,"제목에 특수 문자가 포함됩니다.");
        }
        //내용
        if(plan.getText()==null){
            return new Response(Result.INVALID_PARAM,"내용을 입력해주세요.");
        }
        if(plan.getText().getBytes().length<=0 || plan.getText().getBytes().length>30000){
            return new Response(Result.INVALID_PARAM,"내용 byte길이는 0보다 크고 100 이하여야 합니다.");
        }
        if(Validation.isContainChars(plan.getText(),chars)){
            return new Response(Result.INVALID_PARAM,"내용에 특수 문자가 포함됩니다.");
        }
        //날짜
        if(plan.getStartDt()==null || plan.getEndDt()==null){
            return new Response(Result.INVALID_PARAM,"일정 시작 날짜와 끝나는 날짜를 입력해주세요.");
        }
        if(plan.getStartDt().isAfter(plan.getEndDt())){
            return new Response(Result.INVALID_PARAM,"일정 시작 날짜는 끝나는 날짜보다 앞서야 합니다.");
        }

        return new Response(Result.OK,"OK");
    }

    public Response updatePlan(Plan plan, String key){
        Response result = validationUpdateParam(plan);
        if(result.getResult()==Result.INVALID_PARAM) return result;
        planRepository.updateElement(PlanDmlConstant.UPDATE_PLAN_BY_ID,plan,key);
        return result;
    }

    private Response validationUpdateParam(Plan plan){
        //제목
        String[] chars = {"/","=","!","'"};
        if(plan.getTitle()==null ){
            return new Response(Result.INVALID_PARAM,"제목을 입력해주세요.");
        }
        if(plan.getTitle().getBytes().length<=0 || plan.getTitle().getBytes().length>100){
            return new Response(Result.INVALID_PARAM, "제목 byte길이는 0보다 크고 100 이하여야 합니다.");
        }
        if(Validation.isContainChars(plan.getTitle(),chars)){
            return new Response(Result.INVALID_PARAM,"제목에 특수 문자가 포함됩니다.");
        }
        //내용
        if(plan.getText()==null){
            return new Response(Result.INVALID_PARAM,"내용을 입력해주세요.");
        }
        if(plan.getText().getBytes().length<=0 || plan.getText().getBytes().length>30000){
            return new Response(Result.INVALID_PARAM,"내용 byte길이는 0보다 크고 100 이하여야 합니다.");
        }
        if(Validation.isContainChars(plan.getText(),chars)){
            return new Response(Result.INVALID_PARAM,"내용에 특수 문자가 포함됩니다.");
        }
        //날짜
        if(plan.getStartDt()==null || plan.getEndDt()==null){
            return new Response(Result.INVALID_PARAM,"일정 시작 날짜와 끝나는 날짜를 입력해주세요.");
        }
        if(plan.getStartDt().isAfter(plan.getEndDt())){
            return new Response(Result.INVALID_PARAM,"일정 시작 날짜는 끝나는 날짜보다 앞서야 합니다.");
        }

        return new Response(Result.OK,"OK");
    }

    public void deletePlan(String key){
        planRepository.deleteElement(PlanDmlConstant.DELETE_PLAN_BY_ID,key);
    }

    public String makePlanKey() throws Exception{
        try {
            Cypher cypher = new Cypher();
            String planKey = "";

            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String seed = MakeRandomStr.makeRandomPk(7);

            return cypher.encrypt(today + seed);
        } catch (NoSuchAlgorithmException e){
            logger.info("[PlanService][makePlanKey] 일정 Key 생성 중 예외 발생\n {}",e);
            throw new Exception(e);
        }
    }
}
