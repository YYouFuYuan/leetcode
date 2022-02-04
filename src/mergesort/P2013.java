package mergesort;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.*;

public class P2013 {

    class DetectSquares {

        //外面的哈希表，key为x坐标，
        //里面的哈希表，key为y坐标，value为出现的个数
        public Map<Integer, Map<Integer,Integer>> map = new HashMap<>();

        public DetectSquares() {

        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            Map<Integer,Integer> map1 = map.getOrDefault(x,new HashMap<>());
            map1.put(y,map1.getOrDefault(y,0) + 1);
            map.put(x,map1);
        }


        public void print(){
            for(Integer key :this.map.keySet()){
                Map<Integer,Integer> innerMap = this.map.get(key);
                System.out.println("KEY:"+ key);
                System.out.println("VALUE and count");
                for(Integer key2 : innerMap.keySet()){
                    System.out.println(key2 + ":" + innerMap.get(key2));
                }
            }
        }

        public int count(int[] point) {
            this.print();
            int x = point[0];
            int y = point[1];
            int ret = 0;
            //先取得与同横坐标
            if(map.containsKey(x)){
                Map<Integer,Integer> sameX = map.get(x); //相同横坐标的点
                //枚举相同横坐标的不同纵坐标
                for(Integer otherY : sameX.keySet()){
                    int w = y - otherY; //边长
                    if(Math.abs(w) > 0){ //边长 》 0
                        //(x+w,y) (x+w,otherY)
                        if(map.containsKey(x + w)){
                            ret += (sameX.get(otherY) * map.get(x + w).getOrDefault(otherY,0) * map.get(x+w).getOrDefault(y,0));
                        }
                        //(x-w,y) (x-w,otherY)
                        if(map.containsKey(x - w)){
                            ret += (sameX.get(otherY) * map.get(x - w).getOrDefault(otherY,0) * map.get(x-w).getOrDefault(y,0));

                        }
                    }

                }
            }
            return ret;

        }
    }

    public static void main(String[] args) {
        String[] cmd =  {"DetectSquares","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count","add","add","add","count"};
    }
}


