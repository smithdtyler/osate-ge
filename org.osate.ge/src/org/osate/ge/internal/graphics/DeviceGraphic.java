package org.osate.ge.internal.graphics;

public class DeviceGraphic implements AgeShape {
	public final int lineWidth;

	public DeviceGraphic(final int lineWidth) {
		this.lineWidth = lineWidth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineWidth;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DeviceGraphic other = (DeviceGraphic) obj;
		if (lineWidth != other.lineWidth) {
			return false;
		}
		return true;
	}
}
