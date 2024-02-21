package com.user;

import com.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class UserService {

    private UserRepository userRepository;
    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response signIn(User user){
        try {
            //검증
            Response response = validationSignInParam(user);
            if (response.getResult() != Result.OK) return response;
            User existUser = (User) userRepository.selectByElement(UserDmlConstant.SELECT_USER_BY_ID, user.getId());
            if (existUser == null) {
                return new Response(Result.INCORRECT, "ID를 잘못 입력하셨습니다.");
            }
            if (user.getId().equals(existUser.getId()) && !user.getPw().equals(existUser.getPw())) {
                return new Response(Result.ONLY_ID_CORRECT, "PW를 잘못 입력하셨습니다.");
            }
            return new Response(Result.OK, "OK");
        }catch (Exception e){
            logger.info("[UserService][signIn] fail signIn");
            return new Response(Result.FAIL,"FAIL");
        }
    }

    private Response validationSignInParam(User user) throws Exception{
        String[] chars = {"/", "=", "!", "'"};
        try {
            //id
            if (user.getId() == null) {
                return new Response(Result.INVALID_PARAM, "ID를 입력해주세요.");
            }
            if (user.getId().getBytes().length <= 0 || user.getId().getBytes().length > 100) {
                return new Response(Result.INVALID_PARAM, "ID byte 길이는 0 초과, 100 이하여야 합니다.");
            }
            if (Validation.isContainChars(user.getId(), chars)) {
                return new Response(Result.INVALID_PARAM, "ID에 특수 문자가 포함됩니다.");
            }
            //pw
            if (user.getPw() == null) {
                return new Response(Result.INVALID_PARAM, "PW를 입력해주세요.");
            }
            user.setPw(encryptUserPw(user));
            if (user.getPw().getBytes().length <= 0 || user.getPw().getBytes().length > 100) {
                return new Response(Result.INVALID_PARAM, "PW byte 길이는 0 초과, 100 이하여야 합니다.");
            }
            return new Response(Result.OK, "OK");
        } catch (NoSuchAlgorithmException e){
            throw new Exception(e);
        }
    }
    public User getUserById(String id){
        return (User)userRepository.selectByElement(UserDmlConstant.SELECT_USER_BY_ID,id);
    }

    public Response signUp(User user){
        try {
            //salt 생성
            user.setSalt(MakeRandomStr.makeRandomPk(7));
            //검증
            Response response = validationSignUpParam(user);
            if (response.getResult() != Result.OK) return response;
            //user 추가
            userRepository.insertElement(UserDmlConstant.INSERT_USER, user);
            return new Response(Result.OK, "OK");
        } catch (Exception e){
            logger.info("[UserService][regUser] fail insert");
            e.printStackTrace();
            return new Response(Result.FAIL,"FAIL");
        }
    }

    private Response validationSignUpParam(User user) throws Exception{
        try {
            String[] chars = {"/", "=", "!", "'"};
            //id
            if (user.getId() == null) {
                return new Response(Result.INVALID_PARAM, "ID를 입력해주세요.");
            }
            if (user.getId().getBytes().length <= 0 || user.getId().getBytes().length > 100) {
                return new Response(Result.INVALID_PARAM, "ID byte길이는 0보다 크고 100 이하여야 합니다.");
            }
            if (Validation.isContainChars(user.getId(), chars)) {
                return new Response(Result.INVALID_PARAM, "ID에 특수 문자가 포함됩니다.");
            }
            // ID 중복 체크
            if(userRepository.selectByElement(UserDmlConstant.SELECT_USER_BY_ID,user.getId())!=null) {
                return new Response(Result.DUPLICATE_ID, "이미 존재하는 ID입니다.");
            }

            //pw
            if (user.getPw() == null) {
                return new Response(Result.INVALID_PARAM, "PW를 입력해주세요.");
            }
            user.setPw(encryptUserPw(user));
            if (user.getPw().getBytes().length <= 0 || user.getPw().getBytes().length > 100) {
                return new Response(Result.INVALID_PARAM, "PW byte길이는 0보다 크고 100 이하여야 합니다.");
            }
            
            //name
            if (user.getName() == null) {
                return new Response(Result.INVALID_PARAM, "이름을 입력해주세요.");
            }
            if (user.getName().getBytes().length <= 0 || user.getName().getBytes().length > 100) {
                return new Response(Result.INVALID_PARAM, "이름은 0보다 크고, 100 이하여야 합니다.");
            }
            if (Validation.isContainChars(user.getName(), chars)) {
                return new Response(Result.INVALID_PARAM, "이름에 특수 문자가 포함됩니다.");
            }
        } catch (NoSuchAlgorithmException e){
            throw new Exception(e);
        }

        return new Response(Result.OK,"OK");
    }

    private String encryptUserPw(User user) throws NoSuchAlgorithmException {
        Cypher cypher = new Cypher();
        String result = cypher.encrypt(user.getPw() + user.getSalt());
        return result;
    }
}
