class Outer {
	
	class static Nested {
	
	}

	class Inner {
	
	}

	public static void main(String[] args) {
		Outer.Nested nested = new Nested();
		Outer outer = new Outer();	
		Outer.Inner inner = outer.new Inner();
		//swing		
		JButton button = new ...
		button.onMouseEvent(new MouseEvent() {
			void onClick(Event e) {
			...
			...
			}
			void onPressed(Event e) {
			}	
		});
		//javafx
		Button button = new ...
		button.onAction(other -> {
			...
		});
	}
}

@FunctionalInterface
interface Action {
	void action(Event e);
}


java.util.functions.*
Function T -> R
BiFunction T, Y -> R
Consumer () -> R
BiConsumer
Predicate T -> boolean
IntFunction



final int i = 1;
i++;//нельзя

final MyClass myClass = new MyClass();
myClass = new MyClass(); //нельзя


Object o = ...
if (o instanceof String) {
	String s = (String) o;
	s
}


class Shape {
	double square(Shape s) {
		if (s instanceof Circle) {
			return 		
		} else if (s instanceof Rectangle) {
			return 		
		} else if (s instanceof Trinagle) {
			
		}
	}
}

class Circle extends Shape {
	double square(Shape s) {
		return 
	}
}

class Rectangle {
	double square(Shape s) {
		return 
	}
}

class Triangle {
}

SOLID - 




(-b + sqrt(b*b – 4ac)) / 2a
a*x^2 + b*x + c = 0 


x^2 + 2x + 3 = 0


DEFINE a 1
DEFINE b 2
DEFINE c 3
PUSH a
PUSH 2 //2, a
* //2*a
PUSH b
PUSH -1
* //-b, 2*a
PUSH b
PUSB b
* //b^2, -b, 2*a
PUSH 4
PUSH a
* //4a, b^2, -b, 2*a
PUSH c
* //4ac, b^2, -b, 2*a
- //b^2 - 4ac, -b, 2*a
SQRT // sqrt(b^2 - 4ac), -b, 2*a
+ //-b + sqrt(b^2 - 4ac), 2*a
/ //-b + sqrt(b^2 - 4ac) / 2*a
PRINT 


Map<String, Double> map = new HashMap<>();
hash -> функция
map.put("a", 1);
map.get("a") //1;


input (либо название файла args, если нет, то используем System.in) для считывания команд
List<Command> commands = ...
for (Command c : commands) {
	c.execute();
}
FileParser {
	String s = scanner.nextLine();
	Command c = null;
	switch (s) {
	  case "PUSH": {
		c = new Push(stack, definitions, s.split();
		break;
	  case "POP": {
		c = new Pop(stack, definitions, s.split();
		break;	
	}
	commands.add(c);
}
abstract class Command {
	protected Stack<Double> stack;
	protected Map<String, Double> definitions;
	protected String[] args; 
	//String s = "DEFINE a 4"
	//String[] tokens = s.split(" "); //tokens ={"DEFINE", "a", "4"}
	
	void execute();
}
class Push extends Command {
	Push(Stack s, Map m, String[] args) {
		super(s, m, args);	
	}

	execute() {//ПРОВЕРИТЬ ЧТО СТРОКА Double.parseDouble(args[1]);  
		//throw NumberFormatException если не строка
		//если исключение, то Double d = definitions.get(args[1]);
		//Если есть переменная, кладем в стек, если нет - пишем исключение
		... //if arg строка, то double d = map.get(arg) , stack.push(d);
if число, то сразу stack.push(arg); 
	}
}

class Pop extends Command {
	execute() {
try {

} catch()
	}
}

class ConsoleExecuter //interface Executor
class FileExecutor //interface Executor


PUSH a
PUSH 2

Stack<Double> stack

EmptyStackException;

















