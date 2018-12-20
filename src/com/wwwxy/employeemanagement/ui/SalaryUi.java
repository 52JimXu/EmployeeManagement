package com.wwwxy.employeemanagement.ui;

import com.wwwxy.employeemanagement.control.SalaryControl;

public class SalaryUi {
	public void Salary() {
		SalaryControl sc = new SalaryControl();
		sc.Salary();
	}
	public static void main(String[] args) {
		new SalaryUi().Salary();
	}
}
