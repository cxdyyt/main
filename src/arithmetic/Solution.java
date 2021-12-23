package arithmetic;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (; i < nums.length && map.get(nums[i]) == null; i++) {
            map.put(target - nums[i], i);
        }
        return new int[] { i, map.get(nums[i]) };
    }
}
