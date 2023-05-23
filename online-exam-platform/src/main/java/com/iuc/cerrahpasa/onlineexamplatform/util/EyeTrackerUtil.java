package com.iuc.cerrahpasa.onlineexamplatform.util;

import java.util.*;

public class EyeTrackerUtil {
    private static volatile Map<String, List<Boolean>> historyOfEyeSlipped = new HashMap<>();

    public synchronized static Boolean isStudentCheatting(String studentId, Boolean isEyeSlipped){
        List<Boolean> eyeSlippedList = historyOfEyeSlipped.get(studentId);

        if(Objects.isNull(eyeSlippedList)){
            eyeSlippedList = new ArrayList<>();
            historyOfEyeSlipped.put(studentId, eyeSlippedList);
        }
        eyeSlippedList.add(isEyeSlipped);

        return manageHistoryOfEyeSlipped(eyeSlippedList);
    }

    private static Boolean manageHistoryOfEyeSlipped(List<Boolean> eyeSlippedList){
        if(!eyeSlippedList.get(eyeSlippedList.size()-1)){
            eyeSlippedList.clear();
            return Boolean.FALSE;
        }
        if(eyeSlippedList.size() == 2){
            eyeSlippedList.clear();
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }
}
