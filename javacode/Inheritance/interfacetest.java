abstract class AbstractClass implements InterfaceA 
{

}

interface InterfaceA
{
   public void methodToBeImplemented();
}

class MyClass extends AbstractClass
{
  @Override
  public void methodToBeImplemented(){
      //do something
  }
}  