package org.firstinspires.ftc.teamcode.util;

public class RollingAvg {
	private final double[] history;
	private int i;
	private double sum;

	public RollingAvg(int n) {
		history = new double[n];
	}

	public double update(double x) {
		sum -= history[i];
		history[i] = x;
		sum += x;

		i = (i + 1) % history.length;

		return sum / history.length;
	}
}
