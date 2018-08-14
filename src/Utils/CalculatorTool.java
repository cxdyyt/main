package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CalculatorTool {
	private String filePath = "";
	private Map<String, Double> result = new HashMap<String, Double>();

	public CalculatorTool(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void cal() throws IOException {
		File file = new File(filePath);
		InputStreamReader ir = new InputStreamReader(new FileInputStream(file));
		BufferedReader bd = new BufferedReader(ir);
		String lin = null;
		double res = 0;
		String key = "";
		int linnum = 0;
		while ((lin = bd.readLine()) != null) {
			linnum++;
			if (lin.startsWith("#")) {
				if(linnum != 1) {
					result.put(key, res);
				}
				key = lin;
				res = 0;
				continue;
			}
			String[] nums = lin.split(",");
			for (String str : nums) {
				if (Utils.StringUtils.isEmpty(str)) {
					continue;
				}
				double num = Double.valueOf(str);
				res += num;
			}
		}
		result.put(key, res);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CalculatorTool tool = new CalculatorTool("C:\\Users\\IBM_ADMIN\\Desktop\\cal.txt");
		tool.cal();
		Map<String, Double> res = tool.getResult();
		Set<String> keys = res.keySet();
		for (String key : keys) {
			System.out.println(key + ": " + res.get(key));
		}
	}

}
