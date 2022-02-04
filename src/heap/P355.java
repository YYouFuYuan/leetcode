package heap;

import java.util.*;

public class P355 {

   static class Twitter {

        public Map<String,Integer> followMap = new HashMap<>(); //存储谁和谁是伙伴关系
        public List<int[] > tweetList = new ArrayList<>(); //存储推文 a[0]:userid,a[1]:tweetId

        public Twitter() {

        }

        public void postTweet(int userId, int tweetId) {
            tweetList.add(new int[]{userId,tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> result = new ArrayList<>();
            for(int i = 0; i < tweetList.size();i++){
                int[] tweet = tweetList.get(i);
                if(tweet[0] == userId || followMap.containsKey(userId + "->" + tweet[0])){
                    result.add(tweet[1]);
                }
            }
            return result;
        }

        public void follow(int followerId, int followeeId) {
            followMap.put(followerId + "->" + followeeId,1);
        }

        public void unfollow(int followerId, int followeeId) {
            String str = followerId + "->" + followeeId;
            if(followMap.containsKey(str)){
                followMap.remove(str);
            }
        }
    }
}
