import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Math_Test_Auto
{
	
	static Map<String, String> map_primary_teachers=new HashMap<>();//小学老师账号密码集合
	static Map<String, String> map_Junior_high_school_teachers=new HashMap<>();//初中老师账号密码集合
	static Map<String, String> map_Senior_high_school_teachers=new HashMap<>();//高中老师账号密码集合
	static String[] operators_primary_school= {"+","-","*","/"};//小学难度运算符
	static String[] operators_Middle_school= {"√","^2"};//中学难度运算符
	static String[] operators_High_school= {"sin","cos","tan"};//高中难度运算符
	static Scanner In=new Scanner(System.in);//输入数据
	static int flag_school=0;
	static String user=null;
	//默认账号类型，1为小学，2为初中，3为高中，0为输入不正确
	
	//用户名和密码数据初始化
	public static void init() 
	{
		map_primary_teachers.put("张三1","123");
		map_primary_teachers.put("张三2","123");
		map_primary_teachers.put("张三3","123");
		map_Junior_high_school_teachers.put("李四1","123");
		map_Junior_high_school_teachers.put("李四2","123");
		map_Junior_high_school_teachers.put("李四3","123");
		map_Senior_high_school_teachers.put("王五1","123");
		map_Senior_high_school_teachers.put("王五2","123");
		map_Senior_high_school_teachers.put("王五3","123");
		map_Senior_high_school_teachers.put("王五","123");
	}
	//输入的账号和密码在初始化集合中进行匹配，并返回1个整形数，0为匹配失败，1为小学账号，2为初中账号，3为高中账号
	public static int Matching_User_Password(String username,String password) 
	{
		if(map_primary_teachers.containsKey(username)) 
		{
			if(map_primary_teachers.get(username).equals(password)) 
			{
				user=username;
				return 1;//小学老师账号返回1
			}
			else
				return 0;//账号正确，密码错误返回0
		}
		if(map_Junior_high_school_teachers.containsKey(username)) 
		{
			if(map_Junior_high_school_teachers.get(username).equals(password)) 
			{
				user=username;
				return 2;//初中老师账号返回2
			}
			else
				return 0;//账号正确，密码错误返回0
		}
		if(map_Senior_high_school_teachers.containsKey(username)) 
		{
			if(map_Senior_high_school_teachers.get(username).equals(password)) 
			{
				user=username;
				return 3;//高中老师账号返回3
			}
			else
				return 0;//账号正确，密码错误返回0
		}
		return 0;//不存在的账号返回0
	}
	//教师账号密码登陆
	public static void Landing(String username,String password) 
	{
		flag_school=Matching_User_Password(username,password);
		if(flag_school!=0) 
		{
			switch(flag_school) 
			{
			case 1:
				System.out.println("当前选择为小学出题");
				System.out.println("准备生成小学数学题目，请输入生成题目数量");
				break;
			case 2:
				System.out.println("当前选择为初中出题");
				System.out.println("准备生成初中数学题目，请输入生成题目数量");
				break;
			case 3:
				System.out.println("当前选择为高中出题");
				System.out.println("准备生成高中数学题目，请输入生成题目数量");
				break;
			}
		}
		else
			System.out.println("请输入正确的用户名、密码");
	}
	//判断字符串是否为数字
	public static boolean isNumeric(String str)
	{
		for (int i = str.length();--i>=0;)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	//生成题目
	public static String Question()
	{
		int Operands_number=2+(int)(Math.random()*4);//操作数数量2-5
		int[] Operands= new int[Operands_number];
		for(int i=0;i<Operands_number;i++) 
		{
			Operands[i]=1+(int)(Math.random()*100);
		}
		
		String test="";
		int operators_primary_school_random=0;
		int operators_Middle_school_random=0;
		int operators_High_school_random=0;
		operators_Middle_school_random=(int)(Math.random()*2);
		operators_High_school_random=(int)(Math.random()*3);
		int operators_Middle_school_position_random=(int)(Math.random()*Operands_number);
		int operators_High_school_position_random=(int)(Math.random()*Operands_number);
		int brackets_postion_random=(int)(Math.random()*(Operands_number-1));
		int brackets_random=(int)(Math.random()*2);
		for(int i=0;i<Operands_number-1;i++)
		{
			operators_primary_school_random=(int)(Math.random()*4);
			if(brackets_random==1&&brackets_postion_random==i) 
			{
				test=test+"(";
			}
			if(operators_Middle_school_position_random==i&&flag_school==2&&operators_Middle_school_random==0) 
			{
				test=test+operators_Middle_school[operators_Middle_school_random];
			}
			if(operators_High_school_position_random==i&&flag_school==3&&operators_High_school_random==0) 
			{
				test=test+operators_High_school[operators_High_school_random];
			}
			
			test=test+Operands[i];
			if(brackets_random==1&&brackets_postion_random+1==i) 
			{
				test=test+")";
			}
			if(operators_Middle_school_position_random==i&&flag_school==2&&operators_Middle_school_random==1) 
			{
				test=test+operators_Middle_school[operators_Middle_school_random];
			}
			test=test+operators_primary_school[operators_primary_school_random];
		}
		if(operators_Middle_school_position_random==Operands_number-1&&flag_school==2&&operators_Middle_school_random==0) 
		{
			test=test+operators_Middle_school[operators_Middle_school_random];
		}
		if(operators_High_school_position_random==Operands_number-1&&flag_school==3&&operators_High_school_random==0) 
		{
			test=test+operators_High_school[operators_High_school_random];
		}
		test=test+Operands[Operands_number-1];
		if(brackets_random==1&&brackets_postion_random+1==Operands_number-1) 
		{
			test=test+")";
		}
		if(operators_Middle_school_position_random==Operands_number-1&&flag_school==2&&operators_Middle_school_random==1) 
		{
			test=test+operators_Middle_school[operators_Middle_school_random];
		}
		
		//System.out.println(test);
		
		return test;
	}
	//生成试卷并保存在相应的文件中
	public static void Test_Paper(int questions_number)
	{
		String foldername="Math_Paper"+"\\"+user;
		switch(flag_school) 
		{
		case 1:
			foldername=foldername+"\\"+"小学";
			break;
		case 2:
			foldername=foldername+"\\"+"初中";
			break;
		case 3:
			foldername=foldername+"\\"+"高中";
			break;
		}
		File folder=new File(foldername);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date date=new Date();
		String time=sdf.format(date);
		String filename=folder+"\\"+time+".txt";
		File file=new File(filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		int count=1;
		String question="";
		while(questions_number!=0)
		{
			question=question+count+"、"+Question()+"\r\n\r\n";
			count++;
			//System.out.print(question);
			questions_number--;
		}
		try {
			FileOutputStream out=new FileOutputStream(filename);
			byte buy[]=question.getBytes();
			out.write(buy);
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	//输入命令执行程序
	public static void Shell_command(String cmd) 
	{
		if(cmd.equals("-1")) 
		{
			System.out.println("输入用户名和密码，两者之间用空格隔开");
			flag_school=0;
			while(flag_school==0) 
			{
				Landing(In.next(),In.next());
			}
		}
		else if(isNumeric(cmd)) 
		{
			int questions_number=Integer.valueOf(cmd);
			if(questions_number>=10&&questions_number<=30) 
			{
				Test_Paper(questions_number);
			}
			else {
				System.out.println("题目数量范围为10-30，包含10和30，请重新输入生成题目数量");
				Shell_command(In.next());
			}
		}
		else if(cmd.equals("切换为小学"))
		{
			flag_school=1;
			System.out.println("准备生成小学数学题目，请输入生成题目数量");
			Shell_command(In.next());
		}
		else if(cmd.equals("切换为初中"))
		{
			flag_school=2;
			System.out.println("准备生成中学数学题目，请输入生成题目数量");
			Shell_command(In.next());
		}
		else if(cmd.equals("切换为高中"))
		{
			flag_school=3;
			System.out.println("准备生成高学数学题目，请输入生成题目数量");
			Shell_command(In.next());
		}
		else 
		{
			System.out.println("请输入小学、初中和高中三个选项中的一个");
			Shell_command(In.next());
		}
	}
	//主函数，程序入口
	public static void main(String[] args) 
	{
		init();//账号密码初始化
		
		System.out.println("输入用户名和密码，两者之间用空格隔开");
		flag_school=0;
		while(flag_school==0) 
		{
			Landing(In.next(),In.next());
		}
		while(true) 
		{
			Shell_command(In.next());
		}
	}

}
