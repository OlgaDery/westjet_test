/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Olga
 *
 */
public class AddValues implements Serializable, AddValuesIF {
	
	/**This class is used to calculate 2 values being received from client via Rest recourses.
	 * 
	 */
	private static final long serialVersionUID = 8309660929271483298L;

	

	private Integer value1;
	
	private Integer value2;
	
	private Integer result;
	
	public AddValues (Integer val1, Integer val2) {
		this.value1 = val1;
		this.value2 = val2;
		this.result = val1 + val2;
	}

	/* (non-Javadoc)
	 * @see model.AddValuesIF#getValue1()
	 */
	@Override
	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	/* (non-Javadoc)
	 * @see model.AddValuesIF#getValue2()
	 */
	@Override
	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

	/* (non-Javadoc)
	 * @see model.AddValuesIF#getResult()
	 */
	@Override
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddValues other = (AddValues) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		if (value2 == null) {
			if (other.value2 != null)
				return false;
		} else if (!value2.equals(other.value2))
			return false;
		return true;
	}
	
	

}
