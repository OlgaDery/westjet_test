/**
 * 
 */
package model;

/**
 * @author Olga
 *
 */
public class AddValues {
	
	private Integer value1;
	
	private Integer value2;
	
	private Integer result;
	
	public AddValues (Integer val1, Integer val2) {
		this.value1 = val1;
		this.value2 = val2;
		this.result = val1 + val2;
	}

	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	

}
