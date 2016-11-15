package com.enumeration;

public enum IndexTimeType {
	YEAR {
		@Override
		public String getIndexTime(String time) {
			return time.substring(0, 4);
		}
	},
	MONTH {
		@Override
		public String getIndexTime(String time) {
			return time.substring(0, 4) + time.substring(5, 7);
		}
	},
	DAY {
		@Override
		public String getIndexTime(String time) {
			return time.substring(0, 4) + time.substring(5, 7) + time.substring(8, 10);
		}
	};
	
	public abstract String getIndexTime(String time);
	
	public static void main(String[] args) {
//		IndexTimeType year = IndexTimeType.valueOf("YEAR");
		IndexTimeType month = IndexTimeType.valueOf("MONTH");
		//IndexTimeType day = IndexTimeType.valueOf("DAY");
		System.out.println(month.getIndexTime("2016-10-26T09:18:21.779Z"));
	}
}
