import java.util.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.LocalDate;



public class pair_programming {
						
				static void Print_Command() {
						  System.out.println("請輸入指令號碼或E（結束使用？");
						  System.out.println("輸入指令：");
						  System.out.println("1）A 顯示該月月曆");
						  System.out.println("2）B 西元轉換干支 、 生肖");
						  System.out.println("3）C 計算天數");
						  System.out.println("4）D 計算日期");
						  System.out.println("5）E 結束使用");
				}
					 
					 
						
				static void Get_Input(char Command_line) {
						  
						  if(Command_line == 'A') {
							  Display_month_calendar();
				//			  System.out.println("This is A");
							}
							else if(Command_line == 'B') {
								Convert_western_to_zodiac();
				//				System.out.println("This is B \n");
							}
							else if(Command_line == 'C') {
								CalculateDays();
				//				System.out.println("This is C \n");
							}
							else if(Command_line == 'D') {
								CalculateDateNegative();
				//				System.out.println("This is D \n");
							}
							else if(Command_line == 'E') {
								System.out.println("結束了 \n");
								System.exit(0);
							}
							else if(Command_line == 'F') {
								 CalculateDatePositive();
							}
							else {
								System.out.println("輸入格式錯誤! \n");
							}
				}
					  
					  
				static void Display_month_calendar() {
							 //Declare the Variable
							 String Year = "";
							 String Month ="";
							 String Day = "";
							 String Final_Date = "";
							 
							 // Scan the new Input
							 System.out.println("請輸入欲查詢日期(年/月/日): ");
							 Scanner reader = new Scanner(System.in);
							 String date = reader.nextLine();
							 
							 
							 Year = get_Year(date);
							 Month = get_Month(date);
							 Day = get_Day(Month, date);
							 
							 if((Year == null) || (Month == null) || (Day == null)) {
								 System.out.println("輸入格式錯誤!\n");
							 }
							 else {
								 Final_Date = ( Year + '-' + Month + '-' + Day);		 
							     System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
				
				 			        //printMonthBody(Integer.parseInt(Year), Integer.parseInt(Month));
							    	// Get start day of the week for the first date in the month
							    	int startDay = getStartDay(Integer.parseInt(Year), Integer.parseInt(Month));
							    	// Get number of days in the month
							    	int numberOfDaysInMonth = getNumberOfDaysInMonth(Integer.parseInt(Year), Integer.parseInt(Month));
							    	// Pad space before the first day of the month
							    	int i = 0;
							    	for (i = 0; i < startDay; i++)
							    	  System.out.print("    ");
							  
									for (i = 1; i <= numberOfDaysInMonth; i++) {
									  if (i < 10)
									    System.out.print("   " + i);
									  else
									    System.out.print("  " + i);
									
									  if ((i + startDay) % 7 == 0)
									    System.out.println();
									}
									System.out.println();
							 }
				}
				
				static String get_Year(String date) {
								 int iter;
								 int Year = 0;
								 String Temporary_variable = "";
								 
								 int index = Count_slash_in_string_and_return_index_for_day(date);
									if(index <= 0) {
										return null;
									}
								 
								 for (iter = 0; ( (iter < 4) && (is_digit(date.charAt(iter)))); iter++) {
									 Temporary_variable += date.charAt(iter);
								 }
								 // if the year is "" means the beginning of the string is a character thus, its incorrect
								 if(Temporary_variable == "") {
									 return null;
								 }
								 else {
									 // convert the year into integer
									 Year = Integer.parseInt(Temporary_variable); 
									 //The Year cannot be 0
									 if(Year <= 0) {
										 return null;
									 }
								 }
								 return Temporary_variable;
				}
				
				
				static String get_Month(String date) {
								 int Month=0;
								 int iter;
								 int temp_iter;
								 String Temporary_variable = "";
								 
								 int index = Count_slash_in_string_and_return_index_for_day(date);
									if(index <= 0) {
										return null;
									}
								 
								 // Here we check where to start from the string, since we are looking for month now
								 for (temp_iter = 0; temp_iter < date.length(); temp_iter++){
									 if( date.charAt(temp_iter) == '/') {
										 temp_iter +=1;
										 break;
									 }
								 } 
								 
								for (iter = temp_iter; ( (iter < temp_iter+2) && (is_digit(date.charAt(iter)))); iter++) {
										 Temporary_variable += date.charAt(iter);
								 }
								
								
								// Here it Means either the string did not have month 
								// or at the month place it was some other character
								if(Temporary_variable == "") {
									 return null;
								 }
								else {
									 // convert the year into integer
									 Month = Integer.parseInt(Temporary_variable); 
									 //The Year cannot be 0
									 if((Month <= 0) || (Month > 12)) {
										 return null;
									 }
								 }
								 return Temporary_variable;
				}	
				
				static String get_Day(String Month, String date) {
								int Day= 0;
								int Mth = 0;
								String Temporary_variable = "";
								int index = Count_slash_in_string_and_return_index_for_day(date);
								if(index <= 0) {
									return null;
								}
								for(int iter=index; (iter < date.length() && is_digit(date.charAt(iter))); iter++) {
									Temporary_variable += date.charAt(iter);
								}
								
								if(Temporary_variable == "") {
									 return null;
								 }
									 // convert the year into integer
								 Day = Integer.parseInt(Temporary_variable); 
								 Mth = Integer.parseInt(Month);
									 //The Year cannot be 0
								 if((Day <= 0) || (Day > 31)) {
									 return null;
								 }
								 else if( (Mth == 2) && (Day > 28) ) {
										 return null;
								 }
								 else if(  (Mth == 4 ) || (Mth == 6 ) || (Mth == 9 ) || (Mth == 11 ) ) {
									 if (Day > 30) {
										 return null;
									 }
								 }	 
								 return Temporary_variable; 
				}
				
					 
				static boolean is_digit(char c) {
								 if((c == '0') || (c == '1') || (c == '2') || (c == '3') || (c == '4') || (c == '5') || (c == '6') || (c == '7') || (c == '8') || (c == '9')) {       
									 return true;
								 }
								 return false;
				}
					 
					 
				static int Count_slash_in_string_and_return_index_for_day(String date) {
								 int count=0;
								 int index = 0;
								 for (int temp_iter = 0; temp_iter < date.length(); temp_iter++){
									 if( date.charAt(temp_iter) == '/') {
										 count++;
										 if(count == 2) {
											 index = temp_iter+1;
										 }
									 }
								 } 
								 if(count == 2) {
									 return index;
								 }
								 return 0;
				}
					 
				    
						    
				 static int getNumberOfDaysInMonth(int year, int month) {
						        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)  {return 31;}
						        if (month == 4 || month == 6 || month == 9 || month == 11)  {return 30;}
						        if (month == 2) return isLeapYear(year) ? 29 : 28; { return 0;} // If month is incorrect
				 }
						    
						    
				static boolean isLeapYear(int year) {  return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0); }
						    
						    
				static int getTotalNumberOfDays(int year, int month) {  
						        int total = 0;
						        // Get the total days from 1800 to year - 1
						        for (int i = 1800; i < year; i++)
						        if (isLeapYear(i)) {total = total + 366;}
							    else {total = total + 365;}
						            
						        // Add days from Jan to the month prior to the calendar month
						        for (int i = 1; i < month; i++) {total = total + getNumberOfDaysInMonth(year, i);}
						           
						      return total;
				}
						    
						    
				 static int getStartDay(int year, int month) {
						        // Get total number of days since 1/1/1800
						        int startDay1800 = 3;
						        int totalNumberOfDays = getTotalNumberOfDays(year, month);
						        // Return the start day
						        return (totalNumberOfDays + startDay1800) % 7;
				}
						     
				 static String getDayOfWeek(int day, int year, int month) {
						    	    
						        String dayName = null;
						        String days = LocalDate.of(year, month, day).getDayOfWeek().name();
						              
						        switch (days) {
						        
						        case "SUNDAY":
						            dayName = "Sunday";
						            break;
						        
						        case "MONDAY":
						            dayName = "Monday";
						            break;
						        
						        case "TUESDAY":
						            dayName = "Tuesday";
						            break;
						        
						        case "WEDNESDAY":
						            dayName = "Wednesday";
						            break;
						        
						        case "THURSDAY":
						            dayName = "Thursday";
						            break;
						        
						        case "FRIDAY":
						            dayName = "Friday";
						            break;
						        
						        case "SATURDAY":
						             dayName = "Saturday";
						        
						         }
						        return dayName;
				} 
						    
						    
				 static String getMonthName(int month) {
				
							    String monthName = null;
					
							    switch (month) {
					
							    	  case 1: monthName = "January"; break;
							    	  case 2: monthName = "February"; break;
							    	  case 3: monthName = "March"; break;
							    	  case 4: monthName = "April"; break;
							    	  case 5: monthName = "May"; break;
							    	  case 6: monthName = "June"; break;
							    	  case 7: monthName = "July"; break;
							    	  case 8: monthName = "August"; break;
							    	  case 9: monthName = "September"; break;
							    	  case 10: monthName = "October"; break;
							    	  case 11: monthName = "November"; break;
							    	  case 12: monthName = "December";
							    	  
							    	}
							   return monthName;
				}
						    
				static void printMonthBody(int year, int month) {
					
							    	// Get start day of the week for the first date in the month
							    	int startDay = getStartDay(year, month);
							    	// Get number of days in the month
							    	int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
							    	// Pad space before the first day of the month
							    	int i = 0;
							    	for (i = 0; i < startDay; i++)
							    	  System.out.print("    ");
							  
									for (i = 1; i <= numberOfDaysInMonth; i++) {
									  if (i < 10)
									    System.out.print("   " + i);
									  else
									    System.out.print("  " + i);
									
									  if ((i + startDay) % 7 == 0)
									    System.out.println();
									}
									System.out.println();
				}
					 
				
				static void CalculateDays() {
						 
								 String Year = "";
								 String Month ="";
								 String Day = "";
								 
								 System.out.println("請輸入欲查詢日期(年/月/日): ");
								 Scanner reader = new Scanner(System.in);
								 String date = reader.nextLine();
								 
								 Year = get_Year(date);
								 Month = get_Month(date);
								 Day = get_Day(Month, date);
								 
								 String date2 = Year + '-' + Month + '-' + Day;
								 LocalDate Startday = LocalDate.now();
								 LocalDate EndDay = LocalDate.parse(date2);
								 
								 long diffInDays = ChronoUnit.DAYS.between(Startday, EndDay);
								 
								 if(diffInDays < 0) {
									 diffInDays *=-1;
								 }
							     System.out.println( date + "距離今天還有" + diffInDays + "天" + "\n");  
							      
							 }
							 
							 
							 static boolean isNumeric(String strNum) {
								    if (strNum == null) {
								        return false;
								    }
								    try {
								        double d = Integer.parseInt(strNum);
								    } catch (NumberFormatException nfe) {
								        return false;
								    }
								    return true;
				}
					 
					 
				static void Convert_western_to_zodiac() {
								ArrayList<String> gan = new ArrayList<String>(Arrays.asList("甲","乙","丙","丁","戊","己","庚","辛","壬","癸"));
								ArrayList<String> zhi = new ArrayList<String>(Arrays.asList("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"));
								ArrayList<String> zodiac = new ArrayList<String>(Arrays.asList("鼠","牛","虎","兔","龍","蛇","馬","羊","猴","雞","狗","豬"));
								System.out.println("請輸入欲查詢年:");
								Scanner reader = new Scanner(System.in);
								String date = reader.nextLine();
								if (isNumeric(date)) {
									int date_num = Integer.parseInt(date); // convert into input year into int
									int gan_num = (10 + (date_num-3) % 10 - 1) % 10;  // calculate index to access in array
									int zhi_num = (12 + (date_num-3) % 12 - 1) % 12; // zodiac is the same index as zhi
					
									String out_gan = gan.get(gan_num);
									String out_zodiac = zodiac.get(zhi_num);
									String out_zhi = zhi.get(zhi_num);
									
									System.out.println(String.format("%d是%s%s年，屬%s", date_num, out_gan, out_zhi, out_zodiac));
								 }
								else {
									System.out.println("輸入格式錯誤!");
									return;
								}		 
					}
					 
					 
				static void CalculateDateNegative() {
						 System.out.println("請輸入往後推算得天數: ");
						 Scanner reader = new Scanner(System.in);
						 long days = reader.nextLong();
						 LocalDate date = LocalDate.now().minusDays(days);
						 System.out.println("往後 " + days + " 天是 " + date + "\n");
				}
				
				
				static void CalculateDatePositive() {
					 System.out.println("請輸入往後推算得天數: ");
					 Scanner reader = new Scanner(System.in);
					 long days = reader.nextLong();
					 LocalDate date = LocalDate.now().plusDays(days);
					 System.out.println("往後 " + days + " 天是 " + date + "\n");
			    }
					 
				
				public static void main(String[] args) {
						  char Command_line;
						  int iter = 10;
						  while(iter != 0) {
							      Print_Command();
							      Scanner reader = new Scanner(System.in);
							      Command_line = reader.next().charAt(0);   
								  Get_Input(Command_line);
							  }
						  }
}