/**
 * 
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Olga
 *
 */
public class DateTime implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4483076175562997754L;

	private String zone;
	
	private LocalDateTime time;
	
	public DateTime () {
		
	}
	
	public DateTime (String zone1, LocalDateTime nowTime) {
		
		this.zone = zone1;
		this.time = nowTime;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime now) {
		this.time = now;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		DateTime other = (DateTime) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

}