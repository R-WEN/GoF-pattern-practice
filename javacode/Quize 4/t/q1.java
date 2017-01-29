abstract class UserCommand{
	protected abstract void execute();
	protected abstract void undo();
}



class evalResult extends UserCommand{
	protected void execute()
	{
		System.out.println("do eval");
	}
	protected void undo()
	{
		System.out.println("undo eval");	
	}	
}

class formatDiagram extends UserCommand{
	protected void execute()
	{
		System.out.println("do format");
	}
	protected void undo()
	{
		System.out.println("undo format");	
	}
}

class printResult extends UserCommand{
	protected void execute()
	{
		System.out.println("do print");
	}
	protected void undo()
	{
		System.out.println("undo print");	
	}	
}

abstract class macroCommand extends UserCommand{

	//execute方法可當作template pattern裡的 template method
	//並且要用final，別讓子類複寫而改變流程
	final protected void execute()
	{
		promtUser();
		retrieveInput();
		connectDB();
		diagnostic();
		dissconnectDB();
	}
	protected void undo()
	{
		System.out.println("undo execute");	
	}

	//這兩個應該是hook method，子類別可自己決定要不要複寫或做些改變，所以是concrete，而且宣告成空
	protected  void promtUser(){};
	protected  void diagnostic(){};

	//下面這三個
	//如果是primitive method，就要加abstract 像是abstract public void retrieveInput();
	//也代表著子類別一定要實做 
	//primitive method的目的是為了讓子類別可以將方法實做適合自己的方式，做些微調

	//如果不是，就代表其實子類別可以"共用"這方法
	//像connectDB()跟dissconnectDB
	//所有子類別ㄧ起用，所以子類就不必特別overwrite

	//結論就是下面這三個retrieveInput,connectDB,dissconnectDB方法
	//看自己子類的情況要不要微調，藉而決定是否為primitive method (也就是加abstract)
	//阿我自己是把retrieveInput當做 primitive method
	abstract protected  void retrieveInput();
	protected  void connectDB(){
		System.out.println("connect DB");
	}
	protected  void dissconnectDB(){
		System.out.println("disconnect DB");
	}
}

class VerboseMode extends macroCommand{
	//想要用hook做些微調那就複寫promtUser或是diagnostic;
	//如果retrieveInput,connectDB,dissconnectDB 是 primitive 需要子類調整 就複寫吧!!!
	//因為VerboseMode是複雜模式 所以就有promtUser(提醒用戶)跟diagnostic(診斷?)這兩個hook
	protected  void promtUser(){
		System.out.println("題適用戶");
	}
	protected  void diagnostic(){
		System.out.println("診斷?");
	}
	
	protected void retrieveInput()
	{
		System.out.println("Ver retrieve");
	}
	
}

class SuccinctMode extends macroCommand{
	//想要用hook做些微調那就複寫promtUser或是diagnostic;
	//如果retrieveInput,connectDB,dissconnectDB 是 primitive 需要子類調整 就複寫吧!!!
	protected void retrieveInput()
	{
		System.out.println("succ retrieve");
	}
}


//這是simple factory
class CommandFactory{
	public UserCommand makeUserCommand(String command)
	{
		
		if(command.equals("VerboseMode"))
		{
				return new VerboseMode();
		}
		else if(command.equals("SuccinctMode"))
		{
			return new SuccinctMode();
		}
		else if (command.equals("evalResult"))
		{
			return new evalResult();
		}
		else if (command.equals("formatDiagram"))
		{
			return new formatDiagram();
		}
		else
		{
			return new printResult(); //這邊最後就當作是printResult Command 不再另外拋錯誤 or 提醒使用者沒有這個command之類
		}
	}	
}

public class q1{
	public static void main(String[] args) {
		CommandFactory cf = new CommandFactory();
		UserCommand Ucmd = cf.makeUserCommand("VerboseMode");
		Ucmd.execute();
		//invoker部分就沒有特別去寫了~~

	}
}