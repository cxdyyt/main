package Array;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class ArrayUtils {

	public void moveZeroes(int[] nums) {
		int len = nums.length;
		int end = len - 1;
		for (int i = len - 1; i >= 0; i--) {
			int cur = nums[i];
			if (cur == 0) {
				moveFirstNumberToEnd(nums, i, --len);
			}
		}
	}

	public void moveFirstNumberToEnd(int[] nums, int start, int end) {
		int first = nums[start];
		for (int i = start; i < end; i++) {
			nums[i] = nums[i + 1];
		}
		nums[end] = first;
	}

	public int maxProfit(int[] prices) {
		int len = prices.length;
		int maxProfit = 0;
		int preMax = 0;
		for (int i = len - 1; i >= 0; i--) {
			int currv = prices[i];
			if (currv < preMax) {
				int profit = preMax - currv;
				maxProfit = Math.max(maxProfit, profit);
			} else {
				preMax = currv;
			}
		}
		return maxProfit;
	}

	public int maxProfit2(int[] prices) {
		int len = prices.length;
		int maxProfit = 0;
		int current = 0;
		for (int i = 1; i < len; i++) {
			current = prices[i];
			int pre = prices[i - 1];
			if (current > pre) {
				maxProfit += (current - pre);
			}
		}
		return maxProfit;
	}

	public int[] maxProfit3(int[] prices, int startDay, int endDay) {
		int[] result = new int[3];
		int len = prices.length;
		int maxProfit = 0;
		int preMax = 0;
		for (int i = endDay; i >= startDay; i--) {
			int currv = prices[i];
			if (currv < preMax) {
				int profit = preMax - currv;
				if (profit > maxProfit) {
					result[1] = i;
					maxProfit = profit;
				}
			} else {
				preMax = currv;
				result[2] = i;
			}
		}
		result[0] = maxProfit;
		return result;
	}

	public int maxProfit3(int[] prices) {
		int[] result1 = maxProfit3(prices, 0, prices.length - 1);
		int[] result2 = maxProfit3(prices, 0, result1[0]);
		int[] result3 = maxProfit3(prices, result1[1], prices.length);
		int result = result1[0] + Math.max(result2[0], result3[0]);
		return result;
	}

	public int removeElement(int[] nums, int val) {
		int lastPosition = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[lastPosition++] = nums[i];
			}
		}
		return lastPosition;
	}

	public int calculate(String s) {
		LinkedList<Character> symbolQuen = new LinkedList<Character>();
		LinkedList<Integer> outstr = new LinkedList<Integer>();
		StringBuilder num = new StringBuilder("");
		for (int i = 0; i < s.length() - 1; i++) {
			Integer tempNum = null;
			char ch = s.charAt(i);
			if (ch != '+' && ch != '-' && ch != '(' && ch != ')') {
				if (ch != ' ') {
					num.append(ch);
				}
			} else {
				if (!"".equals(num.toString())) {
					tempNum = Integer.valueOf(num.toString());
					outstr.push(tempNum);
					num = new StringBuilder("");
				}
				if (ch == '(') {
					symbolQuen.push(ch);
				} else if (ch == '+' || ch == '-') {
					Character topch = symbolQuen.peek();
					if (topch != null) {
						if (topch != '(') {
							cal(outstr, symbolQuen.pop());
							symbolQuen.push(ch);
						} else {
							symbolQuen.push(ch);
						}
					} else {
						symbolQuen.push(ch);
					}
				} else if (ch == ')') {
					char tmpchar = 0;
					while (!symbolQuen.isEmpty()) {
						tmpchar = symbolQuen.pop();
						if (tmpchar != '(') {
							cal(outstr, tmpchar);
						} else {
							break;
						}
					}
				}
			}
		}

		char ch = s.charAt(s.length() - 1);
		if (ch >= '0' && ch <= '9' || ch == ' ') {
			if(ch != ' ') {
				num.append(ch);
			}
			if(!"".equals(num.toString())) {
				int tempNum = Integer.valueOf(num.toString());
				outstr.push(tempNum);
			}
			if (!symbolQuen.isEmpty()) {
				cal(outstr, symbolQuen.pop());
			}
		} else if (ch == ')') {
			char tmpchar = 0;
			if(!"".equals(num.toString())) {
				int tempNum = Integer.valueOf(num.toString());
				outstr.push(tempNum);
			}
			while (!symbolQuen.isEmpty()) {
				tmpchar = symbolQuen.pop();
				if (tmpchar != '(') {
					cal(outstr, tmpchar);
				}
			}
		}
		return outstr.pop();
	}

	public void cal(LinkedList<Integer> outstr, char ch) {
		if (ch == '+') {
			Integer sm1 = outstr.pop();
			Integer sm2 = outstr.pop();
			outstr.push(sm2 + sm1);
		} else if (ch == '-') {
			Integer sm1 = outstr.pop();
			Integer sm2 = outstr.pop();
			outstr.push(sm2 - sm1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayUtils aus = new ArrayUtils();
		int[] nums = new int[] { 0, 0, 1 };
		// aus.moveZeroes(nums );
		// aus.moveFirstNumberToEnd(nums, 0, 3);
		int[] prices = new int[] { 5, 8, 9, 2, 5 };
		// aus.maxProfit2(prices);
		// int[] result = aus.maxProfit3(prices,0,4);
		// System.out.println(result);
		System.out.println(aus.calculate("   (  3 ) "));
	}

}
