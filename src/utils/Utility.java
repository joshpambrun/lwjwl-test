package utils;

public class Utility {

    public static int approach(int start, int end, int ammount){
        if(start < end){
            if(start + ammount > end){
                start = end;
            }else{
                start += ammount;
            }
        }else if(start > end){
            if(start - ammount < end){
                start = end;
            }else{
                start -= ammount;
            }
        }

        return start;
    }

    public static float approach(float start, float end, float ammount){
        if(start < end){
            if(start + ammount > end){
                start = end;
            }else{
                start += ammount;
            }
        }else if(start > end){
            if(start - ammount < end){
                start = end;
            }else{
                start -= ammount;
            }
        }

        return start;
    }
}
