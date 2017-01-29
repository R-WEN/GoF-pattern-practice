public class Q1{
	public static void main(String[] args){
		CommandFactory rdbf=new RDBCommandFactory();
		CommandFactory ldapf=new LDAPCommandFactory();
		Invoker invoker=new Invoker();

		System.out.println("Test RDB .... \n");
		invoker.setCommand(rdbf.makeUserCommand("evalResult"));
		invoker.execute();
		invoker.setCommand(rdbf.makeUserCommand("formatDiagram"));
		invoker.execute();
		invoker.setCommand(rdbf.makeUserCommand("printResult"));
		invoker.execute();
		invoker.setCommand(rdbf.makeUserCommand("VerboseMode"));
		invoker.execute();
		invoker.setCommand(rdbf.makeUserCommand("SuccinctMode"));
		invoker.execute();
		System.out.println("Test complete.\n\n");
		System.out.println("Test LDAP .... \n");
		invoker.setCommand(ldapf.makeUserCommand("evalResult"));
		invoker.execute();
		invoker.setCommand(ldapf.makeUserCommand("formatDiagram"));
		invoker.execute();
		invoker.setCommand(ldapf.makeUserCommand("printResult"));
		invoker.execute();
		invoker.setCommand(ldapf.makeUserCommand("VerboseMode"));
		invoker.execute();
		invoker.setCommand(ldapf.makeUserCommand("SuccinctMode"));
		invoker.execute();
		System.out.println("Test complete.");
	}
}

//DBM
abstract class DBM{
	abstract public void evalResult();
	abstract public void formatDiagram();
	abstract public void printResult();
	abstract public void retrieveInput();
	abstract public void connectDB();
	abstract public void disconnectDB();
}
class RDB extends DBM{
	public void evalResult(){
		System.out.println("RDB evelResult");
	}
	public void formatDiagram(){
		System.out.println("RDB formatDiagram");
	}
	public void printResult(){
		System.out.println("RDB print Result");
	}
	public void retrieveInput(){
		System.out.println("RDB retrieveInput");
	}
	public void connectDB(){
		System.out.println("connect RDB");
	}
	public void disconnectDB(){
		System.out.println("disconnect RDB");
	}
}
class LDAP extends DBM{
	public void evalResult(){
		System.out.println("LDAP evelResult");
	}
	public void formatDiagram(){
		System.out.println("LDAP formatDiagram");
	}
	public void printResult(){
		System.out.println("LDAP print Result");
	}
	public void retrieveInput(){
		System.out.println("LDAP retrieveInput");
	}
	public void connectDB(){
		System.out.println("connect LDAP");
	}
	public void disconnectDB(){
		System.out.println("disconnect LDAP");
	}
}
//

//factory method
abstract class CommandFactory{
	protected DBM db=null;
	public UserCommand makeUserCommand(String c){
		UserCommand command=null;
		if (c.equals("evalResult")){
			command=new evalResult(db);
		}else if(c.equals("formatDiagram")){
			command=new formatDiagram(db);
		}else if(c.equals("printResult")){
			command=new printResult(db);
		}else if(c.equals("VerboseMode")){
			command=new VerboseMode(db);
		}else if(c.equals("SuccinctMode")){
			command=new SuccinctMode(db);
		}
		return command;
	}
}
class RDBCommandFactory extends CommandFactory{
	public RDBCommandFactory(){
		db=new RDB();
	}
	
}

class LDAPCommandFactory extends CommandFactory{
	public LDAPCommandFactory(){
		db=new LDAP();
	}
}
//
class Invoker{
	private UserCommand command;
	public void setCommand(UserCommand command){
		this.command=command;
	}
	public void execute(){
		command.execute();
	}
	public void undo(){
		command.undo();
	}
}
//Commands
abstract class UserCommand{

	protected DBM receiver=null;
	abstract public void execute();
	abstract public void undo();
	public UserCommand(DBM receiver){
		this.receiver=receiver;
	}
}

class evalResult extends UserCommand{
	public evalResult(DBM receiver){
		super(receiver);
	}
	public final void execute(){
		receiver.evalResult();
		//more action.
	}
	public final void undo(){
		//undo ..
	}
}

class formatDiagram extends UserCommand{
	public formatDiagram(DBM receiver){
		super(receiver);
	}
	public final void execute(){
		receiver.formatDiagram();
		//more action.
	}
	public final void undo(){
		//undo ..
	}
}

class printResult extends UserCommand{
	public printResult(DBM receiver){
		super(receiver);
	}
	public final void execute(){
		receiver.printResult();
		//more action.
	}
	public final void undo(){
		//undo ..
	}
}

abstract class macroCommand extends UserCommand{
	public macroCommand(DBM receiver){
		super(receiver);
	}
	public final void execute(){
		//follow the sequence, this is a example.
		promptUser();
		retrieveInput();
		connectDB();
		diagnostics();
		disconnectDB();

	}
	public final void undo(){
		//undo ...
	}
	//primitive methods
	protected abstract void retrieveInput();
	protected abstract void connectDB();
	protected abstract void disconnectDB();
	//hook methods
	protected void promptUser(){

	}
	protected void diagnostics(){

	}
}

class VerboseMode extends macroCommand{
	public VerboseMode(DBM receiver){
		super(receiver);
	}
	//primitive methods should be implement 
	protected void retrieveInput(){
		receiver.retrieveInput();
		//more action.
	}
	protected void connectDB(){
		receiver.connectDB();
		//more action.
	}
	protected void disconnectDB(){
		receiver.disconnectDB();
		//more action.
	}
	//hook methods is option.
	protected void promptUser(){
		System.out.println("VerboseMode promptUser");
	}
	protected void diagnostics(){
		System.out.println("VerboseMode diagnosticsDB");
	}
}

class SuccinctMode extends macroCommand{
	public SuccinctMode(DBM receiver){
		super(receiver);
	}

	//primitive methods should be implement 
	protected void retrieveInput(){
		receiver.retrieveInput();
		//more action.
	}
	protected void connectDB(){
		receiver.connectDB();
		//more action.
	}
	protected void disconnectDB(){
		receiver.disconnectDB();
		//more action.
	}
	//hook methods is option.
	protected void promptUser(){
		System.out.println("SuccinctMode promptUser");
	}
	protected void diagnostics(){
		System.out.println("SuccinctMode diagnosticsDB");
	}
}
//

